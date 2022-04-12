package com.engure.juc.other.v2.t3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class T3 {
    public static void main(String[] args) {
        Function<String, String> fun = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };
        System.out.println(fun.apply("eg"));


        Function<String, String> fun2 = (s) -> {return s;};
        //Function<String, String> fun2 = s -> {return s;};
        //Function<String, String> fun2 = s -> s;
        System.out.println(fun2.apply("egggg"));

    }

    public void te1() {
        List<String> list = new ArrayList<>();
        list.forEach(s->{
            System.out.println(s);
        });
    }

    public void te2() {
//        Predicate

//        Consumer

//        Supplier

    }
}
