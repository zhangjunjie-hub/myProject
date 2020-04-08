package mysqlTest.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class RabbitConfig {

    //默认交换机
    public static final String EXCHANGE_NAME_DEFAULT = "spring-boot-exchange-default";
    @Value("${rabbitMq.hostName}")
    private String hostName;
    @Value("${rabbitMq.port}")
    private int port;
    @Value("${rabbitMq.userName}")
    private String userName;
    @Value("${rabbitMq.password}")
    private String password;

    @Bean
    @Primary
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory  connectionFactory = new CachingConnectionFactory(hostName,port);
        if(StringUtils.isNotBlank(userName)){
          connectionFactory.setUsername(userName);
        }
        if(StringUtils.isNotBlank(password)){
            connectionFactory.setPassword(password);
        }
        connectionFactory.setVirtualHost(RabbitConfig.EXCHANGE_NAME_DEFAULT);
        return connectionFactory;
    }

    @Bean
    public TopicExchange topicExchange(){
        TopicExchange topic = new TopicExchange(RabbitAdmin.DEFAULT_EXCHANGE_NAME);
        topic.setShouldDeclare(false);
        return topic;
    }

    @Bean
    public AmqpTemplate amqpTemplate(){
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public AmqpAdmin amqpAdmin(){
        RabbitAdmin admin = new RabbitAdmin(connectionFactory());
        admin.declareExchange(topicExchange());
        return admin;
    }

    @Override
    public String toString() {
        return "RabbitConfig{" +
                "hostName='" + hostName + '\'' +
                ", port=" + port +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
