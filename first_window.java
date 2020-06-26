import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class first_window extends JFrame{
	Container c;
	JLabel lblBlank, lblBlan, lblBla, lblBl, lblB, lblD;
	JButton btnNewPatient, btnExisting, btnExplore, btnDelete, btnUpdate, btnLogout;

	first_window(){
		c = getContentPane();
		c.setLayout(new FlowLayout());
		btnNewPatient = new JButton("New Patient");
		btnExisting = new JButton("Existing Patient");
		btnLogout = new JButton("Logout");
		btnExplore = new JButton("Explore the data");
		btnDelete = new JButton("Delete Patient");
		btnUpdate = new JButton("Update Patient Info");


		lblBlank = new JLabel("                                                                                              ");
		lblBlank.setFont(new Font("Calibri",Font.BOLD,15));

		lblBlan = new JLabel("                                                                                              ");
		lblBlan.setFont(new Font("Calibri",Font.BOLD,15));

		lblBla = new JLabel("                                                                                              ");
		lblBla.setFont(new Font("Calibri",Font.BOLD,15));

		lblBl = new JLabel("                                                                                              ");
		lblBl.setFont(new Font("Calibri",Font.BOLD,15));

		lblB = new JLabel("                                                                                              ");
		lblB.setFont(new Font("Calibri",Font.BOLD,15));

		lblD = new JLabel("                                                                                              ");
		lblD.setFont(new Font("Calibri",Font.BOLD,15));


		ActionListener e1 = (ae) -> {NewPatient np = new NewPatient();dispose();};
		btnNewPatient.addActionListener(e1);

		ActionListener e2 = (ae) -> {Expenses ex = new Expenses();dispose();};
		btnExisting.addActionListener(e2);

		ActionListener e3 = (ae) -> {login l = new login();dispose();};
		btnLogout.addActionListener(e3);

		ActionListener e4 = (ae) -> {Explore exp = new Explore();dispose();};
		btnExplore.addActionListener(e4);

		ActionListener e5 = (ae) -> {DeletePatient fp = new DeletePatient();dispose();};
		btnDelete.addActionListener(e5);

		ActionListener e6 = (ae) -> {UpdatePatient up = new UpdatePatient();dispose();};
		btnUpdate.addActionListener(e6);



		c.add(lblBla);
		c.add(btnNewPatient);
		c.add(lblBlank);
		c.add(btnExisting);
		c.add(lblBlan);
		c.add(btnExplore);
		c.add(lblBl);
		c.add(btnUpdate);
		c.add(lblB);
		c.add(btnDelete);
		c.add(lblD);
		c.add(btnLogout);

		setTitle("HMS Home Page");
		setSize(250,400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

