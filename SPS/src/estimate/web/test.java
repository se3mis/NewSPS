package estimate.web;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRException;

public class test {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws JRException
	 */
	private static String path = "D:\\Projects\\Reports\\";
	private static String dbDriver = "oracle.jdbc.OracleDriver";
	private static String dbUrl = "jdbc:oracle:thin:@10.128.0.55:1521:HQORADEV";
	private static String userName = "misdev";
	private static String password = "misdevp";

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, JRException {
		A3Reports b = new A3Reports(path, dbDriver, dbUrl, userName, password);
 		b.genarateReportWithOutPara(path + "final.jrxml");
	}

}
