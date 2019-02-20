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

public class SakujoAction extends Action{
	public ActionForward execute(ActionMapping mapping , ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SyoukaiForm kai=(SyoukaiForm)form;
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs= null;
		try{
			Class.forName(SyoukaiMatome.oracle);
			con = DBconnection.getConnection();
			con.setAutoCommit(false);
			
			ps=con.prepareStatement("delete from zaiko_syouhin where s_code = ? and s_name = ? and s_price = ? ");
			
			ps.setString(1,kai.getSyoco());
			ps.setString(2,kai.getSyona());
			ps.setInt(3,kai.getTan());
			
			int line=ps.executeUpdate();
			if(line == 0){
				throw new SQLException();
			}
			
			ps=con.prepareStatement("delete from zaiko_order where o_syouhin_code = ?");
			ps.setString(1,kai.getSyoco());
			
			line=ps.executeUpdate();
			if(line == 0){
				throw new SQLException();
			}
			
			ps=con.prepareStatement("select count(*) from zaiko_view where M_NAME=? ");
			ps.setString(1,kai.getMakena());
			rs=ps.executeQuery();

			int count=10;
			if(rs.next()){
				count=rs.getInt("count(*)");
			}
			if(0==count){
				ps=con.prepareStatement("delete from zaiko_maker where m_name = ?");
				ps.setString(1,kai.getMakena());
				line=ps.executeUpdate();
				if(line == 0){
					throw new SQLException();
				}
			}
			con.commit();
		}catch(Exception e){
			e.printStackTrace();
			try{
				con.rollback();
			}catch(SQLException l){
				l.printStackTrace();
				saveErrors(request, SyoukaiMatome.error());
				
				return mapping.findForward("error");
			}
			saveErrors(request, SyoukaiMatome.error());
			
			return mapping.findForward("error");
		}finally{
			try{
				DBconnection.close(con,ps,rs);
			}catch(SQLException e){
				e.printStackTrace();
				saveErrors(request, SyoukaiMatome.error());
				
				return mapping.findForward("error");
			}
		}
		ActionMessages message = new ActionMessages();
		message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.orderSuccess","çÌèú"));
		saveMessages(request, message);
		return mapping.findForward("sakujo");
	}
}
