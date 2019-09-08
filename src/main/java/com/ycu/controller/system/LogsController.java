package com.ycu.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ycu.bean.SysUserLog;
import com.ycu.service.SysUserLogService;

/**
 * Created by Yang on 2018/4/4.
 */
@Controller
@RequestMapping("/log")
public class LogsController {

    @Autowired
    private SysUserLogService sysUserLogService;

    /**
     * 日志列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String search(Model model, SysUserLog dto) {
        return "/content/logList";
    }

}

