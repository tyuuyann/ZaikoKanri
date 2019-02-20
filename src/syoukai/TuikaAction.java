package syoukai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

public class TuikaAction extends Action{
	public ActionForward execute(ActionMapping mapping , ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SyoukaiForm syou=(SyoukaiForm)form;
		
		if("TUIKA".equals(syou.getSelectSyoukai())){
			syou.setSelectSyoukai("TUIKAACTION");
			return mapping.findForward("tuika");
		}
	
		ArrayList list=new ArrayList();
		int price=Integer.parseInt(syou.getTanka());
		String str2="";
		
		Connection con =null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			Class.forName(SyoukaiMatome.oracle);
			con = DBconnection.getConnection();
			con.setAutoCommit(false);
			ps=con.prepareStatement("select * from zaiko_syouhin");
			rs=ps.executeQuery();
			
			while(rs.next()){
				list.add(rs.getString("s_code"));
			}


			ActionMessages error = new ActionMessages();
			for(int i=0; i<list.size() ; i++){
				String test=(String)list.get(i);
				if(syou.getCode().equals(test.trim())){
					error.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.tuikajuufuku"));
					saveErrors(request, error);
					
					return mapping.findForward("errors");
				}
			}
			
			
			ps=con.prepareStatement("select * from zaiko_maker where m_name = ?");
			
			ps.setString(1,syou.getMaker());

			rs=ps.executeQuery();
			
			if(rs.next()){
				str2=rs.getString("m_code");
			}else{
				ps=con.prepareStatement("insert into zaiko_maker values (mkcodenum.nextval , ?)");
				ps.setString(1,syou.getMaker());
				
				int line=ps.executeUpdate();
				if(line == 0){
					throw new SQLException();
				}
				
				ps=con.prepareStatement("select mkcodenum.currval from zaiko_maker");
				rs=ps.executeQuery();
				while(rs.next()){
					str2=rs.getString("CURRVAL");
				}
			}

			
			ps=con.prepareStatement("insert into zaiko_syouhin values ( ? , ? , ? , ? )");
			ps.setString(1,syou.getCode());
			ps.setString(2,syou.getS_name());
			ps.setString(3,str2);
			ps.setInt(4,price);
			
			int line=ps.executeUpdate();
			if(line == 0){
				throw new SQLException();
			}
			
			ps=con.prepareStatement("insert into zaiko_order (o_syouhin_code) values(?)");
			ps.setString(1,syou.getCode());
			
			line=ps.executeUpdate();
			if(line == 0){
				throw new SQLException();
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
		message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.orderSuccess","’Ç‰Á"));
		saveMessages(request,message);
		return mapping.findForward("tuika");
	}
}
