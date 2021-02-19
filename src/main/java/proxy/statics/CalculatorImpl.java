package proxy.statics;

import proxy.Calculator;

/**
 * @author liguanghui02
 * @date 2021/2/4
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int x, int y) {
        int result = x + y;
        return result;
    }

    @Override
    public int sub(int x, int y) {
        int result = x - y;
        return result;
    }

    @Override
    public int mul(int x, int y) {
        int result = x * y;
        return result;
    }

    @Override
    public int div(int x, int y) {
        int result = x / y;
        return result;
    }

}
