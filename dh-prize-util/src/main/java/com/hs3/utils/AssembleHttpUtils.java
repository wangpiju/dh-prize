package com.hs3.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.IllegalFormatFlagsException;
import java.util.Map;

/**
 * Created by zjh on 2016/12/9.
 */
public class AssembleHttpUtils {

    protected static final Logger log = LoggerFactory.getLogger(AssembleHttpUtils.class);

    private static AssembleHttpUtils assembleHttpUtils;
    public static AssembleHttpUtils instance() {
        if (assembleHttpUtils == null) {
            assembleHttpUtils= new AssembleHttpUtils();
        }
        return assembleHttpUtils;
    }

    public enum SendReq {
        quartz_addJobCron("/api/quartz/job/addJobCron", HttpMethod.POST, ImmutableList.of(
                "jobName", "groupName", "cronExpression", "jobClassName"), "添加任务"),
        quartz_addNewJobCron("/api/quartz/job/addNewJobCron", HttpMethod.POST, ImmutableList.of(
                "jobName", "groupName", "cronExpression", "jobClassName", "map"), "添加任务"),
        quartz_addJobWithStartEnd("/api/quartz/job/addJobWithStartEnd", HttpMethod.POST, ImmutableList.of(
                "jobName", "groupName", "map", "begin", "end", "intervalInSeconds", "jobClassName"), "添加任务"),
        quartz_addNewJobWithStartEnd("/api/quartz/job/addNewJobWithStartEnd", HttpMethod.GET, ImmutableList.of(
                "jobName", "groupName", "map", "begin", "end", "intervalInSeconds", "jobClassName"), "添加任务"),
        quartz_pauseJob("/api/quartz/job/pauseJob", HttpMethod.GET, ImmutableList.of("jobName", "groupName"), "停止任务"),
        quartz_resumeJob("/api/quartz/job/resumeJob", HttpMethod.POST, ImmutableList.of("jobName", "groupName"), "重新开启任务"),
        quartz_deleteJob("/api/quartz/job/deleteJob", HttpMethod.POST, ImmutableList.of("jobName", "groupName"), "删除定时任务"),
        quartz_getCurrentlyExecutingNums("/api/quartz/job/getCurrentlyExecutingNums", HttpMethod.POST, "获取当前正在运行的任务"),
        quartz_isStarting("/api/quartz/job/isStarting", HttpMethod.POST, "判断任务是否开启"),
        quartz_startJobs("/api/quartz/job/startJobs", HttpMethod.POST, "开启所有任务"),
        quartz_shutdownJobs("/api/quartz/job/shutdownJobs", HttpMethod.POST, "停止所有任务"),


        quartz_manager_addJob("/api/quartz/manager/addJob", HttpMethod.POST, ImmutableList.of(
                "jobName", "claName", "time"), "添加定时任务"),
        quartz_manager_addJobCron("/api/quartz/manager/addJobCron", HttpMethod.POST, ImmutableList.of(
                "jobName", "groupName", "cronExpression", "jobClassName"), "添加定时任务"),
        quartz_manager_addJobTrigger("/api/quartz/manager/addJobTrigger", HttpMethod.POST, ImmutableList.of(
                "jobGroupName", "jobName", "triggerGroupName", "triggerName", "jobClassName", "time"), "添加定时任务"),
        quartz_manager_addJobTriggerEx("/api/quartz/manager/addJobTriggerEx", HttpMethod.POST, ImmutableList.of(
                "jobGroupName", "jobName", "triggerGroupName", "triggerName", "jobClassName", "time", "map"), "添加定时任务"),
        quartz_manager_modifyJobTimeEx("/api/quartz/manager/modifyJobTimeEx", HttpMethod.POST, ImmutableList.of(
                "jobName", "triggerName", "time"), "修改定时任务"),
        quartz_manager_modifyJobTime("/api/quartz/manager/modifyJobTime", HttpMethod.POST, ImmutableList.of(
                "triggerGroupName", "triggerName", "time"), "修改定时任务"),
        quartz_manager_removeJobWithName("/api/quartz/manager/removeJobWithName", HttpMethod.POST, ImmutableList.of("jobName"), "删除定时任务"),
        quartz_manager_removeJobWithGroup("/api/quartz/manager/removeJobWithGroup", HttpMethod.POST, ImmutableList.of(
                "jobName", "groupName"), "删除定时任务"),
        quartz_manager_removeJobWithTrigger("/api/quartz/manager/removeJobWithTrigger", HttpMethod.POST, ImmutableList.of(
                "jobGroupName", "jobName", "triggerGroupName", "triggerName"), "删除定时任务"),
        quartz_manager_startJobs("/api/quartz/manager/startJobs", HttpMethod.POST, "开始所有定时任务"),
        quartz_manager_shutdownJobs("/api/quartz/manager/shutdownJobs", HttpMethod.POST, "停止所有定时任务"),
        quartz_manager_pauseJob("/api/quartz/manager/pauseJob", HttpMethod.POST, ImmutableList.of("groupName", "name"), "停止定时任务"),
        quartz_manager_pauseTrigger("/api/quartz/manager/pauseTrigger", HttpMethod.POST, ImmutableList.of(
                "triggerGroup", "triggerName"), "停止定时任务"),
        quartz_manager_resumeJob("/api/quartz/manager/resumeJob", HttpMethod.POST, ImmutableList.of(
                "groupName", "name"), "重启定时任务"),
        quartz_manager_resumeTrigger("/api/quartz/manager/resumeTrigger", HttpMethod.POST, ImmutableList.of(
                "triggerGroup", "triggerName"), "重启定时任务"),
        get_taobao_ip("/service/getIpInfo.php", HttpMethod.GET, ImmutableList.of("ip"), "获取第三方库的ip详情");


