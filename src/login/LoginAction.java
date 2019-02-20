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

//!!!		���R�l�N�V�����̎擾�ł����A���̏����Ɠ����L�q�͕����̉ӏ��ŋL�q�����
//!!!		����͂��ł��B
//!!!		���̂悤�ȏ����͑S�Ẵ\�[�X�ɏ����̂ł͂Ȃ��A�K���ȋ��ʃN���X���쐬���A
//!!!		���̃N���X��static���\�b�h���쐬���A���̖߂�l�Ƃ���Connection�N���X��
//!!!		�C���X�^���X��ԋp����悤�ɂ��܂��B���̂悤���Ďg�p����ۂ́A�Ⴆ��
//!!!		DBConnection�ƌ����N���X���쐬���AgetConnection �Ƃ������\�b�h���쐬���āA
//!!!
//!!!		Connection conn = DBConnection.getConnection();
//!!!
//!!!		�̂悤�ɂ��邾���ŗǂ��Ȃ�܂��B
//!!!
//!!!		���̂悤�ɂ��郁���b�g�͎�ɓ�ŁA1�͒P���Ƀ\�[�X�̋L�q�ʂ����鎖�ŁA
//!!!		����1�͉��Ƀf�[�^�x�[�X�T�[�o�̖��O�⃆�[�U���ASID�ȂǕύX�̉\����
//!!!		������̂��ύX���ꂽ���ɂ��A1�̃\�[�X�����C������΍ςނƂ������Ƃł��B
//!!!
//!!!		���������𕡐��̉ӏ��ɋL�q����ꍇ�A�܂Ƃ߂鎖���l���܂��傤�B

			String loginSql = SqlData.LOGINSQL;
			stm=con.prepareStatement(loginSql);


			stm.setString(1,login.getId());
			stm.setString(2,login.getPass());

			rs=stm.executeQuery();

//!!!				��id �� pass �͓��͂��ꂽ�l����擾�ł��Ă���͂��ł��̂ŁA
//!!!				DB����擾����K�v�͖����ł��B


			if(rs.next()){
				login.setId(rs.getString("id"));
				login.setNamae(rs.getString("name"));
				login.setAuthority(rs.getString("authority"));
				login.setCorpname(rs.getString("corpname"));
			}else{
				saveErrors(request, SyoukaiMatome.test());

				return mapping.findForward("miss");
			}


//!!!		��id �� pass �������Ɏw�肵�ăf�[�^���擾����ꍇ�A
//!!!		id�͈�ӂȂ̂ŁA�擾�ł���f�[�^���͂P�� ���� �O���ł��B
//!!!		while �ł͂Ȃ�if���ɂ��Aelse �� �f�[�^���擾�ł��Ȃ��ꍇ�̏�����
//!!!		�������Ă��������B
//!!!		�܂��A�ȉ���if���͕s�v�ɂȂ�܂��B

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
//!!!	�����̂܂܂ł́A��O���������Ă��Ă������\������Ȃ��̂ŁA�ǂ��ŗ�O��
//!!!	�������Ă���̂���������Ȃ��Ă��܂��܂��B
//!!!
//!!!	�܂��A��O�����������ۂɁAcatch��printStackTrace()�����ŏI����Ă��܂��Ă�
//!!!	�����܂���B
//!!!	���͒l�� Integer.parseInt ���鎞�ȂǁA�������Ă���薳���ꍇ�͍\���܂��񂪁A
//!!!	����̂悤�ɒv���I�ȃG���[�����������ꍇ�AprintStackTrace�⃍�O�o�͂����ł�
//!!!	�V�X�e���̎g�p��(���[�U)�ɂ͉��̏����n��Ȃ����߂ł��B
//!!!
//!!!	�G���[�ɂ͎�Ɉȉ��̓��ނ�����܂��B
//!!!
//!!!	�@���͒l�̌��ȂǁA���[�U���ē��͂��鎖�Ōp���\�ȃG���[
//!!!	�A�f�[�^�x�[�X��Q�ȂǁA���[�U����������Ă��񕜕s�\�ȃG���[
//!!!
//!!!	�@�͍ē��͂𑣂����b�Z�[�W��\�����Č��̉�ʂɖ߂邾���Ōp�����ł��܂��B
//!!!	�A�́u�v���I�ȃG���[���������܂����B�Ǘ��҂ɂ��₢���킹�������v���̃G���[��
//!!!	�\������G���[��ʂɑJ�ڂ��A���̍ۊǗ��҂��ォ�猴������肷�邽�߂̎肪����Ƃ���
//!!!	���O���o�͂���悤�ɂ��܂��B�Ȃ��A��ʂɃG���[�̏ڍׂ�\�����鎖�͕��ʂ͂��܂���B


//		session.setAttribute("test",login);
//!!!	��session�X�R�[�v�ւ̊i�[�́Astruts-config.xml�ōs���܂��傤�B

		return mapping.findForward("success");
	}

}
