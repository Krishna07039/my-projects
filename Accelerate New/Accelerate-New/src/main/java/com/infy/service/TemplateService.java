package com.infy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.*;

import com.infy.entity.Emaildetails;
import com.infy.entity.ExcelTable;
import com.infy.entity.MiniTable;
import com.infy.repository.ExcelRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@Service
public class TemplateService {

	@Autowired
	private JavaMailSender emailSender;

	@Value("${spring.mail.username}")
    private String sender;
	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	ExcelRepository excelRepository;
	
	
		/////////////////////////////////////
		public void sendTableEmail2(Emaildetails details, String date) {
		     LocalDate date1=LocalDate.parse(date);
			MiniTable mini=new MiniTable();
			List<ExcelTable>  tableData=excelRepository.findAll();
			List<ExcelTable> list=tableData.stream().filter(h->h.getEndDate().equals(date1)).collect(Collectors.toList());
			Integer count = 0;
	        Integer r = 0,m=0,q=0;
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getAttritionReason().toLowerCase().contains("role ended")) {
                    r++;
                }else if(list.get(i).getAttritionReason().toLowerCase().contains("movement")) {
                    m++;
                }else if(list.get(i).getAttritionReason().toLowerCase().contains("quit") ) {
                    q++;
                }
            }
			mini.setSem(details.getDate());
			mini.setRoleEnded(r);
			mini.setMovement(m);
			mini.setQuit(q);
			count=r+m+q;
			mini.setTotal(count);
			
			MimeMessage message = emailSender.createMimeMessage();

			try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

			// Set the recipient, subject, and other common email properties
			helper.setTo(details.getTo());
			helper.setSubject("Table Data Email");

			// Filter data into "yes" and "no" lists
			List<ExcelTable> yesItems = list.stream()
			.filter(item -> "yes".equalsIgnoreCase(item.getApprovalMail()))
			.collect(Collectors.toList());

			List<ExcelTable> noItems = list.stream()
			.filter(item -> "no".equalsIgnoreCase(item.getApprovalMail()))
			.collect(Collectors.toList());

			// Generate HTML content using Thymeleaf
			Context context = new Context();
			context.setVariable("yesItems", yesItems);
			context.setVariable("noItems", noItems);
			context.setVariable("mini", mini);
			context.setVariable("date", date1);
			String emailContent = templateEngine.process("email-template", context);

			// Set the HTML content
			helper.setText(emailContent, true);
			helper.setCc(details.getCc());
			helper.setBcc(details.getBcc());

			emailSender.send(message);
			} catch (MessagingException e) {
			// Handle exception
			}
			}
		
	    }

