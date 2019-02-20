package login;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kyoutu.DBconnection;
import kyoutu.SyoukaiMatome;
import kyoutu.SqlData;

public class LoginAction extends Action {


	public ActionForward execute(ActionMapping mapping , ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		LoginForm login = (LoginForm)form;
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		try{
			Class.forName(SyoukaiMatome.oracle);
			con = DBconnection.getConnection();

//!!!		↑コネクションの取得ですが、この処理と同じ記述は複数の箇所で記述されて
//!!!		いるはずです。
//!!!		このような処理は全てのソースに書くのではなく、適当な共通クラスを作成し、
//!!!		そのクラスにstaticメソッドを作成し、その戻り値としてConnectionクラスの
//!!!		インスタンスを返却するようにします。そのようして使用する際は、例えば
//!!!		DBConnectionと言うクラスを作成し、getConnection というメソッドを作成して、
//!!!
//!!!		Connection conn = DBConnection.getConnection();
//!!!
//!!!		のようにするだけで良くなります。
//!!!
//!!!		このようにするメリットは主に二つで、1つは単純にソースの記述量が減る事で、
//!!!		もう1つは仮にデータベースサーバの名前やユーザ名、SIDなど変更の可能性の
//!!!		あるものが変更された時にも、1つのソースだけ修正すれば済むということです。
//!!!
//!!!		同じ処理を複数の箇所に記述する場合、まとめる事を考えましょう。

			String loginSql = SqlData.LOGINSQL;
			stm=con.prepareStatement(loginSql);


			stm.setString(1,login.getId());
			stm.setString(2,login.getPass());

			rs=stm.executeQuery();

//!!!				↑id と pass は入力された値から取得できているはずですので、
//!!!				DBから取得する必要は無いです。


			if(rs.next()){
				login.setId(rs.getString("id"));
				login.setNamae(rs.getString("name"));
				login.setAuthority(rs.getString("authority"));
				login.setCorpname(rs.getString("corpname"));
			}else{
				saveErrors(request, SyoukaiMatome.test());

				return mapping.findForward("miss");
			}


//!!!		↑id と pass を条件に指定してデータを取得する場合、
//!!!		idは一意なので、取得できるデータ数は１件 又は ０件です。
//!!!		while ではなくif文にし、else で データが取得できない場合の処理を
//!!!		実装してください。
//!!!		また、以下のif文は不要になります。

		}catch(Exception e){
			e.printStackTrace();
			saveErrors(request, SyoukaiMatome.error());

			return mapping.findForward("error");
		}finally{
			try{
				DBconnection.close(con,stm,rs);
			}catch(SQLException e){
				e.printStackTrace();
				saveErrors(request, SyoukaiMatome.error());

				return mapping.findForward("error");
			}
		}
//!!!	↑このままでは、例外が発生していても何も表示されないので、どこで例外が
//!!!	発生しているのかを見つけ難くなってしまいます。
//!!!
//!!!	また、例外が発生した際に、catchやprintStackTrace()だけで終わってしまっては
//!!!	いけません。
//!!!	入力値を Integer.parseInt する時など、発生しても問題無い場合は構いませんが、
//!!!	今回のように致命的なエラーが発生した場合、printStackTraceやログ出力だけでは
//!!!	システムの使用者(ユーザ)には何の情報も渡らないためです。
//!!!
//!!!	エラーには主に以下の二種類があります。
//!!!
//!!!	①入力値の誤りなど、ユーザが再入力する事で継続可能なエラー
//!!!	②データベース障害など、ユーザが何をやっても回復不能なエラー
//!!!
//!!!	①は再入力を促すメッセージを表示して元の画面に戻るだけで継続ができます。
//!!!	②は「致命的なエラーが発生しました。管理者にお問い合わせ下さい」等のエラーを
//!!!	表示するエラー画面に遷移し、その際管理者が後から原因を特定するための手がかりとして
//!!!	ログを出力するようにします。なお、画面にエラーの詳細を表示する事は普通はしません。


//		session.setAttribute("test",login);
//!!!	↑sessionスコープへの格納は、struts-config.xmlで行いましょう。

		return mapping.findForward("success");
	}

}
