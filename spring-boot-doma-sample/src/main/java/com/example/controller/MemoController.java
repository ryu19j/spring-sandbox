package com.example.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.MemoDao;
import com.example.entity.Memo;

@RestController
@RequestMapping("/memo")
public class MemoController {

	@Autowired
    private MemoDao dao;

    @GetMapping("{id}")
    public Memo getMemo(@PathVariable int id) {
        return dao.selectById(id);
    }

}
