package com.hacknife.retrofit.fileconverter;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class FileConverterFactory extends Converter.Factory {
    private File directory;

    private FileConverterFactory(File directory) {
        this.directory = directory;
        directory.mkdirs();
    }

    public static FileConverterFactory create(File dir) {
        return new FileConverterFactory(dir);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (!type.toString().equalsIgnoreCase(File.class.toString())) return null;
        String fileName = null;
        for (Annotation annotation : annotations) {
            if (!(annotation instanceof FileName)) continue;
            String value = ((FileName) annotation).value();
            if (value.contains("."))
                fileName = value.substring(0, value.lastIndexOf(".")) + "_" + System.currentTimeMillis() + value.substring(value.lastIndexOf("."));
            else
                fileName = value + "_" + System.currentTimeMillis();
            break;
        }
        if (fileName == null) fileName = randomString(32);
        return new FileResponseBodyConverter(directory, fileName);
    }


    private static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
