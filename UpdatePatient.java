import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class UpdatePatient extends JFrame{
	Container c;
	JLabel lblFirstName, lblLastName, lblAadhar, lblContactNumber, lblEmail, lblAddress, lblPin, lblId, lblHead;
	JTextField txtFirstName, txtLastName, txtAadhar, txtContactNumber, txtEmail, txtAddress, txtPin, txtId, txtCode;
	JLabel lblB1, lblB2, lblB3;
	JButton btnUpdate, btnBack, btnFetch;

	UpdatePatient(){

		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));

		lblHead = new JLabel("Update Details");
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


		btnUpdate = new JButton("Update");
		btnBack = new JButton("Back");
		btnFetch = new JButton("Fetch Data");


		ActionListener f0 = (ag) -> {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sfact = cfg.buildSessionFactory();
			Session session = null;
			Transaction t = null;
			try{
			int id = Integer.parseInt(txtId.getText());
			System.out.println(id);
			session = sfact.openSession();
			System.out.println("open");
			t = session.beginTransaction();
			Hospital hos = (Hospital)session.get(Hospital.class,id);
				if(hos != null){
					System.out.println(hos.getfname()+" "+hos.getlname()+" "+hos.getemail()+" "+hos.getadd());
					txtFirstName.setText(hos.getfname());
					txtLastName.setText(hos.getlname());
					Double adh = hos.getaadhar();
					Double cn = hos.getcontact_num();
					txtAadhar.setText(String.valueOf(adh));
					txtContactNumber.setText(String.valueOf(cn));
					txtEmail.setText(hos.getemail());
					txtAddress.setText(hos.getadd());
					txtPin.setText(String.valueOf(hos.getpin()));
		}else{System.out.println("Patient not found!");}
	}catch(Exception e){JOptionPane.showMessageDialog(c,e);}
		};
		btnFetch.addActionListener(f0);




		ActionListener f1 = (ae) -> {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sfact = cfg.buildSessionFactory();

			int Pat_id = 0;
			Session session = null;
			Transaction t = null;
			try{
				session = sfact.openSession();
				System.out.println("open");
				t = session.beginTransaction();
				try{
				Pat_id = Integer.parseInt(txtId.getText());
			}catch(Exception e){JOptionPane.showMessageDialog(c,"Invalid Patient Id.");}
				if(Pat_id<0){
					JOptionPane.showMessageDialog(c,"Please enter a valid Patient Id.");
				}
				else{
				Hospital hos = (Hospital)session.get(Hospital.class,Pat_id);
				if(hos!=null){
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
										try{
											String add = txtAddress.getText();
											if(add.length()>4){
											try{
												Double pin = Double.parseDouble(txtPin.getText());
												try{
													Double contact_num = Double.parseDouble(code+contact);
				try{	
					hos.setiD(Pat_id);									
					hos.setfname(fname);
					hos.setlname(lname);
					hos.setcontact_num(contact_num);
					hos.setemail(email);
					hos.setadd(add);
					hos.setpin(pin);
					session.save(hos);
					t.commit();
					System.out.println("Record Updated!");
				}catch(Exception e){
					t.rollback();
					JOptionPane.showMessageDialog(c,"Data Updation Error");
				}


				}catch(Exception e){JOptionPane.showMessageDialog(c,"Country Code and Contact Number should be NUMBERS only!");}
				}catch(Exception e){JOptionPane.showMessageDialog(c,"Invalid Pin!");}}else{JOptionPane.showMessageDialog(c,"Please enter a valid address!");}
				}catch(Exception e){JOptionPane.showMessageDialog(c,"Invalid Address");}}else{JOptionPane.showMessageDialog(c,"Email cannot be left empty!");}
				}catch(Exception e){JOptionPane.showMessageDialog(c,"Invalid Email");}}else{JOptionPane.showMessageDialog(c,"Please enter a valid Country Code");}
				}catch(Exception e){JOptionPane.showMessageDialog(c,"Country code should be a NUMBER");}}else{JOptionPane.showMessageDialog(c,"Please enter a valid Contact Number!");}
				}catch(Exception e){JOptionPane.showMessageDialog(c,"Contact Numbers Invalid");}}else{JOptionPane.showMessageDialog(c,"Please enter a valid Aadhar number!");}		
				}catch(Exception e){JOptionPane.showMessageDialog(c,"Aadhar should be NUMBER only");}}else{JOptionPane.showMessageDialog(c,"No Special Characters allowed in Last Name!");}
				}catch(Exception e){JOptionPane.showMessageDialog(c,"Last Name Invalid");}}else{JOptionPane.showMessageDialog(c,"No Special Characters allowed in First Name and Should be minimum two alphabets long!");}
				}catch(Exception e){JOptionPane.showMessageDialog(c,"First Name Invalid");}
				}else{JOptionPane.showMessageDialog(c,"Patient id not found!");}
				}
			}catch(Exception e){
				t.rollback();
				JOptionPane.showMessageDialog(c,"Issues "+e);
			}
			finally{
				session.close();
				//System.out.println("Closed!");
			}
		};
		btnUpdate.addActionListener(f1);

		ActionListener f3 = (ae) -> {first_window mw = new first_window();dispose();};
		btnBack.addActionListener(f3);

		c.add(lblHead);
		c.add(lblId);
		c.add(txtId);
		c.add(btnFetch);
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
		c.add(btnUpdate);
		c.add(btnBack);


		setTitle("Personal Details");
		setSize(250,530);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

