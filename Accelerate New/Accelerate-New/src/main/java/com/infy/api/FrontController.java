package com.infy.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.entity.Emaildetails;
import com.infy.entity.ExcelTable;
import com.infy.repository.ExcelRepository;
import com.infy.service.EmployeeService;
import com.infy.service.ExcelService;

@Controller
public class FrontController {

	@Autowired
	private ExcelRepository excelRepository;
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/")
	public String getAllemployees(Model model) {
		
		List<ExcelTable>list=empService.allemps();
		model.addAttribute("list", list);
		return "first";
		
		
	}
	
//	@PostMapping("/filter")
//
//    public String filterByDate(@ModelAttribute Emaildetails email, Model model) {
//
//        LocalDate selectedDate = LocalDate.parse(email.getDate());
//
//        List<ExcelTable> yesData = excelRepository.findByendDateAndApprovalMail(selectedDate, "yes");
//
//        List<ExcelTable> noData = excelRepository.findByendDateAndApprovalMail(selectedDate, "no");
//
//        
//
//        model.addAttribute("yesData", yesData);
//
//        model.addAttribute("noData", noData);
//
//        model.addAttribute("email", email);
//
//        return "index";
//
//    }
}
