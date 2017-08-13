package com.service;

import javax.sql.DataSource;

import com.t1.transaction.JDBCTransactionTemplate;

public class JDBCTransferservice2 implements IBankTransferService {

	private DataSource source;
	
	public JDBCTransferservice2(DataSource source) {
		super();
		this.source = source;
	}

	@Override
	public void transfer(int from, int to, int amount) {
		//TODO 业务代码
	}

}
