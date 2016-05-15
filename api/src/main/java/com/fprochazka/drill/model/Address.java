package com.fprochazka.drill.model;

/**
 * Created by Michaela Bamburová on 21.05.2016.
 */

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection="address")
public class Address
{

	private String id;

	private String address;

	private String city;

	private String state;

	private long zipcode;

	@PersistenceConstructor
	public Address( String address, String city, String state, long zipcode)
	{
		super();
		this.id = UUID.randomUUID().toString();
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public long getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(long zipcode)
	{
		this.zipcode = zipcode;
	}

	@Override
	public String toString()
	{
		return "Address [address=" + address + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + "]";
	}




}
