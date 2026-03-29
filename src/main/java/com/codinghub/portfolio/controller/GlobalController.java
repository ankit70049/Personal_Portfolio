package com.codinghub.portfolio.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute("logos")
    public List<String> getLogos(HttpServletRequest request) {

        String path = request.getServletContext().getRealPath("/img/logos");

        File folder = new File(path);

        List<String> logos = new ArrayList<>();

        if (folder.exists()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    logos.add(file.getName());
                }
            }
        }

        return logos;
    }
}