package thrift.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import service.HelloService;
import service.impl.HelloServiceImpl;

@Slf4j
public class ThreadedSelectorServer {

    public static void main(String[] args) {
        try {
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(8888);
            //使用二进制协议传输数据
            TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();
            //关联处理器与HelloService服务实现
            TProcessor processor = new HelloService.Processor<>(new HelloServiceImpl());
            TThreadedSelectorServer.Args serverArgs = new TThreadedSelectorServer.Args(serverTransport);
            serverArgs.processor(processor);
            serverArgs.protocolFactory(proFactory);
            //使用TThreadedSelectorServer服务端
            TServer server = new TThreadedSelectorServer(serverArgs);
            log.info("Start ThreadedSelectorServer on port 8888...");
            //启动服务，调用到的是AbstractNonblockingServer.serve()
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
