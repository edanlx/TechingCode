package com.example.demo.lesson.grace.apache;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;

public class ExecExample {
    public static void main(String[] args) {
        System.out.println(execCmdWithResult());
    }

    private static String execCmdWithResult() {
        try (//接收正常结果流
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             //接收异常结果流
             ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        ) {
            String command = "ping www.baidu.com";
            CommandLine commandline = CommandLine.parse(command);
            DefaultExecutor exec = new DefaultExecutor();
            exec.setExitValues(null);
            //设置一分钟5秒
            ExecuteWatchdog watchdog = new ExecuteWatchdog(5 * 1000);
            exec.setWatchdog(watchdog);
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
            exec.setStreamHandler(streamHandler);
            exec.execute(commandline);
            //不同操作系统注意编码，否则结果乱码
            String out = outputStream.toString("GBK");
            String error = errorStream.toString("GBK");
            return out + error;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
