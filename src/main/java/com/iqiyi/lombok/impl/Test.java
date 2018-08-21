package com.iqiyi.lombok.impl;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Created by hanzhanyi on 2018/8/20.
 */
public class Test {
    public static void main(String[] args) {
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        javac.run(System.in, null, null,
                //"-verbose",
                //"-Xprint",
                "-XprintRounds",
                "-XprintProcessorInfo",
                "-processor", "com.iqiyi.lombok.impl.HelloWorldProcessor",
                "-d", ".",
                "E:\\WorkSpace\\tset\\lombok\\src\\main\\java\\com\\iqiyi\\lombok\\impl\\HelloWorld.java",
                "E:\\WorkSpace\\tset\\lombok\\src\\main\\java\\com\\iqiyi\\lombok\\impl\\Dummy.java");
    }
}
