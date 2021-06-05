package com.bobsumsol.webstart.site;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/index")
	public String index1() {
		return "index";
	}
	
	@GetMapping("/about")
	public String aboutUs() {
		return "about";
	}
	
	@GetMapping("/services")
	public String services() {
		return "services";
	}
	@GetMapping("/portfolio")
	public String portfolio() {
		return "portfolio";
	}
	@GetMapping("/contact")
	public String showContactForm() {
		return "contact_form";
	}
	@PostMapping("/contact")
	public String submitContact(HttpServletRequest request, Model model) {
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("techbobsum@gmail.com");
		message.setTo("gracsitholet@gmail.com");
	
		String mailSubject = fullname + " has sent a message";
		String mailContent = "Sender Name: " + fullname + "\n";
		mailContent += "Sender Email: " + email + "\n";
		mailContent += "Sender Phone: " + phone + "\n";
		mailContent += "Subject: " + subject + "\n";
		mailContent += "Content: " + content + "\n";
		
		message.setSubject(mailSubject);
		message.setText(mailContent);
		
		mailSender.send(message);
		model.addAttribute("pholder", fullname);
		return "message";
	}
}
