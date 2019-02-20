package logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoutAction extends Action{
	public ActionForward execute(ActionMapping mapping , ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		
		HttpSession session=request.getSession(false);
		
		if(null!=session){
			session.invalidate();
		}
		
//!!!	����ʓI�ȃT�[�o�ł�30���ŃZ�b�V�����^�C���A�E�g�ƂȂ�A�Z�b�V������
//!!!	�����Ŗ���������܂��B
//!!!			
//!!!	getSession() �̈�����false �Ŏ��s�����ꍇ�A�L���ȃZ�b�V������
//!!!	������� null ���Ԃ���܂��B
//!!!	�Z�b�V�����^�C���A�E�g�Ȃǂɂ��A�Z�b�V����������������Ă����ꍇ�A
//!!!	invalidate()�����s����Ɨ�O(NullPointerException)���������A������
//!!!	�p���ł��Ȃ��Ȃ��Ă��܂��܂��B
//!!!	���̂悤�Ȃ��ƂɂȂ�Ȃ��悤�AgetSession()�̌��ʂ�null�łȂ��ꍇ�̂�
//!!!	invalidate() ���s���܂��傤�B
		
		return mapping.findForward("logout");
	}

}
