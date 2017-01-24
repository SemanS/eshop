package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.ShoppingCartDao;
import com.webinson.eurofood.entity.ShoppingCart;
import com.webinson.eurofood.entity.ShoppingCart;
import com.webinson.eurofood.service.ShoppingCartService;
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
 * Created by Slavo on 1/24/2017.
 */

public class ShoppingCartLazyDataModel extends LazyDataModel<ShoppingCart> implements SelectableDataModel<ShoppingCart> {

    private static final long serialVersionUID = -6123945723069023025L;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private transient ShoppingCartService shoppingCartService;

    private static final SortOrder DEFAULT_SORT_ORDER = SortOrder.ASCENDING;
    private static final String DEFAULT_SORT_FIELD = "id";

    public ShoppingCartLazyDataModel(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Object getRowKey(ShoppingCart item) {
        return item.getId();
    }

    @Override
    public ShoppingCart getRowData(String rowKey) {
        Long rowId = Long.valueOf(rowKey);
        @SuppressWarnings("unchecked")
        List<ShoppingCart> items = (List<ShoppingCart>) super.getWrappedData();
        return items.stream().filter(item -> item.getId().equals(rowId)).findAny().orElse(null);

    }

    @Override
    public List<ShoppingCart> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
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
    public List<ShoppingCart> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                           Map<String, Object> filters) {
        Sort sort = null;
        if (sortField != null) {
            sort = new Sort(getDirection(sortOrder != null ? sortOrder : DEFAULT_SORT_ORDER), sortField);
        } else if (DEFAULT_SORT_FIELD != null) {
            sort = new Sort(getDirection(sortOrder != null ? sortOrder : DEFAULT_SORT_ORDER), DEFAULT_SORT_FIELD);
        }
        return filterAndSort(first, pageSize, filters, sort);
    }

    private List<ShoppingCart> filterAndSort(int first, int pageSize, Map<String, Object> filters, Sort sort) {
        Map<String, String> filtersMap = filters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));
        Page<ShoppingCart> page = shoppingCartService.findByFilter(filtersMap, new PageRequest(first / pageSize, pageSize, sort));
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
