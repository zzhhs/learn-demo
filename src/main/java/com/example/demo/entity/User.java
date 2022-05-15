package com.example.demo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User extends Model<User> {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}