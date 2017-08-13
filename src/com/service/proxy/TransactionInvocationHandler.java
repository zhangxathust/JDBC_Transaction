package com.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.t1.trans.ITransactionMgr;

public class TransactionInvocationHandler implements InvocationHandler {
	
	private Object proxied;
	
	private ITransactionMgr transactionMgr;
	

	public TransactionInvocationHandler(Object proxied, ITransactionMgr transactionMgr) {
		super();
		this.proxied = proxied;
		this.transactionMgr = transactionMgr;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
