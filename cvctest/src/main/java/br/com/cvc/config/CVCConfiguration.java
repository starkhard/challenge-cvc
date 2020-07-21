package br.com.cvc.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CVCConfiguration {

    @Bean
    public ObjectMapper mappers(){

        ObjectMapper obm = new ObjectMapper();
        obm.registerModule(new JavaTimeModule());
        obm.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false).
                configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,true);

        return obm;
    }

    @Bean
    public ObjectWriter objectIdWriter(ObjectMapper obm){
        return  obm.writerWithDefaultPrettyPrinter();
    }
}
