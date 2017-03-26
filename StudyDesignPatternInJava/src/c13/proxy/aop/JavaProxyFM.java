package c13.proxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class JavaProxyFM extends FightManager implements InvocationHandler{
   private Object proxied;
   
    public JavaProxyFM(Object proxied) {
       this.proxied = proxied;
    }
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(">開始時間:"  +  new Date().toLocaleString()); 		
		return method.invoke(proxied, args);
	}

}
