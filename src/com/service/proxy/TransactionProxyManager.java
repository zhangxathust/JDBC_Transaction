package com.service.proxy;

import java.lang.reflect.Proxy;

import com.t1.trans.ITransactionMgr;

public class TransactionProxyManager {

	private ITransactionMgr transactionManager;

	public TransactionProxyManager(ITransactionMgr transactionManager) {
		this.transactionManager = transactionManager;
	}

	//  生成业务代理对象
	public Object proxyFor(Object object) {

		return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(),
				new TransactionInvocationHandler(object, transactionManager));
	}

}
