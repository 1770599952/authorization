package com.ycu.dao;

import java.util.List;

import com.ycu.bean.Dic;

public interface DicDao {
    List<Dic> select(Dic dic);
}