package qtel.ping.job;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class utility {
	final static Logger logger = Logger.getLogger(utility.class.getName());
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/astelu";
	// Database credentials
	static final String USER = "astelu";
	static final String PASS = "astelu";



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// conn.setAutoCommit(false);
			System.out.println("Opened database successfully");
			/*
			 * String prf = getTipoChiamataColt("0438584844","0044775544456");
			 * logger.info("prf:" + prf); prf =
			 * getTipoChiamataColt("0438584844","391775544456");
			 * logger.info("prf:" + prf);
			 * 
			 * prf = getTipoChiamataColt("04364246","0436866301");
			 * logger.info("prf:" + prf);
			 * 
			 * prf = getTipoChiamataTiscali("0438584844","445544456");
			 * logger.info("prf:" + prf);
			 * 
			 * prf = getTipoChiamataTiscali("0438584844","390438584844");
			 * logger.info("prf:" + prf);
			 * 
			 * prf = getTipoChiamataTiscali("0438584844","393918584844");
			 * logger.info("prf:" + prf); //mancano tanti prefissi cellulari
			 * conn.close();
			 */
			// sqlExpress_testConn();


			// } catch (SQLException se) {
			// Handle errors for JDBC
			// se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) {
				logger.error("Exception se:" + se.getMessage());
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se1) {
				logger.error("Exception se1:" + se1.getMessage());
			} // end finally try
		} // end try
		logger.info("Good bye");
	}// end main




	/**
	 * 
	 * @param conn
	 * @param table
	 * @return
	 */
	public static HashMap<String, Long> getClientiServizi(Connection conn) {
		HashMap<String, Long> hm = new HashMap<String, Long>();
		Statement st = null;
		ResultSet rs = null;
		try {

			st = conn.createStatement();

			String sql = "SELECT id,ragsoc from ClientiServizi";

			rs = st.executeQuery(sql);

			while (rs.next())
				hm.put(rs.getString(2),rs.getLong(1));

			
		} catch (Exception e) {
			logger.error("Exception e:" + e.getMessage());

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e2) {
				logger.error("Exception e2:" + e2.getMessage());
			}
		}
		return hm;
	}

	/**
	 * 
	 * @param conn
	 * @param table
	 * @return
	 */
	public static HashMap<String, String> getClientiXDSL(Connection conn) {
		HashMap<String, String> hm = new HashMap<String, String>();
		Statement st = null;
		ResultSet rs = null;
		try {

			st = conn.createStatement();

			String sql = "SELECT a.ragsoc, b.ip from ClientiServizi a, clientiXDSL b "
					+ "where a.id=b.idcliente and b.ip <> '' and b.ping = 'X'";

			rs = st.executeQuery(sql);

			while (rs.next())
				hm.put(rs.getString(1),rs.getString(2));

			
		} catch (Exception e) {
			logger.error("Exception e:" + e.getMessage());

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e2) {
				logger.error("Exception e2:" + e2.getMessage());
			}
		}
		return hm;
	}
	

	
}
