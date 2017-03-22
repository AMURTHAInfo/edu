package com.simsui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class HomePageController {

	@RequestMapping(value="/profile",method = RequestMethod.POST)
	public @ResponseBody String processAJAXRequest(@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname) {
		String response = firstname + " here we go " + lastname;
		return response;
	}
}