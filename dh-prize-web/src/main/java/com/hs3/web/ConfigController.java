package com.hs3.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.DataHandler;

/**
 * program: dh-prize
 * des:
 * author: Terra
 * create: 2018-07-18 10:22
 **/
@RestController
@RequestMapping("/api/dh/config")
public class ConfigController {

    @Value("#{prop['dhPrizeHost'] ?:'http://localhost:8081'}")
    private String dhHost;

    @GetMapping("/getDhHost")
    public String getDhHost() {
        return dhHost;
    }
}
