package chen.com.pojo;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "user")
public class User {
    private int id;
    private String name;
    private String address;
    private String sex;
    private Integer age;

    public User(int id, String name, String address, String sex, Integer age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.age = age;
    }
}
