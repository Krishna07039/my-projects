package com.infy.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.ExcelTable;

public interface ExcelRepository extends JpaRepository<ExcelTable,Long> {
	public List<ExcelTable> getEmpByendDate(LocalDate endDate);
	
	public List<ExcelTable>findAllByEndDateBetween(LocalDate startDate,LocalDate endDate);

	//public List<ExcelTable> findByendDateAndApprovalMail(LocalDate date, String approvalMail);
}
