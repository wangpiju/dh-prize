package com.hs3.web;

import com.hs.comm.service.lotts.BonusRiskService;
import com.hs3.commons.Constants;
import com.hs3.entity.sys.SysClear;
import com.hs3.service.sys.SysClearService;
import com.hs3.web.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * program: dh-prize
 * des:
 * author: Terra
 * create: 2018-07-25 16:24
 **/
@Controller
@Scope("prototype")
@RequestMapping({"/admin/risk/bonus"})
public class RiskController {

    @Autowired
    private SysClearService sysClearService;
    @Autowired
    private BonusRiskService bonusRiskService;

    /**
     * 添加清除奖池任务
     */
    @ResponseBody
    @RequestMapping(value = {"/add_clear_task"}, method = {RequestMethod.POST})
    public boolean addRiskBonusTask(
            @RequestParam(value = "executeTime", required = false, defaultValue = "0:01:00") String executeTime
    ) {
        SysClear sysClear = new SysClear();
        sysClear.setId(26);
        sysClear.setJob(26);
        sysClear.setCategory("风控奖池清理");
        sysClear.setTitle("风控奖池清理");
        sysClear.setBeforeDays(1);
        sysClear.setBeforeDaysDefault(1);
        sysClear.setExecuteTime(executeTime);
        sysClear.setStatus(0);
        sysClear.setClearMode(0);
        sysClearService.save(sysClear);
        return true;
    }

    /**
     * 修改清除奖池任务
     */
    @ResponseBody
    @RequestMapping(value = {"/update_clear_task"}, method = {RequestMethod.POST})
    public boolean updateRiskBonusTask(
            @RequestParam(value = "executeTime", required = false, defaultValue = "0:01:00") String executeTime,
            @RequestParam(value = "status", required = false, defaultValue = "0") Integer status
    ) {
//        List<Integer> list = new ArrayList<>();
//        list.add(26);

        SysClear sysClear = new SysClear();
        sysClear.setId(26);
        sysClear.setJob(26);
        sysClear.setCategory("风控奖池清理");
        sysClear.setTitle("风控奖池清理");
        sysClear.setBeforeDays(1);
        sysClear.setBeforeDaysDefault(1);
        sysClear.setExecuteTime(executeTime);
        sysClear.setStatus(status);
        sysClear.setClearMode(0);
        int result = sysClearService.update(sysClear);
        return result > 0;
    }

    /**
     * 删除清除奖池任务
     */
    @ResponseBody
    @RequestMapping(value = {"/del_clear_task"}, method = {RequestMethod.POST})
    public boolean delRiskBonusTask(
            @RequestParam(value = "id", required = false, defaultValue = "26") Integer id
    ) {
        List<Integer> list = new ArrayList<>();
        list.add(id);
        int result = sysClearService.delete(list);
        return result > 0;
    }


    @ResponseBody
    @RequestMapping(value = {"/manual_clear_bonus_pool"}, method = {RequestMethod.POST})
    public boolean manualClearBonusPool() {
        bonusRiskService.clearBonusPool();
        return true;
    }
    @ResponseBody
    @RequestMapping(value = {"/manual_init_bonus_pool"}, method = {RequestMethod.POST})
    public boolean manualInitBonusPool() {
        bonusRiskService.initBonusPool();
        return true;
    }



}
