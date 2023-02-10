import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Dialog.ModalityType;

class receipt_class{
	boolean take_out=false;
	boolean card=false;
	boolean receipt=false;
	boolean pay = false;
}

public class pay extends JDialog{
	// 버튼, 레이블 생성
	// 버튼 두개씩 짝지어서 보임
	// 둘중하나 누르면 다음 세트 보임
	// 테이크아웃/가게 카드/현금 영수증 o/x 선택 끝나면 종료
	JButton bt_takeout = new JButton("");
	JButton bt_takein = new JButton("");
	JButton bt_card = new JButton("");
	JButton bt_cash = new JButton("");
	JButton bt_receiptY = new JButton("");
	JButton bt_receiptN = new JButton("");
	
	boolean take_out;
	boolean card;
	boolean receipt;
	
	private final JPanel panel = new JPanel();
	private final JLabel  lb_takeout = new JLabel("포장");
	private final JLabel lb_takein = new JLabel("매장 내");
	private final JLabel lb_card = new JLabel("카드");
	private final JLabel lb_receiptY = new JLabel("영수증 출력");
	private final JLabel lb_cash = new JLabel("현금");
	private final JLabel lb_receiptN = new JLabel("영수증 미출력");
	String path = System.getProperty("user.dir"); // path에 현재 작업 경로 입력
	public void order_check(receipt_class receipt) {
	}
	
