package com.t1.holder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

public class ConnectionHolder {

	private Map<DataSource, Connection> holder = new HashMap<>();

	public Connection getConnection(DataSource source) throws SQLException {

		if (holder.containsKey(source)) {
			return holder.get(source);
		} else {
			Connection conn = source.getConnection();
			holder.put(source, conn);
			return conn;
		}
	}
	
	public void releaseConnection(DataSource source) throws SQLException{
		getConnection(source).close();
		holder.remove(source);
	}

}
