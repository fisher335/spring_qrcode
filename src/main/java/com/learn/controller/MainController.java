package com.learn.controller;


import com.learn.util.Qrcode;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

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

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/qrcode", method = RequestMethod.POST)
    public String qrcode(Model map, HttpServletRequest request) throws IOException {

        String url = request.getParameter("url");
        System.out.println(url);
        Qrcode.create_qrcode(url);

        map.addAttribute("img","1111");
        return "qrcode";
    }
}
