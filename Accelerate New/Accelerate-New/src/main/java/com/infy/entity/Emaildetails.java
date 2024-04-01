package com.infy.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emaildetails {
	
		private String name;
		private String to;
		private String from;
		private String subject;
		private String cc;
		private String bcc;
		
		private String content;
		private String date;
		

	}



