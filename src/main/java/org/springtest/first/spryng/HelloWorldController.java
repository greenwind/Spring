package org.springtest.first.spryng;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {
	String message = "Welcome to Spring MVC!";

	@Autowired
	@Qualifier(value = "userDAOImpl")
	private PersonDAO personDAO;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");

		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}

	@RequestMapping(value = "/person/get/{id}", method = RequestMethod.GET)
	public ModelAndView addPerson(@PathVariable int id) throws SQLException {
		Person person1 = personDAO.getPerson(id);
		ModelAndView mv = new ModelAndView("getperson");
		mv.addObject("id", person1);
		return mv;
	}
	
	@RequestMapping(value = "/person/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deletePerson(@PathVariable int id) throws SQLException {
		int person1 = personDAO.deletePerson(id);
		ModelAndView mv = new ModelAndView("deleteperson");
		mv.addObject("id", person1);
		return mv;
	}
	
}
