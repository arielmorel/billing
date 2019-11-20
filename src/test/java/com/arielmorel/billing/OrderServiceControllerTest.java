package com.arielmorel.billing;

import com.arielmorel.billing.entities.Order;
import com.arielmorel.billing.entities.OrderDetail;
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
public class OrderServiceControllerTest extends AbstractTest {
    private String uri = "/orders";
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    @org.junit.jupiter.api.Order(1)
    public void createOrder() throws Exception {
        Order order =new Order();
        order.setId(1L);
        order.setCustomer(null);
        order.setCreatedAt(new Date());
        order.setComment("comment");
        order.setStatus(1);
        order.setWarranty(30);


        //		Order detail 1
        OrderDetail detail=new OrderDetail();
        detail.setProduct(null);
        detail.setUnit(1);
        detail.setPrice(700D);
        detail.setOrder(order);

//		Order detail 2
        OrderDetail detail2=new OrderDetail();
        detail2.setProduct(null);
        detail2.setUnit(1);
        detail2.setPrice(900f);
        detail2.setOrder(order);

        order.addItem(detail);
        order.addItem(detail2);

        String inputJson = super.mapToJson(order);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri.concat("/create"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Order is placed successfully");
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void getAllOrder() throws Exception {
        String startDate="2019-11-18";
        String endDate="2019-11-30";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("startDate", startDate)
                .param("endDate", endDate)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Order[] orders = super.mapFromJson(content, Order[].class);
        assertTrue(orders.length > 0);
    }
}
