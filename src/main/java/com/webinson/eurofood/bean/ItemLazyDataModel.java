package com.webinson.eurofood.bean;

/**
 * Created by Slavo on 1/17/2017.
 */

import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.service.ItemService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by Slavo on 1/17/2017.
 */
public class ItemLazyDataModel extends LazyDataModel<Item> implements SelectableDataModel<Item> {

    private static final long serialVersionUID = -6123945723069023025L;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private final transient ItemService itemService;

    private static final SortOrder DEFAULT_SORT_ORDER = SortOrder.ASCENDING;
    private static final String DEFAULT_SORT_FIELD = "header";

    public ItemLazyDataModel(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public Object getRowKey(Item item) {
        return item.getId();
    }

    @Override
    public Item getRowData(String rowKey) {
        Long rowId = Long.valueOf(rowKey);
        @SuppressWarnings("unchecked")
        List<Item> items = (List<Item>) super.getWrappedData();
        return items.stream().filter(item -> item.getId().equals(rowId)).findAny().orElse(null);
    /*List<Item> items = itemDao.findAll();
        return itemDao.findById(rowId);*/
    }

    @Override
    public List<Item> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        Sort sort = new Sort(getDirection(DEFAULT_SORT_ORDER), DEFAULT_SORT_FIELD);
        if (multiSortMeta != null) {
            List<Sort.Order> orders = multiSortMeta.stream()
                    .map(m -> new Sort.Order(getDirection(m.getSortOrder() != null ? m.getSortOrder() : DEFAULT_SORT_ORDER),
                            m.getSortField()))
                    .collect(Collectors.toList());
            sort = new Sort(orders);
        }
        return filterAndSort(first, pageSize, filters, sort);
    }

    @Override
    public List<Item> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                           Map<String, Object> filters) {
        Sort sort = null;
        if (sortField != null) {
            sort = new Sort(getDirection(sortOrder != null ? sortOrder : DEFAULT_SORT_ORDER), sortField);
        } else if (DEFAULT_SORT_FIELD != null) {
            sort = new Sort(getDirection(sortOrder != null ? sortOrder : DEFAULT_SORT_ORDER), DEFAULT_SORT_FIELD);
        }
        return filterAndSort(first, pageSize, filters, sort);
    }

    private List<Item> filterAndSort(int first, int pageSize, Map<String, Object> filters, Sort sort) {
        Map<String, String> filtersMap = filters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));
        Page<Item> page = itemService.findByFilter(filtersMap, new PageRequest(first / pageSize, pageSize, sort));
        this.setRowCount(((Number) page.getTotalElements()).intValue());
        this.setWrappedData(page.getContent());
        return page.getContent();
    }

    private static Sort.Direction getDirection(SortOrder order) {
        switch (order) {
            case ASCENDING:
                return Sort.Direction.ASC;
            case DESCENDING:
                return Sort.Direction.DESC;
            case UNSORTED:
            default:
                return null;
        }
    }
}