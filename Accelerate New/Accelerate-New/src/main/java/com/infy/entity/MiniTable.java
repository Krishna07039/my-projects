package com.infy.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiniTable {
	private String sem;
	private Integer roleEnded;
	private Integer movement;
	private Integer quit;
	private Integer total;

}
