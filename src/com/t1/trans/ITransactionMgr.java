package com.t1.trans;

public interface ITransactionMgr {

	public void start() throws Exception;
	
	public void commit() throws Exception;
	
	public void rollback();
	
	public void close();
}
