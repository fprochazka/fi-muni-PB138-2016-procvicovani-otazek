package com.fprochazka.drill.model;

/**
 * Created by Michaela Bamburová on 21.05.2016.
 */

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection="person")
public class Person
{


	private String id;
	private String name;

	private int age;

	@DBRef(db="address")
	private List<Address> addresses = new ArrayList<Address>();

	/*public Person()
	{}*/


	@PersistenceConstructor
	public Person(String name, int age)
	{
		super();
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.age = age;
	}

	public String getPersonId()
	{
		return id;
	}

	public void setPersonId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public List<Address> getAddresses()
	{
		return addresses;
	}


	public void setAddresses(List<Address> addresses)
	{
		this.addresses = addresses;
	}


	@Override
	public String toString()
	{
		return "Person [personId=" + id + ", name=" + name + ", age=" + age + ", addresses=" + addresses + "]";
	}



}
