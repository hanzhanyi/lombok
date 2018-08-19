package com.iqiyi.lombok.impl;


import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.LinkedHashSet;
import java.util.Set;
@SupportedAnnotationTypes("HelloWorld")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class HelloWorldProcessor extends AbstractProcessor {

    private Messager messager;
    private Filer filer;
    private javax.lang.model.util.Types typeUtil;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnv);
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
        typeUtil = processingEnvironment.getTypeUtils();
    }

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

    // 由注解处理器自动调用，其中ProcessingEnvironment类提供了很多实用的工具类：Filter，Types，Elements，Messager等
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        LinkedHashSet<String> annotations = new LinkedHashSet<String>();
        annotations.add(HelloWorld.class.getCanonicalName());
        return annotations;
    }
}