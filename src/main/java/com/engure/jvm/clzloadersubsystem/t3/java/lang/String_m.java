package com.engure.jvm.clzloadersubsystem.t3.java.lang;

/*******
 * Package: java.lang   
 * Description: 自定义的String类，放在 src/ 下才有意义，即能和rt.jar下的String全类名一直
 *  因冲突改名为 String_m
 * Author: engure
 * Date: 2021/8/2 14:55
 * Modified by: 
 ******/
public class String_m {
    static {
        System.out.println("sysout");
    }
}
