package proxy.dynamic.jdk;


import proxy.Calculator;
import proxy.statics.CalculatorImpl;

import java.lang.reflect.Proxy;

/**
 * @author liguanghui02
 * @date 2021/2/4
 */
public class Main {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Calculator calculator = new CalculatorImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(calculator);

        Calculator calculatorProxy = (Calculator) Proxy.newProxyInstance(
                calculator.getClass().getClassLoader(),
                calculator.getClass().getInterfaces(),
                dynamicProxy
        );
        calculatorProxy.add(10, 20);
    }
}