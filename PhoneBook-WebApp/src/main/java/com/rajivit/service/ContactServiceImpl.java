package com.rajivit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajivit.entity.Contact;
import com.rajivit.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactRepository contactRepo;
	
	@Override
	public boolean saveContact(Contact contact) {
		contact.setActiveSw("Y");
		Contact savedObj = contactRepo.save(contact);
		return savedObj.getContactId() != null;
	}

	@Override
	public List<Contact> getAllContacts() {
	 return contactRepo.findByActiveSw("Y");
	}

	@Override
	public Contact getContactById(Integer contactId) {
		Optional<Contact> findById = contactRepo.findById(contactId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	
	@Override
	public boolean deleteContactById(Integer contactId) {
		try {
			Contact contact = contactRepo.findById(contactId).get();
			contact.setActiveSw("N");
			contactRepo.save(contact);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
