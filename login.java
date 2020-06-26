import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class login extends JFrame{
	Container c;
	JLabel lblUser, lblPass, lblHead, lblBlank, lblBlan;
	JTextField txtuser;
	JPasswordField txtpassword;
	JButton btnLogin, btnExit;

	login(){
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));

		lblHead = new JLabel("Hospital Management System");
		lblHead.setFont(new Font("Calibri",Font.BOLD,25));

		lblBlank = new JLabel("                                                                                              ");
		lblBlank.setFont(new Font("Calibri",Font.BOLD,15));

		lblUser  = new JLabel("User Name: ");
		txtuser = new JTextField(17);

		lblPass  = new JLabel("Password:  ");
		txtpassword = new JPasswordField(17);

		lblBlan = new JLabel("                                                                                              ");
		lblBlan.setFont(new Font("Calibri",Font.BOLD,15));


		btnLogin = new JButton("Login");
		btnExit = new JButton("Exit");
		
		ActionListener e1 = (ae) -> {
		String usern = txtuser.getText();
		String passw = txtpassword.getText();
		int g1 = usern.compareTo("Admin"); 
		int g2 = passw.compareTo("Admin");
		if(g1 == 0){
			if(g2 == 0){
				ActionListener e2 = (ab) -> {first_window fw = new first_window();dispose();};
				btnLogin.addActionListener(e2);
		}
	}else{JOptionPane.showMessageDialog(c,"Incorrect Username Password!");}
	};
	btnLogin.addActionListener(e1);

		c.add(lblHead);
		c.add(lblBlank);
		c.add(lblUser);
		c.add(txtuser);
		c.add(lblPass);
		c.add(txtpassword);
		c.add(lblBlan);
		c.add(btnLogin);
		c.add(btnExit);


		setTitle("Login to HMS");
		setSize(350,250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

}
