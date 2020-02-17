package com.dubbo.postman;

import com.dubbo.postman.util.LogResultPrintStream;
import org.apache.maven.cli.MavenCli;

import java.io.ByteArrayOutputStream;

public class Hyz {

    public static void main(String[] args) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        LogResultPrintStream resultPrintStream = new LogResultPrintStream(stream);

        String pomPath = "/Users/qudian/IdeaProjects/dubbo-postman-master/dubbo-postman/0_0_6-RELEASE_channel-server";
       System.setProperty("maven.multiModuleProjectDirectory","./");
        MavenCli cli = new MavenCli();
                int result = cli.doMain(new String[]{
                                              "dependency:copy-dependencies",
                                              "-DoutputDirectory=./lib",
                                              "-DexcludeScope=provided","-s/Users/qudian/IdeaProjects/dubbo-postman-master/dubbo-postman/.m2/setting.xml",
                                              "-U"}, pomPath, resultPrintStream, resultPrintStream);

        byte[] bytes = resultPrintStream.getLogByteArray();

       String errorContent = new String(bytes);

        System.out.println("结果 " + errorContent);
    }

}
