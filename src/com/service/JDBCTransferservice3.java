package com.service;

import javax.sql.DataSource;

import com.annos.Transactional;
import com.t1.transaction.JDBCTransactionTemplate;

public class JDBCTransferservice3 implements IBankTransferService {

	private DataSource source;
	
	public JDBCTransferservice3(DataSource source) {
		super();
		this.source = source;
	}

	//事务的
	@Transactional
	@Override
	public void transfer(int from, int to, int amount) {
		//TODO 业务代码
	}

}
