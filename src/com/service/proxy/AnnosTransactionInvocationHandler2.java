package com.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.annos.Transactional;
import com.t1.trans.ITransactionMgr;

public class AnnosTransactionInvocationHandler2 implements InvocationHandler {
	
	private Object proxied;
	
	private ITransactionMgr transactionMgr;
	

	public AnnosTransactionInvocationHandler2(Object proxied, ITransactionMgr transactionMgr) {
		super();
		this.proxied = proxied;
		this.transactionMgr = transactionMgr;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 判断是否有注解
		Method originalMethod = proxied.getClass().getMethod(method.getName(), method.getParameterTypes());  
        if (!originalMethod.isAnnotationPresent(Transactional.class))  
        {  
			return method.invoke(proxied, args);  
        }  
		
		transactionMgr.start();
		Object result = null;  
        try  
        {  
            result = method.invoke(proxied, args);  
            transactionMgr.commit();  
        } catch (Exception e)  
        {  
        	transactionMgr.rollback();  
        } finally  
        {  
        	transactionMgr.close();  
        }  
        return result;  
		
	}

}