        protected String url;
        protected HttpMethod method;
        protected String paramStr;
        protected ImmutableList<String> params;
        protected ImmutableList<String> headers;
        protected String description;

        public static Map<String,Object> defaultHeader=new HashMap<>();
        static {
            defaultHeader.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            defaultHeader.put("Accept-Encoding","gzip, deflate");
            defaultHeader.put("Accept-Language","zh-CN,zh;q=0.9");
            defaultHeader.put("Cache-Control","max-age=0");
            defaultHeader.put("Connection","keep-alive");
            defaultHeader.put("Cookie","t=d024c5197908fdb8a5f538a4c895e5d; thw=ph; cna=I3SzExJRUToCAXN9sfwiBZ4; hng=GLOBAL%7Czh-CN%7CUSD%7C99; isg=BNnZ9IYZ5gmJD7rl8lF9yeMX6MW5f8WNQjb_uOB4B_AvmUQ7bd6EcTAIbRumVQ");
            defaultHeader.put("Host","ip.taobao.com");
            defaultHeader.put("Upgrade-Insecure-Requests","1");
            defaultHeader.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        }

        SendReq(String url, HttpMethod method, String paramStr, ImmutableList<String> headers, String description) {
            this.url = url;
            this.method = method;
            this.paramStr = paramStr;
            this.headers = headers;
            this.description = description;
        }

        SendReq(String url, HttpMethod method, ImmutableList<String> params, String description) {
            this.url = url;
            this.method = method;
            this.params = params;
            this.description = description;
        }

        SendReq(String url, HttpMethod method, String description) {
            this.url = url;
            this.method = method;
            this.params = params;
            this.description = description;
        }

        public enum HttpMethod {
            POST,
            GET;
        }
    }


    public ResponseData execRequest(String serverUrl, SendReq req, Object[] args, Object[] argHeaders) {

        Map<String, Object> params = convertParams(req.params, args);
        Map<String, Object> headers = convertParams(req.headers, argHeaders);
        ResponseData result;
        String url = serverUrl + req.url;
        try {
            switch (req.method) {
                case POST:
                    result = HttpClientUtil.buildPost(url, params, headers);
                    break;
                default:
                    result = HttpClientUtil.buildGet(url, params, headers);
                    break;
            }
            return result;
        } catch (Exception e) {
            log.error(req.method + " request robot error, url: " + url + " params: " + JSONObject.toJSONString(params), e);
        }
        return null;
    }


    public ResponseData execRequest(String serverUrl, SendReq req, Map<String, Object> params) {
        ResponseData result;
        String url = serverUrl + req.url;
        try {
            switch (req.method) {
                case POST:
                    result = HttpClientUtil.buildPost(url, params, null);
                    break;
                default:
                    result = HttpClientUtil.buildGet(url, params, null);
                    break;
            }
            return result;
        } catch (Exception e) {
            log.error(req.method + " request robot error, url: " + url + " params: " + JSONObject.toJSONString(params), e);
        }
        return null;
    }

    public ResponseData execRequest(String serverUrl, SendReq req, Object[] args) {
        Map<String, Object> params = convertParams(req.params, args);
        ResponseData result;
        String url = serverUrl + req.url;
        try {
            switch (req.method) {
                case POST:
                    result = HttpClientUtil.buildPost(url, params, null);
                    break;
                default:
                    result = HttpClientUtil.buildGet(url, params, null);
                    break;
            }
            return result;
        } catch (Exception e) {
            log.error(req.method + " request robot error, url: " + url + " params: " + JSONObject.toJSONString(params), e);
        }
        return null;
    }

    public ResponseData execRequest(String serverUrl, SendReq req, Object[] args,Map<String,Object> headers) {
        Map<String, Object> params = convertParams(req.params, args);
        ResponseData result;
        String url = serverUrl + req.url;
        try {
            switch (req.method) {
                case POST:
                    result = HttpClientUtil.buildPost(url, params, headers);
                    break;
                default:
                    result = HttpClientUtil.buildGet(url, params, headers);
                    break;
            }
            return result;
        } catch (Exception e) {
            log.error(req.method + " request robot error, url: " + url + " params: " + JSONObject.toJSONString(params), e);
        }
        return null;
    }

    public ResponseData execRequest(String serverUrl, SendReq req, String paramStr, Object[] argHeaders) {
        Map<String, Object> headers = convertParams(req.headers, argHeaders);
        ResponseData result = null;
        String url = serverUrl + req.url;
        try {
            switch (req.method) {
                case POST:
                    result = HttpClientUtil.buildPost(url, paramStr, headers);
                    break;
            }

            return result;
        } catch (Exception e) {
            log.error(req.method + " request robot error, url: " + url + " params: " + paramStr, e);
        }
        return null;
    }


    private static Map<String, Object> convertParams(ImmutableList<String> keys, Object[] values) {
        if (keys != null && keys.size() > 0) {
            if (keys.size() == values.length) {
                Map<String, Object> params = Maps.newHashMap();
                for (int i = 0; i < keys.size(); i++) {
                    if (values[i] != null) {
                        params.put(keys.get(i), values[i]);
                    }
                }
                return params;
            } else {
                throw new IllegalFormatFlagsException("Request key's size does not match with values's size ,keys : " + keys.toString() + ", values : " + JSONObject.toJSONString(values));
            }
        }
        return new HashMap<>();
    }


}
