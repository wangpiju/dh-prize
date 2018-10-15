package com.re.test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * program: dh-prize
 * des:
 * author: Terra
 * create: 2018-08-09 11:51
 **/

public class ReTest {

    @Test
    public void matchCrawNum() {
        String reg = "\"expect\":\"(?<index>\\d{10})\",\"opencode\":\"(?<n1>\\d{2}),(?<n2>\\d{2}),(?<n3>\\d{2}),(?<n4>\\d{2}),(?<n5>\\d{2})\"";
        String result = "{\"rows\":1,\"code\":\"ah11x5\",\"remain\":\"6216hrs\",\"data\":[{\"expect\":\"2018080918\",\"opencode\":\"05,07,10,02,03\",\"opentime\":\"2018-08-09 11:31:41\",\"opentimestamp\":1533785501}]}";
        System.out.println(reg);
        System.out.println(result);

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(result);
        matcher.find();
        String index = "";
        index = matcher.group("index");
        System.out.println("index" + index);
        System.out.println(matcher.group("index"));
        System.out.println(matcher.group("n1"));
        System.out.println(matcher.group("n2"));
        System.out.println(matcher.group("n3"));
        System.out.println(matcher.group("n4"));
        System.out.println(matcher.group("n5"));
    }
}
