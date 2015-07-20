package org.springtest.first.spryng;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddressDAOImpl implements AddressDAO {

	@PersistenceContext
	protected EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public int addEntry(Address address) {
		em.unwrap(Session.class).save(address);
		return 0;
	}

	@Transactional
	public List<Address> getAddress(String city) {
		Query query = em.unwrap(Session.class).createQuery("select p from Address p where city= :city");
		query.setParameter("city", city);
		return query.list();
	}

	@Transactional
	public List<Address> getAllAddresses() {
		Query query = em.unwrap(Session.class).createQuery("select a from Address a");
		return query.list();
	}

	@Transactional
	public void updateAddress(Address address) {
		em.unwrap(Session.class).update(address);
	}

	@Transactional
	public int deleteAddress(Address address) {
		em.unwrap(Session.class).delete(address);
		return 0;
	}

	@Transactional
	public int deleteAllAddresses() {
		Query query = em.unwrap(Session.class).createQuery("delete from Address");
		return query.executeUpdate();
	}

}
