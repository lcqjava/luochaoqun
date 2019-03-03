package com.luochaoqun.javacore.designpattern.structCategory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description: 享元模式：实现对象的共享
 * 
 * @author: 小艾亲亲
 * @date: 2019年3月2日 上午12:18:05
 * @today:
 */
public class FlyweightPattern {
	public static void main(String[] args) {
		ConnectionPool connectionPool = ConnectionPool.instance();
		Connection connection = connectionPool.getConnection();
		try {
			String sql = "select * from tb_bank_card";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("as");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

class ConnectionPool {
	private Vector<Connection> pool;
	/* 公有属性 */
	private String url = "jdbc:mysql://47.94.170.165:3306/lcq_test";
	private String username = "luochaoqun";
	private String password = "Luochaoqun123!";
	private String driverClassName = "com.mysql.jdbc.Driver";
	private int poolSize = 100;
	private static volatile ConnectionPool instance;
	Connection conn = null;

	public static ConnectionPool instance() {
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new ConnectionPool();
				}
			}
		}
		return instance;
	}

	/* 构造方法，做一些初始化工作 */
	private ConnectionPool() {
		pool = new Vector<Connection>(poolSize);

		for (int i = 0; i < poolSize; i++) {
			try {
				Class.forName(driverClassName);
				conn = DriverManager.getConnection(url, username, password);
				pool.add(conn);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/* 返回连接到连接池 */
	public synchronized void release() {
		pool.add(conn);
	}

	/* 返回连接池中的一个数据库连接 */
	public synchronized Connection getConnection() {
		if (pool.size() > 0) {
			Connection conn = pool.get(0);
			pool.remove(conn);
			return conn;
		} else {
			return null;
		}
	}

}
