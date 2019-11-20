package com.arielmorel.billing;

import com.arielmorel.billing.entities.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Ariel Morel
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceControllerTest extends AbstractTest {
    String uri = "/products";
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    @org.junit.jupiter.api.Order(1)
    public void createProduct() throws Exception {
        Product product = new Product();
        product.setName("Laptop Dell i7");
        product.setDescription("Laptop Description");
        product.setStock(2);
        product.setPriceIn(200D);
        product.setPriceOut(400D);
        product.setActive(Boolean.TRUE);
        product.setCreatedAt(new Date());
        String inputJson = super.mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri.concat("/create"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Product is created successfully");
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void updateProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop Dell i7");
        product.setDescription("Description updated");
        product.setStock(2);
        product.setPriceIn(200D);
        product.setPriceOut(450D);
        String inputJson = super.mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri.concat("/update"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Product is updated successsfully");
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void getProductsList() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Product[] productlist = super.mapFromJson(content, Product[].class);
        assertTrue(productlist.length > 0);
    }
}
