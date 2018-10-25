package com.learn.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Component
public class FileUpload {
    @Value("${web.file-path}")
    private String upload_path;

    public void save_upload_file(byte[] file, String file_name) throws IOException {

        FileOutputStream out = new FileOutputStream(upload_path + File.separator + file_name);
        out.write(file);
        out.flush();
        out.close();
    }

    public List<String> get_filelist(boolean isAddDirectory) {

        String directoryPath = upload_path;
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory) {
                    list.add(file.getAbsolutePath());
                }
                list.addAll(get_filelist(isAddDirectory));
            } else {
                list.add(file.getName());
            }
        }
        return list;
    }

    // 过滤特殊字符 p
// 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
// 清除掉所有特殊字符
    public static String StringFilter(String str) throws PatternSyntaxException {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public String upload_fold(HttpServletRequest request) {

        String Storage_PATH = upload_path;//文件存储位置

        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = params.getFiles("fileFolder");

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            //遍历文件夹
            for (MultipartFile mf : files) {
                if (!mf.isEmpty()) {     //  byte[] content = mf.getBytes();    //文件内容
                    String filename = mf.getName();
                    System.out.print(filename);
                    String originalFilename = mf.getOriginalFilename();
                    System.out.println(originalFilename);
                    System.out.println(mf.getContentType());
                    System.out.println(mf);
                    //格式限制，非jpg格式的不上传
//                    if (!"jpg".equals(originalFilename.substring(originalFilename.lastIndexOf(".") + 1))) {
//                        continue;
//                    }
                    String fileName = originalFilename.substring(originalFilename.lastIndexOf("/") + 1);
                    //为避免文件同名覆盖，给文件名加上时间戳
                    int index = fileName.lastIndexOf(".");
                    String firstName = fileName.substring(0, index);
                    String lastName = fileName.substring(index);
                    fileName = firstName + "_" + System.currentTimeMillis() + lastName;
                    //读取文件
                    bis = new BufferedInputStream(mf.getInputStream());
                    //指定存储的路径
                    bos = new BufferedOutputStream(new FileOutputStream(Storage_PATH + "/" + fileName));
                    int len = 0;
                    byte[] buffer = new byte[10240];
                    while ((len = bis.read(buffer)) != -1) {
                        bos.write(buffer, 0, len);
                    }
                    //刷新此缓冲的输出流，保证数据全部都能写出
                    bos.flush();
                }
            }
            bis.close();
            bos.close();
            return "ok";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "error";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

    }
}
