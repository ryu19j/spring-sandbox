package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.HolidayDao;
import com.example.entity.Holiday;

@Service
public class HolidayService {

	@Autowired
	HolidayDao holidayDao;
	
	public List<Holiday> getHoliday() {
		return holidayDao.findAll();
	}
}
