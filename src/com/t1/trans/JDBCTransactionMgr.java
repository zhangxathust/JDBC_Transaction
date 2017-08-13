package com.t1.trans;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.t1.holder.SingleThreadConnectionHolder;

public class JDBCTransactionMgr implements ITransactionMgr {

	private DataSource dataSource;

	public JDBCTransactionMgr(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void start() throws Exception {
		Connection conn = SingleThreadConnectionHolder.getConnection(dataSource);
		conn.setAutoCommit(false);
	}

	@Override
	public void commit() throws Exception {
		Connection conn = SingleThreadConnectionHolder.getConnection(dataSource);
		conn.commit();
	}

	@Override
	public void rollback() {
		try {
			Connection conn = SingleThreadConnectionHolder.getConnection(dataSource);
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			Connection conn = SingleThreadConnectionHolder.getConnection(dataSource);
			conn.setAutoCommit(true);
			conn.setReadOnly(false);
			SingleThreadConnectionHolder.releaseConnection(dataSource);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
