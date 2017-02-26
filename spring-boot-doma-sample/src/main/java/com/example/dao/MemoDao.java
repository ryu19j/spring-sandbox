package com.example.dao;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.entity.Memo;


@ConfigAutowireable
@Dao
public interface MemoDao {

	@Select
	public Optional<Memo> selectById(int id);
	
	@Insert
	public int insert(Memo memo);

	@Update
	public int update(Memo memo);
	
}
