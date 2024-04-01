package com.infy.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.entity.ExcelTable;
import com.infy.repository.ExcelRepository;

@Service
public class EmployeeService {

	@Autowired
	ExcelRepository excelRepository;

	public List<ExcelTable> allemps(){
		return excelRepository.findAll();
	}
	public List<ExcelTable> getEmpByendDate(LocalDate endDate){
		return excelRepository.getEmpByendDate(endDate);

	}
////////////////
	public List<ExcelTable> getEmployeesByDate(LocalDate date ){
		List<ExcelTable> list =  excelRepository.findAll();
		List<ExcelTable> newList = new ArrayList<>();
		for(int i = 0; i<list.size();i++) {
			if(date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
				if((list.get(i).getEndDate().equals(date))||(list.get(i).getEndDate().equals(date.plusDays(1)))||(list.get(i).getEndDate().equals(date.plusDays(2)))) {
					newList.add(list.get(i));
				}}else if(list.get(i).getEndDate().equals(date)){
					newList.add(list.get(i));
				}else if(isHoliday(date)) {
					if((list.get(i).getEndDate().equals(date.minusDays(1)))) {
						newList.add(list.get(i));
					}}else if((isHoliday(date)) && (date.minusDays(1).getDayOfWeek().equals(DayOfWeek.SATURDAY))&&
							(date.minusDays(2).getDayOfWeek().equals(DayOfWeek.SUNDAY))	) {
						if(list.get(i).getEndDate().equals(date.minusDays(3))) {
							newList.add(list.get(i));
						}	
					}
		}
		return newList;
	}

	public List<LocalDate> getHolidays(int year){
		List<LocalDate> hol =  new ArrayList<>();
		hol.add(LocalDate.of(year, 1, 1));
		hol.add(LocalDate.of(year, 1, 26));
		hol.add(LocalDate.of(year, 5, 1));
		hol.add(LocalDate.of(year, 8, 15));
		hol.add(LocalDate.of(year, 10, 2));
		hol.add(LocalDate.of(year, 12, 25));
		return hol;
	}

	public Boolean isHoliday(LocalDate date) {
		int year =  date.getYear();
		List<LocalDate> l = getHolidays(year);
		return l.contains(date);
	}
}
