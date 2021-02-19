package proxy.test;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) {

        byte[] proxyClassFile = ProxyGenerator.generateProxyClass("proxy.test.Demo", new Class[]{Serializable.class});

        try {
            Files.write(Paths.get("Demo.class"), proxyClassFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}