package proxy.statics;

import proxy.Calculator;

/**
 * @author liguanghui02
 * @date 2021/2/4
 */
public class StaticProxy implements Calculator {
    private Calculator calculator;

    public StaticProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int add(int x, int y) {
        System.out.println("The method [add] begins");
        int result = calculator.add(x, y);
        System.out.println("The method [add] ends");
        return result;
    }

    @Override
    public int sub(int x, int y) {
        System.out.println("The method [sub] begins");
        int result = calculator.sub(x, y);
        System.out.println("The method [sub] ends");
        return result;
    }

    @Override
    public int mul(int x, int y) {
        System.out.println("The method [mul] begins");
        int result = calculator.mul(x, y);
        System.out.println("The method [mul] ends");
        return result;
    }

    @Override
    public int div(int x, int y) {
        System.out.println("The method [div] begins");
        int result = calculator.div(x, y);
        System.out.println("The method [div] ends");
        return result;
    }

}
