package com.encore.kafka.conf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.encore.kafka.vo.PubSubVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableKafka
@Configuration
public class SubConfig {

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrap;

	@Value(value = "${spring.kafka.consumer.group-id}")
	private String group_id;

	@Bean
	public ConsumerFactory<String, List<PubSubVo>> pingConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, group_id);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new ErrorHandlingDeserializer(kafkaDeserializer()));
		
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, List<PubSubVo>> pingKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, List<PubSubVo>> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		factory.setConsumerFactory(pingConsumerFactory());
		return factory;
	}

	protected Deserializer<List<PubSubVo>> kafkaDeserializer() {
		ObjectMapper om = new ObjectMapper();
		om.getTypeFactory().constructParametricType(List.class, PubSubVo.class);
		return new JsonDeserializer<>(om);
	}

}
