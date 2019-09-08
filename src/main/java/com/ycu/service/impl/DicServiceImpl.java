package com.ycu.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ycu.bean.Dic;
import com.ycu.dao.DicDao;
import com.ycu.service.DicService;

@Service
public class DicServiceImpl implements DicService {
    
    @Resource
    private DicDao dicDao;
    
    @Override
    public List<Dic> getListByType(String type) {
		Dic dic = new Dic();
		dic.setType(type);
		return dicDao.select(dic);
    }
}
