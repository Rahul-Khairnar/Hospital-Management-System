import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import java.io.*;
import java.sql.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;


class Plot extends JFrame{
Plot(){
	Configuration cfg = new Configuration();
	cfg.configure("hibernate.cfg.xml");
	SessionFactory sfact = cfg.buildSessionFactory();
	Session session = null;
	DefaultCategoryDataset d1 = new DefaultCategoryDataset();
	int count1 = 0;
	int count2 = 0;
	int count3 = 0;
	int count4 = 0;
	int count5 = 0;
	try{
		session = sfact.openSession();
		//System.out.println("open");
		java.util.List<Hospital> hos = new ArrayList<>();
		hos = session.createQuery("from Hospital").list();
		for(Hospital s: hos){
			//System.out.println(s.getfname());
			String cat1 = s.getcategory_1();
			int c1=cat1.compareTo("Yes");
		//	System.out.println("CAT 1: "+cat1);

			String cat2 = s.getcategory_2();
			int c2 = cat2.compareTo("Yes");
		//	System.out.println("CAT 2: "+cat2);

			String cat3 = s.getcategory_3();
			int c3 = cat3.compareTo("Yes");
		//	System.out.println("CAT 3: "+cat3);


			String cat4 = s.getcategory_4();
			int c4 = cat4.compareTo("Yes");
		//	System.out.println("CAT 4: "+cat4);


			String cat5 = s.getcategory_5();
			int c5 = cat5.compareTo("Yes");	
		//	System.out.println("CAT 5: "+cat5);		

			if(c1==0){
				count1++;
			}
		//	System.out.println("Count 1: "+count1);

			if(c2==0){
				count2++;
			}
		//	System.out.println("Count 2: "+count2);

			if(c3==0){
				count3++;
				}//System.out.println("Count 3: "+count3);
			

			if(c4==0){
				count4++;
				}//System.out.println("Count 4: "+count4);
			
			if(c5==0){
				count5++;
				}//System.out.println("Count 5: "+count5);
}			
	d1.addValue(count1,"Category 1","Category 1");
	d1.addValue(count2,"Category 2", "Category 2");
	d1.addValue(count3, "Category 3", "Category 3");
	d1.addValue(count4,"Category 4","Category 4");
	d1.addValue(count5,"Category 5","Category 5");

	JFreeChart chart = ChartFactory.createBarChart("Illness Counts","Illnesses","Counts",d1,PlotOrientation.VERTICAL,true,false,false);
	ChartPanel cp = new ChartPanel(chart);
	setContentPane(cp);
	setTitle("Illeness Reported Distribution");
	setSize(600,500);
	setLocationRelativeTo(null);
	setVisible(true);
	JOptionPane.showMessageDialog(null,"Category 1: Allergies, Cold, Fever, Conjuctivitis, Diarrhoea, Sour Throat\nCategory 2: Headaches, Stomach aches, Muscle sprains, Dizziness\nCategory 3: Skin Rashes, Gastrointestinal Distress, Itchiness\nCategory 4: COVID-19 Symptoms with travel history or contact with COVID patient\nCategory 5: Others");


}catch(Exception e){
	JOptionPane.showMessageDialog(null,"Disease not updated for some patient. Please check details!!");
}
}
}

