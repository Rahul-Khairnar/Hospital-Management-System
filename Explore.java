import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Explore extends JFrame{
	Container c;
	JLabel lblBlank, lblBlan, lblHead,lblBla;
	JButton btnAllPatients, btnAnalysis, btnBack;

	Explore(){
		c = getContentPane();
		c.setLayout(new FlowLayout());

		btnAllPatients = new JButton("List of All Patients");
		btnAnalysis = new JButton("Charts for Analysis");
		btnBack = new JButton("Back to Home");

		lblHead = new JLabel("  Explore  ");
		lblHead.setFont(new Font("Calibri",Font.BOLD,25));

		lblBlank = new JLabel("                                                                                              ");
		lblBlank.setFont(new Font("Calibri",Font.BOLD,15));

		lblBla = new JLabel("                                                                                              ");
		lblBla.setFont(new Font("Calibri",Font.BOLD,15));

		lblBlan = new JLabel("                                                                                              ");
		lblBlan.setFont(new Font("Calibri",Font.BOLD,15));


		ActionListener e1 = (ae) -> {ViewFrame vf = new ViewFrame();dispose();};
		btnAllPatients.addActionListener(e1);

		ActionListener e2 = (ae) -> {Plot p = new Plot();};
		btnAnalysis.addActionListener(e2);

		ActionListener e3 = (ae) -> {first_window fw = new first_window();dispose();};
		btnBack.addActionListener(e3);

		c.add(lblHead);
		c.add(lblBla);
		c.add(btnAllPatients);
		c.add(lblBlank);
		c.add(btnAnalysis);
		c.add(lblBlan);
		c.add(btnBack);


		setTitle("HMS Home Page");
		setSize(250,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
