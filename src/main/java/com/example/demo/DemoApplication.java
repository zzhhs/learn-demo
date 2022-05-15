package com.example.demo;


import com.example.demo.aspect.RedisLock;
import com.example.demo.entity.Product;
import com.example.demo.entity.Product2;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisLockKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    UserService userService;

    @GetMapping("dd")
    public void dd() {
        userService.d();
    }

    @GetMapping("get")
    public void get(HttpServletResponse response) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "111");
        map.put("2", "按时");
        map.put("3", "12暗示法·");
        map.put("4", "撒点房子现在想咨询");
        map.put("5", "按时");
        map.put("6", "撒反正这");

        response.reset();
        response.setContentType("content-type:octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode("打印.docx", "UTF-8"));
        InputStream inputStream = test.replaceAndGenerateWord("C:\\Users\\sjkg\\Desktop\\123.docx", map);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ServletOutputStream out = response.getOutputStream();
        int b = 0;
        byte[] buffer = new byte[1024];
        while ((b = bufferedInputStream.read(buffer)) != -1) {
            //写到输出流(out)中
            out.write(buffer, 0, b);
        }
        bufferedInputStream.close();
        if (out != null) {
            try {
                out.flush();
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }













    @GetMapping("/wyw")
    //@RedisLock
    public Product wyw(String id, String id2){
        //return "wyw";
        Product product = new Product();
        return product;
    }


    @PostMapping("/wyw2")
    //@RedisLock
    public Product2 wyw2(@RequestBody Product2 product){
        //return "wyw";
        //Product product = new Product();
        return product;
    }

    @PostMapping("/wyw3")
    //@RedisLock
    public String wyw2(String id, String id2){
        //return "wyw";
        //Product product = new Product();
        return id;
    }
}
