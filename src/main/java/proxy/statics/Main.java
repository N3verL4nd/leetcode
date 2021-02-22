package proxy.statics;

import proxy.Calculator;

/**
 * @author liguanghui02
 * @date 2021/2/4
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        StaticProxy calculatorProxy = new StaticProxy(calculator);
        System.out.println(calculatorProxy.add(100, 200));
    }
}
