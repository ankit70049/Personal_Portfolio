package com.codinghub.portfolio.service;

import java.util.List;

import com.codinghub.portfolio.dto.ContactDto;
import com.codinghub.portfolio.entity.ContactEntity;

public interface ContactService {
		
	ContactEntity saveContact(ContactDto contactDto);
	boolean isContactEmailExists(String email);
	List<ContactEntity> readAllContacts();
	void  deleteContactById(int id);
	
}
