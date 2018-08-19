package com.iqiyi.lombok;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JavacTest {
    public static void main(String[] args) {
    JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
//    String [] strs = new String [1] ;
//    strs[0] =  "C:\\Users\\Administrator\\IdeaProjects\\lombok\\src\\main\\java\\com\\iqiyi\\lombok\\annotations\\Getter.java";
//        try {
//            com.sun.tools.javac.Main.main(strs);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    javac.run(System.in, null, null,
            //"-verbose",
            //"-Xprint",
            "-XprintRounds",
            "-XprintProcessorInfo",
            "-classpath","C:\\Users\\Administrator\\.m2\\repository\\org\\projectlombok\\lombok\\1.18.2\\lombok-1.18.2.jar",
            "-d", ".",
    "C:\\Users\\Administrator\\IdeaProjects\\lombok\\src\\main\\java\\com\\iqiyi\\lombok\\annotations\\Getter.java");
    }
}
