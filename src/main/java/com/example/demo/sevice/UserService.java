package com.example.demo.sevice;

import com.example.demo.databasemock.UserDataBaseMock;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.selfexception.*;

@Service
public class UserService {
    public List<User> getAllUsers() { return UserDataBaseMock.userProvider(); }
    public User getUserById(long id) {
        //TODO GTB-知识点: - 下面这几行代码，能看出来对 Optional API 的掌握是不熟练的。
        Optional<User> optionalUser = UserDataBaseMock.findUserById(id);
        //TODO GTB-完成度: - 无法查询 user！这行在提交时间之后做了修改，但是麻痹大意没有进行回归测试。你以为只改了一行就一定不会出问题，是吧。No，这种时候往往都会出问题。
        if(optionalUser.isPresent()) throw new UserNotFoundException("查无此人");
        return optionalUser.get();
    }
    public User addUser(User user) {
        UserDataBaseMock.add(user);
        return user;
    }
}
