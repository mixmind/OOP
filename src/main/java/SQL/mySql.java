package SQL;

import WiFi_data.GeoModDat;
import WiFi_data.Hotspots;
import WiFi_data.Network;
import WiFi_data.WIFI;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mySql {
/*
	public static String _ip = "5.29.193.52";
	public static String _port = "3306";
	public static String _urld = "oop_course_ariel";
	public static String _url = "jdbc:mysql://"+_ip+":"+ _port+"/"+_urld;
	public static String _user = "oop1";
	public static String _password = "Lambda1();";
	*/
	public static String _ip = "";
	public static String _port = "";
	public static String _urld = "";
	public static String _url = "jdbc:mysql://";
	public static String _user = "";
	public static String _password = "";
	public static Connection _con = null;
	//  private static String _url = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";

	public static Network connect() throws IOException, NumberFormatException, ParseException {
		_url+=_ip+":"+ _port+"/"+_urld;
		Network nt = getData();
		//toCSV temp=new toCSV(nt);
		return nt;
	}

	public synchronized static Network getData() throws NumberFormatException, ParseException {
		Statement st = null;
		ResultSet rs = null;
		Network nt = new Network();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {     
			_con = DriverManager.getConnection(_url, _user, _password);
			st = _con.createStatement();
			PreparedStatement pst = _con.prepareStatement("SELECT * FROM ex4_db");
			rs = pst.executeQuery();
			int ind=0;
			while (rs.next()) {
				int size = rs.getInt(7);
				int len = 7+2*size;
				Hotspots data=new Hotspots();
				GeoModDat pos=new GeoModDat();
				pos.setFirtseen(format.parse(rs.getString(2)));
				pos.setId(rs.getString(3));
				pos.setLat(Double.parseDouble(rs.getString(4)));
				pos.setLon(Double.parseDouble(rs.getString(5)));
				pos.setAlt(Double.parseDouble(rs.getString(6)));
				for(int i=8;i<len;i+=2){
					WIFI temp=new WIFI();
					temp.setMac(rs.getString(8));
					temp.setSsid("");
					temp.setRssi((int)Double.parseDouble(rs.getString(9)));
					data.setDataOfdot(pos);
					data.add(temp);
					i+=4;
				}
				nt.add(data);
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(mySql.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (_con != null) { _con.close();  }
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(mySql.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return nt;
	}
	public static Date getUpdate() throws ParseException 
	{
		Statement st = null;
		ResultSet rs = null;
		Date date = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {     
			_con = DriverManager.getConnection(_url, _user, _password);
			st = _con.createStatement();
			rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'oop_course_ariel' AND TABLE_NAME = 'ex4_db'");
			if (rs.next()) {
				date = format.parse(rs.getString(1));
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(mySql.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (_con != null) { _con.close();  }
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(mySql.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return date;
	}
}