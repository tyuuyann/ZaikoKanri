//!!!	このような形でコメントを記述していきます。
//!!!	基本的にどこか一箇所で指摘された内容は全てのソースでも同様です。
//!!!	一度全てのソースのコメントを読み、どのような方針で修正を行うのか
//!!!	決めてから修正に取り掛かりましょう。

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
//		if("有".equals(login.getAuthority())){
//!!!	↑equalsメソッドを使用するときは、nullにならない値で実行し、
//!!!	nullになる可能性があるものを引数にしましょう。
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
