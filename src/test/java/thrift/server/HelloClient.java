package thrift.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.layered.TFramedTransport;
import service.HelloService;

@Slf4j
public class HelloClient {
    public static void main(String[] args) {
        TTransport transport = null;
        try {
            //传输层使用非阻塞I/O
            transport = new TFramedTransport.Factory().getTransport(new TSocket("127.0.0.1", 8888));
            transport.open();
            //使用二进制协议传输数据
            TProtocol protocol = new TBinaryProtocol(transport);
            //使用同步客户端
            HelloService.Client client = new HelloService.Client(protocol);
            String name = "N3verL4nd";
            log.info("HelloClient 请求参数[name]=" + name);
            //调用接口
            String result = client.sayHello(name);
            log.info("Server 返回结果为" + result);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
           if (transport != null) {
               transport.close();
           }
        }
    }
}
