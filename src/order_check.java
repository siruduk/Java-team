import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class order_check extends JDialog {
	
	   String path = System.getProperty("user.dir"); // path에 현재 작업 경로 입력

	   
	public order_check(pay pay,receipt_class receipt) {
		super(pay,"",true);
		setAlwaysOnTop(true);
		getContentPane().setLayout(null);
		
		JPanel pn_pay = new JPanel();
		pn_pay.setBounds(0, 0, 350, 256);
		pn_pay.setBackground(new Color(255, 255, 255));
		getContentPane().add(pn_pay);
		pn_pay.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(231, 95, 84));
		panel.setBounds(0, 0, 340, 40);
		pn_pay.add(panel);
		
		JButton bt_takeout = new JButton("");
		bt_takeout.setBorderPainted(false);
		bt_takeout.setBackground(Color.WHITE);
		bt_takeout.setBounds(10, 82, 90, 90);
		pn_pay.add(bt_takeout);
		
		JButton bt_takein = new JButton("");
		bt_takein.setBorderPainted(false);
		bt_takein.setBackground(Color.WHITE);
		bt_takein.setBounds(10, 82, 90, 90);
		pn_pay.add(bt_takein);
		
		JButton bt_card = new JButton("");
		bt_card.setBorderPainted(false);
		bt_card.setBackground(Color.WHITE);
		bt_card.setBounds(124, 82, 90, 90);
		pn_pay.add(bt_card);
		
		JButton bt_cash = new JButton("");
		bt_cash.setBorderPainted(false);
		bt_cash.setBackground(Color.WHITE);
		bt_cash.setBounds(124, 82, 90, 90);
		pn_pay.add(bt_cash);
		
		JButton bt_receiptN = new JButton("");
		bt_receiptN.setBorderPainted(false);
		bt_receiptN.setBackground(Color.WHITE);
		bt_receiptN.setBounds(238, 82, 90, 90);
		pn_pay.add(bt_receiptN);
		
		JButton bt_receiptY = new JButton("");
		bt_receiptY.setBorderPainted(false);
		bt_receiptY.setBackground(Color.WHITE);
		bt_receiptY.setBounds(238, 82, 90, 90);
		pn_pay.add(bt_receiptY);
		
		JLabel lb_takeout = new JLabel("포장");
		lb_takeout.setHorizontalAlignment(SwingConstants.CENTER);
		lb_takeout.setFont(new Font("메이플스토리", Font.PLAIN, 17));
		lb_takeout.setBounds(10, 182, 90, 25);
		pn_pay.add(lb_takeout);
		
		JLabel lb_takein = new JLabel("매장 내");
		lb_takein.setHorizontalAlignment(SwingConstants.CENTER);
		lb_takein.setFont(new Font("메이플스토리", Font.PLAIN, 17));
		lb_takein.setBounds(12, 182, 90, 25);
		pn_pay.add(lb_takein);
		
		JLabel lb_card = new JLabel("카드");
		lb_card.setHorizontalAlignment(SwingConstants.CENTER);
		lb_card.setFont(new Font("메이플스토리", Font.PLAIN, 17));
		lb_card.setBounds(124, 182, 90, 25);
		pn_pay.add(lb_card);
		
		JLabel lb_cash = new JLabel("현금");
		lb_cash.setHorizontalAlignment(SwingConstants.CENTER);
		lb_cash.setFont(new Font("메이플스토리", Font.PLAIN, 17));
		lb_cash.setBounds(124, 182, 90, 25);
		pn_pay.add(lb_cash);
		
		JLabel lb_receiptY = new JLabel("영수증 출력");
		lb_receiptY.setHorizontalAlignment(SwingConstants.CENTER);
		lb_receiptY.setFont(new Font("메이플스토리", Font.PLAIN, 17));
		lb_receiptY.setBounds(238, 182, 90, 25);
		pn_pay.add(lb_receiptY);
		
		JLabel lb_receiptN = new JLabel("영수증 미출력");
		lb_receiptN.setHorizontalAlignment(SwingConstants.CENTER);
		lb_receiptN.setFont(new Font("메이플스토리", Font.PLAIN, 17));
		lb_receiptN.setBounds(234, 182, 102, 25);
		pn_pay.add(lb_receiptN);
		
		bt_takeout.setIcon(new ImageIcon(path+"\\image\\takeout.png"));
		bt_takein.setIcon(new ImageIcon(path+"\\image\\takein.png"));
		bt_card.setIcon(new ImageIcon(path+"\\image\\card.png"));
		bt_cash.setIcon(new ImageIcon(path+"\\image\\cash.png"));
		bt_receiptY.setIcon(new ImageIcon(path+"\\image\\yes.png"));
		bt_receiptN.setIcon(new ImageIcon(path+"\\image\\no.png"));
		
		JLabel lb_title = new JLabel("메뉴와 옵션들을 한번 더 확인해 주세요.");
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setFont(new Font("메이플스토리", Font.PLAIN, 15));
		lb_title.setBounds(10, 50, 318, 22);
		pn_pay.add(lb_title);
		
		JButton bt_pay = new JButton("결제하기");
		bt_pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receipt.pay=true;
				dispose();
			}
		});
		bt_pay.setForeground(Color.WHITE);
		bt_pay.setFont(new Font("메이플스토리", Font.BOLD, 18));
		bt_pay.setFocusPainted(false);
		bt_pay.setBackground(new Color(231, 95, 84));
		bt_pay.setBounds(40, 217, 120, 21);
		pn_pay.add(bt_pay);
		
		JButton bt_pay_1 = new JButton("다시 선택");
		bt_pay_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bt_pay_1.setForeground(Color.WHITE);
		bt_pay_1.setFont(new Font("메이플스토리", Font.BOLD, 18));
		bt_pay_1.setFocusPainted(false);
		bt_pay_1.setBackground(new Color(231, 95, 84));
		bt_pay_1.setBounds(190, 217, 120, 21);
		pn_pay.add(bt_pay_1);
		
		bt_takeout.setVisible(false);
		bt_takein.setVisible(false);
		bt_card.setVisible(false);
		bt_cash.setVisible(false);
		bt_receiptY.setVisible(false);
		bt_receiptN.setVisible(false);
		
		lb_takeout.setVisible(false);
		lb_takein.setVisible(false);
		lb_card.setVisible(false);
		lb_cash.setVisible(false);
		lb_receiptY.setVisible(false);
		lb_receiptN.setVisible(false);
		
		if(receipt.card) {
			bt_card.setVisible(true);
			lb_card.setVisible(true);
		}
		else {
			bt_cash.setVisible(true);
			lb_cash.setVisible(true);
		}
		
		if(receipt.take_out) {
			bt_takeout.setVisible(true);
			lb_takeout.setVisible(true);
		}
		else {
			bt_takein.setVisible(true);
			lb_takein.setVisible(true);
		}
		if(receipt.receipt) {
			bt_receiptY.setVisible(true);
			lb_receiptY.setVisible(true);
		}
		else {
			bt_receiptN.setVisible(true);
			lb_receiptN.setVisible(true);
		}
		Dimension frameSize = getSize();
	    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation((windowSize.width - frameSize.width) / 2,
	               (windowSize.height - frameSize.height) / 2);
		this.setSize(355,295);
		this.setVisible(true);
	}
}
