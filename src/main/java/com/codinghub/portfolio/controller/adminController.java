package com.codinghub.portfolio.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codinghub.portfolio.dto.ServiceDto;
import com.codinghub.portfolio.entity.ServiceEntity;
import com.codinghub.portfolio.service.ContactService;
import com.codinghub.portfolio.service.ServicesService;

@Controller
@RequestMapping("/admin")
public class adminController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private ServicesService servicesService;

	
	
	@GetMapping("/home")
	public String home() {
	
		return "admin/adminHome";
	}
	
	@GetMapping("/readAllContacts")
	public String readAllContacts(Model model) {
		model.addAttribute("contactData", contactService.readAllContacts());
		return "admin/readAllContacts";
	}
	
	@GetMapping("/deleteContactById")
	public String deleteContactById(@RequestParam int id) {
		contactService.deleteContactById(id);
		return "redirect:/admin/readAllContacts";
	}
	
	@GetMapping("/addService")
	public String addServiceView() {
		
		return "admin/addService";
	}
	
	
	@PostMapping("/addService")
	public String addService(@Valid   @ModelAttribute ServiceDto serviceDto, BindingResult result, Model model, RedirectAttributes redirect, HttpServletRequest request) throws Exception {																								
		
		if(result.hasErrors()) {
			model.addAttribute("result", "Invalid Input");
			model.addAttribute("errors", result.getFieldErrors());
			return "admin/addService";
		}
		
		if(serviceDto.getServiceFile() == null || serviceDto.getServiceFile().isEmpty()) {
			model.addAttribute("result", "File must be uploaded");
			return "admin/addService";
		}
		
		MultipartFile multipartFile = serviceDto.getServiceFile();
		long size = multipartFile.getSize(); // in  byte
		
		if(size > (1024*1024)) {   //  (1024byte * 1024byte) => 1MB
			model.addAttribute("fileError", "File size must not exceeded 1MB");
			return "admin/addService";
		}
		
		
		String realPath = request.getServletContext().getRealPath("/img/services");
		
//		String originalFileName = UUID.randomUUID().toString() + LocalDateTime.now().toString().replace(":", "a") + multipartFile.getOriginalFilename();
//		
//		Path path = Paths.get(realPath, originalFileName); // concatenate 
//		File file = path.toFile();
		
//	multipartFile.transferTo(file);
		
		
		servicesService.saveService(realPath, multipartFile, serviceDto);
		redirect.addFlashAttribute("result", "Service Added Sucessfully");
		
		return "redirect:/admin/addService";
	}
	
	
	@GetMapping("/readAllServices")
	public String readAllServices(Model model) {
		model.addAttribute("listOfServices", servicesService.readServices());
		return "admin/readAllServices";
	}
	
	@GetMapping("/deleteService")
	public String deleteService(@RequestParam int id, @RequestParam String filename, RedirectAttributes redirect, HttpServletRequest request) {
		
		String realPath = request.getServletContext().getRealPath("img/services/");
		
		servicesService.deleteService(realPath, id, filename);
	
		return "redirect:/admin/readAllServices";
	}
	
	
	@GetMapping("/updateService")
	public String updateServiceView(@RequestParam int id, Model model) {
		
		Optional<ServiceEntity> service = servicesService.readService(id);
		ServiceEntity serviceEntity = service.get();
		model.addAttribute("serviceData", serviceEntity);
	
		return "/admin/updateService";
	}
	
	
	@PostMapping("/updateService")
	public String updateServiceView(@RequestParam int id, @RequestParam String oldFileName, @ModelAttribute ServiceDto serviceDto, RedirectAttributes redirect, HttpServletRequest request) throws Exception {
	
		String realPath = request.getServletContext().getRealPath("img/services/");
		MultipartFile serviceFile =	serviceDto.getServiceFile();

		servicesService.updateService(realPath, serviceFile, serviceDto, id, oldFileName);
		
		return "redirect:/admin/readAllServices";
	}
	
	

	@GetMapping("/uploadResume")
	public String uploadResume() {
		return "admin/uploadResume";
	}
	
	
	@PostMapping("/uploadResume")
	public String uploadResume(@RequestParam MultipartFile resume , RedirectAttributes redirectAttribute, HttpServletRequest request) throws IllegalStateException, IOException {
		
		if(resume == null || resume.isEmpty()) {
			redirectAttribute.addFlashAttribute("result", "Resume must be uploaded");
			return "redirect:/admin/uploadResume";
		}
		
		
		long size = resume.getSize(); // in  byte
		
		if(size > (2*1024*1024)) {   //  (2 * 1024byte * 1024byte) => 2MB
			redirectAttribute.addFlashAttribute("result", "Resume size must not exceeded 2MB");
			return "redirect:/admin/uploadResume";
		}
		     
		String contentType = resume.getContentType();
		if(!contentType.contains("pdf")) {
			redirectAttribute.addFlashAttribute("result", "Resume should be in PDF format");
			return "redirect:/admin/uploadResume";
		}
		
		
		String realPath = request.getServletContext().getRealPath("/resume/");
		
		//New File Upload
		Path path = Paths.get(realPath, "MyResume.pdf");
		File file = path.toFile();
		
		//If resume exists then delete old resume
		if(file.exists()) {
			file.delete();
		}
		
		// resume uploaded
		resume.transferTo(file);
		
		redirectAttribute.addFlashAttribute("result", "Resume Uploaded Successfully");
		return "redirect:/admin/uploadResume";
	}
	
	
	
	@GetMapping("/uploadProfile")
	public String uploadProfile() {
		return "admin/uploadProfile";
	}
	
	
	@PostMapping("/uploadProfile")
	public String uploadProfile(@RequestParam MultipartFile profile , RedirectAttributes redirectAttribute, HttpServletRequest request, Model model) throws IllegalStateException, IOException {
		
		if(profile == null || profile.isEmpty()) {
			redirectAttribute.addFlashAttribute("result", "profile must be uploaded");
			return "redirect:/admin/uploadProfile";
		}
		
		String contentType = profile.getContentType();
		
		if (contentType == null || !contentType.startsWith("image/")) {
		    redirectAttribute.addFlashAttribute("result", "Only image files allowed");
		    return "redirect:/admin/uploadProfile";
		}

		
		String realPath = request.getServletContext().getRealPath("/profile/");
		
		//New File Upload
		Path path = Paths.get(realPath, "profile");
		File file = path.toFile();
		
		//If profile photo exists then delete old profile photo
		if(file.exists()) {
			file.delete();
		}
		
		// profile uploaded
		profile.transferTo(file);
		
		redirectAttribute.addFlashAttribute("result", "Profile Uploaded Successfully");
		return "redirect:/admin/uploadProfile";
	}

	
	@GetMapping("/uploadProfileBanner")
	public String uploadProfileBanner() {
		return "admin/uploadProfileBanner";
	}

	@PostMapping("/uploadProfileBanner")
	public String uploadProfileBanner(@RequestParam MultipartFile profileBanner , RedirectAttributes redirectAttribute, HttpServletRequest request, Model model) throws IllegalStateException, IOException {
		
		if(profileBanner == null || profileBanner.isEmpty()) {
			redirectAttribute.addFlashAttribute("result", "Profile Banner must be uploaded");
			return "redirect:/admin/uploadProfileBanner";
		}
		
		String contentType = profileBanner.getContentType();
		
		if (contentType == null || !contentType.startsWith("image/")) {
		    redirectAttribute.addFlashAttribute("result", "Only image files allowed");
		    return "redirect:/admin/uploadProfileBanner";
		}
		
		String realPath = request.getServletContext().getRealPath("/img/banner");
		
		//New File Upload
		Path path = Paths.get(realPath, "profile-banner");
		File file = path.toFile();
		
		//If profile photo exists then delete old profile photo
		if(file.exists()) {
			file.delete();
		}
		
		// profile uploaded
		profileBanner.transferTo(file);
		
		redirectAttribute.addFlashAttribute("result", "Profile Banner Uploaded Successfully");
		return "redirect:/admin/uploadProfileBanner";
	}
	
	
	@GetMapping("/addLogo")
	public String addLogoView() {
		return "admin/addLogo";
	}
	
	@PostMapping("/addLogo")
	public String addLogo(@RequestParam MultipartFile logo , RedirectAttributes redirectAttribute, HttpServletRequest request, Model model) throws IllegalStateException, IOException {
		
		if(logo == null || logo.isEmpty()) {
			redirectAttribute.addFlashAttribute("result", "Logo must be uploaded");
			return "redirect:/admin/addLogo";
		}
		
		String contentType = logo.getContentType();

		if (contentType == null || !contentType.startsWith("image/")) {
		    redirectAttribute.addFlashAttribute("result", "Only image files allowed");
		    return "redirect:/admin/addLogo";
		}
		
		
		String realPath = request.getServletContext().getRealPath("/img/logos");
		
	    // Unique filename
        String fileName = UUID.randomUUID() + "_" + logo.getOriginalFilename();
		
		//New File Upload
		Path path = Paths.get(realPath, fileName);
		File file = path.toFile();
		
		// logo uploaded
		logo.transferTo(file);
		
		redirectAttribute.addFlashAttribute("result", "Logo Uploaded Successfully");
		
	
		
		return "redirect:/admin/addLogo";  
	}

	


}
