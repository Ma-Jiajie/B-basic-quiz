package com.example.demo.databasemock;

import com.example.demo.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataBaseMock {
    private static List<User> users = new ArrayList<>();
    private static boolean isInited = false;

    private static void makeData() {
        isInited = true;
        users.add(new User(1, "Qaz Wsx", 19, "profile.jpeg" ,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."));
        users.add(new User(2, "Wsx Rfv", 22, "profile.jpeg" ,"The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here."));
        users.add(new User(3, "Rfv Tgb", 32, "profile.jpeg" ,"There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour."));
        users.add(new User(4, "Tgb Yhn", 25, "profile.jpeg" ,"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old."));
    }

    public static List<User> userProvider() {
        if(!isInited) makeData();
        return users;
    }

    public static User findUserById(long id) {
        return users.stream().filter(user -> user.getId()==id).findFirst().get();
    }
}
