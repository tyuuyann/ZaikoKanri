package syoukai;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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


public class SyoukaiAction extends Action{
	
	public ActionForward execute(ActionMapping mapping , ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ArrayList list=new ArrayList();
		HttpSession session =request.getSession();
		String param=(String)session.getAttribute("selectSyoukai");
		SyoukaiForm syou=(SyoukaiForm)form;
		if("1".equals(syou.getHanbetu())){
			syou.setHanbetu("2");

			syou.setSelectSyoukai(syou.getSelectSyoukai());
			session.setAttribute("selectSyoukai",syou.getSelectSyoukai());
			return mapping.findForward("syoukaigamen");
		}
	

		if(!"ZAIKOSYOUKAI".equals(param)){
			syou.setZaikosuu("");
		}
		String []fuku ={syou.getFuku0() , syou.getFuku1() , syou.getFuku2() , syou.getIjou0() , syou.getIjou1()};
		String []kensaku = {syou.getMaker(),syou.getCode(),syou.getS_name(),syou.getTanka(),syou.getZaikosuu()};
		int kazu1=0;
		int kazu2=0;
		if(!"".equals(kensaku[3])){
			kazu1=Integer.parseInt(kensaku[3]);
		}
		if(!"".equals(kensaku[4])){
			kazu2=Integer.parseInt(kensaku[4]);
		}
		
		String []yobu = {"m_name","s_code","s_name","s_price","o_stock"};
		
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs= null;
		try{
			Class.forName(SyoukaiMatome.oracle);
			con = DBconnection.getConnection();
			StringBuffer sql = new StringBuffer("select * from ZAIKO_VIEW");

			sql=SyoukaiMatome.kensaku(sql,kensaku,fuku,yobu);
			ps=con.prepareStatement(sql.toString());
			int cnt=1;
			
			for(int i=0; i<kensaku.length; i++){
				if(i==3 && !kensaku[i].equals("")){
					ps.setInt(cnt,kazu1);
					cnt++;
				}else if(i==4 && !kensaku[i].equals("")){
					ps.setInt(cnt,kazu2);
				}else if(!kensaku[i].equals("")){
					ps.setString(cnt,"%"+kensaku[i]+"%");
					cnt++;
				}
			}
			
			rs=ps.executeQuery();

			while(rs.next()){
				SyoukaiForm kai=new SyoukaiForm();
				kai.setMakena(rs.getString("M_name"));
				kai.setSyoco(rs.getString("S_code"));
				kai.setSyona(rs.getString("S_name"));
				kai.setTan(rs.getInt("S_price"));
				kai.setZai(rs.getInt("O_stock"));
				list.add(kai);
			}
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
		
		int all = list.size();					// �擾�����f�[�^��
		int totalPage = (all - 1) / 10 + 1; 	// ���y�[�W��
		// ���݂̃y�[�W��
		int now=syou.getNowPage();
		
		now=SyoukaiMatome.page(syou.getCommand(),now);
		// ���݂̃y�[�W�����Z�b�g����
		syou.setNowPage(now);
		syou.setTotalPage(totalPage);
		session.setAttribute("start",now*10);
		request.setAttribute("syoukai",list);
		
		return mapping.findForward("kekka");
	}

}
//!!!	�� print�����c���Ă��܂����A�f�o�b�O���s�����߂� print �����g���̂�
//!!!	�Â�����̏퓅��i�ŁA�ł���{�I�Ŕ���₷�����ؕ��@�ł��B
//!!!	�������f�o�b�O���ς�ł��܂��Ύז��ȏ��ł����A�\�[�X�����Â炭����
//!!!	�����ł�����܂��B
//!!!	���̂悤�Ȗ����������Ă����̂� log4J�Ƃ������C�u�����ł��B
//!!!	Log4j�ł́A5�̏o�̓��x���Ƃ������̂����肻�ꂼ��A
//!!!	
//!!!	DEBUG:
//!!!	INFO:
//!!!	WARN:
//!!!	ERROR:
//!!!	FATAL:
//!!!	
//!!!	���̂悤�Ɏg���܂��i�������܂��Ă���킯�ł͂���܂���j�B
//!!!	
//!!!	System.out.println ���̑���ɁAlogger.debug �� logger.info �ƌ�����
//!!!	���\�b�h���g�p���ď����o�͂���悤�ɋL�q���܂��B
//!!!	
//!!!	log4j.properties �� log4j.xml �Ƃ�����Log4j�̏o�͐ݒ�t�@�C����K�؂�
//!!!	�L�q���A�o�̓��x���� DEBUG ���ɐݒ肵�܂��B
//!!!	��������ΊJ���̏����i�K�ł́A�ϐ��̒l�ȂǍׂ�����񂪑S�ďo�͂���܂��B
//!!!	�J�����i�݁A�f�o�b�O�̕K�v�����܂薳���Ȃ��ė�����A�o�̓��x���� INFO ��
//!!!	�����グ�܂��B
//!!!	����� debug ���\�b�h�ł̃��O�̏o�͎͂~�܂�Ainfo �ȏ�̃��\�b�h�̃��O����
//!!!	���o�͂����悤�ɂȂ�܂��B
//!!!	�X�ɊJ�����i�݁Ainfo�̏����s�v�ɂȂ�����A�o�̓��x���� WARN �ɂ���΁A
//!!!	debug �� info �̃��O���o�͂���Ȃ��Ȃ�܂��B
//!!!	
//!!!	�����o�O���������Ăэׂ�����񂪕K�v�ɂȂ�����A�o�̓��x����DEBUG�ɖ߂���
//!!!	�ċN�����邾���ŁA�\�[�X��������������R���p�C�����������������O�̏o�͂�
//!!!	����ł���킯�ł��B