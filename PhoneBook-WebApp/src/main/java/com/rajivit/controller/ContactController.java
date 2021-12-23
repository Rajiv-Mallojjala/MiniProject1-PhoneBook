package com.rajivit.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rajivit.constants.AppConstants;
import com.rajivit.entity.Contact;
import com.rajivit.props.ApplicationProperties;
import com.rajivit.service.ContactService;

@Controller
public class ContactController {

	private static final Logger logger = LoggerFactory.getLogger("ContactController.class");

	@Autowired
	ContactService contactService;

	@Autowired
	private ApplicationProperties appProps;

	@GetMapping("/loadForm")
	public String loadForm(Model model) {
		logger.debug("*********************Load form execution started************************");
		model.addAttribute("contact", new Contact());
		logger.debug("*********************Load form execution completed************************");
		logger.info("*********************loadForm() executed successfully************************");
		return AppConstants.INDEX_PAGE;
	}

	@PostMapping("/saveContact")
	public String handleSaveContactBtn(Contact contact, RedirectAttributes attribute) {
		logger.debug("*********************save contact execution started************************");
		Map<String, String> messages = appProps.getMessages();
		String txtMsg = "";
		if (contact.getContactId() == null) {
			txtMsg = messages.get(AppConstants.SAVE_SUCC);
		} else {
			txtMsg = messages.get(AppConstants.UPDATE_SUCC);
		}
		boolean isSaved = contactService.saveContact(contact);
		if (isSaved) {
			logger.info("*********************Contact saved************************");
			attribute.addFlashAttribute(AppConstants.SUCC_MSG, txtMsg);
		} else {
			logger.info("*********************Contact not saved************************");
			attribute.addFlashAttribute(AppConstants.ERR_MSG, messages.get(AppConstants.SAVE_FAIL));
		}
		logger.debug("*********************save contact execution completed************************");
		return "redirect:/loadForm";
		
	}

	@GetMapping("/viewContacts")
	public String viewContacts(Model model) {
		List<Contact> contactList = contactService.getAllContacts();
		model.addAttribute("contacts", contactList);
		return "viewContacts";

	}

	@GetMapping("/delete")
	public String handleDeleteClick(@RequestParam("cid") Integer cid, Model model) {
		contactService.deleteContactById(cid);
		return "redirect:/viewContacts";
	}

	@GetMapping("/edit")
	public String handleEditClick(@RequestParam("cid") Integer cid, Model model) {
		Contact contactObj = contactService.getContactById(cid);
		model.addAttribute("contact", contactObj);
		return AppConstants.INDEX_PAGE;
	}

}
