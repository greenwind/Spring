package org.springtest.first.spryng;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "public")
public class Person {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_Seq")
    @SequenceGenerator(name = "User_Seq", sequenceName = "user_seq", allocationSize = 1, schema = "public")

	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@OneToMany(mappedBy = "person")
	private List<Address> address = new ArrayList<Address>();

	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void speak() {
		System.out.println("hi, hi, hi");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

}
