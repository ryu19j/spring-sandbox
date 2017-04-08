package com.example.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Holiday {

	@Id
	public String holiday;
	
	public String holidayName;

	public String getHoliday() {
		return holiday;
	}

	public String getHolidayName() {
		return holidayName;
	}

}
