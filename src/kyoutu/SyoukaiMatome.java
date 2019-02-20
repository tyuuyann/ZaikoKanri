package kyoutu;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class SyoukaiMatome {
	public static final String oracle="oracle.jdbc.driver.OracleDriver";
	public static int page(String str0 ,int i){
		if("KENSAKU".equals(str0)){
			i=0;
		}
		
		if("NEXT".equals(str0)){
			i++;
		}
		
		// 「BACK」ボタンが押されていたら、現在のページ数に -1 する
		if("BACK".equals(str0)){
			i--;
		}
		return i;
	}
	
	public static StringBuffer kensaku(StringBuffer sb,  String[] str ,String[] str1,String []str2){
		String mozi=" where";
		for(int i=0; i<str.length; i++){
			if(!str[i].equals("")){
				if(str1[i].equals("0")){
					sb.append(mozi+" "+str2[i]+" like ?");
					mozi=" and ";
				}else if(str1[i].equals("1")){
					sb.append(mozi+" not( "+str2[i]+" like ? )");
					mozi=" and ";
				}else if(str1[i].equals("3")){
					sb.append(mozi+" "+str2[i]+" >= ? ");
					mozi=" and ";
				}else if(str1[i].equals("4")){
					sb.append(mozi+" "+str2[i]+" < ?");
					mozi=" and ";
				}
			}
		}
		sb.append(" order by m_name ");
		
		return sb;
	}
	
	public static ActionMessages error(){
		ActionMessages error = new ActionMessages();
		error.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.error"));
		
		return error;
	}
	
	public static ActionMessages test(){
		ActionMessages error = new ActionMessages();
		error.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.user"));
		
		return error;
	}
	

}
