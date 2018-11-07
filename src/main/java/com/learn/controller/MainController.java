package com.learn.controller;

import cn.hutool.Hutool;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.LineSeparator;
import cn.hutool.core.util.NetUtil;
import cn.hutool.http.HttpUtil;
import com.learn.util.FileUpload;
import com.learn.util.Qrcode;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.PrinterLocation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.logging.Logger;

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

    @Value("${web.file-path}")
    private String upload_path;

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
    public String Qrcode() {
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
    public void download_img(@PathVariable("file_name") String file_name, HttpServletResponse res) throws UnsupportedEncodingException {
        qrcode.download(res, file_name);
    }

    @GetMapping(value = "/upload")
    public String upload_file_get() {
        return "upload";
    }

    @PostMapping(value = "/upload")
    public String upload_file_post(@RequestParam("file") MultipartFile file) throws IOException {

        String file_name = file.getOriginalFilename();
        File tmp_file = new File(file_name);
        String real_name = tmp_file.getName();
        logger.info("upload file -----------------------" + real_name);
        fileupload.save_upload_file(file.getBytes(), real_name);


        return "redirect:/file";
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

    @RequestMapping(value = "/list")
    public String list_header(HttpServletRequest request, Model map) {
        Enumeration headerList = request.getHeaderNames();
        Map map_tmp = new HashMap<String, String>();
        for (Enumeration e = headerList; e.hasMoreElements(); ) {
            String name = e.nextElement().toString();
            String val = request.getHeader(name);
            map_tmp.put(name, val);
        }
        map.addAttribute("headers", map_tmp);
        return "list";

    }

    @ResponseBody
    @RequestMapping (value = "/test")
    public String get_folder(HttpServletRequest request, Model map) {

        HashMap<String, String> a = new HashMap<>();
        a.put("name", "fengshaomin");
        for (Map.Entry<String, String> one : a.entrySet()) {

            System.out.println(one.getKey()+one.getValue());

        }

        a.forEach((k,v)->{System.out.println(k+v);});

        ArrayList<String> l = new ArrayList<>();
        l.add("fengshaomin");
        l.add("shaomin");
        l.add("haha");
        l.add("haha");



        System.out.println(l);

        System.out.println("--------------------------------------------------------------");

        HashSet s = new HashSet();

        s.addAll(l);

        System.out.println(s);
        l.forEach(item->{System.out.println(item);});
        List<String> li = FileUtil.listFileNames(upload_path);

        String ip = NetUtil.getLocalhost().getHostAddress();
        return Convert.toStr(ip);

    }

}
