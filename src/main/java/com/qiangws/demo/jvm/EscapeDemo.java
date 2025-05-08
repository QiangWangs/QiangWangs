package com.qiangws.demo.jvm;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : EscapeDemo
 * @packageName : com.qiangws.demo.jvm
 * @createTime : 2025/3/28
 * @description :  TODO       方法逃逸
 * 线程逃逸
 * 当一个对象可能被外部线程访问到，这种称为线程逃逸。
 * 例如赋值给类变量或可以在其它线程中访问的实例变量
 */
public class EscapeDemo {

    /**
     * 当一个对象在方法里面被定义后，它可能被外部方法所引用，这种称为方法逃逸
     * 方法逃逸包括：
     * 通过调用参数，将对象地址传递到其他方法中，
     * 对象通过return语句将对象指针，返回给其他方法
     * @param s1
     * @param s2
     * @return
     */
    //StringBuffer对象发生了方法逃逸   return
    //StringBuffer sb是一个方法内部变量，上述代码中直接将sb返回，这样这个StringBuffer有可能被其他方法所改变，这样它的作用域就不只是在方法内部，虽然它是一个局部变量，称其逃逸到了方法外部。
    //甚至还有可能被外部线程访问到，譬如赋值给类变量或可以在其他线程中访问的实例变量，称为线程逃逸。
    //想要逃逸方法的话，需要让对象本身被外部调用，或者说， 对象的指针，传递到了 方法之外
    public static StringBuffer createStringBuffer(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb;
    }

    // 非方法逃逸
    public static String createString(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }

    
}
