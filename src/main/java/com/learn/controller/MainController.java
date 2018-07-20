package com.learn.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fengshaomin on 2018/7/20 0020.
 */

@Controller
@RequestMapping(value = "/")
public class MainController {

    @RequestMapping(value = "/")
    public String hello() {

        return "index";
    }
}
