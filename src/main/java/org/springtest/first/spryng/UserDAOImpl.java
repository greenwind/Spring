package org.springtest.first.spryng;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDAOImpl implements PersonDAO {

	@PersistenceContext
	protected EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public Person addPerson(Person person) throws SQLException {
		em.unwrap(Session.class).save(person);
		return person;
	}

	@Transactional
	public List<Person> getPerson(String name) throws SQLException {
		Query query = em.unwrap(Session.class).createQuery(
				"select p from Person p where name= :name");
		query.setParameter("name", name);
		return query.list();
		// return getEntityManager().find(Person.class, id);
	}

	@Transactional
	public List<Person> getPeople() throws SQLException {
		Query query = em.unwrap(Session.class).createQuery(
				"select p from Person p");
		return query.list();
	}

	@Transactional
	public Person updatePerson(Person person) throws SQLException {
		em.unwrap(Session.class).update(person);
		return person;
	}

	@Transactional
	public int deletePerson(Person person) throws SQLException {
		em.unwrap(Session.class).delete(person);
		return 0;
	}

	@Transactional
	public int deleteAll() throws SQLException {
		Query query = em.unwrap(Session.class)
				.createQuery("delete from Person");
		return query.executeUpdate();
	}

	@Transactional
	public int deletePerson(int id) throws SQLException {
		Query query = em.unwrap(Session.class).createQuery(
				"delete from Person where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	@Transactional
	public Person getPerson(int id) throws SQLException {
		Query query = em.unwrap(Session.class).createQuery("select p from Person p where id= :id");
		query.setParameter("id", id);
		return (Person)query.uniqueResult();
	}
}
