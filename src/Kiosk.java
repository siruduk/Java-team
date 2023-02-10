import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.sql.DriverManager;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import javax.swing.*;

public class Kiosk extends JFrame {
	
	Kiosk k=this;
   public int [] day=new int[31];
   public int [] month=new int [12];
   // 메뉴 이름 및 가격 선언
   public String [] menu_coffee = new String[6];
   public int [] price_coffee = new int [6];
   public int menu=1;
   JButton [] bt_coffee = new JButton[6];
   JLabel [] lb_menu_coffee = new JLabel[6];
   JLabel [] lb_price_coffee = new JLabel[6];
   
   // 카테고리 Button 이름 및 객체 생성
   public String [] category = {"Coffee", "Beverage", "Flatccino", "Ade"};
   public JButton [] bt_category = new JButton[category.length];
   String path = System.getProperty("user.dir"); // path에 현재 작업 경로 입력
   
   // 삭제 버튼 이름 및 객체 생성
   public String [] delete = {"삭제", "초기화"};
   public JButton [] bt_delete = new JButton[delete.length];
   public JTable order_table;
   public JLabel total_price_1;
   public JLabel total_product_num;
   
   //관리자모드 진입 버튼
   public JButton [] bt_admin=new JButton[2];
   int pw=1;
   
    //추천
   public String [] ageLine = {"10대", "20대","30대", "40대"};
   public String [] sexLine = {"남성", "여성"};
   int age = 0;
   int sex = 0;
   JButton [] bt_age = new JButton[ageLine.length];
   JButton [] bt_sex = new JButton[sexLine.length];
   int[] xmm = {800,800,800};
   int[] ymm = {800,800,800};
   String[] best_Menu_Name = new String[3];
   static int[] x_Coordinate = {230,455,680,800};
   static int[] y_Coordinate = {0,269,800};
   
   Database db = new Database();
   private JPanel pn_menu;
   
   public void Best_Menu_Clear(){ //초기화
      for(int i=0; i<3; i++) {xmm[i] = 800; ymm[i] = 800;}
   }
      
