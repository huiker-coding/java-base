package com.example.aopannotaion.dao;

import com.example.aopannotaion.core.Select;

public interface PersonDao {

    @Select(sql = "select name from tbl_person where id=?")
    String findNameById(int id);
}
