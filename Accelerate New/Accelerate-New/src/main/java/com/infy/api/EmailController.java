package com.infy.api;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.entity.Emaildetails;
import com.infy.service.EmailService;
import com.infy.service.TemplateService;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@RestController
public class EmailController {

	@Autowired
    public TemplateService templateService;

	@PostMapping("/sendingEmail/{date}")
    public String sendEmail(@RequestBody Emaildetails email, @PathVariable String date) {
        templateService.sendTableEmail2(email, date);
     return "sended";
 

    }
	
}
