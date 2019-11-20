package com.arielmorel.billing;


import com.arielmorel.billing.entities.Customer;
import com.arielmorel.billing.entities.Order;
import com.arielmorel.billing.entities.OrderDetail;
import com.arielmorel.billing.entities.Product;
import com.arielmorel.billing.repositories.CustomerRepository;
import com.arielmorel.billing.repositories.OrderDetailRepository;
import com.arielmorel.billing.repositories.ProductRepository;
import com.arielmorel.billing.services.IOrderService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
	private IOrderService orderService;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;

	private Logger log=Logger.getGlobal();

	private static Customer localCustomer;
	private static Product product1,product2;

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
		product.setPriceIn(200D);
		product.setPriceOut(400D);
		product.setActive(Boolean.TRUE);
		product.setCreatedAt(new Date());
		product1=productRepository.save(product);
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
			assertNotNull(product.getId());
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
		customer.setPhone("8093195517");
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
		customer.setPhone("8093195517");
		customer.setEmail("arielmoreld@gmail.com");
		localCustomer=customerRepository.findById(1L).get();

	}


	@Test
	@org.junit.jupiter.api.Order(6)
	public void createOrder(){
		//		Order
		Order order =new Order();
		order.setId(1L);
		order.setCustomer(localCustomer);
		order.setCreatedAt(new Date());
		order.setStatus(1);
		order.setWarranty(30);

		log.info("Order created correctly");

		//		Order detail 1
		OrderDetail detail=new OrderDetail();
		detail.setProduct(product1);
		detail.setUnit(1);
		detail.setPrice(700D);
		detail.setOrder(order);

//		Order detail 2
		OrderDetail detail2=new OrderDetail();
		detail2.setProduct(product2);
		detail2.setUnit(1);
		detail2.setPrice(900f);
		detail2.setOrder(order);

		order.addItem(detail);
		order.addItem(detail2);
		Order orderSaved =orderService.placeOrder(order);

//		System.out.println(orderEntitySaved.getOrderDetailList().get(0).getOrderEntity().getTotal());
	}

	@Test
	@org.junit.jupiter.api.Order(7)
	public void findAllOder(){
		List<Order> all = orderService.findAll();
		assertThat(all, hasSize(1));
	}

	@Test
	@org.junit.jupiter.api.Order(7)
	public void getTotalFromOrder(){
		List<Order> all = orderService.findAll();
		assertThat(all.get(0).getTotal(),greaterThanOrEqualTo(1600D));
	}

}
