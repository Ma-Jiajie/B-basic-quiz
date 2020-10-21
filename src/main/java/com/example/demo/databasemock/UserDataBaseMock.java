package com.example.demo.databasemock;

import com.example.demo.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDataBaseMock {
    private static List<User> usersMock = Arrays.asList(
            new User(1, "Qaz Wsx", 19, "profile.jpeg" ,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."),
            new User(2, "Wsx Rfv", 22, "profile.jpeg" ,"The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here."),
            new User(3, "Rfv Tgb", 32, "profile.jpeg" ,"There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour."),
            new User(4, "Tgb Yhn", 25, "profile.jpeg" ,"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.")
            );

    private static List<User> users = new ArrayList<>(usersMock);
    public static List<User> userProvider() {
        return users;
    }

    public static Optional<User> findUserById(long id) {
        return users.stream().filter(user -> user.getId()==id).findFirst();
    }

    public static void add(User user) {
        users.add(user);
    }

}
