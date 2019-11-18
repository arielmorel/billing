package com.arielmorel.billing;


import com.arielmorel.billing.entities.Customer;
import com.arielmorel.billing.entities.Order;
import com.arielmorel.billing.entities.OrderDetail;
import com.arielmorel.billing.entities.Product;
import com.arielmorel.billing.repositories.CustomerRepository;
import com.arielmorel.billing.repositories.OrderDetailRepository;
import com.arielmorel.billing.repositories.OrderRepository;
import com.arielmorel.billing.repositories.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;


import static org.hamcrest.CoreMatchers.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;




@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BillingApplicationTests {

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;

	private Logger log=Logger.getGlobal();

	private static Customer localCustomer;
	private Product product1,product2;

	@Test
	void contextLoads() {
	}

	@Test
	@org.junit.jupiter.api.Order(1)
	public void createProduct(){
		Product product=new Product();
		product.setName("DELL LATITUDE 5480");
		product.setDescription("Laptop Description");
		product.setStock(2);
		product.setPriceIn(200);
		product.setPriceOut(400);
		product.setActive(Boolean.TRUE);
		product.setCreatedAt(new Date());
		productRepository.save(product);
		log.info("Product saved correctly");
	}

	@Test
	@org.junit.jupiter.api.Order(2)
	public void updateProduct(){
		Product product = productRepository.findById(1L).get();
		if(product!=null){
			product.setDescription("Description updated");
			product.setActive(Boolean.FALSE);
			productRepository.save(product);
			log.info("Product updated correctly");
			Assert.notNull(product.getId());
		}

	}

	@Test
	@org.junit.jupiter.api.Order(3)
	public void findAllProducts(){
		List<Product> all = productRepository.findAll();
		assertThat(all.size(), is(1));
	}

	@Test
	@org.junit.jupiter.api.Order(4)
	public void createCustomer(){
		//	Customer
		Customer customer=new Customer();
		customer.setName("Ariel");
		customer.setLastName("Morel");
		customer.setCellPhone("8093195517");
		customer.setEmail("arielmoreld@gmail.com");
		customerRepository.save(customer);
		log.info("Customer created correctly");
	}

	@Test
	@org.junit.jupiter.api.Order(5)
	public void findCustomer(){
		//	Customer
		Customer customer=new Customer();
		customer.setName("Ariel");
		customer.setLastName("Morel");
		customer.setCellPhone("8093195517");
		customer.setEmail("arielmoreld@gmail.com");
		localCustomer=customerRepository.findById(1L).get();

	}


	@Test
	@org.junit.jupiter.api.Order(6)
	public void createOrder(){
		//		Order
		System.out.println("customer: "+localCustomer);
		Order order =new Order();
		order.setCustomer(localCustomer);
		order.setCreatedAt(new Date());
		Order orderSaved = orderRepository.save(order);
		log.info("Order created correctly");

		//		Order detail 1
		OrderDetail detail=new OrderDetail();
		detail.setProduct(product1);
		detail.setUnit(1);
		detail.setPrice(700f);
		detail.setOrder(orderSaved);

		//		Order detail 2
		OrderDetail detail2=new OrderDetail();
		detail.setProduct(product2);
		detail.setUnit(1);
		detail.setPrice(900f);
		detail.setOrder(orderSaved);

//		OrderDetail invoiceDetailCreate = orderDetailRepository.save(detail);
//		Assert.notNull(invoiceDetailCreate);
	}

	@Test
	@org.junit.jupiter.api.Order(7)
	public void findAllOder(){
		List<Order> all = orderRepository.findAll();
		assertThat(all, hasSize(1));
		System.out.println(all);
	}


	}
