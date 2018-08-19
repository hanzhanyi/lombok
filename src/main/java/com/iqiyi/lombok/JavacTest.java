package com.iqiyi.lombok;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JavacTest {
    public static void main(String[] args) {
    JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
    javac.run(System.in, null, null,
            //"-verbose",
            //"-Xprint",
            "-XprintRounds",
            "-XprintProcessorInfo",
            "-processor","com.iqiyi.lombok.impl.HelloWorldProcessor",
            "-d", "../../../",
    "C:\\Users\\Administrator\\IdeaProjects\\lombok\\src\\main\\java\\com\\iqiyi\\lombok\\impl\\HelloWorld.java",
    "C:\\Users\\Administrator\\IdeaProjects\\lombok\\src\\main\\java\\com\\iqiyi\\lombok\\impl\\Dummy.java");
    }
}
