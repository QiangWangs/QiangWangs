package com.qiangws.demo.threaddemo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author 王强
 * @create 2022/3/8 17:23
 */
public class Test {
    public static void main(String[] args) throws IOException {
        universalCopy();
        channelBufferCopy();
        channelCopy();

    }
    private static void universalCopy() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\node-2.rar");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\copy\\node-1.rar");
        long current = System.currentTimeMillis();
        int len;
        byte[] byteBuffer = new byte[1024 * 1024];
        while ((len = fileInputStream.read(byteBuffer)) != -1){
            fileOutputStream.write(byteBuffer, 0 ,len);
        }
        fileOutputStream.close();
        fileInputStream.close();
        System.out.println("universalCopy take：" + (System.currentTimeMillis() - current));
    }

    private static void channelBufferCopy() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("E:\\node-2.rar");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\copy\\node-2.rar");
        long current = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        while (fileInputStream.getChannel().read(byteBuffer) != -1){
            byteBuffer.flip();
            fileOutputStream.getChannel().write(byteBuffer);
            byteBuffer.clear();
        }
        fileOutputStream.close();
        fileInputStream.close();
        System.out.println("channelBufferCopy take：" + (System.currentTimeMillis() - current));
    }

    static void channelCopy() throws IOException{
        FileInputStream fileInputStream = new FileInputStream("E:\\node-2.rar");
        FileChannel fromChannel = fileInputStream.getChannel();
        FileChannel toChannel = FileChannel.open(Paths.get("E:\\copy\\node-3.rar"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        long current = System.currentTimeMillis();
        fromChannel.transferTo(0, fileInputStream.available(), toChannel);
        toChannel.close();
        fromChannel.close();
        System.out.println("channelCopy take：" + (System.currentTimeMillis() - current));
    }
}
