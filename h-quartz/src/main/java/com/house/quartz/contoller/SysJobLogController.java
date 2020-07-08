package com.house.quartz.contoller;


import com.house.common.base.controller.BaseController;
import com.house.common.base.entity.AjaxResult;
import com.house.quartz.service.ISysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


/**
 * 调度日志操作处理
 *
 * @author numberone
 */
@Controller
@RequestMapping("/monitor/jobLog")
public class SysJobLogController extends BaseController {
    private String prefix = "monitor/job";

    @Autowired
    private ISysJobLogService jobLogService;

    @GetMapping()
    public String jobLog() {
        return prefix + "/jobLog";
    }

   /* @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysJobLog jobLog) {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        return getDataTable(list);
    }*/

    /*@PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysJobLog jobLog) {
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        ExcelUtil<SysJobLog> util = new ExcelUtil<SysJobLog>(SysJobLog.class);
        return util.exportExcel(list, "调度日志");
    }*/

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(jobLogService.deleteJobLogByIds(ids));
    }

    @GetMapping("/detail/{jobLogId}")
    public String detail(@PathVariable("jobLogId") Long jobLogId, ModelMap mmap) {
        mmap.put("name", "jobLog");
        mmap.put("jobLog", jobLogService.selectJobLogById(jobLogId));
        return prefix + "/detail";
    }

    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        jobLogService.cleanJobLog();
        return AjaxResult.success();
    }
}
