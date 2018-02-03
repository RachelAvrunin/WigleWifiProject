package SQL;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import project.*;

public class mySQL {

	public ArrayList<WifiScan> ReadTable(Connecting det) {
		Statement st = null;
		ResultSet rs = null;
		Connection _con = null;

		ArrayList<WifiScan> List = new ArrayList<WifiScan>();
		try {
			_con = DriverManager.getConnection(det.getUrl(), det.getUser(), det.getPass());
			st = _con.createStatement();

			PreparedStatement pst = _con.prepareStatement("SELECT * FROM " + det.getTableName());
			rs = pst.executeQuery();
			@SuppressWarnings("unused")
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				WifiScan wifi = new WifiScan();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Point3D point = new Point3D(rs.getDouble(4), rs.getDouble(5), rs.getDouble(6));
				Date time = sdf.parse(rs.getString(2));
				wifi.time=time.toString();
				wifi.id=rs.getString(3);
				wifi.p=point;

				int numNetworks = rs.getInt(7);
				for (int i = 0; i < numNetworks; i++) {
					wifi.addline((new wifi(Integer.parseInt(rs.getString(9 + 2 * i)), rs.getString(8 + 2 * i), "non",  0)));
				}

				List.add(wifi);

			}
		} catch (SQLException | ParseException ex) {
			System.out.println("eror");
			Logger lgr = Logger.getLogger(mySQL.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (_con != null) {
					_con.close();
				}
			} catch (SQLException ex) {
				System.out.println("eror2");

				Logger lgr = Logger.getLogger(mySQL.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}

		for (WifiScan s : List)
			System.out.println(s.toString());

		return List;
	}
}