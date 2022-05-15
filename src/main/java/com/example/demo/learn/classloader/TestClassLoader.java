package com.example.demo.learn.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * TestClassLoader
 *
 * @author zouzhihao
 * @date 2021/1/17
 */
public class TestClassLoader {
    private URLClassLoader urlClassLoader;

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
//        String path = "D:\\work\\project\\sjkg-manage\\manage-service\\biz-app\\target\\biz-app.jar";//外部jar包的路径
        String path = "D:\\work\\project\\sjkg-manage\\manage-service\\biz-app\\target\\classes\\";
        URL url1 = new URL("file:" + path);
        URLClassLoader loader = new URLClassLoader(new URL[]{url1});
        Class<?> aClass = loader.loadClass("com.icaremgt.cloud.biz.modular.patient.entity.PatientInfo");
//        Class<?> aClass = loader.loadClass("org.springframework.boot.loader.ClassPathIndexFile");

        System.out.println(url1);
//        test1();
//        test2();
    }

    public static void test2() throws MalformedURLException, ClassNotFoundException {
        //我这里使用本地服务器做示范，http:为前缀，表示从互联网通过HTTP访问加载
        //相同的，使用file:前缀，从本地加载；还可以使用ftp:前缀从互联网通过FTP访问加载。
        String path = "D:\\work\\project\\sjkg-manage\\manage-service\\biz-app\\target\\biz-app.jar";
        URL[] urls={new URL("file:"+path)};
        System.out.println("starting....");
        //获取当前时间
        long time=System.currentTimeMillis();
        //通过URLClassLoader构造器生成myclassloader对象
        URLClassLoader myclassloader=new URLClassLoader(urls);
        //使用URLClassLoader的loadClass动态加载类mytest，并调用Class对象的newInstance()方法创建了该类的对象。
        //使用接口声明一个Test引用类型的变量m，并把newInstance()生成的对象强制类型转换为Test
        Class<?> patientInfo = myclassloader.loadClass("BOOT-INF.classes.com.icaremgt.cloud.biz.modular.patient.entity.PatientInfo");
        //输出加载的耗时
        System.out.println("need time:"+(System.currentTimeMillis()-time));
        System.out.println("load end start method....");
        //因编译时类型和运行时类型都有write方法，因此可以使用编译时类型直接调用运行时类型的方法。
        //m.write();
    }
    private static void test1() {
//        String path = "D:\\work\\project\\sjkg-manage\\manage-service\\biz-app\\target\\biz-app.jar";//外部jar包的路径
        String path = "D:\\work\\project\\sjkg-manage\\manage-service\\biz-app\\target\\classes\\";
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();//所有的Class对象
        Map<Class<?>, Annotation[]> classAnnotationMap = new HashMap<Class<?>, Annotation[]>();//每个Class对象上的注释对象
        Map<Class<?>, Map<Method, Annotation[]>> classMethodAnnoMap = new HashMap<Class<?>, Map<Method,Annotation[]>>();//每个Class对象中每个方法上的注释对象
        try {
            JarFile jarFile = new JarFile(new File(path));
            URL url = new URL("file:" + path);
            ClassLoader loader = new URLClassLoader(new URL[]{url});//自己定义的classLoader类，把外部路径也加到load路径里，使系统去该路经load对象
            Enumeration<JarEntry> es = jarFile.entries();
            while (es.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) es.nextElement();
                String name = jarEntry.getName();
                if(name != null && name.endsWith(".class")){
                    //只解析了.class文件，没有解析里面的jar包
                    //默认去系统已经定义的路径查找对象，针对外部jar包不能用
                    //Class<?> c = Thread.currentThread().getContextClassLoader().loadClass(name.replace("/", ".").substring(0,name.length() - 6));
                    Class<?> c = loader.loadClass(name.replace("/", ".").substring(0,name.length() - 6));
                    //自己定义的loader路径可以找到
                    System.out.println(c);
                    classes.add(c);
                    Annotation[] classAnnos = c.getDeclaredAnnotations();
                    classAnnotationMap.put(c, classAnnos);
                    Method[] classMethods = c.getDeclaredMethods();
                    Map<Method, Annotation[]> methodAnnoMap = new HashMap<Method, Annotation[]>();
                    for(int i = 0;i<classMethods.length;i++){
                        Annotation[] a = classMethods[i].getDeclaredAnnotations();
                        methodAnnoMap.put(classMethods[i], a);
                    }
                    classMethodAnnoMap.put(c, methodAnnoMap);
                }
            }
            System.out.println(classes.size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
