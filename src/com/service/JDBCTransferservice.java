package com.service;

import javax.sql.DataSource;

import com.t1.transaction.JDBCTransactionTemplate;

public class JDBCTransferservice implements IBankTransferService {

	private DataSource source;
	
	public JDBCTransferservice(DataSource source) {
		super();
		this.source = source;
	}

	@Override
	public void transfer(int from, int to, int amount) {
		new JDBCTransactionTemplate(source) {
			@Override
			protected void doJob() throws Exception {
				//TODO 事务操作
			}
		}.doJobInTransaction();;
	}

}
