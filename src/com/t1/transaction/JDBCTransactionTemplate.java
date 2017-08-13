package com.t1.transaction;

import javax.sql.DataSource;

import com.t1.trans.ITransactionMgr;
import com.t1.trans.JDBCTransactionMgr;

public abstract class JDBCTransactionTemplate {

	private ITransactionMgr transactionManager;

	public JDBCTransactionTemplate(DataSource source) {
		super();
		this.transactionManager = new JDBCTransactionMgr(source);
	}
	
	 public void doJobInTransaction()  
	    {  
	        try  
	        {  
	            transactionManager.start();  
	            doJob();  
	            transactionManager.commit();  
	        } catch (Exception e)  
	        {  
	            transactionManager.rollback();  
	        } finally  
	        {  
	            transactionManager.close();  
	        }  
	    }  
	       
	    protected abstract void doJob() throws Exception;
	
}
