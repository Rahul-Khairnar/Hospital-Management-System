import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class DiseasePage extends JFrame{
	Container c;
	JLabel lblHead, lblPatientId;
	JCheckBox cbDisease1, cbDisease2, cbDisease3, cbDisease4, cbDisease5;
	JButton btnSubmit, btnBack;
	JTextField txtId,txtOther;
	JLabel lblBlank1,lblBlank2, lblBlank3;

	DiseasePage(){
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		lblHead = new JLabel("                       Illnesses");
		lblHead.setFont(new Font("Calibri", Font.BOLD,30));

		lblPatientId = new JLabel("             Patient Id entered on previous page");

		cbDisease1 = new JCheckBox("Allergies, Cold, Fever, Conjuctivitis, Diarrhoea, Sour Throat");
		cbDisease2 = new JCheckBox("Headaches, Stomach aches, Muscle sprains, Dizziness");
		cbDisease3 = new JCheckBox("Skin Rashes, Gastrointestinal Distress, Itchiness"); 
		cbDisease4 = new JCheckBox("COVID-19 Symptoms with travel history or contact with COVID patient");
		
		cbDisease5 = new JCheckBox("Other");
		txtOther = new JTextField(30);

		txtId = new JTextField(4);
		btnSubmit = new JButton("Submit");
		btnBack = new JButton("Back");

		lblBlank1 = new JLabel("                                                                                                                                                         ");

		lblBlank2 = new JLabel("                                              ");
		lblBlank3 = new JLabel("                                                                                     ");


		c.add(lblHead);
		c.add(lblBlank3);
		c.add(lblPatientId);
		c.add(txtId);
		c.add(cbDisease1);
		c.add(cbDisease2);
		c.add(cbDisease3);
		c.add(cbDisease4);
		c.add(cbDisease5);
		
		c.add(txtOther);
		c.add(lblBlank1);

		c.add(lblBlank2);
		c.add(btnSubmit);
		c.add(btnBack);



		ActionListener f1 = (ae) -> {


			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sfact = cfg.buildSessionFactory();

			Session session = null;
			Transaction t = null;
			int id = Integer.parseInt(txtId.getText());
			String category_1 = "";
			String category_2 = "";
			String category_3 = "";
			String category_4 = "";
			String category_5 = "";


			if(cbDisease1.isSelected()){
				category_1 = category_1 + "Yes";
			}else{category_1 = category_1 + "No";}
			
			if(cbDisease2.isSelected()){
				category_2 = category_2  + "Yes";
			}else{category_2 = category_2 + "No";}
			
			if(cbDisease3.isSelected()){ 
				category_3 = category_3  + "Yes";
			}else{category_3 = category_3 + "No";}
			
			if(cbDisease4.isSelected()){
				category_4 = category_4 + "Yes";
			}else{category_4 = category_4 + "No";}
			
			if(cbDisease5.isSelected()){
				category_5 = category_5 + "Yes";
			}else{category_5 = category_5 + "No";}

			if((category_1.length()>0) || (category_2.length()>0) || (category_3.length()>0) || (category_4.length()>0) || (category_5.length()>0)){
			try{
			session = sfact.openSession();
			System.out.println("open");
			t = session.beginTransaction();

			Hospital h = (Hospital)session.get(Hospital.class,id);
			if(h!=null){
				h.setcategory_1(category_1);
				h.setcategory_2(category_2);
				h.setcategory_3(category_3);
				h.setcategory_4(category_4);
				h.setcategory_5(category_5);
				session.save(h);
				t.commit();
				JOptionPane.showMessageDialog(c,"Diseases Updated!");
			}
			else{
				JOptionPane.showMessageDialog(c,"Record doesnot exist!");
			}
			}catch(Exception e){
				t.rollback();
			JOptionPane.showMessageDialog(c,"Issues "+e);
			}
			finally{
				session.close();
				//System.out.println("Closed!");
			}
		}else{JOptionPane.showMessageDialog(c,"Please select atleast one option!");}
		};
		btnSubmit.addActionListener(f1);


		ActionListener f2 = (ae) -> {PatientImage img = new PatientImage();dispose();};
		btnBack.addActionListener(f2);

		setTitle("Personal Details");
		setSize(450,500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
