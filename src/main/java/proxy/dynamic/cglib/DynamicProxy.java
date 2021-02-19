package proxy.dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author liguanghui02
 * @date 2021/2/4
 */
public class DynamicProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println(proxy.getClass().getName());

        System.out.println("The method [" + method.getName() + "] begins with " + Arrays.toString(args));

        Object result = proxy.invokeSuper(obj, args);

        System.out.println("The method [" + method.getName() + "] ends with " + result);

        return result;
    }
}
