package test.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author liguanghui02
 * @date 2021/6/22
 */
public class OrderEventListener {

    @Subscribe
    public void executeObjMsg1(OrderMessage msg) {
        System.out.println("OrderEventListener::executeObjMsg1" + " " + msg);
    }

    @Subscribe
    public void executeObjMsg2(OrderMessage msg) {
        System.out.println("OrderEventListener::executeObjMsg2" + " " + msg);
    }

    @Subscribe
    public void executeStringMsg(String msg) {
        System.out.println("OrderEventListener::executeStringMsg" + " " + msg);
    }

    @Subscribe
    public void executeIntMsg(Integer msg) {
        System.out.println("OrderEventListener::executeIntMsg" + " " + msg);
    }

}
