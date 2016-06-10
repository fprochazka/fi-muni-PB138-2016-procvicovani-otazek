
package com.fprochazka.drill;

import com.fprochazka.drill.config.ApplicationConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

public class Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(ApplicationConfig.class, args);
	}

//	public static void main(String[] args)
//	{
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new ClassPathResource("application-context.xml").getPath());
//		PersonRepo personRepo = context.getBean(PersonRepo.class);
//		AddressRepo addressRepo = context.getBean(AddressRepo.class);
//
//		Person personAchilles = new Person("Achilles ONO TO FUNGUJE", 23);
//		personRepo.save(personAchilles);
//
//		Person personHektor = new Person("Hektor", 25);
//
//		Address address = new Address("221b Baker Street","London NW1","London",12345l);
//		Address address2 = new Address("borough market","East London","London",123321);
//
//		List<Address> addresses = personHektor.getAddresses();
//
//		addresses.add(address);
//		addresses.add(address2);
//		personAchilles.setAddresses(addresses);
//
//		addressRepo.save(address);
//		addressRepo.save(address2);
//		personRepo.save(personHektor);
//
//		/*Iterable<Person> personList = personRepo.findAll();
//		System.out.println("Person List : ");
//		for (Person person : personList) {
//			System.out.println(person);
//		}
//
//		System.out.println("By age: ");
//		for (Person person : personRepo.searchByAge()) {
//			System.out.println(person);
//		}
//
//
//		System.out.println("All Hector addresses: ");
//		for (Address adress : personRepo.getAllAddressesInPerson()) {
//			System.out.println(adress);
//		}*/
//
//		PersonRepoImpl myRepo = new PersonRepoImpl(personRepo);
//		myRepo.deletePerson(personHektor);
//
//		System.out.println("All persons: ");
//		for (Person person : myRepo.getAllPersons()) {
//			System.out.println(person);
//		}
//
//
//
//
//
//		personRepo.deleteAll();
//		addressRepo.deleteAll();
//
//		context.close();
//	}
}