   public void Best_Menu_AS(){ 
      try {
         //데이터베이스 연결
         Class.forName("com.mysql.cj.jdbc.Driver");
         db.con = DriverManager.getConnection(db.url, db.user, db.passwd);
         db.stmt = db.con.createStatement();
         db.viewData(sex,age,best_Menu_Name);      //데이터 조회
         
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
   }
   
   public void find_Location() {
      int cnt=0, xcnt=0 ,ycnt=0;
      
      String[] check_Menu = {"아메리카노", "카페라떼", "카페모카", "카라멜 마끼아또", "바닐라 라떼", "흑당 콜드브루",
                        "달고나 라떼", "초콜릿","화이트 초콜릿",
                        "토피넛 라떼", "초콜릿 칩 플랫치노", "녹차 플랫치노", "플레인 요거트 플랫치노",
                        "레몬에이드", "자몽에이드", "청포도에이드"};
      for(int i=0; i<check_Menu.length; i++) {
         for(int j=0; j<3; j++) {
            if(best_Menu_Name[j].equals(check_Menu[i])) {
               switch(menu) {
               case 1:
                  if(i == 0) { xcnt = 0; ycnt = 0; }
                  else if(i == 1) { xcnt = 1; ycnt = 0; }
                  else if(i == 2) { xcnt = 2; ycnt = 0; }
                  else if(i == 3) { xcnt = 0; ycnt = 1; }
                  else if(i == 4) { xcnt = 1; ycnt = 1; }
                  else if(i == 5) { xcnt = 2; ycnt = 1; }
                  else { xcnt = 3; ycnt = 2; }
                  break;
               case 2:
                  if(i == 6) { xcnt = 0; ycnt = 0; }
                  else if(i == 7) { xcnt = 1; ycnt = 0; }
                  else if(i == 8) { xcnt = 2; ycnt = 0; }
                  else { xcnt = 3; ycnt = 2; }
                  break;
               case 3:
                  if(i == 9) { xcnt = 0; ycnt = 0; }
                  else if(i == 10) { xcnt = 1; ycnt = 0; }
                  else if(i == 11) { xcnt = 2; ycnt = 0; }
                  else if(i == 12) { xcnt = 0; ycnt = 1; }
                  else { xcnt = 3; ycnt = 2; }
                  break;
               case 4:
                  if(i == 13) { xcnt = 0; ycnt = 0; }
                  else if(i == 14) { xcnt = 1; ycnt = 0; }
                  else if(i == 15) { xcnt = 2; ycnt = 0; }
                  else { xcnt = 3; ycnt = 2; }
                  break;
               }
               xmm[cnt] = x_Coordinate[xcnt];
               ymm[cnt] = y_Coordinate[ycnt];
               cnt++;
               break;
            }
         }
         if(cnt==3) break;
      }
   }
   
   public void get_Menu(){ 
      try {
         //데이터베이스 연결
         Class.forName("com.mysql.cj.jdbc.Driver");
         db.con = DriverManager.getConnection(db.url, db.user, db.passwd);
         db.stmt = db.con.createStatement();
         db.viewmenu(menu, menu_coffee, price_coffee);
         
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
   }
   
   
   public void updateprise() {//메뉴추가 삭제 초기화 이벤트시마다 값 갯수 변경
      int prise=0;
      DefaultTableModel m=(DefaultTableModel)order_table.getModel();
      int count=0;
      for(int i=1;i<m.getRowCount();i++) {
    	 count+=Integer.valueOf(m.getValueAt(i, 1).toString()); 
         prise+=Integer.valueOf(m.getValueAt(i, 2).toString());
      }
      
      total_product_num.setText(Integer.toString(count)+"개");
      total_price_1.setText(Integer.toString(prise)+"원");
      total_price_1.setName(Integer.toString(prise));
   }
   
   public void set_menu() {
      
      get_Menu();
      for(int i=0;i<6;i++) {
         if(menu_coffee[i]==null) {
            bt_coffee[i].setName("");
            bt_coffee[i].setText("");
            lb_price_coffee[i].setText("");
            lb_menu_coffee[i].setText("");
            bt_coffee[i].setVisible(false);
            lb_price_coffee[i].setVisible(false);
            lb_menu_coffee[i].setVisible(false);
         }
         else {
            bt_coffee[i].setName(menu_coffee[i]);
            bt_coffee[i].setText(Integer.toString(price_coffee[i]));
            lb_price_coffee[i].setText(String.valueOf(price_coffee[i]));
            lb_menu_coffee[i].setText(menu_coffee[i]);
            bt_coffee[i].setIcon(new ImageIcon(path+"\\image\\"+menu_coffee[i]+".png"));
            bt_coffee[i].setVisible(true);
            lb_price_coffee[i].setVisible(true);
            lb_menu_coffee[i].setVisible(true);

         }
      }
   }
   
   public void add_menu(JButton b) {
       DefaultTableModel model=(DefaultTableModel)order_table.getModel();
       boolean q=true;
       for(int i=0;i<model.getRowCount();i++)
       if(model.getValueAt(i,0).equals(b.getName())) {   
    	   model.setValueAt(Integer.parseInt(model.getValueAt(i, 1).toString())+1, i, 1);
    	   model.setValueAt(((Integer.parseInt(model.getValueAt(i, 1).toString())+1)*Integer.parseInt(b.getText())), i, 2);
    	   q=false;
    	   break;
       }//선택한 메뉴가 주문내역에 있으면 수량 +1

       if(q) {
    	   String [] a = {b.getName(),"1",b.getText()};
           model.addRow(a);
       }//아니면 주문내역에 추가
       updateprise();
   }
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Kiosk frame = new Kiosk();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   /**
    * Create the frame.
    */
   public Kiosk() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 1239, 692);
      
      JPanel pn_menu = new JPanel();

      pn_menu = new JPanel();
      pn_menu.setBackground(new Color(253, 234, 225));
      pn_menu.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(pn_menu);
      pn_menu.setLayout(null);
      
      
      //coffee 패널 생성
      JPanel pn_coffee = new JPanel();
      pn_coffee.setBackground(Color.WHITE);
      pn_coffee.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
      pn_coffee.setBounds(0, 102, 763, 539);
      pn_menu.add(pn_coffee);
      pn_coffee.setLayout(null);
      
      //
      //추천이미지생성
      ImageIcon bestImg = new ImageIcon(path+"\\image\\1111.png");
      
      JLabel[] bestImgLbl = {new JLabel(),new JLabel(),new JLabel()};
      
      for(int i=0; i<3; i++)
      {
         bestImgLbl[i].setIcon(bestImg);
          bestImgLbl[i].setBounds(xmm[i], ymm[i], 90, 90);
          bestImgLbl[i].setHorizontalAlignment(JLabel.CENTER);
           pn_coffee.add(bestImgLbl[i]);
      }
        
      for(int i=0; i<ageLine.length; i++) {
         bt_age[i] = new JButton(ageLine[i]);
         bt_age[i].setBounds(33, 35+(80*i), 80, 60);
         pn_coffee.add(bt_age[i]);
         bt_age[i].setForeground(Color.WHITE);
         bt_age[i].setFocusPainted(false);
         bt_age[i].setFont(new Font("메이플스토리 Bold", Font.PLAIN, 17));
         bt_age[i].setBackground(new Color(231, 95, 84));
         bt_age[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JButton b=(JButton)e.getSource();//이벤트가 발생한 버튼 확인
               if(b.getText().equals("10대")||b.getText().equals("20대")||b.getText().equals("30대")||b.getText().equals("40대")) {
                  for(int i=0; i<ageLine.length; i++) {
                     bt_age[i].setForeground(Color.WHITE);
                     bt_age[i].setBackground(new Color(231, 95, 84));
                  }
               }
               if(b.getText().equals("10대")) {
                  if(age == 1) age = 0;
                  else {
                     b.setForeground(Color.BLACK);
                     b.setBackground(Color.WHITE);
                     age = 1;
                  }
               }
               else if(b.getText().equals("20대")) {
                  if(age == 2) age = 0;
                  else {
                     b.setForeground(Color.BLACK);
                     b.setBackground(Color.WHITE);
                     age = 2;
                  }
               }
               else if(b.getText().equals("30대")) {
                  if(age == 3) age = 0;
                  else {
                     b.setForeground(Color.BLACK);
                     b.setBackground(Color.WHITE);
                     age = 3;
                  }
               }
               else if(b.getText().equals("40대")) {
                  if(age == 4) age = 0;
                  else {
                     b.setForeground(Color.BLACK);
                     b.setBackground(Color.WHITE);
                     age = 4;
                  }
               }
               if(age == 0 && sex == 0) Best_Menu_Clear();
               else {Best_Menu_AS(); find_Location();}
               for(int i=0; i<3; i++){
                  bestImgLbl[i].setBounds(xmm[i], ymm[i], 90, 90);
               }
            }
         });
      }   
      
      for(int i=0; i<sexLine.length; i++) {
         bt_sex[i] = new JButton(sexLine[i]);
         bt_sex[i].setBounds(33, 355+(80*i), 80, 60);
         pn_coffee.add(bt_sex[i]);
         bt_sex[i].setForeground(Color.WHITE);
         bt_sex[i].setFocusPainted(false);
         bt_sex[i].setFont(new Font("메이플스토리 Bold", Font.PLAIN, 14));
         bt_sex[i].setBackground(new Color(231, 95, 84));
         bt_sex[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JButton b=(JButton)e.getSource();//이벤트가 발생한 버튼 확인
               for(int i=0; i<2; i++) {
                  bt_sex[i].setForeground(Color.WHITE);
                  bt_sex[i].setBackground(new Color(231, 95, 84));
               }
               if(b.getText().equals("남성")) {
                  if(sex == 1) sex = 0;
                  else {
                     b.setForeground(Color.BLACK);
                     b.setBackground(Color.WHITE);
                     sex = 1;
                  }
                  
               }
               else if(b.getText().equals("여성")) {
                  if(sex == 2) sex = 0;
                  else {
                     b.setForeground(Color.BLACK);
                     b.setBackground(Color.WHITE);
                     sex = 2;
                  }
               }
               if(age == 0 && sex == 0) Best_Menu_Clear();
               else {Best_Menu_AS(); find_Location();}
               for(int i=0; i<3; i++){
                  bestImgLbl[i].setBounds(xmm[i], ymm[i], 90, 90);
               }
            }
         });
      }
      //
      
      for(int i=0; i<menu_coffee.length; i++) {
         // 메뉴 버튼 생성
         bt_coffee[i] = new JButton();
         bt_coffee[i].setFont(new Font("메이플스토리 Bold", Font.PLAIN, 0));
         bt_coffee[i].setVerticalAlignment(SwingConstants.TOP);
         bt_coffee[i].setFocusPainted(false);
         bt_coffee[i].setForeground(new Color(255,255,255));//text값 안보이게
         bt_coffee[i].addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               JButton b=(JButton)e.getSource();//이벤트가 발생한 버튼 확인
               add_menu(b);
            }
         });
         
         if(i<3) {
            bt_coffee[i].setBounds(140+(210*i), 10, 170, 242);
         }
         else {
            bt_coffee[i].setBounds(140+(210*(i-3)), 279, 170, 242);
         }
         
         bt_coffee[i].setBackground(Color.WHITE);
         bt_coffee[i].setOpaque(false);
         pn_coffee.add(bt_coffee[i]);
         bt_coffee[i].setVisible(true);
         
         // 이름 label 생성
         lb_menu_coffee[i] = new JLabel();
         lb_menu_coffee[i].setForeground(Color.BLACK);
         lb_menu_coffee[i].setHorizontalAlignment(SwingConstants.CENTER);
         lb_menu_coffee[i].setFont(new Font("메이플스토리 Bold", Font.PLAIN, 15));
         lb_menu_coffee[i].setHorizontalAlignment(JLabel.CENTER);
         if(i<3)
            lb_menu_coffee[i].setBounds(140+(210*i), 195, 170, 31);
         else
            lb_menu_coffee[i].setBounds(140+(210*(i-3)), 464, 170, 31);
         pn_coffee.add(lb_menu_coffee[i]);
         
         // 가격 label 생성
         lb_price_coffee[i] = new JLabel();
         lb_price_coffee[i].setHorizontalAlignment(SwingConstants.CENTER);
         lb_price_coffee[i].setForeground(Color.BLACK);
         lb_price_coffee[i].setFont(new Font("메이플스토리 Bold", Font.PLAIN, 15));
         lb_price_coffee[i].setHorizontalAlignment(JLabel.CENTER);
         if(i<3)
            lb_price_coffee[i].setBounds(150+(210*i), 221, 150, 31);
         else
            lb_price_coffee[i].setBounds(150+(210*(i-3)), 490, 150, 31);
         pn_coffee.add(lb_price_coffee[i]);
      }
      set_menu();
      
      

      
      // title과 카테고리를 붙일 panel생성
      JPanel pn_top = new JPanel();
      pn_top.setBackground(new Color(231, 95, 84));
      pn_top.setBounds(0, 0, 763, 102);
      pn_menu.add(pn_top);
      pn_top.setLayout(null);
      pn_top.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
      
      
      // 카테고리 버튼 추가
      for(int i=0; i<category.length; i++)
      {
         bt_category[i] = new JButton(category[i]);
         bt_category[i].setForeground(Color.WHITE);
         bt_category[i].setBorderPainted(false);
         bt_category[i].setFocusPainted(false);
         bt_category[i].setBackground(new Color(231, 95, 84));
         bt_category[i].setBounds(140+147*i, 53, 150, 49);
         bt_category[i].setFont(new Font("메이플스토리 Bold", Font.BOLD, 20));
         if(i==0) {
            bt_category[i].setForeground(Color.BLACK);
            bt_category[i].setBackground(Color.WHITE);
         }
         bt_category[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JButton b=(JButton)e.getSource();//이벤트가 발생한 버튼 확인
               for(int i=0; i<=3; i++) {
                  bt_category[i].setForeground(Color.WHITE);
                  bt_category[i].setBackground(new Color(231, 95, 84));
               }
               if(b.getText().equals("Coffee")) {
                  menu=1;
                  b.setForeground(Color.BLACK);
                  b.setBackground(Color.WHITE);
                  set_menu();
                  
               }
               else if(b.getText().equals("Beverage")) {
                  menu=2;
                  b.setForeground(Color.BLACK);
                  b.setBackground(Color.WHITE);
                  set_menu();
               }
               else if(b.getText().equals("Flatccino")) {
                  menu=3;
                  b.setForeground(Color.BLACK);
                  b.setBackground(Color.WHITE);
                  set_menu();
               }
               else if(b.getText().equals("Ade")) {
                  menu=4;
                  b.setForeground(Color.BLACK);
                  b.setBackground(Color.WHITE);
                  set_menu();
               }
               if(!(sex ==0 && age ==0)) {
                        find_Location();
                        for(int i=0; i<3; i++){
                            bestImgLbl[i].setBounds(xmm[i], ymm[i], 90, 90);
                            }
                    }
            }
         });
         pn_top.add(bt_category[i]);
      }
      
      // 키오스크 이름 Label 생성
      JLabel title = new JLabel("DEU KIOSK");
      title.setFont(new Font("메이플스토리 Bold", Font.BOLD, 20));
      title.setForeground(Color.WHITE);
      title.setBounds(307, 10, 148, 33);
      pn_top.add(title);   
      
      
      // 주문내역 패널 생성
      JPanel pn_order = new JPanel();
      pn_order.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
      pn_order.setBackground(Color.WHITE);
      pn_order.setBounds(781, 67, 428, 375);
      pn_menu.add(pn_order);
      pn_order.setLayout(null);
      
      
      // 주문내역 label생성
      JLabel lb_order = new JLabel("주문 내역");
      lb_order.setBounds(945, 10, 123, 55);
      pn_menu.add(lb_order);
      
      lb_order.setForeground(new Color(231, 95, 84));
      lb_order.setFont(new Font("메이플스토리 Bold", Font.BOLD, 27));
      
      // 결제 창 패널 생성
      JPanel pay = new JPanel();
      String[] a= {"메뉴","수량","가격"};
      DefaultTableModel m = new DefaultTableModel(a,0) {

         public boolean isCellEditable(int i, int c) {
             return false;
         } 

         };      
      m.addRow(a);
      order_table=new JTable(m);
      order_table.setRowHeight(30);
      pn_order.add(order_table);   
      order_table.setBounds(12, 10, 404, 355);
      order_table.setFont(new Font("메이플스토리 Bold", Font.BOLD, 20));
      pay.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
      pay.setBackground(Color.WHITE);
      pay.setBounds(781, 485, 430, 156);
      pn_menu.add(pay);
      pay.setLayout(null);
      DefaultTableCellRenderer d=new DefaultTableCellRenderer();
      d.setHorizontalAlignment(SwingConstants.CENTER);
      order_table.setDefaultRenderer(order_table.getColumnClass(0), d);
      order_table.setShowVerticalLines(false);
      order_table.setShowHorizontalLines(false);
      order_table.getColumn("메뉴").setPreferredWidth(210);
      order_table.getColumn("수량").setPreferredWidth(20);
      order_table.getColumn("가격").setPreferredWidth(50);

      
      // 총 선택된 상품 개수 Label 추가
      total_product_num = new JLabel("0개");
      JLabel total_product = new JLabel("선택된 상품   :   ");
      total_product_num.setFont(new Font("메이플스토리 Bold", Font.PLAIN, 21));
      total_product.setFont(new Font("메이플스토리 Bold", Font.PLAIN, 21));
      total_product_num.setBounds(174, 27, 142, 48);
      total_product.setBounds(12, 27, 166, 48);
      pay.add(total_product_num);
      pay.add(total_product);

      
      // 전체 결제 금액 Label 추가
      JLabel total_price = new JLabel("결제 금액      :    ");
      total_price_1 = new JLabel("0원");
      total_price.setFont(new Font("메이플스토리 Bold", Font.PLAIN, 21));
      total_price_1.setFont(new Font("메이플스토리 Bold", Font.PLAIN, 21));
      total_price.setBounds(12, 85, 166, 48);
      total_price_1.setBounds(174, 85, 142, 48);
      pay.add(total_price);
      pay.add(total_price_1);

      // 결제하기 버튼 추가
      JButton bt_pay = new JButton("결제하기");
      bt_pay.setForeground(Color.WHITE);
      bt_pay.setFocusPainted(false);   
      bt_pay.setFont(new Font("메이플스토리 Bold", Font.BOLD, 18));
      bt_pay.setBounds(280, 0, 150, 156);
      pay.add(bt_pay);
      bt_pay.setBackground(new Color(231, 95, 84));
      bt_pay.setIcon(new ImageIcon(path+"\\image\\124462 (1).png"));
      bt_pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(order_table.getRowCount()>1) {
            	receipt_class receipt = new receipt_class() ;
           	 	pay p=new pay(k,receipt);
           	 	p.setVisible(true);
           	 	
           	 	p.setModal(true);
           	 	if(receipt.pay) {
	           	 
	           	 if(receipt.receipt) {
	           		try {
	           		    OutputStream output = new FileOutputStream(path+"/receipt.txt");
	           		    String str="";
	           		    if(receipt.take_out)str+="포장\n";
	           		    else str+="매장\n";
	           		    for(int i=1;i<order_table.getRowCount();i++)
	           		     str+=order_table.getValueAt(i, 0)+" "+order_table.getValueAt(i, 1)+" "+order_table.getValueAt(i, 2)+"\n";
	           		    byte[] by=str.getBytes();
	           		    output.write(by);
	           				
	           		} catch (Exception k) {
	           	            System.out.println(k.toString());
	           		}
	           	 }

	           	try {
		               Class.forName("com.mysql.cj.jdbc.Driver");
		               	db.con = DriverManager.getConnection(db.url, db.user, db.passwd);
		                db.updatesale(Integer.parseInt(total_price_1.getName()));
		                DefaultTableModel m=(DefaultTableModel)order_table.getModel();
		                m.setNumRows(1);
		                updateprise();
		               }
		               catch(Exception k) {
		                System.out.println(k.toString());
		            }	           	 
	           	 	}
           	 	}
            }
            });
      
      //관리자 모드 열기
      for(int i=0;i<2;i++) {
         bt_admin[i]=new JButton(Integer.toString(i));
         bt_admin[i].setForeground(new Color(255,255,255));
         if(i==0) bt_admin[i].setBounds(307, 10, 110, 23);
         else bt_admin[i].setBounds(945, 20, 123, 37);
         pn_menu.add(bt_admin[i]);
         bt_admin[i].setBorderPainted(false);
         bt_admin[i].setContentAreaFilled(false);
         bt_admin[i].setFocusPainted(false);
         bt_admin[i].setOpaque(false);//버튼 투명화
         bt_admin[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JButton b=(JButton)e.getSource();
               if(b.getText().equals("0")&&pw==1||pw==2||pw==4)pw++;
               else if(b.getText().equals("1")&&pw==3)pw++;
               else pw=1;
               if(pw==5) {
                  new chart();
               }
               }
         });
      }
      

      // 삭제, 초기화 버튼 생성
      for(int i=0; i<delete.length; i++) {
            bt_delete[i] = new JButton(delete[i]);
            bt_delete[i].setBounds(1012+(100*i), 444, 97, 31);
            pn_menu.add(bt_delete[i]);
            bt_delete[i].setFocusPainted(false);
            bt_delete[i].setForeground(Color.WHITE);
            bt_delete[i].setFont(new Font("메이플스토리 Bold", Font.PLAIN, 17));
            bt_delete[i].setBackground(Color.BLACK);
            bt_delete[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton b=(JButton)e.getSource();//이벤트가 발생한 버튼 확인
                    if(b.getText().equals("삭제")) {
                        int row=order_table.getSelectedRow();
                        if(row==-1)return;
                        DefaultTableModel m=(DefaultTableModel)order_table.getModel();
                        m.removeRow(row);
                        updateprise();
                    }
                    else {
                        DefaultTableModel m=(DefaultTableModel)order_table.getModel();
                        m.setNumRows(1);
                        updateprise();
                    }
                }
            });
        }
      
      Dimension frameSize = getSize();
      Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation((windowSize.width - frameSize.width) / 2,
              (windowSize.height - frameSize.height) / 2);
   }
   
}