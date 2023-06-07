import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Database {
	Connection con = null;
	Statement stmt = null;
	static String url = "jdbc:mysql://Localhost:3306/test";	
	static String user = "shiru";
	static String passwd = "1234";

	
	//조회
	void viewData(int a,int b,String[] c) {	
		try {
			String[] sex_Name = {"남여","남자","여자"};
			String[] age_Name = {"전체","10대","20대","30대","40대"};
			String viewStr1 = "SELECT * FROM "+sex_Name[a]+" ORDER BY "+age_Name[b]+" DESC";
			ResultSet result1 = stmt.executeQuery(viewStr1);
			int cnt1 = 0;
			String[] d = new String[16];
			
			while(result1.next()) {
				d[cnt1]=result1.getString("메뉴");
				cnt1++;
			}
			for(int i=0; i<3; i++) c[i] = d[i];
			
		} catch(Exception e) {
			System.out.println("데이터 조회 실패 이유 : " + e.toString());
		}
	}
	void viewsales(int []month,int[]day) {
		try {			
				int cnt1 =0;
				String viewStr1 = "SELECT 매출 FROM 월 where no <= "+LocalDate.now().getMonthValue();
				ResultSet result1 = stmt.executeQuery(viewStr1);
				while(result1.next()) {
					if(month.length<cnt1)break;
					month[cnt1]=result1.getInt(1);
					cnt1++;
				}
				viewStr1 = "SELECT 매출 FROM 일 where no <= "+LocalDate.now().getDayOfMonth();
				result1 = stmt.executeQuery(viewStr1);
				cnt1 =0;
				while(result1.next()) {
					day[cnt1]=result1.getInt("매출");
					cnt1++;
				}
			}
		catch(Exception e) {
			System.out.println("데이터 조회 실패 이유 : " + e.toString());
		}
	}
	void viewmenu(int i,String[]menu,int[]prise) {
		try {
			String viewStr1 = "SELECT 메뉴,가격 FROM 메뉴 where 종류 = "+ i;

			ResultSet result1 = stmt.executeQuery(viewStr1);
			int cnt1 = 0;
			String[] d = new String[16];
			
			while(result1.next()) {
				menu[cnt1]=result1.getString("메뉴");
				prise[cnt1]=result1.getInt("가격");
				cnt1++;
			}
			while(cnt1<6) {
				menu[cnt1]=null;
				cnt1++;
			}
			
		} catch(Exception e) {
			System.out.println("데이터 조회 실패 이유 : " + e.toString());
		}
	}
	void updatesale(int sale) {
		try {
			String viewStr1 = "UPDATE test.일 set 매출=매출+"+sale+" where no="+LocalDate.now().getDayOfMonth();
			stmt = con.prepareStatement(viewStr1);
			int a=stmt.executeUpdate(viewStr1);
			viewStr1 = "UPDATE test.월 set 매출=매출+"+sale+" where no="+LocalDate.now().getMonthValue();
			stmt = con.prepareStatement(viewStr1);
			a=stmt.executeUpdate(viewStr1);
			
		} catch(Exception e) {
			System.out.println("데이터 조회 실패 이유 : " + e.toString());
		}
		

	}
	

}
