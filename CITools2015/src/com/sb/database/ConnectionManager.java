package com.sb.database;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sb.ci.exception.CIException;

public class ConnectionManager {

	private static DataSource dataSource;

	public static Connection getConnection() {
		try {
			if (dataSource == null) {
				Context initialContext;
				initialContext = new InitialContext();
				Context newContext = (Context) initialContext
						.lookup("java:/comp/env");
				dataSource = (DataSource) newContext.lookup("jdbc/stormBots");
			}

			return dataSource.getConnection();
			
		} catch (Exception e) {
			throw new CIException(e);
		}
	}

}
