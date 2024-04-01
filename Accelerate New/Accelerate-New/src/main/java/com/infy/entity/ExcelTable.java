package com.infy.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelTable {



	private String emailIDofRequester;

	@Id
	private Long empId;


	private String workerInfosysMail;

	private LocalDate notificationMailReceivedDate;

	private LocalDate approvedMailRecieveddate;

	private String approvalMail;
	//private Boolean status;

	private String itpApprovalMail;

	private String trainingSc; 

	private String movement;

	private Integer jobLevel;

	private	String empDU;

	private  String empUnit;

	private String  projectManager;

	private String deliveryManager;

	private String sem;

	private String resourceMailCode;

	private String location;

	private String pendingTimesheets;

	private String contractorId;

	private String contractorName;

	private String  sowId;

	private LocalDate endDate;

	private LocalDate contractorEndDate;

	private String attritionCategory;

	private String attritionReason;

	private String rehireStatus;

	private String reason;

	private String gdceId;

	private String actualReason;

	private String redeployment;

	private String readyToRedeploy;

	private String reasonForNo;

	private String sowName;

	private String workorderNew;

	private String skill;

	private String availableInRDPortal;

	private String gtLevelReport;

	private String asset;

	private String submitteddate;

	private String noForAssetSubmission;

	private String assetStatus;


} 
