package login;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class LoginForm extends ActionForm{

//!!!	フィールドと ゲッター・セッターの並び順を統一して
//!!!	おきましょう。

	private String id;
	private String namae;
	private String pass;
	private String authority;
	private String corpname;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNamae() {
		return namae;
	}
	public void setNamae(String namae) {
		this.namae = namae;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if(isNull(id)){
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.idNull"));
		}
		if(isNull(pass)){
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.passNull"));
		}

		return errors;
	}

	private boolean isNull(String val){
		boolean boo = val == null || val.trim().length() == 0;
		return boo;
	}

}
