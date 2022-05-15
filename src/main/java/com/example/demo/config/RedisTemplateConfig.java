package com.example.demo.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.CountDownLatch;

/**
 * RedisTemplate  配置
 *
 * @author 王杰
 */
@Configuration
@AllArgsConstructor
public class RedisTemplateConfig {
	private final RedisConnectionFactory redisConnectionFactory;
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}
//	@Bean
//	public CountDownLatch countDownLatch() {
//		return new CountDownLatch(1);
//	}
//	@Bean
//	SyncHandler syncHandler(CountDownLatch countDownLatch) {
//		return new SyncHandler(countDownLatch);
//	}
//	@Bean
//    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter childrenListenerAdapter,
//                                            MessageListenerAdapter childrenBirthListenerAdapter, MessageListenerAdapter womenCardInfoListenerAdapter,
//                                            MessageListenerAdapter babyListenerAdapter, MessageListenerAdapter postpartumListenerAdapter,
//                                            MessageListenerAdapter newBornListenerAdapter) {
//		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.addMessageListener(childrenListenerAdapter, new PatternTopic(SyncConstant.CHILDREN + "_topic"));
//		container.addMessageListener(childrenBirthListenerAdapter, new PatternTopic(SyncConstant.CHILD_BIRTH + "_topic"));
//		container.addMessageListener(womenCardInfoListenerAdapter, new PatternTopic(SyncConstant.WOMEN_CARD_INFO + "_topic"));
//		container.addMessageListener(babyListenerAdapter, new PatternTopic(SyncConstant.BABY + "_topic"));
//		container.addMessageListener(postpartumListenerAdapter, new PatternTopic(SyncConstant.POSTPARTUM + "_topic"));
//		container.addMessageListener(newBornListenerAdapter, new PatternTopic(SyncConstant.NEWBORN + "_topic"));
//		return container;
//	}
//	/**
//	 * 配置儿童基本信息 top处理
//	 */
//	@Bean
//    MessageListenerAdapter childrenListenerAdapter(SyncHandler syncHandler) {
//		return new MessageListenerAdapter(syncHandler, "children");
//	}
//	/**
//	 * 配置 分娩记录 top处理
//	 */
//	@Bean
//    MessageListenerAdapter childrenBirthListenerAdapter(SyncHandler syncHandler) {
//		return new MessageListenerAdapter(syncHandler, "childrenBirth");
//	}
//	/**
//	 * 配置 孕卡 top处理
//	 */
//	@Bean
//    MessageListenerAdapter womenCardInfoListenerAdapter(SyncHandler syncHandler) {
//		return new MessageListenerAdapter(syncHandler, "womenCardInfo");
//	}
//	/**
//	 * 配置 婴幼儿随访记录 top处理
//	 */
//	@Bean
//    MessageListenerAdapter babyListenerAdapter(SyncHandler syncHandler) {
//		return new MessageListenerAdapter(syncHandler, "baby");
//	}
//
//	/**
//	 * 配置 产后随访记录 top处理
//	 */
//	@Bean
//    MessageListenerAdapter postpartumListenerAdapter(SyncHandler syncHandler) {
//		return new MessageListenerAdapter(syncHandler, "postpartum");
//	}
//
//
//	/**
//	 * 配置 新生儿随访记录 top处理
//	 */
//	@Bean
//    MessageListenerAdapter newBornListenerAdapter(SyncHandler syncHandler) {
//		return new MessageListenerAdapter(syncHandler, "newBorn");
//	}
}