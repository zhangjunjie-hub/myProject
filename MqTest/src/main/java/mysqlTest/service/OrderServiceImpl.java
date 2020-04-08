package mysqlTest.service;

import mysqlTest.entity.OrderSuccessMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {
    @Autowired
    @Qualifier("amqpTemplate")
    private AmqpTemplate amqpTemplate;
    public static final String EXCHANGE_NAME_DEFAULT = "spring-boot-exchange-default";
    public static final String Order_ROUTING_KEY = "order_routing_key";
    public void send(){
        OrderSuccessMessage msg = new OrderSuccessMessage();
        for(int i=0;i<100;i++){
           msg.setMsgCode(Integer.toString(i));
           msg.setMsgContent("this is the "+i+"th messages.");
           msg.setMsgStatus("0");
           String message = msg.toString();
           amqpTemplate.convertAndSend(EXCHANGE_NAME_DEFAULT,Order_ROUTING_KEY,message);
        }

    }

}
