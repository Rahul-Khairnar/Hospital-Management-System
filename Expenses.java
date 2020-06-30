import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.time.*;
import java.time.format.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;




class Expenses extends JFrame{
	Container c;
	JTable tbExpense;
	JLabel lblExpense, lblId;
	JTextArea taExpenses;
	JTextField txtId;	
	JButton btnFind, btnBack, btnImage;

	LocalDate di = LocalDate.now();

	LocalTime ti = LocalTime.now();
	DateTimeFormatter d1 = DateTimeFormatter.ofPattern("hh:mm");
	String s2 = d1.format(ti);
	String admitted = "Date: "+di+" Time: "+s2;


	Expenses(){
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));

		lblExpense = new JLabel("                            Total Expenses of the Patient                           ");
		lblExpense.setFont(new Font("Calibri",Font.BOLD,25));


		//String str = Heading+"\n\n    "+Name+"\n\n    "+PatientId+"            "+AadharNo+"          "+Contact+"\n\n    "+Illness+"\n    "+Start+"\n    "+end+"\n    "+NoOfDays+"\n\n    "+Room+"\n    "+Medicines+"\n    "+Food+"\n    "+Other+"\n\n    "+Total;

		taExpenses = new JTextArea(27,60);
		taExpenses.setEditable(false);
		taExpenses.setLineWrap(true);
		taExpenses.setFont(new Font("Calibri",Font.BOLD,14));

		lblId = new JLabel("Patient id");
		txtId = new JTextField(16);

		btnFind = new JButton("Find");
		btnBack = new JButton("Back");
		btnImage = new JButton("View Patient Image");



		ActionListener e1 = (ae) -> {

			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sfact = cfg.buildSessionFactory();

			Session session = null;
			Transaction t = null;

			int id = Integer.parseInt(txtId.getText());
			

			String Heading = "                                                                                                        Expenses";
			String Name = "Name of the Patient: ";
			String PatientId = "Patient Id.: ";
			String AadharNo = "Aadhar No: ";
			String Start = "Admitted on: ";
			String end = "Dicharged on: "+admitted;
			String NoOfDays = "No of Days Admitted: "+"10";
			String Contact = "Contact Number: ";
			String Room = "Room Rent ----------------------- Rs.xxxxxxx";
			String Medicines = "Medicine Cost ------------------- Rs.xxxxxxx";
			String Food = "Food Cost ------------------------- Rs.xxxxxxx";
			String Other = "Other Expenses ----------------- Rs.xxxxxxx";
			String Total = "Total -------------------------------- Rs.xxxxxxx";
			String Illness = "Problems Reported: ";



			try{
			session = sfact.openSession();
			System.out.println("open");
			t = session.beginTransaction();
			Hospital h = (Hospital)session.get(Hospital.class,id);
			if(h!=null){
				String fname = h.getfname();
				String lname = h.getlname();
				Name = Name+fname + " " + lname;
				PatientId = PatientId + h.getiD();
				AadharNo = AadharNo + h.getaadhar();
				Contact = Contact + h.getcontact_num();
				Illness = Illness + "Category 1: "+h.getcategory_1()+", Category 2: "+h.getcategory_2()+", Category 3: "+h.getcategory_3()+", Category 4: "+h.getcategory_4()+", Category 5: "+h.getcategory_5();
				Start = Start + h.getadmitted();

				String str = Heading+"\n\n    "+Name+"\n\n    "+PatientId+"            "+AadharNo+"          "+Contact+"\n\n    "+Illness+"\n\n\n    "+Start+"\n    "+end+"\n\n    "+Room+"\n    "+Medicines+"\n    "+Food+"\n    "+Other+"\n\n    "+Total;
				taExpenses.setText("");
				taExpenses.setText(str);
				}else{System.out.println("Record doesnot exist!");
			}
			}catch(Exception e){
				t.rollback();
				System.out.println("Issues "+e);
			}
			finally{
				session.close();
				System.out.println("Closed!");
			}
		};
		btnFind.addActionListener(e1);

		ActionListener e2 = (ae) -> {first_window fw = new first_window();dispose();};
		btnBack.addActionListener(e2);


		ActionListener e3 = (ae) -> {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sfact = cfg.buildSessionFactory();

			Session session = null;
			Transaction t = null;
			session = sfact.openSession();
			System.out.println("open");
			t = session.beginTransaction();
			int id = Integer.parseInt(txtId.getText());
			Hospital h = (Hospital)session.get(Hospital.class,id);
			if(h != null){
        		byte[] bFile = h.getImage();
		        try{
    		        FileOutputStream fos = new FileOutputStream(id+"fetched.png");
       			    fos.write(bFile);
       	    		fos.close();

       	    		BufferedImage img = ImageIO.read(new File(id+"fetched.png"));
         			ImageIcon icon = new ImageIcon(img);
         			JLabel label = new JLabel(icon);
         			JOptionPane.showMessageDialog(null, label);
        	}catch(Exception e){
           	 e.printStackTrace();
        	}
        }else{JOptionPane.showMessageDialog(c,"Patient doesnot exists");}
			};
			btnImage.addActionListener(e3);


		c.add(lblExpense);
		c.add(lblId);
		c.add(txtId);
		c.add(taExpenses);
		c.add(btnFind);
		c.add(btnImage);
		c.add(btnBack);

		setTitle("Patient Details");
		setSize(700,650);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}


