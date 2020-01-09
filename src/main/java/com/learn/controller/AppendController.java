package com.learn.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.learn.util.OcrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
