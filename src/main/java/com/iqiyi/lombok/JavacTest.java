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
                "-classpath", "C:\\Users\\hanzhanyi\\.m2\\repository\\org\\projectlombok\\lombok\\1.18.2\\lombok-1.18.2.jar",
                "-d", ".",
                "E:\\WorkSpace\\tset\\lombok\\src\\main\\java\\com\\iqiyi\\lombok\\Getter.java");
    }
}
