package com.hacknife.retrofit.fileconverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class FileResponseBodyConverter implements Converter<ResponseBody, File> {
    private File directory;
    private String fileName;

    public FileResponseBodyConverter(File directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
    }

    @Override
    public File convert(ResponseBody body) throws IOException {
        InputStream inputStream = body.byteStream();
        File file = new File(directory, fileName);
        OutputStream outputStream = new FileOutputStream(file);
        byte[] fileReader = new byte[4096];
        while (true) {
            int read = inputStream.read(fileReader);
            if (read == -1) break;
            outputStream.write(fileReader, 0, read);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
        return file;
    }
}
