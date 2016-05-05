package util.common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Encryption {
	
	
	public static void main(String[] args) throws Exception {
		
		Encryption en =new Encryption();
		//Boolean result = en.ValidateLogin("mitfin", "itunesr2");
		Boolean result = en.ValidateLogin("ES123", "TEST123");
		   
		System.out.println("The authentication is....:"+result);
	}
	
	public  Boolean ValidateLogin(String userName, String password ) throws Exception{
		return validate(userName.trim().toUpperCase(),password);
		
	}
	
	private Connection dbConnect() throws Exception {

		String db_url = null;
		String db_userName = null;
		String db_password = null;
		String db_driver = null;

		Connection conn = null;
		
		db_url = "jdbc:oracle:thin:@10.128.0.40:1521:CEB";
		db_userName = "misdev";
		db_password = "misdevp";
		db_driver = "oracle.jdbc.driver.OracleDriver";
		
		try {

			Class.forName(db_driver);// .newInstance();
			conn = (Connection) DriverManager.getConnection(db_url,
					db_userName, db_password);
			System.out.println("connected");

		} catch (SQLException ex) {
			throw new Exception("Database Connection Error !!");
		}
		return conn;
	}

	/**
	 * This is the public interface to validated user name an password
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private  Boolean validate(String userName, String password) throws Exception {

		Boolean valiity = false;

		Double M_PS = 0d;
		Double M_PU = 0d;
		Double M_P = 0d;

		String m_User = CheckPass(userName);

		if (!(m_User.trim().equals(""))) {
			String m_Pass = CheckPass(password);
			if (!(m_Pass.trim().equals(""))) {
				M_PS = Double.parseDouble(m_Pass);
				M_PU = Double.parseDouble(m_User);

				M_P = ((M_PU + M_PS) / 100005600000.9987);

				if (chkPassworEquality(M_P.toString(), userName)) {
					valiity = true;
				}

			}
		}

		return valiity;
	}

	/**
	 * This will check the password an the encrypted value.
	 * 
	 * @param M_P
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	private boolean chkPassworEquality(String M_P, String userName)
			throws Exception {

		boolean result = false;

		String selectQuery = "SELECT PSWRD FROM SAUSERM1 WHERE USER_ID = '"
				+ userName + "'";

		Connection con = dbConnect();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(selectQuery);

		while (rs.next()) {
			if (null != rs.getString("PSWRD")) {

				Double dbVal = Double.parseDouble(rs.getString("PSWRD"));
				Double enteredVal = Double.parseDouble(M_P);

				Double roundUp = 0d;

				DecimalFormat twoDForm = new DecimalFormat("#.########");
				roundUp = Double.valueOf(twoDForm.format(enteredVal / dbVal));

				if (roundUp.equals(1.00000000)) {
					result = true;
					break;
				}

			}
		}

		return result;
	}

	/**
	 * 
	 * @param literal
	 * @return
	 */
	private String CheckPass(String literal) {

		Integer PAS_LEN = 1;
		String M_PASS = "";
		String M_PASS1 = "";

		char[] cArray = literal.trim().toCharArray();

		ArrayList<String> strList = new ArrayList<String>();

		for (int i = 0; i < cArray.length; i++) {
			strList.add(Character.toString(cArray[i]));
		}

		int cArrayLength = cArray.length;

		Long LEN_TOT = 0l;

		while (PAS_LEN <= 10) {
			Long PAS_CHAR = 0l;
			Long POS_CHAR = 0l;

			if (cArrayLength < PAS_LEN) {
				if (PAS_LEN == 0) {
					PAS_CHAR = 9999l;
				} else {
					PAS_CHAR = LEN_TOT;
				}
			} else {
				// PAS_CHAR = decrypt(cArray[PAS_LEN-1]);
				PAS_CHAR = decrypt(strList.get(PAS_LEN - 1));
			}
			if (PAS_CHAR.equals(0)) {
				M_PASS = "";
				M_PASS1 = M_PASS;
				break;
			}

			POS_CHAR = decrypt(PAS_LEN.toString());

			Long ENC_CHAR = PAS_CHAR + POS_CHAR;
			LEN_TOT = LEN_TOT + ENC_CHAR;

			if (M_PASS.equals("")) {
				M_PASS = ENC_CHAR.toString();
			} else {
				M_PASS = M_PASS.concat(ENC_CHAR.toString());
			}
			PAS_LEN = PAS_LEN + 1;
		}

		M_PASS1 = M_PASS;

		return M_PASS1;

	}

	/**
	 * Actual decrypting happens here. !! Values are directly taken from MITFIN
	 * Service.
	 * 
	 * @param IN_STR
	 * @return
	 */
	private Long decrypt(String IN_STR) {

		Long OUT_CHAR = 0l;

		if (IN_STR.equals("A"))
			OUT_CHAR = 2457l;
		else if (IN_STR.equals("B"))
			OUT_CHAR = 2459l;
		else if (IN_STR.equals("C"))
			OUT_CHAR = 2460l;
		else if (IN_STR.equals("D"))
			OUT_CHAR = 2461l;
		else if (IN_STR.equals("E"))
			OUT_CHAR = 2462l;
		else if (IN_STR.equals("F"))
			OUT_CHAR = 2463l;
		else if (IN_STR.equals("G"))
			OUT_CHAR = 2464l;
		else if (IN_STR.equals("H"))
			OUT_CHAR = 2465l;
		else if (IN_STR.equals("I"))
			OUT_CHAR = 2466l;
		else if (IN_STR.equals("J"))
			OUT_CHAR = 2468l;
		else if (IN_STR.equals("K"))
			OUT_CHAR = 2469l;
		else if (IN_STR.equals("L"))
			OUT_CHAR = 2470l;
		else if (IN_STR.equals("M"))
			OUT_CHAR = 2471l;
		else if (IN_STR.equals("N"))
			OUT_CHAR = 2472l;
		else if (IN_STR.equals("O"))
			OUT_CHAR = 2472l;
		else if (IN_STR.equals("P"))
			OUT_CHAR = 2473l;
		else if (IN_STR.equals("Q"))
			OUT_CHAR = 2473l;
		else if (IN_STR.equals("R"))
			OUT_CHAR = 2474l;
		else if (IN_STR.equals("S"))
			OUT_CHAR = 2475l;
		else if (IN_STR.equals("T"))
			OUT_CHAR = 2476l;
		else if (IN_STR.equals("U"))
			OUT_CHAR = 2478l;
		else if (IN_STR.equals("V"))
			OUT_CHAR = 2478l;
		else if (IN_STR.equals("W"))
			OUT_CHAR = 2479l;
		else if (IN_STR.equals("X"))
			OUT_CHAR = 2481l;
		else if (IN_STR.equals("Y"))
			OUT_CHAR = 2482l;
		else if (IN_STR.equals("Z"))
			OUT_CHAR = 2483l;
		else if (IN_STR.equals("a"))
			OUT_CHAR = 4055l;
		else if (IN_STR.equals("b"))
			OUT_CHAR = 4056l;
		else if (IN_STR.equals("c"))
			OUT_CHAR = 4057l;
		else if (IN_STR.equals("d"))
			OUT_CHAR = 4060l;
		else if (IN_STR.equals("e"))
			OUT_CHAR = 4061l;
		else if (IN_STR.equals("f"))
			OUT_CHAR = 4063l;
		else if (IN_STR.equals("g"))
			OUT_CHAR = 4064l;
		else if (IN_STR.equals("h"))
			OUT_CHAR = 4065l;
		else if (IN_STR.equals("i"))
			OUT_CHAR = 4066l;
		else if (IN_STR.equals("j"))
			OUT_CHAR = 4068l;
		else if (IN_STR.equals("k"))
			OUT_CHAR = 4069l;
		else if (IN_STR.equals("l"))
			OUT_CHAR = 5470l;
		else if (IN_STR.equals("m"))
			OUT_CHAR = 5471l;
		else if (IN_STR.equals("n"))
			OUT_CHAR = 5472l;
		else if (IN_STR.equals("o"))
			OUT_CHAR = 5472l;
		else if (IN_STR.equals("p"))
			OUT_CHAR = 5473l;
		else if (IN_STR.equals("q"))
			OUT_CHAR = 5473l;
		else if (IN_STR.equals("r"))
			OUT_CHAR = 5474l;
		else if (IN_STR.equals("s"))
			OUT_CHAR = 5475l;
		else if (IN_STR.equals("t"))
			OUT_CHAR = 5476l;
		else if (IN_STR.equals("u"))
			OUT_CHAR = 5478l;
		else if (IN_STR.equals("v"))
			OUT_CHAR = 5478l;
		else if (IN_STR.equals("w"))
			OUT_CHAR = 5479l;
		else if (IN_STR.equals("x"))
			OUT_CHAR = 5481l;
		else if (IN_STR.equals("y"))
			OUT_CHAR = 5482l;
		else if (IN_STR.equals("z"))
			OUT_CHAR = 9999l;
		else if (IN_STR.equals("0"))
			OUT_CHAR = 2502l;
		else if (IN_STR.equals("1"))
			OUT_CHAR = 2503l;
		else if (IN_STR.equals("2"))
			OUT_CHAR = 2504l;
		else if (IN_STR.equals("3"))
			OUT_CHAR = 2505l;
		else if (IN_STR.equals("4"))
			OUT_CHAR = 2506l;
		else if (IN_STR.equals("5"))
			OUT_CHAR = 2507l;
		else if (IN_STR.equals("6"))
			OUT_CHAR = 2508l;
		else if (IN_STR.equals("7"))
			OUT_CHAR = 2509l;
		else if (IN_STR.equals("8"))
			OUT_CHAR = 2561l;
		else if (IN_STR.equals("9"))
			OUT_CHAR = 2562l;
		else if (IN_STR.equals("10"))
			OUT_CHAR = 2563l;
		else
			OUT_CHAR = 0l;

		return OUT_CHAR;

	}

}
