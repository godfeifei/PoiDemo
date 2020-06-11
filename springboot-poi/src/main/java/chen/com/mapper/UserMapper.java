package chen.com.mapper;

import chen.com.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void add(User user);

    List<User> findAll();
}
