package syoukai;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import kyoutu.DBconnection;
import kyoutu.SyoukaiMatome;
//!!!	���s�v��import���͍폜���Ă����܂��傤�B

public class HachuuAction extends Action{
	public ActionForward execute(ActionMapping mapping , ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ArrayList makerList=new ArrayList();
		ArrayList syoucodeList=new ArrayList();
		ArrayList syounameList=new ArrayList();
		HttpSession session=request.getSession();

		TasuForm tasuForm = (TasuForm) form;
		String selectList = tasuForm.getSelectList();
		tasuForm.getSelectSub();
		
		if("on".equals(tasuForm.getPush())){
			tasuForm.setJutyu("0");
			tasuForm.setPush("off");
		}
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
	
		String selectMaker = null;
		String selectScode = null;
		String selectSname = null;
		try{
			Class.forName(SyoukaiMatome.oracle);
			con=DBconnection.getConnection();


			ps=con.prepareStatement(" select * from zaiko_maker order by m_name ");
			rs=ps.executeQuery();
			while(rs.next()){
				if(selectMaker == null){
					selectMaker=rs.getString("m_name") ;
				}
				makerList.add(new LabelValueBean(rs.getString("m_name"), rs.getString("m_name")));
			}

			if("0".equals(selectList) || "1".equals(selectList) || "2".equals(selectList) || "3".equals(selectList)){
				selectMaker=tasuForm.getMakerName() ;
			}
			

			ps=con.prepareStatement("select * from Zaiko_sm where m_name = ?");
			ps.setString(1,selectMaker);
				
			rs=ps.executeQuery();
			while(rs.next()){
				syoucodeList.add(new LabelValueBean(rs.getString("s_code"), rs.getString("s_code")));
				syounameList.add(new LabelValueBean(rs.getString("s_name"), rs.getString("s_name")));
			}
				
				
			// ���i�R�[�h�̑I�����ύX���ꂽ��
			if("1".equals(selectList)){
				selectScode=tasuForm.getShouhinCode();
				ps=con.prepareStatement(" select * from zaiko_sm where s_code = ? ");
				ps.setString(1,selectScode);
				rs=ps.executeQuery();
				while(rs.next()){
					selectSname=rs.getString("s_name");
				}

			// ���i���̂̑I�����ύX���ꂽ��
			}else if("2".equals(selectList)){
				selectSname=tasuForm.getShouhinName();
				ps=con.prepareStatement(" select * from zaiko_sm where s_name = ? ");
				ps.setString(1,selectSname);
				rs=ps.executeQuery();
				while(rs.next()){
					selectScode=rs.getString("s_code");
				}
				
			//�o�^�{�^���������ꂽ�Ƃ��̏���
			}else if("3".equals(selectList)){
				System.out.print(selectList);
				int Jsuu=Integer.parseInt(tasuForm.getJutyu());
				int zaiko=0;
				selectScode=tasuForm.getShouhinCode();
				selectSname=tasuForm.getShouhinName();
				
				ps=con.prepareStatement(" select * from ZAIKO_ORDER where o_syouhin_code = ? ");
				ps.setString(1,selectScode);
				rs=ps.executeQuery();
				while(rs.next()){
					zaiko=rs.getInt("o_stock");	
				}
					
				if("HATYU".equals(tasuForm.getSelectSub())){
					zaiko=zaiko+Jsuu;		
				}else if("JUTYU".equals(tasuForm.getSelectSub())){
					zaiko=zaiko-Jsuu;
				}
				ActionMessages error = new ActionMessages();
				if(0 < zaiko && 99999999 >= zaiko){
					ps=con.prepareStatement(" update zaiko_order set o_stock = ? where o_syouhin_code = ? ");
					ps.setInt(1,zaiko);
					ps.setString(2,selectScode);
					int line=ps.executeUpdate();
					if(line == 0){
						throw new SQLException();
					}
					ActionMessages message = new ActionMessages();
					if("HATYU".equals(tasuForm.getSelectSub())){		
						message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.orderSuccess", "����"));
					}else if("JUTYU".equals(tasuForm.getSelectSub())){								
						message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.orderSuccess", "��"));
					}
					saveMessages(request, message);
				}else if(0 >= zaiko){		
						error.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.fusoku"));
				}else if(99999999 < zaiko){
						error.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.suuziover"));
				}
			
//!!!				���G���[���b�Z�[�W��saveErrors ���g�p���܂��傤�B
					
//!!!				�Ȃ��A�����̓`�F�b�N�␔�l�`�F�b�N�A�����`�F�b�N�ȂǁA
//!!!				DB�ɐڑ����Ȃ��Ă��`�F�b�N�ł�����̂�ActionForm�ōs���܂��B
//!!!				ActionForm�Ń`�F�b�N�ς݂ł���΁AAction�ł̓`�F�b�N�͕s�v�ł��B
				saveErrors(request, error);
			}
			
			
			tasuForm.setMakerName(selectMaker);
			tasuForm.setShouhinCode(selectScode);
			tasuForm.setShouhinName(selectSname);
			
//!!!		�����́Ars.close() �� stmt.close()�Acon.close() �ł͗�O������
//!!!		����\��������܂��B
//!!!		���̂��߁A�S�Ă�close�͓Ɨ����� try�` catch���ōs���̂�������
//!!!		�����ł��B
//!!!		finally����������A������ close ���s���悤�ɂ��܂��傤�B
			
		}catch(Exception e){
			e.printStackTrace();
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
		tasuForm.setSelectList("");
		session.setAttribute("makerList", makerList);
		session.setAttribute("syoucodeList", syoucodeList );
		session.setAttribute("syounameList", syounameList );
		
		return mapping.findForward("hachuu");
	}

}
