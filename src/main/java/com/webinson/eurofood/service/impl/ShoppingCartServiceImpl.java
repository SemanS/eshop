package com.webinson.eurofood.service.impl;

import com.webinson.eurofood.assembler.CartItemAssembler;
import com.webinson.eurofood.assembler.ShoppingCartAssembler;
import com.webinson.eurofood.dao.ShoppingCartDao;
import com.webinson.eurofood.dto.CartItemDto;
import com.webinson.eurofood.dto.ShoppingCartDto;
import com.webinson.eurofood.entity.CartItem;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.entity.ShoppingCart;
import com.webinson.eurofood.service.CategoryService;
import com.webinson.eurofood.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Slavo on 1/7/2017.
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Autowired
    ShoppingCartAssembler shoppingCartAssembler;

    @Autowired
    CartItemAssembler cartItemAssembler;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void saveShoppingCart(ShoppingCartDto shoppingCartDto, Set<CartItemDto> cartItemDtos) {

        ShoppingCart shoppingCart = shoppingCartAssembler.toModel(shoppingCartDto);

        shoppingCart.setTimeStamp(Calendar.getInstance());

        for (CartItemDto cartItemDto : cartItemDtos) {
            CartItem cartItem = new CartItem();
            cartItem = cartItemAssembler.toModel(cartItemDto);
            cartItem.setShoppingCart(shoppingCart);
            shoppingCart.addCartItem(cartItem);
        }

        /*entityManager.persist(shoppingCart);*/
        shoppingCartDao.save(shoppingCart);
    }

    @Override
    public Page<ShoppingCart> findByFilter(Map<String, String> filters, Pageable pageable) {
        return shoppingCartDao.findAll(getFilterSpecification(filters), pageable);
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