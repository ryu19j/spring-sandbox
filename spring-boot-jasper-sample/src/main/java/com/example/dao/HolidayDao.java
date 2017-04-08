package com.example.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.entity.Holiday;

@ConfigAutowireable
@Dao
public interface HolidayDao {

	@Select
	public List<Holiday> findAll();
}
