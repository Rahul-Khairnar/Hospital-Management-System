import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class DeletePatient extends JFrame{
	Container c;
	JLabel lblId, lblHead;
	JTextField txtId;
	JButton btnDelete, btnBack;

	DeletePatient(){
		c = getContentPane();
		c.setLayout(new FlowLayout());

		lblHead = new JLabel("      Delete Patient      "); 
		lblHead.setFont(new Font("Calibri",Font.BOLD,25));

		lblId = new JLabel("Patient id");

		txtId = new JTextField(16);

		btnDelete = new JButton("Delete Patient");
		btnBack = new JButton("Back");

		ActionListener e1 = (ae) -> {

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
			Pat_id = Integer.parseInt(txtId.getText());
			if(Pat_id<0){
				JOptionPane.showMessageDialog(c,"Please enter a valid Patint Id.");
			}else{
			Hospital hos = (Hospital)session.get(Hospital.class,Pat_id);
			if(hos!=null){
				session.delete(hos);
				t.commit();
				JOptionPane.showMessageDialog(c,"Record for "+Pat_id+" deleted!");
			}
			else{
				JOptionPane.showMessageDialog(c,"Record doesnot exist!");
			}
		}
		}catch(Exception e){
			t.rollback();
			JOptionPane.showMessageDialog(c,"Invalid Patient Id.");
		}
		finally{
			session.close();
			//JOptionPane.showMessageDialog(c,"Closed!");
		}

		};
		btnDelete.addActionListener(e1);

		ActionListener e2 = (ae) -> {first_window fw = new first_window();dispose();};
		btnBack.addActionListener(e2);

		c.add(lblHead);
		c.add(lblId);
		c.add(txtId);
		c.add(btnDelete);
		c.add(btnBack);
		setTitle("HMS Home Page");
		setSize(250,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
