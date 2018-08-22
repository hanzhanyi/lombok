package com.iqiyi.lombok.impl;


import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("HelloWorld")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class HelloWorldProcessorVersionTwo extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnv);
    }

    /**
     * process是javac编译器在执行注解处理器代码时要调用的过程
     *
     * @param annotations   可获取到此注解处理器所要处理的注解集合
     * @param roundEnv      访问到当前这个Round中的语法树节点，每个语法树节点在这里标识一个Element，在jdk6中的javax.lang.model包
     *                      中定义了16类Element
     * @param processingEvn 常用的实例变量：在注解处理器初始化的时候创建，代表了注解处理器框架提供的一个上下文环境，要创建新的
     *                      代码、想编译器输出信息、获取其他工具类等都需要用到这个实例变量。
     *                      每一个注解处理器在运行时候都是单利的，如果不需要改变或生成语法树的内容，process()方法就可以返回一个为false的布尔值
     *                      通知编译器这个Round中的代码未发生变化，无需构造新的JAVACompiler实例，
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            processingEnv.getMessager().printMessage(
                    Diagnostic.Kind.NOTE, "Hello Worlds!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        return true;
    }

    // 用来指定支持的java版本，一般来说我们都是支持到最新版本，因此直接返回 SourceVersion.latestSupported()即可
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}