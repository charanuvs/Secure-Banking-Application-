package edu.asu.securebanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author
 * 
 * 		Welcome Controller
 *
 */
@Controller
public class WelcomeController {
	
	/**
	 * @param model
	 * @return view
	 * 
	 *         Welcome hack
	 */
	@RequestMapping(value = { "/index.html", "" }, method = RequestMethod.GET)
	public String welcome(final Model model) {
		model.addAttribute("person", "Some person");
		return "welcome";
	}
	
}