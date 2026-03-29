package com.codinghub.portfolio.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinghub.portfolio.dto.ContactDto;
import com.codinghub.portfolio.entity.ContactEntity;
import com.codinghub.portfolio.repositories.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public ContactEntity saveContact(ContactDto contactDto) {
		
		// validate:
	
	/*
		//convert dto into entity manually
		ContactEntity contactEntity = new ContactEntity();
		contactEntity.setName(contactDto.getName());
		contactEntity.setEmail(contactDto.getEmail());
		contactEntity.setSubject(contactDto.getSubject());
		contactEntity.setMessage(contactDto.getMessage());
		contactEntity.setDatetime(LocalDateTime.now().toString());
	*/
		
		
		// convert dto into entity by using ModelMapper
		ContactEntity contactEntity = modelMapper.map(contactDto, ContactEntity.class);
		contactEntity.setDatetime(LocalDateTime.now().toString());
		
		return contactRepository.save(contactEntity);
	}


	@Override
	public boolean isContactEmailExists(String email) {
		
		return contactRepository.existsByEmail(email);
	
	}


	@Override
	public List<ContactEntity> readAllContacts() {
	
		return contactRepository.findAll();
	}


	@Override
	public void deleteContactById(int id) {
		
		contactRepository.deleteById(id);
	}

}

