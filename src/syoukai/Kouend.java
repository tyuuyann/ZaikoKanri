package syoukai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kyoutu.DBconnection;
import kyoutu.SyoukaiMatome;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class Kouend extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SyoukaiForm syou = (SyoukaiForm) form;
		if ("on".equals(syou.getKousin())) {
			syou.setMaker(syou.getMakena());
			syou.setCode(syou.getSyoco());
			syou.setS_name(syou.getSyona());
			syou.setZai(syou.getTan());

			syou.setKousin("off");

			return mapping.findForward("kousen");
		}
		String m_code = "";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(SyoukaiMatome.oracle);
			con = DBconnection.getConnection();
			con.setAutoCommit(false);

			if ("delete".equals(syou.getKousin())) {
				Class.forName(SyoukaiMatome.oracle);
				con = DBconnection.getConnection();
				con.setAutoCommit(false);

				ps = con.prepareStatement("delete from zaiko_syouhin where s_code = ? and s_name = ? and s_price = ? ");

				ps.setString(1, syou.getSyoco());
				ps.setString(2, syou.getSyona());
				ps.setInt(3, syou.getTan());

				int line = ps.executeUpdate();
				if (line == 0) {
					throw new SQLException();
				}

				ps = con
						.prepareStatement("delete from zaiko_order where o_syouhin_code = ?");
				ps.setString(1, syou.getSyoco());

				line = ps.executeUpdate();
				if (line == 0) {
					throw new SQLException();
				}

				ps = con
						.prepareStatement("select count(*) from zaiko_view where M_NAME=? ");
				ps.setString(1, syou.getMakena());
				rs = ps.executeQuery();

				int count = 10;
				if (rs.next()) {
					count = rs.getInt("count(*)");
				}
				if (0 == count) {
					ps = con
							.prepareStatement("delete from zaiko_maker where m_name = ?");
					ps.setString(1, syou.getMakena());
					line = ps.executeUpdate();
					if (line == 0) {
						throw new SQLException();
					}
				}
				ActionMessages message = new ActionMessages();
				message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.orderSuccess","削除"));
				saveMessages(request, message);
				return mapping.findForward("sakujo");
			} else {

				if (syou.getMaker().equals(syou.getMakena())) {
					ps = con
							.prepareStatement(" update zaiko_syouhin set S_code = ? , S_name = ? , S_price = ? where S_code = ? and S_name = ? and S_price = ? ");
					ps.setString(1, syou.getSyoco());
					ps.setString(2, syou.getSyona());
					ps.setInt(3, syou.getTan());
					ps.setString(4, syou.getCode());
					ps.setString(5, syou.getS_name());
					ps.setInt(6, syou.getZai());

					int line = ps.executeUpdate();
					if (line == 0) {
						throw new SQLException();
					}

				} else {
					ps = con
							.prepareStatement("select * from zaiko_maker where m_name = ?");
					ps.setString(1, syou.getMakena());
					rs = ps.executeQuery();

					if (rs.next()) {
						m_code = rs.getString("m_code");

					} else {
						ps = con
								.prepareStatement("insert into zaiko_maker values (mkcodenum.nextval , ?)");
						ps.setString(1, syou.getMakena());
						int line = ps.executeUpdate();
						if (line == 0) {
							throw new SQLException();
						}

						ps = con
								.prepareStatement("select mkcodenum.currval from zaiko_maker");
						rs = ps.executeQuery();
						while (rs.next()) {
							m_code = rs.getString("CURRVAL");
						}
					}

					ps = con.prepareStatement("update zaiko_syouhin set S_code = ? , S_name = ? , S_maker_code = ? , S_price = ? where S_code = ? and S_name = ? and S_price = ?");

					ps.setString(1, syou.getSyoco());
					ps.setString(2, syou.getSyona());
					ps.setString(3, m_code);
					ps.setInt(4, syou.getTan());
					ps.setString(5, syou.getCode());
					ps.setString(6, syou.getS_name());
					ps.setInt(7, syou.getZai());

					int line = ps.executeUpdate();
					if (line == 0) {
						throw new SQLException();
					}

					ps = con
							.prepareStatement("select count(*) from zaiko_view where M_NAME=? ");
					ps.setString(1, syou.getMaker());
					rs = ps.executeQuery();

					int count = 10;
					if (rs.next()) {
						count = rs.getInt("count(*)");
					}
					if (0 == count) {
						ps = con
								.prepareStatement("delete from zaiko_maker where m_name = ?");
						ps.setString(1, syou.getMaker());
						line = ps.executeUpdate();
						if (line == 0) {
							throw new SQLException();
						}
					}

				}

				ps = con.prepareStatement(" update zaiko_order set o_syouhin_code = ? where o_syouhin_code = ? ");
				ps.setString(1, syou.getSyoco());
				ps.setString(2, syou.getCode());

				int line = ps.executeUpdate();
				if (line == 0) {
					throw new SQLException();
				}
			}

			// !!! ↑ここでは複数のテーブルを更新していますが、仮に一つ目のテーブルの更新が
			// !!! 上手くいったとしても、二つ目や三つ目の更新に失敗した場合、整合性を保つため
			// !!! には全てを取消した方が良い場合があります。
			// !!! よってその場合は rollback を使用して元の状態に戻すのが正解となります。
			// !!! そのため、Conection con を取得した直後に、
			// !!! con.setAutoCommit(false)を使用して自動的にコミットが実行されるのを止め、
			// !!! 3つのテーブルの削除に成功したらcommitを行い、いずれかのタイミングで
			// !!! 例外が発生した場合はrollbackを行う、といった処理が必要になります。
			con.commit();
			ActionMessages message = new ActionMessages();

			message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.orderSuccess", "更新"));
			saveMessages(request, message);

			return mapping.findForward("kouend");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			saveErrors(request, SyoukaiMatome.error());

			return mapping.findForward("error");
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException l) {
				l.printStackTrace();
				saveErrors(request, SyoukaiMatome.error());

				return mapping.findForward("error");
			}

			e.printStackTrace();
			request.setAttribute("kekka", syou);

			saveErrors(request, SyoukaiMatome.error());

			return mapping.findForward("error");
		} finally {
			try {
				DBconnection.close(con, ps, rs);
			} catch (Exception e) {
				e.printStackTrace();
				saveErrors(request, SyoukaiMatome.error());

				return mapping.findForward("error");
			}
		}
		
	}

}
