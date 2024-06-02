package com.santiago.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.santiago.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.santiago.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.santiago.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.santiago.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		//manyToOne();
		manyToOneFindById();
	}

	public void manyToOne() {
		Client client = new Client("Santiago", "Penilla");
		clientRepository.save(client);
		Invoice invoice = new Invoice("Computer", 1000.0);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
		
	}
	
	public void manyToOneFindById() {
		Optional<Client> optionalClient = clientRepository.findById(1L);
		if(optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("Desktop Computer", 5000.0);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}
		
		
	}

}
