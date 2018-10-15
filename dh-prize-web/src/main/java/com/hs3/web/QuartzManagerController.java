package com.hs3.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hs3.entity.sys.SysClear;
import com.hs3.service.quartz.QuartzManagerService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * program: java-kernal
 * des:
 * author: Terra
 * create: 2018-07-04 10:43
 **/
@RestController
@RequestMapping("/api/quartz/manager")
public class QuartzManagerController {

    @Autowired
    private QuartzManagerService quartzManagerService;


    @RequestMapping(value = "/addJob", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void addJob(String jobName, String claName, String time) {
        Class cls = null;
        try {
            cls = Class.forName(claName);
            quartzManagerService.addJob(jobName, cls, time);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addJobCron", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void addJobCron(String jobName, String groupName, String cronExpression, String jobClassName) {
        Class cls = null;
        try {
            cls = Class.forName(jobClassName);
            quartzManagerService.addJob(jobName, groupName, cronExpression, cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addJobTrigger", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void addJobTrigger(String jobGroupName, String jobName, String triggerGroupName, String triggerName, String jobClassName, String time) {
        Class cls = null;
        try {
            cls = Class.forName(jobClassName);
            quartzManagerService.addJob(jobGroupName, jobName, triggerGroupName, triggerName, cls, time);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addJobTriggerEx", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void addJobTriggerEx(String jobGroupName, String jobName, String triggerGroupName, String triggerName, String jobClassName, String time, String map) {
        Class cls = null;
        try {
            Map<String,Object> mapObj = JSONObject.parseObject(map, Map.class);
            Object sysClearObj = mapObj.get("sys_clear");
            if (sysClearObj != null) {
                SysClear sysClear = JSONObject.parseObject(JSON.toJSONString(mapObj.get("sys_clear")),SysClear.class);
                mapObj.put("sys_clear", sysClear);
            }
            cls = Class.forName(jobClassName);
            quartzManagerService.addJobEx(jobGroupName, jobName, triggerGroupName, triggerName, cls, time, mapObj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/modifyJobTimeEx", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void modifyJobTimeEx(String jobName, String trigggername, String time) {
        quartzManagerService.modifyJobTimeEx(jobName, trigggername, time);
    }

    @RequestMapping(value = "/modifyJobTime", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void modifyJobTime(String triggerGroupName, String triggerName, String time) {
        quartzManagerService.modifyJobTime(triggerGroupName, triggerName, time);
    }

    @RequestMapping(value = "/removeJob", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void removeJob(String jobName) {
        quartzManagerService.removeJob(jobName);
    }

    @RequestMapping(value = "/removeJobWithGroup", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void removeJobWithGroup(String jobName, String groupName) {
        quartzManagerService.removeJob(jobName, groupName);
    }

    @RequestMapping(value = "/removeJobWithTrigger", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void removeJobWithTrigger(String jobGroupName, String jobName, String triggerGroupName, String triggerName) {
        quartzManagerService.removeJob(jobGroupName, jobName, triggerGroupName, triggerName);
    }

    @RequestMapping(value = "/startJobs", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void startJobs() {
        quartzManagerService.startJobs();
    }

    @RequestMapping(value = "/shutdownJobs", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void shutdownJobs() {
        quartzManagerService.shutdownJobs();
    }

    @RequestMapping(value = "/pauseJob", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void pauseJob(String groupName, String name)
            throws SchedulerException {
        quartzManagerService.setPauseJob(groupName, name);
    }

    @RequestMapping(value = "/pauseTrigger", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void pauseTrigger(String triggerGroup, String triggerName) {
        quartzManagerService.setPauseTrigger(triggerGroup, triggerName);
    }

    @RequestMapping(value = "/resumeJob", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void resumeJob(String groupName, String name)
            throws SchedulerException {
        quartzManagerService.setResumeJob(groupName, name);
    }

    @RequestMapping(value = "/resumeTrigger", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void resumeTrigger(String triggerGroup, String triggerName) {
        quartzManagerService.setResumeTrigger(triggerGroup, triggerName);
    }
}
