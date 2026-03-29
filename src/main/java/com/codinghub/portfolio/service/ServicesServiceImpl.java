package com.codinghub.portfolio.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codinghub.portfolio.dto.ServiceDto;
import com.codinghub.portfolio.entity.ServiceEntity;
import com.codinghub.portfolio.repositories.ServiceRepository;

@Service
//@Transactional    //@Transactional annotation by default only rollback in case of Checked exception 
@Transactional(rollbackOn = Exception.class)  // here, It is rollback in case of any type of exception(Checked, Unchecked)
public class ServicesServiceImpl implements ServicesService{
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	
	@Override
	public ServiceEntity saveService(String realPath, MultipartFile multipartFile, ServiceDto serviceDto) throws Exception {
		
		//DB Logic
		String filename = UUID.randomUUID().toString() + LocalDateTime.now().toString().replace(":", "a") + multipartFile.getOriginalFilename();      

		ServiceEntity serviceEntity = modelMapper.map(serviceDto, ServiceEntity.class);
		serviceEntity.setFilename(filename);
		serviceEntity.setDatetime(LocalDateTime.now().toString());
		
		ServiceEntity entity = serviceRepository.save(serviceEntity);
		
		//File Logic
		Path path = Paths.get(realPath, filename); // concatenate 
		File file = path.toFile();	
		multipartFile.transferTo(file);


		return entity;
		
	}


	@Override
	public List<ServiceEntity> readServices() {
		
		return serviceRepository.findAll();
	}


	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteService(String realPath, int id, String filename) {
		//Delete from DB
		serviceRepository.deleteById(id);
		
		//Delete from File
		File file = new File(realPath + File.separator + filename);
		file.delete();
		
	}


	@Override
	public Optional<ServiceEntity> readService(int id) {
		
		return serviceRepository.findById(id);
	}


	@Override
	public ServiceEntity updateService(String realPath, MultipartFile multipartFile, ServiceDto serviceDto, int id,
			String oldFileName) throws Exception {
		
		if(multipartFile!=null && !multipartFile.isEmpty()) {
			//new file
			
			//DB Logic
			
			String filename = UUID.randomUUID().toString() + LocalDateTime.now().toString().replace(":", "a") + multipartFile.getOriginalFilename();      

			ServiceEntity serviceEntity = modelMapper.map(serviceDto, ServiceEntity.class);
			serviceEntity.setId(id);
			serviceEntity.setFilename(filename);
			serviceEntity.setDatetime(LocalDateTime.now().toString());
			
			ServiceEntity entity = serviceRepository.save(serviceEntity);
			
			// here, existing file deleted
			new File(realPath + File.separator + oldFileName).delete();
			
			// here, new file uploaded
			Path path = Paths.get(realPath, filename); // concatenate 
			File file = path.toFile();	
			multipartFile.transferTo(file); 

			return entity;
		}
		else {
			// old file
			
			ServiceEntity serviceEntity = modelMapper.map(serviceDto, ServiceEntity.class);
			serviceEntity.setId(id);
			serviceEntity.setFilename(oldFileName);
			serviceEntity.setDatetime(LocalDateTime.now().toString());
			
			ServiceEntity entity = serviceRepository.save(serviceEntity);
			return entity;
		}
		
	}

	
}
