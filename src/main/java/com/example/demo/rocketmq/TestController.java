package com.example.demo.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * TestController
 *
 * @author zouzhihao
 * @date 2020/12/23
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private Producer producer;

    private List<String> mesList;

    @Autowired
    RedissonClient redissonClient;

    /**
     * 初始化消息
     */
    public TestController() {
        mesList = new ArrayList<>();
        mesList.add("1");
        mesList.add("2");
        mesList.add("3");
        mesList.add("4");
        mesList.add("5");

        RLock lock = redissonClient.getLock("");

        lock.lock();
        lock.tryLock();

        lock.unlock();
    }

    @RequestMapping("/text/rocketmq")
    public Object callback() throws Exception {
        //总共发送五次消息
        for (String s : mesList) {
            //创建生产信息
            Message message = new Message(JmsConfig.TOPIC, "zzh_tag", ("开始:" + s).getBytes());
            //发送
            SendResult sendResult = producer.getProducer().send(message);
            log.info("输出生产者信息={}",sendResult);
        }
        return "成功";
    }
}
