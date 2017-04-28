package com.webinson.eurofood.service.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.webinson.eurofood.assembler.CartItemAssembler;
import com.webinson.eurofood.assembler.ShoppingCartAssembler;
import com.webinson.eurofood.bean.RegisterBean;
import com.webinson.eurofood.dao.ItemCounterDao;
import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.dao.ShoppingCartDao;
import com.webinson.eurofood.dao.UserDao;
import com.webinson.eurofood.dto.CartItemDto;
import com.webinson.eurofood.dto.ShoppingCartDto;
import com.webinson.eurofood.entity.*;
import com.webinson.eurofood.service.CategoryService;
import com.webinson.eurofood.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Slavo on 1/7/2017.
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    UserDao userDao;

    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Autowired
    ShoppingCartAssembler shoppingCartAssembler;

    @Autowired
    CartItemAssembler cartItemAssembler;

    @Autowired
    ItemCounterDao itemCounterDao;

    @Autowired
    ItemDao itemDao;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void saveShoppingCart(ShoppingCartDto shoppingCartDto, Set<CartItemDto> cartItemDtos) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ShoppingCart shoppingCart = shoppingCartAssembler.toModel(shoppingCartDto);

        shoppingCart.setTimeStamp(Calendar.getInstance());
        shoppingCart.setProcessed(false);
        Set<CartItem> cartItems = new HashSet<>();

        if (authentication.getName() == "anonymousUser") {
            shoppingCart.setUser(userDao.findByUsername("anonymous"));
        } else {
            shoppingCart.setUser(userDao.findByUsername(authentication.getName()));
        }

        for (CartItemDto cartItemDto : cartItemDtos) {
            CartItem cartItem = new CartItem();
            cartItem = cartItemAssembler.toModel(cartItemDto);
            cartItem.setShoppingCart(shoppingCart);
            ItemCounter itemCounter = new ItemCounter();
            if (itemCounterDao.findByItemId(cartItem.getItemId()) != null) {
                itemCounter = itemCounterDao.findByItemId(cartItem.getItemId());
                itemCounter.setCounter(itemCounter.getCounter() + cartItem.getQuantity());
            } else {
                itemCounter.setItem(itemDao.findById(cartItem.getItemId()));
                itemCounter.setCounter(cartItem.getQuantity());
            }

            itemCounterDao.save(itemCounter);
            cartItems.add(cartItem);
        }
        shoppingCart.setCartItems(cartItems);
        shoppingCartDao.save(shoppingCart);
    }

    @Override
    public Page<ShoppingCart> findByFilter(Map<String, String> filters, Pageable pageable) {
        return shoppingCartDao.findAll(getFilterSpecification(filters), pageable);
    }

    @Override
    public int getLastId() {
        final JPAQuery<ShoppingCart> query = new JPAQuery<>(entityManager);
        QShoppingCart shoppingCart = QShoppingCart.shoppingCart;
        return  query.from(shoppingCart).select(shoppingCart.id.max()).fetchOne().intValue();
    }

    private Specification<ShoppingCart> getFilterSpecification(Map<String, String> filterValues) {
        return (Root<ShoppingCart> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Optional<Predicate> predicate = filterValues.entrySet().stream()
                    .filter(v -> v.getValue() != null && v.getValue().length() > 0)
                    .map(entry -> {
                        Path<?> path = root;
                        String key = entry.getKey();
                        if (entry.getKey().contains(".")) {
                            String[] splitKey = entry.getKey().split("\\.");
                            path = root.join(splitKey[0]);
                            key = splitKey[1];
                        }
                        return builder.like(path.get(key).as(String.class), "%" + entry.getValue() + "%");
                    })
                    .collect(Collectors.reducing((a, b) -> builder.and(a, b)));
            return predicate.orElseGet(() -> alwaysTrue(builder));
        };
    }

    private Predicate alwaysTrue(CriteriaBuilder builder) {
        return builder.isTrue(builder.literal(true));
    }

}