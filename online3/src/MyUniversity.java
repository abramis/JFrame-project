import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import uni.db.UniversityDB;


public class MyUniversity extends JFrame{

	Student student;
	uni.db.UniversityDB stdb;
	JComboBox combo;
	JTextField name;
	JTextArea tel;
	JTextArea ad;
	JButton button;
	MenuItem item ;
	
	public MyUniversity() {
		super("University DB");
		setVisible(true);
		stdb=UniversityDB.openDB();
		if(stdb.isEmpty())
		{
			initDatabase();
		}


		MenuBar menuBar = new MenuBar();
		Menu mbitem1 = new Menu("File");


		item = new MenuItem("Save");

		mbitem1.add(item);
		menuBar.add(mbitem1);
		this.setMenuBar(menuBar);

	    
	    JLabel lab1 = new JLabel("Student ID:");
	    combo = new JComboBox(stdb.getObjectIDs());
		JLabel lab2 = new JLabel("Student Name:");
	    JLabel lab3 = new JLabel("Telephone:");
	    tel = new JTextArea();
	    JLabel lab4 = new JLabel("Address:");
	    name = new JTextField();
	    name.setEditable(false);
	    ad= new JTextArea();
	    ad.setPreferredSize(new Dimension(200,100));
	    button=new JButton("Update");
	    
	    lab1.setAlignmentX(LEFT_ALIGNMENT);
		combo.setAlignmentX(LEFT_ALIGNMENT);
		lab2.setAlignmentX(LEFT_ALIGNMENT);
		name.setAlignmentX(LEFT_ALIGNMENT);
		lab3.setAlignmentX(LEFT_ALIGNMENT);
		tel.setAlignmentX(LEFT_ALIGNMENT);
		lab4.setAlignmentX(LEFT_ALIGNMENT);
		ad.setAlignmentX(LEFT_ALIGNMENT);
		button.setAlignmentX(LEFT_ALIGNMENT);
	    
		Container contPane = this.getContentPane();
	    
		contPane.setLayout(new BoxLayout(contPane, BoxLayout.PAGE_AXIS));
	    contPane.add(lab1);
		contPane.add(combo);
		contPane.add(lab2);
		contPane.add(name);
		contPane.add(lab3);
		contPane.add(tel);
		contPane.add(lab4);
		contPane.add(ad);
		contPane.add(button);
	    
		MyWindowListener mwl = new MyWindowListener();
	    this.addWindowListener(mwl);
		button.addActionListener(mwl);
	    combo.addActionListener(mwl);
	    item.addActionListener(mwl);
	    
	    String s=(String)combo.getSelectedItem();
		Student p=(Student)stdb.retrieveObject(s);
		name.setText(p.getName());
		tel.setText(p.getTelephone());
		ad.setText(p.getAdress());
		
	    pack();
	    
	}
	public static void main(String[] args) {
		MyUniversity app = new MyUniversity();

	}
	private class MyWindowListener extends WindowAdapter implements ActionListener {
	    public void windowClosing(WindowEvent event) {
	    	System.out.println("Saving DB");
			stdb.storeDB();
	    	System.out.println("Exit Window Application");
	      
	      System.exit(0); 
	    }


		public void actionPerformed(ActionEvent e) {
		  System.out.println(e.getActionCommand());
		  if(e.getSource()==button)
		  {
			  String s=(String)combo.getSelectedItem();
			  Student p=(Student)stdb.retrieveObject(s);
			  p.setTelephone(tel.getText());
			  p.setAdress(ad.getText());
		  }
		  else if(e.getSource()==combo)
		  {
			  JComboBox cb = (JComboBox) e.getSource(); 
			  String s=(String)cb.getSelectedItem();
			  Student p=(Student)stdb.retrieveObject(s);
			  name.setText(p.getName());
			  tel.setText(p.getTelephone());
			  ad.setText(p.getAdress());
		  }
		  else
		  {
			  System.out.println("Saving DB");
			  stdb.storeDB();
		  
		  }
		
		}
		

		
	}
	public void initDatabase() {

		Student p;

		p = new Student("2013030001","Nikos Papadopoulos","6922334455","Dimokratias 43\n 73100 CHANIA");

		stdb.storeObject(p);

		p = new Student("2013030002","Nikos Papapetrou","691133446","Anagnostou Gogoni 12\n 73100 CHANIA");

		stdb.storeObject(p);

		p = new Student("2013030003","Kostas Petrou","691133456","Sfakianaki 56\n 73100 CHANIA");

		stdb.storeObject(p);

		p = new Student("2013030004","Dimitris Paterakis","6974433221","Anapauseos 100 \n 73100 CHANIA");

		stdb.storeObject(p);

		}

}
