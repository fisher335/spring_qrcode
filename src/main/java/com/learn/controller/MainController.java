package com.learn.controller;

import cn.hutool.http.HttpUtil;
import com.learn.util.FileUpload;
import com.learn.util.Qrcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author fengshaomin
 * @date 2018/7/20 0020
 */

@Controller
@RequestMapping(value = "/")
public class MainController {
    @Autowired
    private Qrcode qrcode;
    @Autowired
    private FileUpload fileupload;

    @Value("${web.file-path}")
    private String uploadPath;

    private Logger logger = Logger.getLogger(getClass().toString());

    @RequestMapping(value = "")
    public String hello() {
        return "index";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/qrcode")
    public String qrcode() {
        return "index";
    }


    @RequestMapping(value = "/qrcode", method = RequestMethod.POST)
    public String qrcode(Model map, HttpServletRequest request) throws IOException {

        String url = request.getParameter("url");

        String fileName = qrcode.create_qrcode(url);

        map.addAttribute("file", fileName);
        return "qrcode";
    }

    @RequestMapping(value = "/download/{file_name}")
    public void downloadImg(@PathVariable("file_name") String fileName, HttpServletResponse res) throws UnsupportedEncodingException {
        qrcode.download(res, fileName);
    }

    @GetMapping(value = "/upload")
    public String uploadFileGet() {
        return "upload";
    }

    @PostMapping(value = "/upload")
    public String uploadFilePost(@RequestParam("file") MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        File tmpFile = new File(fileName);
        String realName = tmpFile.getName();
        logger.info("upload file -----------------------" + realName);
        fileupload.saveUploadFile(file.getBytes(), realName);


        return "redirect:/file";
    }

    @RequestMapping(value = "/file")
    public String listFile(Model map) {

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

    @RequestMapping(value = "/list")
    public String listHeader(HttpServletRequest request, Model map) {
        Enumeration headerList = request.getHeaderNames();
        Map tmpMap = new HashMap<String, String>(16);
        for (Enumeration e = headerList; e.hasMoreElements(); ) {
            String name = e.nextElement().toString();
            String val = request.getHeader(name);
            tmpMap.put(name, val);
        }
        tmpMap.put("ip",request.getRemoteAddr());
        map.addAttribute("headers", tmpMap);
        return "list";

    }

    @ResponseBody
    @RequestMapping(value = "/test")
    public String getFolder(HttpServletRequest request, Model map) {
        String s = HttpUtil.get("http://web.bjsasc.com");
        return s;
    }
}
