package pl.sdacademy.wiosnademo.config;

import static pl.sdacademy.wiosnademo.config.Queues.HELLO_WORLD;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import pl.sdacademy.wiosnademo.listeners.HelloWorldListener;

//@Configuration
public class JmsConfig {

  @Bean
  public MessageListenerContainer listenerContainer(@Qualifier("jmsConnectionFactory") final ConnectionFactory connectionFactory) {
    final DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setDestinationName(HELLO_WORLD);
    container.setMessageListener(new HelloWorldListener());
    return container;
  }
}
