package com.peche.app.utility;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

@Component
public class Utility {

    public byte[] compressPayload(byte[] payload) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(payload);
            gzipOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String searchRegex(String words) {
        String[] keyWords = words.split("\\s+");
        StringBuilder regexBuilder = new StringBuilder();

        for (String word : keyWords) {
            if (regexBuilder.length()>0) {
                regexBuilder.append(".*");
            }
            regexBuilder.append(word);
        }

        return regexBuilder.toString();
    }
}
