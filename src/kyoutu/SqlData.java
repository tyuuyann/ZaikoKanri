package kyoutu;

public class SqlData {
	// ログイン取得
	public static final String LOGINSQL = "SELECT " +
											"A.ID," +
											"A.NAME," +
											"A.AUTHORITY," +
											"B.CORPNAME" +
										  " FROM " +
										  	"USERMASTER A " +
										  	"INNER JOIN CORPMASTER B ON(A.CORPID = B.CORPID)" +
										  " WHERE " +
										  	"A.ID = ?" +
										  " AND " +
										  	"A.PASS = ?";
}
