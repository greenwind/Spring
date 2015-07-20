package org.springtest.first.spryng;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml" })
@TransactionConfiguration(defaultRollback = true)
public class UserDaoImplTest {

	@Autowired
	@Qualifier(value = "userDAOImpl")
	private PersonDAO personDAO;

	@Before
	public void before() throws SQLException {
		personDAO.deleteAll();
	}
	
	@Test
	@Ignore
	public void addPersonTest() throws SQLException {
		Person person = personDAO.addPerson(new Person());
		Person person1 = personDAO.getPerson(person.getId());
		assertNotNull("Person not found", person1);
	}
	
	@Test
	public void getPersonTest() throws SQLException {
		Person person = personDAO.addPerson(new Person());
		Person person1 = personDAO.getPerson(person.getId());
		assertNotNull("Person not found", person1);
	}
}
