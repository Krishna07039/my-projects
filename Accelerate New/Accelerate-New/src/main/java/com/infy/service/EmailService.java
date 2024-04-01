package com.infy.service;

import java.io.IOException;


import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.infy.entity.Emaildetails;
import com.infy.repository.ExcelRepository;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@Service
public class EmailService {

	@Autowired
	public ExcelRepository excelRepository;
	

	@Autowired 
	private JavaMailSender javaMailSender;
	
	@Autowired 
	private Configuration config;
	

	@Value("${spring.mail.username}")
	private String sender;
	
	public String sendMailWithAttachment(Emaildetails details, Map<String, Object> model) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException
	{

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {


	mimeMessageHelper	= new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,StandardCharsets.UTF_8.name());

			Template t = config.getTemplate("Email.ftl");
			String html =  FreeMarkerTemplateUtils.processTemplateIntoString(t,model);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getTo());
			mimeMessageHelper.setText(html,true);
			mimeMessageHelper.setSubject(details.getSubject());

			javaMailSender.send(mimeMessage);
			return "Mail sent Successfully";
		}

		catch (MessagingException e) {
			return "Error while sending mail!!!";
		}
	}
 }
