package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return dao.selectById(id).orElse(new Memo());
    }

    @PostMapping
    Memo create(@RequestParam String content) {
        Memo entity = new Memo();
        entity.content = content;
        dao.insert(entity);
        return entity;
    }

    @PostMapping("{id}")
    void update(@PathVariable int id, @RequestParam String content) {
        dao.selectById(id).ifPresent(entity -> {
            entity.content = content;
            dao.update(entity);
        });
    }
}
