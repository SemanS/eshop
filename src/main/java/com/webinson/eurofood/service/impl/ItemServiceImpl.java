package com.webinson.eurofood.service.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.webinson.eurofood.assembler.ItemAssembler;
import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.entity.QItem;
import com.webinson.eurofood.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Slavo on 10/17/2016.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Autowired
    ItemAssembler itemAssembler;

    @PersistenceContext
    EntityManager entityManager;

    public List<ItemDto> getAllItems() {

        List<ItemDto> items = new ArrayList<ItemDto>();
        items = itemAssembler.toDtos(itemDao.findAll());
        return items;

    }

    @Override
    public String getTextOfItemByUrl(String url) {
        return itemDao.findByUrl(url).getText();
    }

    @Override
    public ItemDto getItemByUrl(String url) {
        ItemDto itemDto = new ItemDto();
        itemDto = itemAssembler.toDto(itemDao.findByUrl(url));
        return itemDto;
    }

    @Override
    public void saveItemByUrl(String url, String text) {
        Item item = itemDao.findByUrl(url);
        item.setText(text);
        itemDao.save(item);
    }

    @Override
    public List<ItemDto> getItemsByCategory(CategoryDto categoryDto) {
        Category category = new Category();

        final JPAQuery<Item> query = new JPAQuery<>(entityManager);
        QItem item = QItem.item;
        List<Item> items = query.from(item).select(item).where(item.category.id.eq(categoryDto.getId())).fetch();
        return itemAssembler.toDtos(items);

    }

    @Override
    public Page<Item> findByFilter(Map<String, String> filters, Pageable pageable) {
        return itemDao.findAll(getFilterSpecification(filters), pageable);
    }

    @Override
    public ItemDto getItemById(Long id) {
        return itemAssembler.toDto(itemDao.findById(id));
    }

    @Override
    public List<ItemDto> getAllPromotedItems() {
        final JPAQuery<Item> query = new JPAQuery<>(entityManager);
        QItem item = QItem.item;
        List<Item> items = query.from(item).select(item).where(item.isDiscount.eq(true)).fetch();
        return itemAssembler.toDtos(items);
    }

    private Specification<Item> getFilterSpecification(Map<String, String> filterValues) {
        return (Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
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
