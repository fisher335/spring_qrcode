package com.learn.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.learn.util.OcrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.element.NestingKind;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AppendController {

    @Autowired
    private HttpServletRequest myHttpRequest;

    @Autowired
    private HttpServletResponse myHttpResponse;

    @Autowired
    private OcrUtil ocrUtil;


    @RequestMapping(value = "/wiki/")
    public void get_wiki() throws IOException {

        myHttpResponse.sendRedirect("http://www.baidu.com");
    }


    @GetMapping(value = "/ocr")
    public String get_ocr() {
        return "ocr";
    }

    @PostMapping(value = "/ocr/")
    public String post_ocr(@RequestParam("file") MultipartFile file, Model map) throws IOException {


        String result = ocrUtil.getOcr(file.getBytes());

        JSONObject js = JSONUtil.parseObj(result);
        StaticLog.info(js.toString());

        JSONArray jsarray = js.getJSONArray("words_result");

        String result_ocr = "";
        for (int i = 0; i < jsarray.size(); i++) {
            JSONObject js_word = JSONUtil.parseObj( jsarray.get(i));

            result_ocr = result_ocr + js_word.get("words")+"<br>";
        }
        map.addAttribute("ocr", result_ocr);

        return "ocr";
    }

    @ResponseBody
    @RequestMapping(value = "/es")
    public Map<String,String> cription(@RequestParam("un")  String un, @RequestParam("pd")  String pd  ) throws IOException {

        String auth = String.format("%s:%s",un,pd);
        String auth1 = baseCry(auth);

        String headerAuth = "Authorization: Basic "+auth1;
        String authString = baseCry(headerAuth);
        Map<String,String> rst = new HashMap<>();
        rst.put("header", headerAuth);
        rst.put("stringAuth",authString);
        return rst;
    }

    public String baseCry(String s) throws UnsupportedEncodingException {
        String r = new String(Base64.getEncoder().encode(s.getBytes("utf-8")),"utf-8");
        return r;
    }
}
