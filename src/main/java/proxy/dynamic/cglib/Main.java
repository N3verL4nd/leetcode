package proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import proxy.Calculator;
import proxy.statics.CalculatorImpl;

/**
 * @author liguanghui02
 * @date 2021/2/4
 */
public class Main {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new DynamicProxy());
        enhancer.setSuperclass(CalculatorImpl.class);
        Calculator calculatorProxy = (Calculator) enhancer.create();
        calculatorProxy.add(100, 200);
    }
}
