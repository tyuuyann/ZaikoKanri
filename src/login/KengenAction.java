//!!!	���̂悤�Ȍ`�ŃR�����g���L�q���Ă����܂��B
//!!!	��{�I�ɂǂ�����ӏ��Ŏw�E���ꂽ���e�͑S�Ẵ\�[�X�ł����l�ł��B
//!!!	��x�S�Ẵ\�[�X�̃R�����g��ǂ݁A�ǂ̂悤�ȕ��j�ŏC�����s���̂�
//!!!	���߂Ă���C���Ɏ��|����܂��傤�B

package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class KengenAction extends Action{
	public ActionForward execute(ActionMapping mapping , ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LoginForm login=(LoginForm)form;
//		if("�L".equals(login.getAuthority())){
//!!!	��equals���\�b�h���g�p����Ƃ��́Anull�ɂȂ�Ȃ��l�Ŏ��s���A
//!!!	null�ɂȂ�\����������̂������ɂ��܂��傤�B
//			if("ari".equals(login.getKengen())){
//				login.setKengen("nasi");
//			}else{
//				login.setKengen("ari");
//			}
//		}else{
//			login.setKengen("nasi");
//		}

//		request.setAttribute("kengen",login.getKengen());
		return mapping.findForward("authority");
	}

}
