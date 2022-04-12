package com.engure.java.proxy;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/4 23:01
 *
 * * * * * * * * * * */

interface IpDemo {
    public String hello(String s);
}

public class PDemo implements IpDemo {

    @Override
    public String hello(String s) {
        System.out.println("hello " + s);
        return "hello over!";
    }

    public static void main(String[] args) {
        PDemo pDemo = new PDemo();
        IpDemo demo = ((IpDemo) CrazyProxy.getProxyObj(pDemo));

        System.out.println(pDemo);
        System.out.println(demo.getClass());

        System.out.println("===================");

        String res = demo.hello("engure");
        System.out.println(res);
    }

}

/**
 * jdk 动态代理简单实现，
 * 被代理的类必须实现了接口，
 * ((接口)getProxyObj(obj)).实例方法()
 */
class CrazyProxy {
    private CrazyProxy() {
    }

    public static Object getProxyObj(Object obj) {

        Object res = Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                (proxy, method, args) -> {  // public Object invoke(Object proxy, Method method, Object[] args)
                    System.out.println("调用：" + method.getName() + " " + Arrays.toString(args));
                    Object returnValue = null;
                    try {
                        returnValue = method.invoke(obj, args);
                    } catch (Exception e) {
                        System.out.println("异常：" + e.getMessage());
                    } finally {
                        System.out.println("结果：" + returnValue);
                        return returnValue;
                    }
                });

        return res;
    }

}