	public pay(Kiosk k,receipt_class receipt) {
		super(k,"",true);
		pay p=this;
		setAlwaysOnTop(true);
		getContentPane().setLayout(null);
		
		JPanel pn_pay = new JPanel();
		pn_pay.setBounds(0, 0, 340, 240);
		pn_pay.setBackground(new Color(255, 255, 255));
		getContentPane().add(pn_pay);
		pn_pay.setLayout(null);

		bt_takeout.setIcon(new ImageIcon(path+"\\image\\takeout.png"));
		bt_takeout.setBackground(new Color(255,255,255));
		bt_takeout.setBounds(50, 70, 90, 90);
		bt_takeout.setBorderPainted(false);
		bt_takeout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b=(JButton)e.getSource();//이벤트가 발생한 버튼 확인
                bt_takein.setVisible(false);
                bt_takeout.setVisible(false);
                bt_card.setVisible(true);
                bt_cash.setVisible(true);
                lb_takein.setVisible(false);
                lb_takeout.setVisible(false);
                lb_card.setVisible(true);
                lb_cash.setVisible(true);
                receipt.take_out=true;
            }
        });
		pn_pay.add(bt_takeout);
		
		
		bt_takein.setIcon(new ImageIcon(path+"\\image\\takein.png"));
		bt_takein.setBackground(new Color(255,255,255));
		bt_takein.setBounds(200, 70, 90, 90);
		bt_takein.setBorderPainted(false);
		bt_takein.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b=(JButton)e.getSource();//이벤트가 발생한 버튼 확인
                bt_takein.setVisible(false);
                bt_takeout.setVisible(false);
                bt_card.setVisible(true);
                bt_cash.setVisible(true);
                lb_takein.setVisible(false);
                lb_takeout.setVisible(false);
                lb_card.setVisible(true);
                lb_cash.setVisible(true);
            }
        });
		pn_pay.add(bt_takein);
		
		bt_card.setIcon(new ImageIcon(path+"\\image\\card.png"));
		bt_card.setBackground(new Color(255,255,255));
		bt_card.setBounds(50, 70, 90, 90);
		bt_card.setBorderPainted(false);
		bt_card.setVisible(false);
		bt_card.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b=(JButton)e.getSource();//이벤트가 발생한 버튼 확인
                bt_card.setVisible(false);
                bt_cash.setVisible(false);
                bt_receiptY.setVisible(true);
                bt_receiptN.setVisible(true);
                lb_card.setVisible(false);
                lb_cash.setVisible(false);
                lb_receiptY.setVisible(true);
                lb_receiptN.setVisible(true);
                receipt.card=true;
            }
        });
		pn_pay.add(bt_card);

		bt_cash.setIcon(new ImageIcon(path+"\\image\\cash.png"));
		bt_cash.setBackground(new Color(255,255,255));
		bt_cash.setBounds(200, 70, 90, 90);
		bt_cash.setBorderPainted(false);
		bt_cash.setVisible(false);
		bt_cash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b=(JButton)e.getSource();//이벤트가 발생한 버튼 확인
                bt_card.setVisible(false);
                bt_cash.setVisible(false);
                bt_receiptY.setVisible(true);
                bt_receiptN.setVisible(true);
                lb_card.setVisible(false);
                lb_cash.setVisible(false);
                lb_receiptY.setVisible(true);
                lb_receiptN.setVisible(true);
            }
        });
		pn_pay.add(bt_cash);

		bt_receiptY.setIcon(new ImageIcon(path+"\\image\\yes.png"));
		bt_receiptY.setBackground(new Color(255,255,255));
		bt_receiptY.setBounds(50, 70, 90, 90);
		bt_receiptY.setBorderPainted(false);
		bt_receiptY.setVisible(false);
		bt_receiptY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b=(JButton)e.getSource();//이벤트가 발생한 버튼 확인
                receipt.receipt=true;
                setVisible(false);
                new order_check(p,receipt);
                dispose();
            }
        });
		pn_pay.add(bt_receiptY);

		bt_receiptN.setIcon(new ImageIcon(path+"\\image\\no.png"));
		bt_receiptN.setBackground(new Color(255,255,255));
		bt_receiptN.setBounds(200, 70, 90, 90);
		bt_receiptN.setBorderPainted(false);
		bt_receiptN.setVisible(false);
		bt_receiptN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b=(JButton)e.getSource();//이벤트가 발생한 버튼 확인
                setVisible(false);
                new order_check(p,receipt);
                dispose();
            }
        });
		pn_pay.add(bt_receiptN);
		panel.setBounds(0, 0, 335, 40);
		panel.setBackground(new Color(231,95, 84));
		
		pn_pay.add(panel);
		
		lb_takeout.setHorizontalAlignment(SwingConstants.CENTER);
		lb_takeout.setBounds(50, 170, 90, 25);
		lb_takeout.setFont(new Font("메이플스토리 Bold", Font.PLAIN, 17));
		pn_pay.add(lb_takeout);
		lb_takein.setHorizontalAlignment(SwingConstants.CENTER);
		
		lb_takein.setBounds(200, 170, 90, 25);
		lb_takein.setFont(new Font("메이플스토리 Bold", Font.PLAIN, 17));		
		pn_pay.add(lb_takein);
		lb_card.setHorizontalAlignment(SwingConstants.CENTER);
		
		lb_card.setBounds(50, 170, 90, 25);
		lb_card.setVisible(false);
		lb_card.setFont(new Font("메이플스토리 Bold", Font.PLAIN, 17));		
		pn_pay.add(lb_card);
		lb_receiptY.setHorizontalAlignment(SwingConstants.CENTER);
		
		lb_receiptY.setBounds(50, 170, 90, 25);
		lb_receiptY.setVisible(false);
		lb_receiptY.setFont(new Font("메이플스토리 Bold", Font.PLAIN, 17));		
		pn_pay.add(lb_receiptY);
		lb_cash.setHorizontalAlignment(SwingConstants.CENTER);
		
		lb_cash.setBounds(200, 170, 90, 25);
		lb_cash.setVisible(false);
		lb_cash.setFont(new Font("메이플스토리 Bold", Font.PLAIN, 17));		
		pn_pay.add(lb_cash);
		lb_receiptN.setHorizontalAlignment(SwingConstants.CENTER);
		
		lb_receiptN.setBounds(195, 170, 106, 25);
		lb_receiptN.setVisible(false);
		lb_receiptN.setFont(new Font("메이플스토리 Bold", Font.PLAIN, 17));		
		pn_pay.add(lb_receiptN);
		
		Dimension frameSize = getSize();
	    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation((windowSize.width - frameSize.width) / 2,
	               (windowSize.height - frameSize.height) / 2);
		this.setSize(350,280);
	
}
}
