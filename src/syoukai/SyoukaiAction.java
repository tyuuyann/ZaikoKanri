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
		
		int all = list.size();					// 取得したデータ数
		int totalPage = (all - 1) / 10 + 1; 	// 総ページ数
		// 現在のページ数
		int now=syou.getNowPage();
		
		now=SyoukaiMatome.page(syou.getCommand(),now);
		// 現在のページ数をセットする
		syou.setNowPage(now);
		syou.setTotalPage(totalPage);
		session.setAttribute("start",now*10);
		request.setAttribute("syoukai",list);
		
		return mapping.findForward("kekka");
	}

}
//!!!	↑ print文が残っていますが、デバッグを行うために print 文を使うのは
//!!!	古くからの常套手段で、最も基本的で判りやすい検証方法です。
//!!!	しかしデバッグが済んでしまえば邪魔な情報ですし、ソースを見づらくする
//!!!	原因でもあります。
//!!!	そのような問題を解決してくれるのが log4Jというライブラリです。
//!!!	Log4jでは、5つの出力レベルというものがありそれぞれ、
//!!!	
//!!!	DEBUG:
//!!!	INFO:
//!!!	WARN:
//!!!	ERROR:
//!!!	FATAL:
//!!!	
//!!!	等のように使います（そう決まっているわけではありません）。
//!!!	
//!!!	System.out.println 文の代わりに、logger.debug や logger.info と言った
//!!!	メソッドを使用して情報を出力するように記述します。
//!!!	
//!!!	log4j.properties や log4j.xml といったLog4jの出力設定ファイルを適切に
//!!!	記述し、出力レベルを DEBUG 等に設定します。
//!!!	そうすれば開発の初期段階では、変数の値など細かい情報が全て出力されます。
//!!!	開発が進み、デバッグの必要があまり無くなって来たら、出力レベルを INFO に
//!!!	引き上げます。
//!!!	すると debug メソッドでのログの出力は止まり、info 以上のメソッドのログだけ
//!!!	が出力されるようになります。
//!!!	更に開発が進み、infoの情報も不要になったら、出力レベルを WARN にすれば、
//!!!	debug と info のログも出力されなくなります。
//!!!	
//!!!	もしバグが発生し再び細かい情報が必要になったら、出力レベルをDEBUGに戻して
//!!!	再起動するだけで、ソースを書き換えたりコンパイルし直す事無くログの出力を
//!!!	制御できるわけです。