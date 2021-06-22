package test.eventbus;

import com.google.common.eventbus.EventBus;

import java.util.Date;

/**
 * @author liguanghui02
 * @date 2021/6/22
 */
public class Test {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test");
        eventBus.register(new OrderEventListener());
        // 发布消息
        eventBus.post(new OrderMessage("消息"));
        eventBus.post("123");
        eventBus.post(123);
        eventBus.post(new Date());
    }
}
