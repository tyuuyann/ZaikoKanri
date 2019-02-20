package syoukai;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class TasuForm extends ActionForm{
	private String makerName;
	private String shouhinCode;
	private String shouhinName;
	
	private String selectList;
	
	private String selectSub;		// メニューボタン
	
	private String m_mei;
	private String s_code;
	private String s_name;
	private String jutyu;
	
	private String touroku;
	private String push;
	
	
	public String getMakerName() {
		return makerName;
	}
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
	public String getShouhinCode() {
		return shouhinCode;
	}
	public void setShouhinCode(String shouhinCode) {
		this.shouhinCode = shouhinCode;
	}
	public String getShouhinName() {
		return shouhinName;
	}
	public void setShouhinName(String shouhinName) {
		this.shouhinName = shouhinName;
	}
	public String getSelectList() {
		return selectList;
	}
	public void setSelectList(String selectList) {
		this.selectList = selectList;
	}
	public String getSelectSub() {
		return selectSub;
	}
	public void setSelectSub(String selectSub) {
		this.selectSub = selectSub;
	}
	
	
	public String getJutyu() {
		return jutyu;
	}
	public void setJutyu(String jutyu) {
		this.jutyu = jutyu;
	}
	public String getM_mei() {
		return m_mei;
	}
	public void setM_mei(String m_mei) {
		this.m_mei = m_mei;
	}
	public String getS_code() {
		return s_code;
	}
	public void setS_code(String s_code) {
		this.s_code = s_code;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getTouroku() {
		return touroku;
	}
	public void setTouroku(String touroku) {
		this.touroku = touroku;
	}
	
	
	public String getPush() {
		return push;
	}
	public void setPush(String push) {
		this.push = push;
	}
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if("3".equals(selectList) && ! "on".equals(push)){
			try{
				int num = Integer.parseInt(jutyu);
				
				if(99999999 < num){
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.suuzimax"));
				}else if(0 >= num){
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.suuzimin"));
				}
			}catch(Exception e){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.suuzi"));
			}
			selectList="";
		}
		
		return errors;
	}
}
