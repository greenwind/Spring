package org.springtest.first.spryng;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main( String[] args ) throws SQLException {
    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        DataSource ds = (DataSource)context.getBean("dataSource");
        ds.getConnection();
        
        PersonDAO p = (PersonDAO) context.getBean("userDAOImpl");
        p.addPerson(new Person("aaa"));
        
        AddressDAO a = (AddressDAO)context.getBean("addressDAOImpl");

        Person person = new Person();
        Address address = new Address();
        
        address.setPerson(person);
        person.getAddress().add(address);
        
        a.addEntry(address);
       //
       // PersonDAO p = (PersonDAO)context.getBean("personDAO");
      // PersonDAOImpl p = (PersonDAOImpl)context.getBean(PersonDAO.class);
       //
       // for (int i = 0; i < 10; i++) {
       //     p.addPerson(new Person("Bob"));
       // }
       // p.deletePerson(3);
        //p.getPerson(4);
        //p.getPeople();
    }
}
