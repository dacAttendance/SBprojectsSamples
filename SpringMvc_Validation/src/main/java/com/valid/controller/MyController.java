package com.valid.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.valid.model.Student;
import com.valid.service.StudentService;

@Controller
public class MyController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value ="/" ,method=RequestMethod.GET)
	public String homePage(Model model){
		model.addAttribute("headerMessage", "Welcome to Star Technology");
		return "home";
	}
	
	@RequestMapping(value ="/login" ,method=RequestMethod.GET)
	public String loginPage(Model model){
		model.addAttribute("studentCredential", new Student());
		return "login";
	}

	@RequestMapping(value ="/register" ,method=RequestMethod.GET)
	public String registerPage(Model model){
		model.addAttribute("student", new Student());
		return "register";
	}

	@RequestMapping(value ="/registerSuccess" ,method=RequestMethod.POST)
	public ModelAndView registerSuccess(@ModelAttribute("student") Student student){
		
		studentService.registerStudent(student);
		ModelAndView modelAndView = new ModelAndView("welcome");
		modelAndView.addObject("student", student);
		return modelAndView;
	}
	
	@RequestMapping(value ="/loginSuccess" ,method=RequestMethod.POST)
	public ModelAndView loginSuccess(@Valid @ModelAttribute("studentCredential") Student studentCredential, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return new ModelAndView("login");
		}
		
		ModelAndView modelAndView = new ModelAndView("welcome");
		Student student  = studentService.validateStudentCredential(studentCredential.getEmail(), studentCredential.getPassword());
		if(student != null){
			modelAndView.addObject("student", student );
			return modelAndView;
		}else{
			 modelAndView = new ModelAndView("notFound");
		}
		return modelAndView;
	}
	
	@ModelAttribute
	public void headerMessage(Model model){
		model.addAttribute("headerMessage", "Welcome to Star Technology");
		
		List<String> techList = new ArrayList<>();
		techList.add("Hibernate");
		techList.add("Spring");
		techList.add("JSP");
		techList.add("Servlet");
		techList.add("Struts");
		
		List<String> citesList = new ArrayList<>();
		citesList.add("Pune");
		citesList.add("Chennai");
		citesList.add("Delhi");
		citesList.add("Other");
		
		model.addAttribute("technologyList", techList);
		model.addAttribute("citesList", citesList);
	}
}	
//	@RequestMapping("/login")
//	public String login() {
//		return "login";
//	}
//	
//	@GetMapping("/register")
//	public String reeg(){
//		return "register";
//	}

