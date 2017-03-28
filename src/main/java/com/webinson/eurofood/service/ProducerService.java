package com.webinson.eurofood.service;

import com.webinson.eurofood.entity.Producer;
import com.webinson.eurofood.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Created by Slavo on 3/24/2017.
 */
public interface ProducerService {

    Page<Producer> findByFilter(Map<String, String> filters, Pageable pageable);

}
