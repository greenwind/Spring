package org.springtest.first.spryng;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {

	public Person addPerson(Person person) throws SQLException;

	public Person getPerson(int id) throws SQLException;
	
	public List<Person> getPerson(String name) throws SQLException;

	public List<Person> getPeople() throws SQLException;

	public Person updatePerson(Person person) throws SQLException;

	public int deletePerson(int id) throws SQLException;

	public int deleteAll() throws SQLException;

}
