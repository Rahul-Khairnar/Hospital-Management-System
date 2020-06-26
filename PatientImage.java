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


class PatientImage extends JFrame{
	Container c;
	JButton btnCapture, btnSave, btnNext,btnBack, btnFetch;
	JTextField txtPat_id, txtName;
	JLabel lblBlank, lblBlan,lblBla,lblBl;
	JLabel lblHead, lblId, lblName;

	PatientImage(){
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));

		lblHead = new JLabel("        Patient Image        ");
		lblHead.setFont(new Font("Calibri",Font.BOLD,25));

		lblBlank = new JLabel("                                                                                              ");
		lblBlank.setFont(new Font("Calibri",Font.BOLD,15));

		lblBla = new JLabel("                                                                                              ");
		lblBla.setFont(new Font("Calibri",Font.BOLD,15));

		lblBlan = new JLabel("                                                                                              ");
		lblBlan.setFont(new Font("Calibri",Font.BOLD,15));

		lblBl = new JLabel("                                                                                              ");
		lblBl.setFont(new Font("Calibri",Font.BOLD,15));

		lblId = new JLabel("  Patient Id");
		lblName = new JLabel("      Patient Name      ");


		txtPat_id = new JTextField(9);
		txtName = new JTextField(15);


		btnFetch = new JButton("Fetch Data");
		btnCapture = new JButton("Capture Image");
		btnSave = new JButton("Save image");
		btnNext = new JButton("Illness Page");
		btnBack = new JButton("Back to Home");



		ActionListener f1 = (aa) -> {
				try{
					int id = Integer.parseInt(txtPat_id.getText());
					Webcam webcam = Webcam.getDefault();
       				webcam.open();
      				BufferedImage image = webcam.getImage();
		    	    ImageIO.write(image, "PNG", new File(id+".png"));

		    	    BufferedImage img = ImageIO.read(new File(id+".png"));
         			ImageIcon icon = new ImageIcon(img);
         			JLabel label = new JLabel(icon);
         			JOptionPane.showMessageDialog(null, label);

		    	}catch(IOException e){
		    		System.out.println(e);
		    	}
		};
		btnCapture.addActionListener(f1);


	
		ActionListener f2 = (ab) -> {
			try{
				Configuration cfg = new Configuration();
				cfg.configure("hibernate.cfg.xml");
				SessionFactory sfact = cfg.buildSessionFactory();

				Session session = null;
				Transaction t = null;
				session = sfact.openSession();
				System.out.println("open");
				t = session.beginTransaction();
				int id = Integer.parseInt(txtPat_id.getText());
				Hospital h = (Hospital)session.get(Hospital.class,id);
				if(h!=null){
					txtName.setText(h.getfname()+" "+h.getlname());
				}else{
				JOptionPane.showMessageDialog(c,"Record doesnot exist!");
			}
		}catch(Exception e){System.out.println(e);}
		};
		btnFetch.addActionListener(f2);

	

		ActionListener f3 = (ac) -> {DiseasePage d = new DiseasePage();dispose();};
		btnNext.addActionListener(f3);

		ActionListener f4 = (ad) -> {
			try{
				Configuration cfg = new Configuration();
				cfg.configure("hibernate.cfg.xml");
				SessionFactory sfact = cfg.buildSessionFactory();

				Session session = null;
				Transaction t = null;
				session = sfact.openSession();
				System.out.println("open");
				t = session.beginTransaction();
				int id = Integer.parseInt(txtPat_id.getText());
				Hospital h = (Hospital)session.get(Hospital.class,id);
				if(h!=null){
					File file = new File(id+".png");
     		   		byte[] bFile = new byte[(int) file.length()];
     		
	 			    FileInputStream fileInputStream = new FileInputStream(file);
	    		    //convert file into array of bytes
	     			fileInputStream.read(bFile);
	     			fileInputStream.close();
					h.setImage(bFile);

					session.save(h);
					t.commit();
					JOptionPane.showMessageDialog(c,"Photo saved!");
				}
				else{
					JOptionPane.showMessageDialog(c,"Record doesnot exist!");
				}
			}catch(Exception e){JOptionPane.showMessageDialog(c,"Data Updation Error");}
		};
		btnSave.addActionListener(f4);

		ActionListener f5 = (ae) -> {first_window fw = new first_window();dispose();};
		btnBack.addActionListener(f5);


		c.add(lblHead);
		c.add(lblId);
		c.add(txtPat_id);
		c.add(btnFetch);
		c.add(lblName);
		c.add(txtName);
		c.add(lblBlank);
		c.add(btnCapture);
		c.add(lblBlan);
		c.add(btnSave);
		c.add(lblBl);
		c.add(btnNext);
		c.add(lblBla);
		c.add(btnBack);



		setTitle("Patient Image");
		setSize(300,440);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}
}
