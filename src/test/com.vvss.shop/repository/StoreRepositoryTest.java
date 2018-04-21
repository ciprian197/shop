package com.vvss.shop.repository;

import com.vvss.shop.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class StoreRepositoryTest {

    private StoreRepository storeRepository;

    @Before
    public void setUp() {
        storeRepository = new StoreRepository();
    }

    @Test
    public void stockSituation_ok_emptyList() {
        final List<Product> result = this.storeRepository.stockSituation();
        assertTrue(result.isEmpty());
    }

    @Test
    public void addProduct_productCodeUsed_error() throws IOException {
        // Given
        final Product product = new Product(1, "milk", "food", 20);
        storeRepository.addNewProduct(product);
        final Product newProduct = new Product(1, "milk", "food", 20);

        // When
        final String result = storeRepository.addNewProduct(newProduct);

        // Then
        assertEquals(result, "This code already exists");
    }

    @Test
    public void addProduct_quantityIsLessThan0_error() throws IOException {
        // Given
        final Product product = new Product(1, "milk", "food", -20);
        // When
        final String result = storeRepository.addNewProduct(product);

        // Then
        assertEquals(result, "code q");
    }

    @Test
    public void addProduct_codeIsLessThan0_error() throws IOException {
        // Given
        final Product product = new Product(-1, "milk", "food", 20);
        // When
        final String result = storeRepository.addNewProduct(product);

        // Then
        assertEquals(result, "code q");
    }

    @Test
    public void getProductsCategory_oneProductWIthSpecifiedCategory_product() throws IOException {
        // Given
        final Product product = new Product(1, "milk", "food", 20);
        final Product newProduct = new Product(2, "juice", "fsss", 20);

        storeRepository.addNewProduct(product);
        storeRepository.addNewProduct(newProduct);


        final List<Product> result = storeRepository.getProductsCategory("food");

        // Then
        assertEquals(result.get(0), product);
    }

    @Test
    public void getProductsCategory_noProductWithProductCategory_emptyList() throws IOException {
        // Given
        final Product product = new Product(1, "milk", "liquid", 20);
        final Product newProduct = new Product(2, "juice", "fsss", 20);

        storeRepository.addNewProduct(product);
        storeRepository.addNewProduct(newProduct);

        final List<Product> result = storeRepository.getProductsCategory("food");

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void getProductsCategory_allProductsFromGivenCategory_completeList() throws IOException {
        // Given
        final Product product = new Product(1, "milk", "liquid", 20);
        final Product newProduct = new Product(2, "juice", "liquid", 20);

        storeRepository.addNewProduct(product);
        storeRepository.addNewProduct(newProduct);

        final List<Product> result = storeRepository.getProductsCategory("liquid");

        // Then
        assertTrue(result.contains(product));
        assertTrue(result.contains(newProduct));
    }

}
