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
		
//!!!	↑一般的なサーバでは30分でセッションタイムアウトとなり、セッションは
//!!!	自動で無効化されます。
//!!!			
//!!!	getSession() の引数をfalse で実行した場合、有効なセッションが
//!!!	無ければ null が返されます。
//!!!	セッションタイムアウトなどにより、セッションが無効化されていた場合、
//!!!	invalidate()を実行すると例外(NullPointerException)が発生し、処理が
//!!!	継続できなくなってしまいます。
//!!!	このようなことにならないよう、getSession()の結果がnullでない場合のみ
//!!!	invalidate() を行いましょう。
		
		return mapping.findForward("logout");
	}

}
