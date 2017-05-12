package com.contactManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.contactManager.event.ContactListCriteria;
import com.contactManager.event.EmailDetails;
import com.contactManager.model.Contact;
import com.contactManager.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController 
{
	@Autowired
	private ContactService contactService;

	@RequestMapping(method = RequestMethod.GET, produces ="application/json")
	@ResponseBody
	public  List<Contact> getContacts(@RequestParam("instituteName") String instituteName,
			@RequestParam("country") String country,
			@RequestParam("state") String state,
			@RequestParam("status") String status) {
		ContactListCriteria criteria = new ContactListCriteria(instituteName,country,state,status);
		return contactService.getContacts(criteria);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{email}", produces = "application/json" )
	@ResponseBody
	public  Contact getContact(@PathVariable String email){
		return contactService.getContact(email);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json" )
	@ResponseBody
	public  Contact createContact(@RequestBody Contact contact) {
		return contactService.createContact(contact);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{email}", produces = "application/json")
	@ResponseBody
	public Contact updateContact(@PathVariable String email, @RequestBody Contact contact){
		return contactService.updateContact(email,contact);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{email}", produces = "application/json")
	@ResponseBody
	public Contact deleteContact(@PathVariable String email){
		return contactService.deleteContact(email);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/send-mail", produces = "application/json")
	@ResponseBody
	public  List<Contact> sendEmail(@RequestBody EmailDetails email) { 
		return contactService.sendEmail(email);
	}
}