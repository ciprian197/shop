package com.vvss.shop.repository;

import com.vvss.shop.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
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

    @Test
    public void addProduct_productCodeUsed_error()throws IOException{
        // Given
        Product product = new Product(1,"milk","food",20);
        storeRepository.addNewProduct(product);
        Product newProduct = new Product(1,"milk","food",20);

        // When
        String result = storeRepository.addNewProduct(newProduct);

        // Then
        assertEquals(result,"This code already exists");
    }

    @Test
    public void addProduct_quantityIsLessThan0_error()throws IOException{
        // Given
        Product product = new Product(1,"milk","food",-20);
        // When
        String result = storeRepository.addNewProduct(product);

        // Then
        assertEquals(result,"code q");
    }
    @Test
    public void addProduct_codeIsLessThan0_error()throws IOException{
        // Given
        Product product = new Product(-1,"milk","food",20);
        // When
        String result = storeRepository.addNewProduct(product);

        // Then
        assertEquals(result,"code q");
    }

    @Test
    public void getProductsCategory_productCodeUsed_error()throws IOException{
        // Given
        Product product = new Product(1,"milk","food",20);
        Product newProduct = new Product(2,"juice","fsss",20);

        storeRepository.addNewProduct(product);
        storeRepository.addNewProduct(newProduct);


        List<Product> result = storeRepository.getProductsCategory("food");

        // Then
        assertEquals(result.get(0),product);
    }

}
