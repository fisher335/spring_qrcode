package com.learn.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Component
public class FileUpload {
    @Value("${web.upload-path}")
    private String upload_path;

    public void save_upload_file(byte[] file, String file_name) throws IOException {

        FileOutputStream out = new FileOutputStream(upload_path + File.separator + "upload" + File.separator + file_name);
        out.write(file);
        out.flush();
        out.close();
    }

    public List<String> get_filelist(boolean isAddDirectory) {

        String directoryPath = upload_path+ File.separator+"upload";
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
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    return m.replaceAll("").trim();
}
}
