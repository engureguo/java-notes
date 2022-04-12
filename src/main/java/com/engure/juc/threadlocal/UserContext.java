package com.engure.juc.threadlocal;

class UserContext {
    private static final ThreadLocal<UserInfo> userContext = new ThreadLocal<>();

    public static UserInfo get() {
        return userContext.get();
    }

    public static void set(UserInfo ui) {
        userContext.set(ui);
    }

}