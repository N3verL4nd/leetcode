package proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author liguanghui02
 * @date 2021/2/4
 */
public class DynamicProxy implements InvocationHandler {
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());

        System.out.println("The method [" + method.getName() + "] begins with " + Arrays.toString(args));

        Object result = method.invoke(target, args);

        System.out.println("The method [" + method.getName() + "] ends with " + result);

        return result;
    }
}