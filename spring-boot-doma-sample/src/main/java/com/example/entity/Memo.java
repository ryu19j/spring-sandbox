package com.example.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Memo {
    @Id
    public Integer id;
    public String content;
}
