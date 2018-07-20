package com.learn.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Hashtable;

/**
 * Created by fengshaomin on 2018/7/20 0020.
 */
public class Qrcode {

    public  static  String create_qrcode(String text) throws IOException {
        text = text.replace("http://", "");
        text = text.replace("https://", "");
        text = text.replace("*", "");
        text = text.replace("#", "");
        text = text.replace(".", "");
        text = text.replace("!", "");
        int width = 300;
        int height = 300;
        String format = "png";
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            String file_name = text + ".png";
            String pathPrefix = ResourceUtils.getURL("classpath:").getPath();
            String file_path = String.format(pathPrefix+"/static/qrcode/%s", file_name);
            System.out.println(file_path);
            Path file = new File(file_path).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            return file_name;
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error";
    }

}
