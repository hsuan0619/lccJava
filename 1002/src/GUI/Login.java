package GUI;

import java.awt.event.*;

import javax.swing.*;
import DB.TrainSQL;

public class Login extends JFrame implements ActionListener{
	public static String userAcc;
	
	private JLabel txtId=new JLabel("帳號：");
	private JLabel txtPass = new JLabel("密碼：");
	
	private JTextField inputId = new JTextField();
	private JPasswordField inputPass = new JPasswordField();
	private JButton btnLogin = new JButton("登入");
	private JButton btnCancel=new JButton("取消");
	
	Object[][] data;
	
	public Login() {
		super("資源系統");
		//x,y,寬,高
		txtId.setBounds(50,50,50,20);
		add(txtId);
		txtPass.setBounds(50,80,50,20);
		add(txtPass);
		inputId.setBounds(110,50,100,20);
		add(inputId);
		inputPass.setBounds(110,80,100,20);
		add(inputPass);
		
		btnLogin.setBounds(70,120,75,20);
		btnLogin.addActionListener(this);
		add(btnLogin);
		
		btnCancel.setBounds(155,120,75,20);
		btnCancel.addActionListener(this);
		add(btnCancel);
		
		setLayout(null);
		setBounds(100,100,300,200);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == btnLogin) {
			//getText：抓字串
			String acc = inputId.getText();
			//密碼藥用getPassword
			String pwd = String.valueOf(inputPass.getPassword());
			
			boolean isCheck = false;
			try {
				TrainSQL tsql = new TrainSQL();
				isCheck = tsql.Login(acc, pwd);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			if(isCheck) {
				userAcc=acc;
				new MainContent();
				setVisible(false);
				
			}else{
				//跳出錯誤視窗
				JOptionPane.showMessageDialog(null, "帳密錯誤","登入資訊",JOptionPane.PLAIN_MESSAGE);
			}
		}
		if(event.getSource() == btnCancel) {
			inputId.setText("");
			inputPass.setText("");
		}
	}
}
