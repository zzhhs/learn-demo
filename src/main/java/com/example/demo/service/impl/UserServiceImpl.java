package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    UserMapper userMapper;
    public int j = 0;

    public long success = 0;

    public long noSuccess = 0;

    public long sb = 0;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean doScz()  {
        j++;
        System.out.println("我是"+j);
//        Integer num = productMapper.getNum();

//        if(num <= 0){
//            log.info("没买到");
//            noSuccess++;
//        }else {
//            log.info("买到了！！！！！！");
//            productMapper.updateNum();
//            success++;
//        }
        Product product = productMapper.selectById(1);
        if(product.getNum()<=0){
            log.info("没买到");
            noSuccess++;
        }else {
            Integer result = productMapper.updateNum(product.getVersion(), new Date().getTime());
            if(result!=0){
                log.info("买到了！！！！！！");
                success++;
            }else {
                sb++;
            }
        }

//            for (long i = 1L; i<6; i++) {
//                User.builder().id(i).name("sb"+i).build().updateById();
//            }
//            Date date = new SimpleDateFormat("yyyy-MM").parse("2020-05");
//
//            for (long i = 1L; i<6; i++) {
//                User.builder().id(i).name("not sb"+i).build().updateById();
//            }
        log.info("success:{};noSuccess:{};sb:{}",success, noSuccess, sb);
        return true;



    }

    @Override
    public void d() {
        User user = userMapper.getByUserId();
        User user2 = userMapper.getByUserId();
    }
}
