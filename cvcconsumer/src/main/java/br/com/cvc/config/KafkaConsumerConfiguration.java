package br.com.cvc.config;

import br.com.cvc.builder.Reservations;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {

    @Value("${boot.strap.server}")
    private String BOOT_STRAP_SERVER;
    @Value("${group.cvc.id}")
    private String GROUP_ID;

    @Bean
    public Map<String, Object> consumerConfiguration() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOT_STRAP_SERVER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Reservations> kafkaListenerContainerFactory() {

        ConsumerFactory<String, Reservations> consumerFactory =
                new DefaultKafkaConsumerFactory<>(consumerConfiguration(),
                        new StringDeserializer(),new JsonDeserializer<>(Reservations.class));

        ConcurrentKafkaListenerContainerFactory<String,Reservations> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        return factory;

    }
}
