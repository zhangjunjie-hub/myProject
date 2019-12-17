package mqtest.com.java.test.connections;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class ConnectionBuild {
    public static Connection getConnection() throws Exception{
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.94.150");
        factory.setPort(5672);
        //设置vhost
        factory.setVirtualHost("test");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        return connection;
    }


    //生产者方法
    public static void publisher() throws Exception {
        //获取连接
      Connection connection =   getConnection();
      //创建通道
        Channel channel = connection.createChannel();
        //声明创建队列
        channel.queueDeclare("myQueue",false,false,false,null);
        //消息内容
        for(int i =0;i<100;i++) {
            String message = "this is my "+i+" rabbitMQ";
            channel.basicPublish("", "myQueue", null, message.getBytes());
            System.out.println("发送消息:" + message);
        }
        channel.close();
        connection.close();

    }

    //消费者方法
    public static  void consumerMethod() throws Exception{
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        //声明通道
        channel.queueDeclare("myQueue",false,false,false,null);
        //定义消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume("myQueue",true,consumer);
        while(true){
           QueueingConsumer.Delivery delivery = consumer.nextDelivery();
           String message = new String(delivery.getBody());
            System.out.println("consumer 消费消息："+message);
        }

    }

    public static void main(String[] args) throws Exception {
        //发布消息
       // publisher();
        //消费消息
        consumerMethod();

    }








}
