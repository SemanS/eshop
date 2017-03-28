package com.webinson.eurofood.service.impl;

import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.dao.ProducerDao;
import com.webinson.eurofood.entity.Producer;
import com.webinson.eurofood.entity.ShoppingCart;
import com.webinson.eurofood.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Slavo on 3/24/2017.
 */
@Service
public class ProucerServiceImpl implements ProducerService {

    @Autowired
    ProducerDao producerDao;

    @Override
    public Page<Producer> findByFilter(Map<String, String> filters, Pageable pageable) {
        return producerDao.findAll(getFilterSpecification(filters), pageable);
    }

    private Specification<Producer> getFilterSpecification(Map<String, String> filterValues) {
        return (Root<Producer> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
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
