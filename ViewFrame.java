import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;

class ViewFrame extends JFrame{
	Container c;
	JLabel lblHead;
	JTextArea txtArea;
	JButton btnBack;

	ViewFrame(){
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));


		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact = cfg.buildSessionFactory();
		Session session = null;

		lblHead = new JLabel("Patient Details");
		lblHead.setFont(new Font("Calibri",Font.BOLD,25));

		txtArea = new JTextArea(25,50);
		JScrollPane scrollableTextArea = new JScrollPane(txtArea);

		scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  

		txtArea.setLineWrap(false);

		btnBack = new JButton("Back");

		try{
			session = sfact.openSession();
			System.out.println("open");
			java.util.List<Hospital> hos = new ArrayList<>();
			hos = session.createQuery("from Hospital").list();
			for(Hospital s: hos){
				String cat1 = s.getcategory_1();
				String cat2 = s.getcategory_2();
				String cat3 = s.getcategory_3();
				String cat4 = s.getcategory_4();
				String cat5 = s.getcategory_5();
				String disease = "Cat 1: "+cat1+", Cat 2: "+cat2+", Cat 3: "+cat3+", Cat 4: "+cat4+", Cat 5: "+cat5;
				String pat = "Patient Id: "+s.getiD()+"       Name: "+s.getfname()+" "+s.getlname()+"\nAadhar Card No.: "+s.getaadhar()+"           Contact No: "+s.getcontact_num()+"\nProblems Reported: "+disease;
				txtArea.append(pat+"\n\n");
		}
		}catch(Exception e){
			//System.out.println("Issues "+e);
		}
		finally{
			session.close();
			//System.out.println("Closed!");
		}

		ActionListener e3 = (ae) -> {Explore exp = new Explore();dispose();};
		btnBack.addActionListener(e3);

		c.add(lblHead);
		c.add(scrollableTextArea);
		c.add(btnBack);

		setTitle("Patient Details");
		setSize(600,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}


