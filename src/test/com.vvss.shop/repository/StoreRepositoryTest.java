package com.vvss.shop.repository;

import com.vvss.shop.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class StoreRepositoryTest {

    private StoreRepository storeRepository;

    @Before
    public void setUp(){
        storeRepository = new StoreRepository();
    }

    @Test
    public void stockSituation_ok_emptyList(){
        List<Product> result = this.storeRepository.stockSituation();
        assertTrue(result.isEmpty());
    }



}
