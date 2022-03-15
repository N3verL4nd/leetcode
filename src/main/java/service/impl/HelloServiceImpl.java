package service.impl;

import org.apache.thrift.TException;
import service.HelloService;

public class HelloServiceImpl implements HelloService.Iface {
    @Override
    public String sayHello(String name) throws TException {
        return "hello, " + name;
    }
}
