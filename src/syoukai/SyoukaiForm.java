package syoukai;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
;

public class SyoukaiForm extends ActionForm {
	private String maker;
	private String code;
	private String s_name;
	private String tanka;
	private String zaikosuu;
	private String fuku0="0";
	private String fuku1="0";
	private String fuku2="0";
	private String ijou0="3";
	private String ijou1="3";
	private String command;

	private String selectList;
	private String selectIndex;
	private String kousin;
	private String hanbetu;
	
	private String selectSyoukai;
	
	private String syona;
	private String makena;
	private String syoco;
	private int tan;
	private int zai;
	
	private int nowPage;		// 現在のページ数
	private int totalPage;
	

	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getFuku0() {
		return fuku0;
	}
	public void setFuku0(String fuku0) {
		this.fuku0 = fuku0;
	}
	public String getFuku1() {
		return fuku1;
	}
	public void setFuku1(String fuku1) {
		this.fuku1 = fuku1;
	}
	public String getFuku2() {
		return fuku2;
	}
	public void setFuku2(String fuku2) {
		this.fuku2 = fuku2;
	}
	public String getIjou0() {
		return ijou0;
	}
	public void setIjou0(String ijou0) {
		this.ijou0 = ijou0;
	}
	public String getIjou1() {
		return ijou1;
	}
	public void setIjou1(String ijou1) {
		this.ijou1 = ijou1;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getTanka() {
		return tanka;
	}
	public void setTanka(String tanka) {
		this.tanka = tanka;
	}
	public String getZaikosuu() {
		return zaikosuu;
	}
	public void setZaikosuu(String zaikosuu) {
		this.zaikosuu = zaikosuu;
	}
	public String getMakena() {
		return makena;
	}
	public void setMakena(String makena) {
		this.makena = makena;
	}
	public String getSyoco() {
		return syoco;
	}
	public void setSyoco(String syoco) {
		this.syoco = syoco;
	}
	
	
	public String getSelectSyoukai() {
		return selectSyoukai;
	}
	public void setSelectSyoukai(String selectSyoukai) {
		this.selectSyoukai = selectSyoukai;
	}
	
	public String getSyona() {
		return syona;
	}
	public void setSyona(String syona) {
		this.syona = syona;
	}
	public int getTan() {
		return tan;
	}
	public void setTan(int tan) {
		this.tan = tan;
	}
	public int getZai() {
		return zai;
	}
	public void setZai(int zai) {
		this.zai = zai;
	}
	public String getSelectList() {
		return selectList;
	}
	public void setSelectList(String selectList) {
		this.selectList = selectList;
	}
	public String getSelectIndex() {
		return selectIndex;
	}
	public void setSelectIndex(String selectIndex) {
		this.selectIndex = selectIndex;
	}
	
	
	public String getHanbetu() {
		return hanbetu;
	}
	public void setHanbetu(String hanbetu) {
		this.hanbetu = hanbetu;
	}
	public String getKousin() {
		return kousin;
	}
	public void setKousin(String kousin) {
		this.kousin = kousin;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	// 「BACK」ボタン表示・非表示の判定
	public boolean isBackButton() {
		// 現在のページが先頭ページでなければtrue
		boolean boo = nowPage>0 ;
		
		return boo;
	}
	
	public boolean isNextButton(){
		
		boolean boo = nowPage < totalPage-1;
		
		return boo;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if("2".equals(hanbetu)){
			int i;
			if(!isNull(tanka)){
				if(isNum(tanka)){
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.suuzi"));
				}else if(0>(i=Integer.parseInt(tanka))){
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.suuzimin"));
				}else if(8<tanka.length()){
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.suuzimax"));
				}
			}
			if("ZAIKOSYOUKAI".equals(selectSyoukai)){
				if(!isNull(zaikosuu)){
					if(isNum(zaikosuu)){
						errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.suuzi"));
					}else if(0>(i=Integer.parseInt(zaikosuu))){
						errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.suuzimin"));
					}else if(8<zaikosuu.length()){
						errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.suuzimax"));
					}
				}
			}
		}
		if("TUIKAACTION".equals(selectSyoukai)){
			if(isNull(maker)){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.tuikama"));
			}else if(20<maker.length()){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.makernameover"));
			}
			
			if(isNull(code)){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.tuikaco"));
			}else if(6<code.length()){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.codemoziover"));
			}
			
			if(isNull(s_name)){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.tuikasn"));
			}else if(20<s_name.length()){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.syouhinnameover"));
			}
			
			if(isNull(tanka)){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.tuikatan"));
			}else if(isNum(tanka)){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.tuikanum"));
			}else if(8<tanka.length()){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.suuzimax"));
			}
		
		}
		return errors;
	}
	
	private boolean isNull(String val){
		boolean boo = val == null || val.trim().length() == 0;
		return boo;
	}
	
	private boolean isNum(String val){
		boolean boo =false;
		try{
			int i=Integer.parseInt(val);
		}catch(Exception e){
			boo=true;
		}
		
		return boo;
	}
	
	private boolean Non(int val){
		
		boolean boo=false;
		if(0==val){
			boo=true;
		}
		
		return boo;
	}

}
