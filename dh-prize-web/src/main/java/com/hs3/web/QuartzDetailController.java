package com.hs3.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hs3.entity.sys.SysClear;
import com.hs3.service.quartz.QuartzService;
import com.hs3.utils.DateUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * program: java-kernal
 * des:
 * author: Terra
 * create: 2018-07-04 10:40
 **/
@RestController
@RequestMapping("/api/quartz/job")
public class QuartzDetailController {

    @Autowired
    private QuartzService quartzService;

    @RequestMapping(value = "/addJobCron", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void addJobCron(String jobName, String groupName, String cronExpression, String jobClassName) {
        Class cls = null;
        try {
            cls = Class.forName(jobClassName);
            quartzService.addJob(jobName, groupName, cronExpression, cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addNewJobCron", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void addNewJobCron(String jobName, String groupName, String cronExpression, String jobClassName, String map) {
        Class cls = null;
        try {
            Map<String, Object> mapObj = JSONObject.parseObject(map, Map.class);
            Object sysClearObj = mapObj.get("sys_clear");
            if (sysClearObj != null) {
                SysClear sysClear = JSONObject.parseObject(JSON.toJSONString(mapObj.get("sys_clear")),SysClear.class);
                mapObj.put("sys_clear", sysClear);
            }
            cls = Class.forName(jobClassName);
            quartzService.addNewJob(jobName, groupName, cronExpression, cls, mapObj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addJobWithStartEnd", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void addJobWithStartEnd(String jobName, String groupName, String map, String begin, String end, int intervalInSeconds, String jobClassName) {
        Date beginDate = null, endDate = null;
        Class cls = null;
        try {
            try {
                beginDate = DateUtils.toDate(begin, "yyyy-MM-dd HH:mm:ss");
                endDate = DateUtils.toDate(end, "yyyy-MM-dd HH:mm:ss");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Map<String, Object> mapObj = JSONObject.parseObject(map, Map.class);
            Object sysClearObj = mapObj.get("sys_clear");
            if (sysClearObj != null) {
                SysClear sysClear = JSONObject.parseObject(JSON.toJSONString(mapObj.get("sys_clear")),SysClear.class);
                mapObj.put("sys_clear", sysClear);
            }
            cls = Class.forName(jobClassName);
            quartzService.addJob(jobName, groupName, mapObj, beginDate, endDate, intervalInSeconds, cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addNewJobWithStartEnd", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public boolean addNewJobWithStartEnd(String jobName, String groupName, String map, String begin, String end, int intervalInSeconds, String jobClassName) {
        Date beginDate = null, endDate = null;
        Class cls = null;
        try {
            try {
                beginDate = DateUtils.toDate(begin, "yyyy-MM-dd HH:mm:ss");
                endDate = DateUtils.toDate(end, "yyyy-MM-dd HH:mm:ss");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Map<String, Object> mapObj = JSONObject.parseObject(map, Map.class);
            Object sysClearObj = mapObj.get("sys_clear");
            if (sysClearObj != null) {
                SysClear sysClear = JSONObject.parseObject(JSON.toJSONString(mapObj.get("sys_clear")),SysClear.class);
                mapObj.put("sys_clear", sysClear);
            }
            cls = Class.forName(jobClassName);
            return quartzService.addNewJob(jobName, groupName, mapObj, beginDate, endDate, intervalInSeconds, cls);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/pauseJob", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void pauseJob(String jobName, String groupName)
            throws SchedulerException {
        quartzService.setPauseJob(jobName, groupName);
    }

    @RequestMapping(value = "/resumeJob", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void resumeJob(String jobName, String groupName)
            throws SchedulerException {
        quartzService.setResumeJob(jobName, groupName);
    }

    @RequestMapping(value = "/deleteJob", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteJob(String jobName, String groupName) {
        quartzService.deleteJob(jobName, groupName);
    }

    @RequestMapping(value = "/getCurrentlyExecutingNums", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int getCurrentlyExecutingNums() {
        return quartzService.getCurrentlyExecutingNums();
    }

    @RequestMapping(value = "/isStarting", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public boolean isStarting() {
        return quartzService.isStarting();
    }

    @RequestMapping(value = "/startJobs", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int startJobs() {
        return quartzService.startJobs();
    }

    @RequestMapping(value = "/shutdownJobs", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int shutdownJobs() {
        return quartzService.shutdownJobs();
    }


}
