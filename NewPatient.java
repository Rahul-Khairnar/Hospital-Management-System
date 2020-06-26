import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.time.*;
import java.time.format.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;



class NewPatient extends JFrame{
	Container c;
	JLabel lblFirstName, lblLastName, lblAadhar, lblContactNumber, lblEmail, lblAddress, lblPin, lblId, lblHead;
	JTextField txtFirstName, txtLastName, txtAadhar, txtContactNumber, txtEmail, txtAddress, txtPin, txtId, txtCode;
	JLabel lblB1, lblB2, lblB3;
	JButton btnNextPage, btnBack, btnSubmit;

	NewPatient(){

		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));

		lblHead = new JLabel("Personal Details");
		lblHead.setFont(new Font("Calibri",Font.BOLD,25));
		lblFirstName = new JLabel("First Name");
		lblLastName = new JLabel("Last Name");
		lblAadhar = new JLabel("Aadhar No.");
		lblContactNumber = new JLabel("Mobile/LandLine Number with code");
		lblEmail = new JLabel("Email Id.");
		lblAddress = new JLabel("Address");
		lblPin = new JLabel("Pincode");
		lblId = new JLabel("Patient Unique Id.");
		lblB1 = new JLabel("                                                         ");


		txtFirstName = new JTextField(16);
		txtLastName = new JTextField(16);
		txtAadhar = new JTextField(16);
		txtContactNumber = new JTextField(13);
		txtCode = new JTextField(3);
		txtEmail = new JTextField(16);
		txtAddress = new JTextField(16);
		txtPin = new JTextField(16);
		txtId = new JTextField(16);


		btnNextPage = new JButton("Next");
		btnBack = new JButton("Back");
		btnSubmit = new JButton("Submit");

		LocalDate di = LocalDate.now();

		LocalTime ti = LocalTime.now();
		DateTimeFormatter d1 = DateTimeFormatter.ofPattern("hh:mm");
		String s2 = d1.format(ti);
		String admitted = "Date: "+di+" Time: "+s2;


		ActionListener f1 = (ae) -> {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sfact = cfg.buildSessionFactory();

			Session session = null;
			Transaction t = null;

			try{
				int iD = Integer.parseInt(txtId.getText());
				if(iD > 0){
				try{
					int count = 0;
					String fname = txtFirstName.getText();	
					for(int i = 0;i<fname.length();i++){
						if(Character.isLetter(fname.charAt(i)) == false){
							count++;
						}
				}
				if((count == 0) && (fname.length() >= 2)){				
					try{
						String lname = txtLastName.getText();
						int count1 = 0;
						for(int i = 0;i<lname.length();i++){
							if(Character.isLetter(lname.charAt(i)) == false){
								count1++;
							}
					}
					if(count1 == 0){								
						try{
							Double aadhar = Double.parseDouble(txtAadhar.getText());
							if(aadhar>0){
							try{
								String contact = txtContactNumber.getText();
								if(contact.length()>7){
								try{
									String code = txtCode.getText();
									if(code.length()>0){
									try{
										String email = txtEmail.getText();
										if(email.length()>=6){
											int count_a = 0;
											int count_b = 0;
											for(int i = 0;i<email.length();i++){
												char b = email.charAt(i);
												if(b == '@'){
													count_a++;
												}
												if(b == '.'){
													count_b++;
												}
											}if((count_a == 1) && (count_b == 1)){
										try{
											String add = txtAddress.getText();
											if(add.length()>4){
											try{
												Double pin = Double.parseDouble(txtPin.getText());
												try{
													Double contact_num = Double.parseDouble(code+contact);

					

			try{
				session = sfact.openSession();
				t = session.beginTransaction();
				String category_1 = "";
				String category_2 = "";
				String category_3 = "";
				String category_4 = "";
				String category_5 = "";

				File file = new File("default.png");
     		    byte[] bFile = new byte[(int) file.length()];
     		
	 			    FileInputStream fileInputStream = new FileInputStream(file);
	    		    //convert file into array of bytes
	     			fileInputStream.read(bFile);
	     			fileInputStream.close();

				Hospital s = new Hospital(iD,fname,lname,aadhar,contact_num,email,add,pin,admitted, category_1, category_2, category_3, category_4, category_5, bFile);
				session.save(s);
				t.commit();
				txtFirstName.setText("");
				txtPin.setText("");
				txtId.setText("");
				txtAadhar.setText("");
				txtContactNumber.setText("");
				txtCode.setText("");
				txtEmail.setText("");
				txtAddress.setText("");
				txtPin.setText("");
				txtLastName.setText("");
				JOptionPane.showMessageDialog(c,"Record Added!");
			}catch(Exception e){
				t.rollback();
				JOptionPane.showMessageDialog(c,"Record already Exists! ");
			}
			finally{
				session.close();
				//JOptionPane.showMessageDialog(c,"Closed!");
			}

		}catch(Exception e){JOptionPane.showMessageDialog(c,"Country Code and Contact Number should be NUMBERS only!");}
		}catch(Exception e){JOptionPane.showMessageDialog(c,"Invalid Pin!");}}else{JOptionPane.showMessageDialog(c,"Please enter a valid address!");}
		}catch(Exception e){JOptionPane.showMessageDialog(c,"Invalid Address");}}else{JOptionPane.showMessageDialog(c,"Please enter a valid email!");}}else{JOptionPane.showMessageDialog(c,"Please enter a valid email");}
		}catch(Exception e){JOptionPane.showMessageDialog(c,"Invalid Email");}}else{JOptionPane.showMessageDialog(c,"Please enter a valid Country Code");}
		}catch(Exception e){JOptionPane.showMessageDialog(c,"Country code should be a NUMBER");}}else{JOptionPane.showMessageDialog(c,"Please enter a valid Contact Number!");}
		}catch(Exception e){JOptionPane.showMessageDialog(c,"Contact Numbers Invalid");}}else{JOptionPane.showMessageDialog(c,"Please enter a valid Aadhar number!");}		
		}catch(Exception e){JOptionPane.showMessageDialog(c,"Aadhar should be NUMBER only");}}else{JOptionPane.showMessageDialog(c,"No Special Characters allowed in Last Name!");}
		}catch(Exception e){JOptionPane.showMessageDialog(c,"Last Name Invalid");}}else{JOptionPane.showMessageDialog(c,"No Special Characters allowed in First Name and Should be minimum two alphabets long!");}
		}catch(Exception e){JOptionPane.showMessageDialog(c,"First Name Invalid");}}else{JOptionPane.showMessageDialog(c,"Patient Id. should be Positive Integer!");}
		}catch(Exception e){JOptionPane.showMessageDialog(c,"Issue with Patient Id.");
		}
		};
		btnSubmit.addActionListener(f1);

		ActionListener f2 = (ab) -> {PatientImage img = new PatientImage();dispose();};
		btnNextPage.addActionListener(f2);

		ActionListener f3 = (ae) -> {first_window mw = new first_window();dispose();};
		btnBack.addActionListener(f3);

		c.add(lblHead);
		c.add(lblId);
		c.add(txtId);
		c.add(lblFirstName);
		c.add(txtFirstName);
		c.add(lblLastName);
		c.add(txtLastName);
		c.add(lblAadhar);
		c.add(txtAadhar);
		c.add(lblContactNumber);
		c.add(txtCode);
		c.add(txtContactNumber);
		c.add(lblEmail);
		c.add(txtEmail);
		c.add(lblAddress);
		c.add(txtAddress);
		c.add(lblPin);
		c.add(txtPin);
		c.add(lblB1);
		c.add(btnSubmit);
		c.add(btnNextPage);
		c.add(btnBack);


		setTitle("Personal Details");
		setSize(250,500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

