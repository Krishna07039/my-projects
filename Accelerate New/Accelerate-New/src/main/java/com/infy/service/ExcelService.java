package com.infy.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.infy.entity.ExcelTable;
import com.infy.helper.ExcelHelper;
import com.infy.repository.ExcelRepository;

@Service
public class ExcelService {

	@Autowired
	  ExcelRepository repository;

	  public void save(MultipartFile file) {
	  
	    try {
	    	repository.deleteAll();
	      List<ExcelTable> el = ExcelHelper.excelToTutorials(file.getInputStream());
	      repository.saveAll(el);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	  }

	  public ByteArrayInputStream load() {
	    List<ExcelTable> exceltable = repository.findAll();

	    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(exceltable);
	    return in;
	  }
}
