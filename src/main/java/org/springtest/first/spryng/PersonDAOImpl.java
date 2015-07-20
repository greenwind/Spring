package org.springtest.first.spryng;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDAOImpl implements PersonDAO {

	@Autowired
	private DataSource ds;

	public Person addPerson(Person person) throws SQLException {

		Connection conn = null;
		PreparedStatement sql = null;

		try {
			conn = ds.getConnection();

			sql = conn
					.prepareStatement("insert into public.user (id, name) values (NEXTVAL('user_seq'), ?)");

			sql.setString(1, person.getName());
			int updated = sql.executeUpdate();
			return person;

		} finally {
			// it will always be executed
			sql.close();
			conn.close();
		}
	}

	public Person getPerson(int id) throws SQLException {
		Connection conn = ds.getConnection();
		String sql = "select * from public.user where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			try {
				ResultSet res = ps.executeQuery();
				try {
					while (res.next()) {
						int idD = res.getInt("id");
						// int idD = Integer.parseInt(res.getString("id"));
						String name = res.getString("name");
						System.out.println(idD + name);
						return new Person(idD, name);
					}
				} finally {
					res.close();
				}
			} finally {
				ps.close();
			}
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Person> getPeople() throws SQLException {

		List<Person> people = new ArrayList<Person>();

		Connection conn = ds.getConnection();

		String sql = "select id, name from public.user order by id";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {

			ResultSet res = ps.executeQuery();

			try {
				while (res.next()) {
					int id = res.getInt("id");
					String name = res.getString("name");

					Person person = new Person(id, name);
					people.add(person);
					System.out.println(people);
				}
				return people;

			} finally {
				res.close();
			}
		} finally {
			ps.close();
			conn.close();
		}
		// return null;
	}

	public Person updatePerson(Person person) throws SQLException {
		Connection conn = null;
		PreparedStatement sql = null;

		try {
			conn = ds.getConnection();

			sql = conn
					.prepareStatement("update public.user set name = ? where id = ? ");
			sql.setInt(1, person.getId());
			sql.setString(1, person.getName());

			int updated = sql.executeUpdate();
			return person;

		} finally {
			sql.close();
			conn.close();
		}
	}

	public int deletePerson(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement sql = null;

		try {
			conn = ds.getConnection();

			sql = conn
					.prepareStatement("delete from public.user where id = ? ");
			sql.setInt(1, id);

			int updated = sql.executeUpdate();
			return updated;

		} finally {
			sql.close();
			conn.close();
		}
	}

	public int deleteAll() throws SQLException {
		Connection conn = null;
		PreparedStatement sql = null;

		try {
			conn = ds.getConnection();

			sql = conn.prepareStatement("delete from people");

			int updated = sql.executeUpdate();
			return updated;

		} finally {
			sql.close();
			conn.close();
		}
	}

	public List<Person> getPerson(String name) throws SQLException {

		List<Person> people = new ArrayList<Person>();

		Connection conn = ds.getConnection();

		String sql = "select id, name from public.user where name = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		try {

			ResultSet res = ps.executeQuery();

			try {
				while (res.next()) {
					int id = res.getInt("id");

					Person person = new Person(id, name);
					people.add(person);
					System.out.println(people);
				}
				return people;

			} finally {
				res.close();
			}
		} finally {
			ps.close();
			conn.close();
		}
	}
}
