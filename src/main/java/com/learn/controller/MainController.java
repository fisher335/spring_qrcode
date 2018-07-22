package com.learn.controller;

import com.learn.util.FileUpload;
import com.learn.util.Qrcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by fengshaomin on 2018/7/20 0020.
 */

@Controller
@RequestMapping(value = "/")
public class MainController {
    @Autowired
    private Qrcode qrcode;
    @Autowired
    private FileUpload fileupload;


    @RequestMapping(value = "/")
    public String hello() {
        return "index";
    }

    @RequestMapping(value = "/qrcode")
    public String Qrcode() {
        return "index";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/qrcode", method = RequestMethod.POST)
    public String qrcode(Model map, HttpServletRequest request) throws IOException {

        String url = request.getParameter("url");

        String fire_name = qrcode.create_qrcode(url);

        map.addAttribute("file", fire_name);
        return "qrcode";
    }

    @RequestMapping(value = "/download/{file_name}")
    public void download_img(@PathVariable String file_name, HttpServletResponse res) throws UnsupportedEncodingException {
        qrcode.download(res, file_name);
    }

    @GetMapping(value = "/upload")
    public String upload_file_get() {
        return "upload";
    }

    @PostMapping(value = "/upload")
    public String upload_file_post(@RequestParam(value = "file") MultipartFile file) throws IOException {

        String file_name = file.getOriginalFilename();
        System.out.println(file_name);
        fileupload.save_upload_file(file.getBytes(), file_name);
        return "redirect:https://www.fengshaomin.com/file";
    }

    @RequestMapping(value = "/file")
    public String list_file(Model map) {

        List<String> result = fileupload.get_filelist(false);
        map.addAttribute("file", result);
        return "file";
    }

    @RequestMapping(value = "/zhuang")
    public String zhuang(Model map) {

        map.addAttribute("name", "冯文韬");
        map.addAttribute("tel", "15110202919");
        map.addAttribute("addr", "海淀区厢黄旗东路柳浪家园南里26号楼1单元701");
        return "zhuang";
    }

}
