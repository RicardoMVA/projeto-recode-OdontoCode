package com.qualiti.odontoSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/servicos")
	public String servicos() {
		return "servicos";
	}
	
	@RequestMapping(value = "/contato")
	public String contato() {
		return "contato";
	}
	
	@RequestMapping(value = "/agendar")
	public String agendar() {
		return "agendar";
	}
}
