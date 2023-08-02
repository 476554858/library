package com.zjx.common;

import com.zjx.pojo.User;

/**
 * user上下文
 */
public class UserContext {

    public static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void setContext(User user){
         threadLocal.set(user);
    }

    public static User getUser(){
        return threadLocal.get();
    }

    public static Integer getUserId(){
        User user = threadLocal.get();
        if(user != null){
            return user.getId();
        }
        return null;
    }
}
