package com.rajivit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rajivit.entity.Contact;
import com.rajivit.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	ContactService contactService;

	@GetMapping("/loadForm")
	public String loadForm(Model model) {
		model.addAttribute("contact", new Contact());
		return "index";
	}

	@PostMapping("/saveContact")
	public String handleSaveContactBtn(Contact contact, RedirectAttributes attribute){
		boolean isSaved = contactService.saveContact(contact);
		if (isSaved) {
			attribute.addFlashAttribute("succMsg", "Contact Saved Successfully");
		} else {
			attribute.addFlashAttribute("errMsg", "Failed to save the contact");
		}
		return "redirect:/loadForm";
	}

}
