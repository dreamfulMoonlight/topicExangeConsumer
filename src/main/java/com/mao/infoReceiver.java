package com.mao;

/*
    消息接收者
    @author Administrator
    @RabbitListrener bindings: 搜索队列
    @QueueBinding value:指定队列的名称
                  Exange:配置交换器
    @Queue value:配置队列名称
            autoDelete:是否是一个可删除的临时队列

     @Exange value:为交换器起个名称
            type:指定具体的交换器类型
 */

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(
        bindings = @QueueBinding(
                value=@Queue(value="${mq.config.queue.info}",autoDelete = "true"),
                exchange = @Exchange(value="${mq.config.exchange}",type= ExchangeTypes.TOPIC),
                key = "*.log.info"
        )
)
public class infoReceiver {
    /*
    接收消息的方法，采用消息队列监听机制

     */
    @RabbitHandler
    public void process(String msg){
        System.out.println("Info.......receiver:" +msg);
    }
}
