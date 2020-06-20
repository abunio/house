package com.house.quartz.contoller;

import com.house.quartz.base.AjaxResult;
import com.house.quartz.base.BaseController;
import com.house.quartz.entity.SysJob;
import com.house.quartz.service.ISysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.house.quartz.base.AjaxResult.error;
import static com.house.quartz.base.AjaxResult.success;

/**
 * 调度任务信息操作处理
 * 
 * @author numberone
 */
@Controller
@RequestMapping("/monitor/job")
public class SysJobController extends BaseController

{
    private String prefix = "monitor/job";

    @Autowired
    private ISysJobService jobService;

    @GetMapping()
    public String job()
    {
        return prefix + "/job";
    }

    /*@PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysJob job)
    {
        startPage();
        List<SysJob> list = jobService.selectJobList(job);
        return getDataTable(list);
    }*/

    /*@PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysJob job)
    {
        List<SysJob> list = jobService.selectJobList(job);
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        return util.exportExcel(list, "定时任务");
    }*/

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            jobService.deleteJobByIds(ids);
            return success();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return error(e.getMessage());
        }
    }

    @GetMapping("/detail/{jobId}")
    public String detail(@PathVariable("jobId") Long jobId, ModelMap mmap)
    {
        mmap.put("name", "job");
        mmap.put("job", jobService.selectJobById(jobId));
        return prefix + "/detail";
    }

    /**
     * 任务调度状态修改
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysJob job)
    {
        job.setUpdateBy("");
        return toAjax(jobService.changeStatus(job));
    }

    /**
     * 任务调度立即执行一次
     */
    @PostMapping("/run")
    @ResponseBody
    public AjaxResult run(Long jobId)
    {
        return toAjax(jobService.run(jobId));
    }

    /**
     * 新增调度
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存调度
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysJob job)
    {
        job.setCreateBy("");
        return toAjax(jobService.insertJobCron(job));
    }

    /**
     * 修改调度
     */
    @GetMapping("/edit/{jobId}")
    public String edit(@PathVariable("jobId") Long jobId, ModelMap mmap)
    {
        mmap.put("job", jobService.selectJobById(jobId));
        return prefix + "/edit";
    }

    /**
     * 修改保存调度
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysJob job)
    {
        job.setUpdateBy("");
        return toAjax(jobService.updateJobCron(job));
    }
    
    /**
     * 校验cron表达式是否有效
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(SysJob job)
    {
        return jobService.checkCronExpressionIsValid(job.getCronExpression());
    }
}
