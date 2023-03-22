package org.k.service;

import org.k.dao.ProductType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ProductTypeService {
    //@Transactional
    public List<ProductType> getAllType();
}
