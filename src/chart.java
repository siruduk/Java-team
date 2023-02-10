import java.awt.*;
import java.sql.DriverManager;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class chart extends JFrame
{
	 /**
	 * 
	 */
	int d=LocalDate.now().getDayOfMonth();
	int m=LocalDate.now().getMonthValue();
	private static final long serialVersionUID = 1L;
	int month[]=new int[m];
	int mc=100000;//0의자릿수 나눗셈
	int mcheck=2;//길이조절
	///한달의 높낮이 조절용
	
	
	
	int day[]=new int[d];
	int dcheck=2;//길이조절
	int dc=10000;//0의자릿수 나눗셈
	///하루의 높낮이 조절용
	
	
	int yesterday;
	int today;//오후 판매액
	
	Color col=new Color(231, 95, 84);
	
	class DrawingPanel extends JPanel//월 매출
	{
	    
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			g.clearRect(0,0,getWidth(),getHeight());
			g.setColor(col);
	     
	        g.setColor(col);
	        g.fillRect(10,0,60,25);
	        g.setFont(new Font("메이플스토리 BOLD", Font.BOLD, 14));
	        g.setColor(Color.white);
	       
	        g.drawString("일일매출",11,15);
	        g.setColor(Color.black);
	        g.drawRect(60,30,610,250);
	        g.drawLine(60,250,670,250);	  
	        
	        int []dscore = new int[day.length];
	        for(int i=0;i<day.length;i++) {
	        	dscore[i]=day[i]/dc*dcheck;
	        }
	        
	        
	        for(int cnt = 1 ;cnt<11;cnt++)
	        {
	        	g.setFont(new Font("메이플스토리 BOLD", Font.BOLD, 12));
	            g.drawString(cnt *10 +"",20,255-20*cnt);
	        }
	        
	        for(int i=0;i<d;i++) {	        	
	        	int x=77+i*18;//일별 x 좌표
	        	if(i%2==0)
	        	g.drawString(Integer.toString(i+1)+"일",x,270);
	        }
	        for(int i=0;i<dscore.length;i++) {
	        	
	        	int x=80+i*18;//일별 x좌표 
	        	g.setColor(new Color(250, 154+3*i, 154));
	        	g.fillRect(x,250-dscore[i],18,dscore[i]);
	        	g.setColor(Color.black);
	        	g.drawRect(x,250-dscore[i],18,dscore[i]);
	        }
	        
	        
	    }
	}
	class Drawing extends JPanel
	{
	    
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			 	g.clearRect(0,0,getWidth(),getHeight());
		        g.clearRect(0,0,getWidth(),getHeight());
				g.setColor(col);		        
				g.fillRect(10,10,60,25);
		        
		        g.setColor(Color.white);
		        g.setFont(new Font("메이플스토리 BOLD", Font.BOLD, 14));
		        g.drawString("월간매출",11,24);
		        
		        
		        g.setColor(Color.BLACK);
		        g.drawRect(61,40,380,210);
		        g.drawRect(61,40,380,235);
		        
		        g.setFont(new Font("메이플스토리 BOLD", Font.BOLD, 12));
		        
		        int score[]=new int[month.length];//달 길이 조절        
		        for(int i=0;i<month.length;i++) {
		        	score[i]=month[i]/mc*mcheck;
		        }
		        //그래프 길이 조절용
		        
		        
		        for(int i = 1 ;i<11;i++)
		        {
		            g.drawString(i *100 +"",20,255-20*i); 
		        }
		        
		        for(int i=0;i<score.length-1;i++) {
		        	int month=80+i*30;//월별 x 좌표
		        	int nextmonth=month+30;		        	
		        	g.drawLine(month,250-score[i],nextmonth,250-score[i+1]);
		        	g.fillOval(nextmonth-2, 248-score[i+1], 4,4);
		        }
		        for(int i=0;i<m;i++) {
		        	
		        	g.setColor(Color.BLACK);		        	
		        	g.drawString(Integer.toString(i+1)+"월",70+i*30,270);
		        	
		        }
	        
	    }
	}
	//전일대비
	class CDrawing extends JPanel
	{
	    
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			g.clearRect(0,0,getWidth(),getHeight());			
			g.setColor(col);
	        
	        g.fillRect(10,10,60,25);	        
	        g.setColor(Color.white);
	        g.setFont(new Font("메이플스토리 BOLD", Font.BOLD, 14));
	        g.drawString("전일대비",11,24);
	        g.setColor(Color.black);
	        
	        
	        
	        int a=getWidth()-80;//길이조절용 임시 정수
	        g.drawRect(60,40,a-60,220);
	        for(int i = 1 ;i<LocalDateTime.now().getMonthValue()-1;i++)
	        {
	        	g.setFont(new Font("메이플스토리 BOLD", Font.BOLD, 12));
	            g.drawString(i *10 +"만",20,265-20*i);
	        }
	        
	    	//전날
	        int money=yesterday*dcheck/dc;//그래프 길이
	        g.setColor(Color.blue);
        	g.fillRect(80,260-money,40,money);
        	g.setColor(Color.black);
        	g.drawRect(80,260-money,40,money);
        	g.setFont(new Font("메이플스토리 BOLD", Font.BOLD, 12));
	        g.drawString("어제",88,259-money);
	        
	        
        	//당일
        	int nextmoney=today*dcheck/dc;//그래프 길이
        	g.setColor(Color.red);
        	g.fillRect(150,260-nextmoney,40,nextmoney);
        	g.setColor(Color.black);
        	g.drawRect(150,260-nextmoney,40,nextmoney);
        	g.setFont(new Font("메이플스토리 BOLD", Font.BOLD, 12));
	        g.drawString("오늘",158,259-nextmoney);
	    }
	}
	public void set_score(){
		Database db=new Database();
		try {	
			//데이터베이스 연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			db.con = DriverManager.getConnection(db.url, db.user, db.passwd);
			db.stmt = db.con.createStatement();
			db.viewsales(month, day);
		} catch(Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				db.stmt.close();
				db.con.close();				
			} catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		int d=LocalDate.now().getDayOfMonth();
		if(d!=1) yesterday=day[d-2];
		today=day[d-1];
	}
	public chart() {
		getContentPane().setForeground(new Color(255, 128, 64));
		set_score();
		setTitle("graph");
        setSize(800,700);        
        Container contentPane = getContentPane();
        
        
        DrawingPanel drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(800, 300));
        contentPane.add(drawingPanel, BorderLayout.SOUTH);
        
        Drawing panel = new Drawing();
        panel.setBackground(new Color(255, 128, 64));
        panel.setPreferredSize(new Dimension(484, 10));
        getContentPane().add(panel, BorderLayout.WEST);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(col);
        panel_1.setPreferredSize(new Dimension(10, 70));
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        JLabel lblNewLabel = new JLabel("매출");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("메이플스토리 BOLD", Font.BOLD, 30));
        lblNewLabel.setPreferredSize(new Dimension(80, 60));
        panel_1.add(lblNewLabel);
        
       
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
	    CDrawing panel_2 = new CDrawing();
        panel_2.setBackground(new Color(255, 128, 128));
        panel_2.setPreferredSize(new Dimension(300, 10));
        getContentPane().add(panel_2, BorderLayout.EAST);
        
        setVisible(true);
        
       	}
	
	
}
