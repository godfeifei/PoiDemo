package chen.com.service;

import chen.com.mapper.UserMapper;
import chen.com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void add(List<User> read) {
        System.out.println(read.size());
        for (User user : read) {
            userMapper.add(user);
        }
    }
    public List<User> findAll(){
        List<User> list=userMapper.findAll();
        return list;
    }
}
