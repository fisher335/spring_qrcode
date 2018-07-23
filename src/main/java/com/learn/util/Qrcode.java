package com.learn.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.Hashtable;

/**
 * Created by fengshaomin on 2018/7/20 0020.
 */
@Component
public class Qrcode {

    @Value("${web.upload-path}")
    private String upload_path;


    public String create_qrcode(String text) throws IOException {
        String file_name = String.valueOf(Math.random()*1000000);
        int width = 300;
        int height = 300;
        String format = "png";
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            file_name = file_name + ".png";
            String file_path = upload_path + File.separator+"qrcode"+File.separator+ file_name;

            Path file = new File(file_path).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            return file_name;
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error";
    }

    public void download(HttpServletResponse res, String file_name) throws UnsupportedEncodingException {

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file_name, "UTF-8"));

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(upload_path +File.separator+"qrcode"+File.separator+ file_name
            )));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
