package com.example.demo.util.example.apache;

import org.apache.commons.io.EndianUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class IOExample {
    public static void main(String[] args) throws IOException {
        // 非常好用的三个方法
        new FileUtils(); //拷贝文件等操作
        new IOUtils();
        new FilenameUtils();

        // 不常用
        new EndianUtils();
        // File file = new File("");
        // 获取后缀名
        System.out.println(FilenameUtils.getExtension("file.txt"));
        //格式化路径
        String normalize = FilenameUtils.normalize("D:" + File.separator + "data.txt");
        System.out.println(normalize);
        //转unix分隔符
        System.out.println(FilenameUtils.separatorsToUnix("D:" + File.separator + "data.txt"));
        System.out.println(FilenameUtils.separatorsToWindows("D:" + File.separator + "data.txt"));

        //目录分隔符
        System.out.println(IOUtils.DIR_SEPARATOR);
        System.out.println(IOUtils.DIR_SEPARATOR_UNIX);
        System.out.println(IOUtils.DIR_SEPARATOR_WINDOWS);
        System.out.println(IOUtils.LINE_SEPARATOR);
        System.out.println(IOUtils.LINE_SEPARATOR_UNIX);
        System.out.println(IOUtils.LINE_SEPARATOR_WINDOWS);
        test1();
    }

    public static void test1() throws IOException {
        // try with 写法
        try (
                InputStream is = IOUtils.toInputStream("This is a String", "utf-8");
                OutputStream os = new FileOutputStream("/Users/seal/IdeaProjects/demo/src/main/java/com/example/demo/example/test2.txt");
        ) {
            //copy流
            int bytes = IOUtils.copy(is, os);
            System.out.println("File Written with " + bytes + " bytes");
            // 读文件为string
            System.out.println(IOUtils.toString(new FileReader("/Users/seal/IdeaProjects/demo/src/main/java/com/example/demo/example/test2.txt")));
        } catch (Exception e) {

        }

        //读
        try (FileInputStream fin = new FileInputStream("test2.txt")) {
            List ls = IOUtils.readLines(fin, "utf-8");
            for (int i = 0; i < ls.size(); i++) {
                System.out.println(ls.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //写
        List<String> ls = new ArrayList<>();
        ls.add("asdasd");
        ls.add("ada21");
        ls.add("addsf");
        try (OutputStream os = new FileOutputStream("test3.txt")) {
            IOUtils.writeLines(ls, IOUtils.LINE_SEPARATOR_WINDOWS, os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读网站
        InputStream in = new URL("http://commons.apache.org").openStream();
        try {
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader buf = new BufferedReader(inR);
            String line;
            while ((line = buf.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            in.close();
        }
    }
}
