package mainProject;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.sql.Statement;


public class SQL {
	private String ip;
	private int port;
	private String user;
	private String password;
	private String table_schema;
	private String table;
	public SQL(String ip, int port, String user, String password, String table_schema, String table) {
		super();
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.password = password;
		this.table_schema = table_schema;
		this.table = table;
	}
	private static Connection _con = null;
	public static Vector<sameScanWifi> collectInfoFromSQL(SQL info) throws java.lang.Exception{
		Vector<sameScanWifi> dataBase=new Vector<sameScanWifi>();
		Statement st = null;
		ResultSet rs = null;
		try {
			_con = DriverManager.getConnection("jdbc:mysql://"+info.ip+":" + info.port +"/"+info.table_schema, info.user, info.password);
			PreparedStatement pst = _con.prepareStatement("SELECT * FROM "+ info.table);
			rs = pst.executeQuery();
			while (rs.next()) {
				sameScanWifi tempSameScanWifi=new sameScanWifi();
				tempSameScanWifi.setAltitude( String.valueOf(rs.getDouble(6)));
				tempSameScanWifi.setID( rs.getString(3));
				tempSameScanWifi.setLatitude( String.valueOf(rs.getDouble(4)));
				tempSameScanWifi.setLongitude( String.valueOf(rs.getDouble(5)));
				tempSameScanWifi.setTime(rs.getString(2));
				for(int j=8;j<rs.getInt(7)*2+7;j+=2){
					wifi tempWifi=new wifi();
					tempWifi.setMAC(rs.getString(j));
					tempWifi.setRSSI( String.valueOf(rs.getInt(j+1)));
					tempSameScanWifi.insert(tempWifi);
				}
				dataBase.add(tempSameScanWifi);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQL.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (_con != null) { _con.close();  }
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(SQL.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}

		return dataBase;
	}

	public static String lastModified(SQL info) throws SQLException {
		// TODO Auto-generated method stub
		Statement st = null;
		ResultSet rs = null;
		_con = DriverManager.getConnection("jdbc:mysql://"+info.ip+":" + info.port +"/"+info.table_schema, info.user, info.password);
		st = _con.createStatement();
		rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = '"+info.table_schema+"' AND TABLE_NAME = '"+info.table+"'");
		rs.next();
		System.out.println("**** Update: "+rs.getString(1));
		
		return rs.getString(1);
	}
}