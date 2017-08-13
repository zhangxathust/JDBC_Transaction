package com.t1.holder;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class SingleThreadConnectionHolder {

	private ThreadLocal<ConnectionHolder> holder = new ThreadLocal<ConnectionHolder>();

	public Connection getConnection(DataSource source) throws SQLException {

		ConnectionHolder cHolder = holder.get();
		if (cHolder != null) {
			return cHolder.getConnection(source);
		} else {
			cHolder = new ConnectionHolder();
			holder.set(cHolder);
			return cHolder.getConnection(source);
		}
	}

	public void releaseConnection(DataSource source) throws SQLException {
		ConnectionHolder cHolder = holder.get();
		if (cHolder != null) {
			cHolder.releaseConnection(source);
		}
	}
}
