package thrift.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;
import service.HelloService;
import service.impl.HelloServiceImpl;

@Slf4j
public class HsHaServer {

    public static void main(String[] args) {
        try {
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(8888);
            //使用二进制协议传输数据
            TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();
            //关联处理器与HelloService服务实现
            TProcessor processor = new HelloService.Processor<>(new HelloServiceImpl());
            THsHaServer.Args serverArgs = new THsHaServer.Args(serverTransport);
            serverArgs.processor(processor);
            serverArgs.protocolFactory(proFactory);
            serverArgs.transportFactory(new TFramedTransport.Factory());
            //使用THsHaServer服务端
            TServer server = new THsHaServer(serverArgs);
            log.info("Start HsHaServer on port 8080 ...");
            //启动服务
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
