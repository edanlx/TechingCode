package com.example.demo.util.exmaple.apache;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class CompressExample {
    public static void main(String[] args) throws Exception {
        // http://commons.apache.org/proper/commons-compress/examples.html
        // zip();
        // unZip();
    }
    public static void zip() throws Exception
    {
        File zipFile = new File("/Users/seal/Downloads/test.zip");
        ArchiveOutputStream stream = new ZipArchiveOutputStream(zipFile);
        File[] files = new File("/Users/seal/Downloads/UI").listFiles();
        for (File file : files)
        {
            InputStream in = new FileInputStream(file);
            ArchiveEntry entry = new ZipArchiveEntry(file, file.getName());
            // 添加一个条目
            stream.putArchiveEntry(entry);
            IOUtils.copy(in, stream);
            // 结束
            stream.closeArchiveEntry();
            in.close();
        }
        stream.finish();
        stream.close();
    }

    public static void unZip() throws Exception
    {
        InputStream stream = new FileInputStream("/Users/seal/Downloads/jihuoma.zip");
        ArchiveInputStream inputStream = new ZipArchiveInputStream(stream);
        FileUtils.forceMkdir(new File("/Users/seal/Downloads/jihuoma/1/"));
        ArchiveEntry entry = null;
        while ((entry = inputStream.getNextEntry()) != null)
        {
            System.out.println(entry.getName());
            try (FileOutputStream outputStream = new FileOutputStream("/Users/seal/Downloads/jihuoma/1/"+entry.getName())) {
                IOUtils.copy(inputStream, outputStream);
            }
        }
        inputStream.close();
        stream.close();
    }
}
