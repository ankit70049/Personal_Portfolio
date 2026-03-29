package com.codinghub.portfolio.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleMaxUploadSizeExceededException(Exception e, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {            
		
		String header = httpServletRequest.getHeader("referer");
		System.out.println(header);
		System.out.println(e);
		
		//redirectAttributes.addFlashAttribute("result", "File size must not exceeded 3MB");
		//redirectAttributes.addFlashAttribute("result", "Somthing Went Wrong..!"+e.getMessage());
		
		
		 if (header == null || header.isEmpty()) {
			 return "redirect:/?error=size"; // fallback
	     }
		 
		//return "redirect:"+header;
		 return "redirect:" + header + (header.contains("?") ? "&" : "?") + "error=size";
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public String handleGeneralException(Exception e, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {            
		
		String header = httpServletRequest.getHeader("referer");
		System.out.println(header);
		System.out.println(e);
		
		//redirectAttributes.addFlashAttribute("result", "Somthing Went Wrong..!");
		//redirectAttributes.addFlashAttribute("result", "Somthing Went Wrong..!"+e.getMessage());
		
		if (header == null || header.isEmpty()) {
			 return "redirect:/?error=general";
	     }
		
		
		//return "redirect:"+header;
		 return "redirect:" + header + (header.contains("?") ? "&" : "?") + "error=general";
	}
	
	
}

