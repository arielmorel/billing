package com.arielmorel.billing;


import com.arielmorel.billing.entities.Customer;
import com.arielmorel.billing.entities.Invoice;
import com.arielmorel.billing.entities.InvoiceDetail;
import com.arielmorel.billing.entities.Product;
import com.arielmorel.billing.repositories.CustomerRepository;
import com.arielmorel.billing.repositories.InvoiceDetailRepository;
import com.arielmorel.billing.repositories.InvoiceRepository;
import com.arielmorel.billing.repositories.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
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
	private InvoiceDetailRepository invoiceDetailRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
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
	@Order(1)
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
	@Order(2)
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
	@Order(3)
	public void findAllProducts(){
		List<Product> all = productRepository.findAll();
		assertThat(all.size(), is(1));
	}

	@Test
	@Order(4)
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
	@Order(5)
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
	@Order(6)
	public void createOrder(){
		//		Invoice
		System.out.println("customer: "+localCustomer);
		Invoice invoice =new Invoice();
		invoice.setCustomer(localCustomer);
		invoice.setCreatedAt(new Date());
		Invoice invoiceSaved = invoiceRepository.save(invoice);
		log.info("Order created correctly");

		//		Invoice detail 1
		InvoiceDetail detail=new InvoiceDetail();
		detail.setProduct(product1);
		detail.setUnit(1);
		detail.setPrice(700f);
		detail.setInvoice(invoiceSaved);

		//		Invoice detail 2
		InvoiceDetail detail2=new InvoiceDetail();
		detail.setProduct(product2);
		detail.setUnit(1);
		detail.setPrice(900f);
		detail.setInvoice(invoiceSaved);

//		InvoiceDetail invoiceDetailCreate = invoiceDetailRepository.save(detail);
//		Assert.notNull(invoiceDetailCreate);
	}

	@Test
	@Order(7)
	public void findAllOder(){
		List<Invoice> all = invoiceRepository.findAll();
		assertThat(all, hasSize(1));
		System.out.println(all);
	}


	}
