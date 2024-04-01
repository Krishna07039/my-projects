package com.infy.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.infy.entity.ExcelTable;

public class ExcelHelper {

	  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = {"Email ID of Requester", "EMP#", "Worker Infosys Email", "Offboarding Notification Mail sent or received on",
			  "Off boarding Approval received on" ,"Approval email","ITP Approval received","Training screen Shot ","Is Movement across GDCE ID ","Job Level","Emp DU","Emp Unit",
			  "PM","DM","SEM","Resource Mailcode","Location","Any Pending Timesheets or Revisions","Contractor Id",
			  "Contractor Name","SOW ID","End Date","Contractor end date","Attrition category","Attrition Reason",
			  "Rehire Status","Reason with ITP's Approval","GDCE ID","Actual Reason","Redeployment",
			  "Resource available to redeploy","If no, Reason for unavailability for redeployment ",
			  "If yes, SOW Name for new onboarding","If yes, Work order #","Skill",
			  "Available in Redeployment portal","GT Level Report","Does Onsite resource holding Bank asset","Date on when the request submitted",
			  "Request number for device submission","Status on device submission"};
	  
	  static String SHEET = "Meow";

	  public static boolean hasExcelFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static ByteArrayInputStream tutorialsToExcel(List<ExcelTable> et) {

	    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	      Sheet sheet = workbook.createSheet(SHEET);

	      // Header
	      Row headerRow = sheet.createRow(0);

	      for (int col = 0; col < HEADERs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(HEADERs[col]);
	      }

	      int rowIdx = 1;
	      for (ExcelTable e : et) {
	        Row row = sheet.createRow(rowIdx++);

	        row.createCell(0).setCellValue(e.getEmailIDofRequester());
	        row.createCell(1).setCellValue(e.getEmpId());
	        row.createCell(2).setCellValue(e.getWorkerInfosysMail());
	        row.createCell(3).setCellValue(e.getNotificationMailReceivedDate());
	        row.createCell(4).setCellValue(e.getApprovedMailRecieveddate());
	        row.createCell(5).setCellValue(e.getApprovalMail());
	        row.createCell(6).setCellValue(e.getItpApprovalMail());
	        row.createCell(7).setCellValue(e.getTrainingSc());
	        row.createCell(8).setCellValue(e.getMovement());
	        row.createCell(9).setCellValue(e.getJobLevel());
	        row.createCell(10).setCellValue(e.getEmpDU());
	        row.createCell(11).setCellValue(e.getEmpUnit());
	        row.createCell(12).setCellValue(e.getProjectManager());
	        row.createCell(13).setCellValue(e.getDeliveryManager());
	        row.createCell(14).setCellValue(e.getSem());
	        row.createCell(15).setCellValue(e.getResourceMailCode());
	        row.createCell(16).setCellValue(e.getLocation());
	        row.createCell(17).setCellValue(e.getPendingTimesheets());
	        row.createCell(18).setCellValue(e.getContractorId());
	        row.createCell(19).setCellValue(e.getContractorName());
	        row.createCell(20).setCellValue(e.getSowId());
	        row.createCell(21).setCellValue(e.getEndDate());
	        row.createCell(22).setCellValue(e.getContractorEndDate());
	        row.createCell(23).setCellValue(e.getAttritionCategory());
	        row.createCell(24).setCellValue(e.getAttritionReason());
	        row.createCell(25).setCellValue(e.getRehireStatus());
	        row.createCell(26).setCellValue(e.getReason());
	        row.createCell(27).setCellValue(e.getGdceId());
	        row.createCell(28).setCellValue(e.getActualReason());
	        row.createCell(29).setCellValue(e.getRedeployment());
	        row.createCell(30).setCellValue(e.getReadyToRedeploy());
	        row.createCell(31).setCellValue(e.getReasonForNo());
	        row.createCell(32).setCellValue(e.getSowName());
	        row.createCell(33).setCellValue(e.getWorkorderNew());
	        row.createCell(34).setCellValue(e.getSkill());
	        row.createCell(35).setCellValue(e.getAvailableInRDPortal());
	        row.createCell(36).setCellValue(e.getGtLevelReport());
	        row.createCell(37).setCellValue(e.getAsset());
	        row.createCell(38).setCellValue(e.getSubmitteddate());
	        row.createCell(39).setCellValue(e.getNoForAssetSubmission());
	        row.createCell(40).setCellValue(e.getAssetStatus());
	       
	        
	      }

	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
	    }
	  }

	  public static List<ExcelTable> excelToTutorials(InputStream is) {
	    try {
	      Workbook workbook = new XSSFWorkbook(is);

	      Sheet sheet = workbook.getSheet(SHEET);
	      Iterator<Row> rows = sheet.iterator();

	      List<ExcelTable> excel = new ArrayList<ExcelTable>();

	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();

	        // skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }

	        Iterator<Cell> cellsInRow = currentRow.iterator();

	        ExcelTable ms = new ExcelTable();

	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();

	          switch (cellIdx) {
	          case 0:
	           ms.setEmailIDofRequester(currentCell.getStringCellValue());
	            break;

	          case 1:
	           ms.setEmpId((long)currentCell.getNumericCellValue());
	            break;

	          case 2:
	           ms.setWorkerInfosysMail(currentCell.getStringCellValue());
	            break;

	          case 3:
	            ms.setNotificationMailReceivedDate(currentCell.getLocalDateTimeCellValue().toLocalDate());
	            break;
	          case 4:
		           ms.setApprovedMailRecieveddate(currentCell.getLocalDateTimeCellValue().toLocalDate());
		            break;
	          case 5:
		           ms.setApprovalMail(currentCell.getStringCellValue());
		            break;
	          case 6:
		           ms.setItpApprovalMail(currentCell.getStringCellValue());
		            break;
	          case 7:
		           ms.setTrainingSc(currentCell.getStringCellValue());
		            break;
	          case 8:
		           ms.setMovement(currentCell.getStringCellValue());
		            break;
	          case 9:
		           ms.setJobLevel((int)currentCell.getNumericCellValue());
		            break;
	          case 10:
		           ms.setEmpDU(currentCell.getStringCellValue());
		            break;
	          case 11:
		          ms.setEmpUnit(currentCell.getStringCellValue());
		            break;
	          case 12:
		           ms.setProjectManager(currentCell.getStringCellValue());
		            break;
		        
	          case 13:
		          ms.setDeliveryManager(currentCell.getStringCellValue());
		            break;
		            
	          case 14:
		          ms.setSem(currentCell.getStringCellValue());
		            break;
		            
	          case 15:
		         ms.setResourceMailCode(currentCell.getStringCellValue());
		            break;
		            
	          case 16:
		         ms.setLocation(currentCell.getStringCellValue());
		            break;
	          case 17:
		          ms.setPendingTimesheets(currentCell.getStringCellValue());
		            break;
	          case 18:
	        	  ms.setContractorId(currentCell.getStringCellValue());
		       
		            break;
	          case 19:
		           ms.setContractorName(currentCell.getStringCellValue());
		            break;
		          
	          case 20:
		          ms.setSowId(currentCell.getStringCellValue());
		            break;
	          case 21:
		         ms.setEndDate(currentCell.getLocalDateTimeCellValue().toLocalDate());
		            break;
	          case 22:
		        ms.setContractorEndDate(currentCell.getLocalDateTimeCellValue().toLocalDate());
		            break;
	          case 23:
		          ms.setAttritionCategory(currentCell.getStringCellValue());
		            break;
	          case 24:
		          ms.setAttritionReason(currentCell.getStringCellValue());
		            break;
	          case 25:
		        ms.setRehireStatus(currentCell.getStringCellValue());   
		            break;
	          case 26:
		          ms.setReason(currentCell.getStringCellValue()); 
		            break;
	          case 27:
		           ms.setGdceId(currentCell.getStringCellValue());
		            break;
	          case 28:
		           ms.setActualReason(currentCell.getStringCellValue());
		            break;
	          case 29:
		           ms.setRedeployment(currentCell.getStringCellValue());
		            break;
	          case 30:
		           ms.setReadyToRedeploy(currentCell.getStringCellValue());
		            break;
	          case 31:
		           ms.setReasonForNo(currentCell.getStringCellValue());
		            break;
	          case 32:
		           ms.setSowName(currentCell.getStringCellValue());
		            break;
	          case 33:
		           ms.setWorkorderNew(currentCell.getStringCellValue());
		            break;
	          case 34:
		           ms.setSkill(currentCell.getStringCellValue());
		            break;
		           
	          case 35:
		           ms.setAvailableInRDPortal(currentCell.getStringCellValue());
		            break;
	          case 36:
		           ms.setGtLevelReport(currentCell.getStringCellValue());
		            break;
	          case 37:
		           ms.setAsset(currentCell.getStringCellValue());
		            break;
	          case 38:
		           ms.setSubmitteddate(currentCell.getStringCellValue());
		            break;
	          case 39:
		           ms.setNoForAssetSubmission(currentCell.getStringCellValue());
		            break;
	          case 40:
		           ms.setAssetStatus(currentCell.getStringCellValue());
		            break;
	          
	          default:
	            break;
	          }

	          cellIdx++;
	        }

	        excel.add(ms);
	      }

	      workbook.close();

	      return excel;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
	    }
	  }
	}


