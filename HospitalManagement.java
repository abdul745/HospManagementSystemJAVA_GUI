import java.awt.event.*;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import java.sql.*;


class HospitalManagement
{
	public static void main(String args[])
	{
			
		Win1 start=new Win1();
			start.window1();
	}
}

class Win1
{
	void window1()
	{
		JFrame win1=new JFrame();
	win1.setSize(1366,786);
	win1.setLayout(null);
	win1.setDefaultCloseOperation(win1.EXIT_ON_CLOSE);
		JLabel welcome=new JLabel("Welcome To City Hospital");
		welcome.setFont(new Font("Times New Roman", Font.BOLD, 40));
		
		welcome.setBounds(400,200,683,100);
		win1.add(welcome);
		

		JButton reg=new JButton("Registration");
		reg.setBounds(300,362,150,70);
		win1.add(reg);
		reg.addActionListener(new winEventHandler(win1));
		
		JButton app=new JButton("Appointments");
		app.setBounds(459,362,150,70);
		win1.add(app);
		app.addActionListener(new winEventHandler(win1));
		
		JButton emrg=new JButton("Emergency");
		emrg.setBounds(618,362,150,70);
		win1.add(emrg);
		emrg.addActionListener(new winEventHandler(win1));
		
		JButton rltv=new JButton("Meeting");
		rltv.setBounds(777,362,150,70);
		win1.add(rltv);
		rltv.addActionListener(new winEventHandler(win1));
		
		JButton hstry=new JButton("Database");
		hstry.setBounds(936,362,150,70);
		win1.add(hstry);
		hstry.addActionListener(new winEventHandler(win1));
		
		win1.setVisible(true);
		
	}
	
	
}


class EventHandler implements ActionListener
{  
		static Connection connection=null;
		static Statement statement=null;
		PreparedStatement prepare=null;
		ResultSet resultSet=null;
		
	JRadioButton dis1;
	JRadioButton dis2;
	JRadioButton dis3;
	JRadioButton dis4;
	JTextField nam;
	JTextField age;
	JTextField nic;
	JTextField mob;
	JRadioButton r_type1;
	JRadioButton r_type2;
	JRadioButton r_type3;
	JTextField bill;
	JRadioButton sv1;	
	JRadioButton sv2;
	JRadioButton sv3;
	
	EventHandler(JRadioButton dis11,JRadioButton dis22,JRadioButton dis33,JRadioButton dis44,JTextField namm, JTextField agee, JTextField nicc, JTextField mobb,JRadioButton r_type11,JRadioButton r_type22,JRadioButton r_type33)
	{
		this.dis1=dis11;
		this.dis2=dis22;
		this.dis3=dis33;
		this.dis4=dis44;
		this.nam=namm;
		this.age=agee;
		this.nic=nicc;
		this.mob=mobb;
		this.r_type1= r_type11;
		this.r_type2= r_type22;
		this.r_type3= r_type33;
		
	}
	EventHandler()
	{
		
	}
	EventHandler(JRadioButton dis11,JRadioButton dis22,JRadioButton dis33,JRadioButton dis44,JTextField namm,JTextField agee,JTextField mobb)
	{
		this.dis1=dis11;
		this.dis2=dis22;
		this.dis3=dis33;
		this.dis4=dis44;
		this.nam=namm;
		this.age=agee;
		this.mob=mobb;
	}
	EventHandler(JRadioButton dis11,JRadioButton dis22,JRadioButton dis33,JRadioButton dis44,JRadioButton sv11,JRadioButton sv22,JRadioButton sv33)
	{
		this.dis1=dis11;
		this.dis2=dis22;
		this.dis3=dis33;
		this.dis4=dis44;
		this.sv1=sv11;
		this.sv2=sv22;
		this.sv3=sv33;
	}
	EventHandler(JTextField nicc)
	{
		this.nic=nicc;
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String msAccDB= "Database.accdb";
		String dbURL="jdbc:ucanaccess://"+msAccDB;
		connection=DriverManager.getConnection(dbURL);
		statement=connection.createStatement();
		
		}
	catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		JButton button=(JButton)ae.getSource();
		String but=button.getText();
		if(but.equals("Register"))
		{
			String diss="";
			String Billing="";
			String T_Bill="";
			try
		{
			if(dis1.isSelected())
			{
			diss=dis1.getText();
			}
			else if(dis2.isSelected())
			{
			diss=dis2.getText();
			}
			else if(dis3.isSelected())
			{
			diss=dis3.getText();
			}
			else if(dis4.isSelected())
			{
			diss=dis4.getText();
			}
			
				
				
				
			
			prepare=connection.prepareStatement("insert into Registrations (ID,Disease,Names,Age,CNIC,Mobile_No,Room_type,Total_Bill) values (?,?,?,?,?,?,?,?)");
				prepare.setString(1,"0");
				if(diss.equals("Surgery"))
				{
				prepare.setString(2,"Surgery");	
				}
				else if(diss.equals("Medicine"))
				{
				prepare.setString(2,"Medicine");	
				}
				else if(diss.equals("Cardiology"))
				{
				prepare.setString(2,"Cardiology");	
				}
				else if(diss.equals("Gynecology"))
				{
				prepare.setString(2,"Gynecology");	
				}
			prepare.setString(3,nam.getText());
			prepare.setString(4,age.getText());
			prepare.setString(5,nic.getText());
			prepare.setString(6,mob.getText());
				
				if(r_type1.isSelected())
				{
				Billing=r_type1.getText();
				}
				else if(r_type2.isSelected())
				{
				Billing=r_type2.getText();
				}
				else if(r_type3.isSelected())
				{
				Billing=r_type3.getText();
				}
				prepare.setString(7,Billing);
				if(Billing.equals("1st Class Room"))
				{
				prepare.setString(8,"3000");
				T_Bill="3000";
				}
				else if(Billing.equals("2nd Class Room"))
				{
				prepare.setString(8,"2000");	
				T_Bill="2000";
				}
				else if(Billing.equals("3rd Class Room"))
				{
				prepare.setString(8,"1000");
				T_Bill="1000";
				}
				System.out.println("Your Total Bill is: Rs."+T_Bill+"/=");
				prepare.executeUpdate();
				if(connection!=null)
			{
			
			prepare.close();
			statement.close();			
			connection.close();	
			
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		
		
		else if(but.equals("Confirm"))
		{
			String diss="";
			try
		{
			if(dis1.isSelected())
			{
			diss=dis1.getText();
			}
			else if(dis2.isSelected())
			{
			diss=dis2.getText();
			}
			else if(dis3.isSelected())
			{
			diss=dis3.getText();
			}
			else if(dis4.isSelected())
			{
			diss=dis4.getText();
			}
			
				
				
				
			
			prepare=connection.prepareStatement("insert into Appointments (ID,Disease,Names,Age,Mobile_No) values (?,?,?,?,?)");
				prepare.setString(1,"0");
				if(diss.equals("Surgery"))
				{
				prepare.setString(2,"Surgery");	
				}
				else if(diss.equals("Medicine"))
				{
				prepare.setString(2,"Medicine");	
				}
				else if(diss.equals("Cardiology"))
				{
				prepare.setString(2,"Cardiology");	
				}
				else if(diss.equals("Gynecology"))
				{
				prepare.setString(2,"Gynecology");	
				}
			prepare.setString(3,nam.getText());
			prepare.setString(4,age.getText());
			prepare.setString(5,mob.getText());
			
			prepare.executeUpdate();
				if(connection!=null)
			{
			
			prepare.close();
			statement.close();			
			connection.close();	
			
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		
		else if(but.equals("Check"))
		{
			if(dis1.isSelected() && sv1.isSelected() || dis2.isSelected() && sv1.isSelected() || dis3.isSelected() && sv1.isSelected() ||dis3.isSelected() && sv1.isSelected())
			{
				System.out.println("Call the Doctor");	
			}
			if(dis1.isSelected() && sv2.isSelected() || dis2.isSelected() && sv2.isSelected() || dis3.isSelected() && sv2.isSelected() ||dis3.isSelected() && sv1.isSelected())
			{
				System.out.println("Admit in Emergency Ward.");	
			}
			if(dis1.isSelected() && sv3.isSelected() || dis2.isSelected() && sv3.isSelected() || dis3.isSelected() && sv3.isSelected() ||dis3.isSelected() && sv1.isSelected())
			{
				System.out.println("Admit in ICU Immediately and Register Patient");	
			}
		}
		
		else if(but.equals("Find"))
		{
			try
			{	
				String fnd=nic.getText();
				String query="select * from Registrations where CNIC='" + fnd +"'";
				//String query="select * from Registrations where CNIC=?";
				
			//	System.out.println(query);
				
				prepare=connection.prepareStatement(query);
				
				//prepare.setString(5,fnd);
				
				resultSet = prepare.executeQuery();
			
				resultSet.next();
   
				System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5)+"\t"+resultSet.getString(6)+"\t"+resultSet.getString(7));
				
				if(connection!=null)
			{
			resultSet.close();
			prepare.close();
			statement.close();			
			connection.close();				
			}
			}
			catch(Exception e)
			{
			e.printStackTrace();
			}
		}
		else if(but.equals("RegisterationsD"))
			{
				try
				{
				resultSet=statement.executeQuery("SELECT * FROM REGISTRATIONS");
				while(resultSet.next())
				{
					System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5)+"\t"+resultSet.getString(6)+"\t"+resultSet.getString(7)+"\t"+resultSet.getString(8));
				
				}
				
				}
				catch(Exception e)
			{
			e.printStackTrace();
			}
			finally 
			{
				try
				{
					if(null!=connection)
					{
						resultSet.close();
						
					statement.close();			
					connection.close();
					}
					
				}
				catch(Exception e)
			{
			e.printStackTrace();
			}
				
			}
			}
			else if(but.equals("AppointmentsD"))
			{
				try
				{
				resultSet=statement.executeQuery("select * from Appointments");
				while(resultSet.next())
				{
					System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5));
				
				}
					
				}
				catch(Exception e)
				{
			e.printStackTrace();
				}
				finally 
			{
				try
				{
						if(null!=connection)
						{
						resultSet.close();
						
						statement.close();			
						connection.close();
						}
					
				}
					catch(Exception e)
					{
					e.printStackTrace();
					}
				
			}
			}
			
			
		}
}	


class winEventHandler implements ActionListener
{
	JFrame ff;
	winEventHandler(JFrame f)
	{
		ff=f;
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		JButton slct=(JButton)ae.getSource();
		String a=slct.getText();
		if(a.equals("Main Menu")) 
		{
			ff.setVisible(false);
			Win1 ab=new Win1();
			ab.window1();
		}
		if(a.equals("Registration")) 
		{
			ff.setVisible(false);
			Win2 ab=new Win2();
			ab.window2();
		}
		if(a.equals("Appointments")) 
		{
			ff.setVisible(false);
			Win3 ab=new Win3();
			ab.window3();
		}
		if(a.equals("Emergency")) 
		{
			ff.setVisible(false);
			Win4 ab=new Win4();
			ab.window4();
		}
		if(a.equals("Meeting")) 
		{
			ff.setVisible(false);
			Win5 ab=new Win5();
			ab.window5();
		}
		if(a.equals("Database")) 
		{
			ff.setVisible(false);
			Win6 ab=new Win6();
			ab.window6();
		}
		
		
	}
	
	
}

class Win2
{
	 void window2()
		{
		
		JFrame win2=new JFrame();
		win2.setLayout(null);
		win2.setSize(1366,786);
		
		win2.setDefaultCloseOperation(win2.EXIT_ON_CLOSE);
		
		JLabel reg=new JLabel("Patient Registration");
		reg.setFont(new Font("Times New Roman", Font.BOLD, 30));
		reg.setBounds(500,3,683,100);
		win2.add(reg);
		
		JRadioButton sur=new JRadioButton("Surgery");
		sur.setBounds(460,70,100,50);
		win2.add(sur);
		JRadioButton card=new JRadioButton("Cardiology");
		card.setBounds(560,70,100,50);
		win2.add(card);
		JRadioButton med=new JRadioButton("Medicine");
		med.setBounds(660,70,100,50);
		win2.add(med);
		JRadioButton gyn=new JRadioButton("Gynecology");
		gyn.setBounds(760,70,100,50);
		win2.add(gyn);
		ButtonGroup dis=new ButtonGroup();
		dis.add(sur);
		dis.add(card);
		dis.add(med);
		dis.add(gyn);
		
		JLabel name=new JLabel("Enter Name");
		name.setBounds(100,130,683,100);
		win2.add(name);
		
		JTextField nm=new JTextField();
		nm.setBounds(455,140,500,50);
		win2.add(nm);
		
		JLabel age=new JLabel("Enter age");
		age.setBounds(100,200,683,100);
		win2.add(age);
		
		JTextField ag=new JTextField();
		ag.setBounds(455,210,500,50);
		win2.add(ag);
		
		JLabel cnic=new JLabel("Enter CNIC");
		cnic.setBounds(100,270,683,100);
		win2.add(cnic);
		
		JTextField nic=new JTextField();
		nic.setBounds(455,280,500,50);
		win2.add(nic);
		
		JLabel m_no=new JLabel("Enter Mobile Number");
		m_no.setBounds(100,340,683,100);
		win2.add(m_no);
		
		JTextField no=new JTextField();
		no.setBounds(455,350,500,50);
		win2.add(no);
		
		
		JRadioButton c1=new JRadioButton("1st Class Room");
		c1.setBounds(460,410,150,20);
		win2.add(c1);
		JRadioButton c2=new JRadioButton("2nd Class Room");
		c2.setBounds(650,410,150,20);
		win2.add(c2);
		JRadioButton c3=new JRadioButton("3rd Class Room");
		c3.setBounds(840,410,150,20);
		win2.add(c3);
		ButtonGroup cls=new ButtonGroup();
		cls.add(c1);
		cls.add(c2);
		cls.add(c3);

		JButton cnf=new JButton("Register");
		cnf.setBounds(600,530,150,70);
		win2.add(cnf);
		cnf.addActionListener(new EventHandler(sur,card,med,gyn,nm,ag,nic,no,c1,c2,c3));
		
		
		
		JButton menu=new JButton("Main Menu");
		menu.setBounds(1100,550,150,70);
		win2.add(menu);
		menu.addActionListener(new winEventHandler(win2));
		
		win2.setVisible(true);
		}
	
}


class Win3
{
	void window3()
	{
		JFrame win3=new JFrame();
		win3.setLayout(null);
		win3.setSize(1366,786);
		
		win3.setDefaultCloseOperation(win3.EXIT_ON_CLOSE);
		
		JLabel reg=new JLabel("Appointments");
		reg.setFont(new Font("Times New Roman", Font.BOLD, 30));

		reg.setBounds(500,5,683,100);
		win3.add(reg);
		
		JRadioButton sur=new JRadioButton("Surgery");
		sur.setBounds(460,70,100,50);
		win3.add(sur);
		JRadioButton card=new JRadioButton("Cardiology");
		card.setBounds(560,70,100,50);
		win3.add(card);
		JRadioButton med=new JRadioButton("Medicine");
		med.setBounds(660,70,100,50);
		win3.add(med);
		JRadioButton gyn=new JRadioButton("Gynecology");
		gyn.setBounds(760,70,100,50);
		win3.add(gyn);
		ButtonGroup dis=new ButtonGroup();
		dis.add(sur);
		dis.add(card);
		dis.add(med);
		dis.add(gyn);
		
		JLabel name=new JLabel("Enter Name");
		name.setBounds(100,130,683,100);
		win3.add(name);
		
		JTextField nm=new JTextField();
		nm.setBounds(455,140,500,50);
		win3.add(nm);
		
		JLabel age=new JLabel("Enter age");
		age.setBounds(100,200,683,100);
		win3.add(age);
		
		JTextField ag=new JTextField();
		ag.setBounds(455,210,500,50);
		win3.add(ag);
		
		JLabel m_no=new JLabel("Enter Mobile Number");
		m_no.setBounds(100,270,683,100);
		win3.add(m_no);
		
		JTextField no=new JTextField();
		no.setBounds(455,280,500,50);
		win3.add(no);
		
		
		JButton cnf=new JButton("Confirm");
		cnf.setBounds(600,350,150,70);
		win3.add(cnf);
		
		cnf.addActionListener(new EventHandler(sur,card,med,gyn,nm,ag,no));
		
		JButton menu=new JButton("Main Menu");
		menu.setBounds(1100,550,150,70);
		win3.add(menu);
		menu.addActionListener(new winEventHandler(win3));
		
		
		win3.setVisible(true);
	}
}


class Win4
{
	void window4()
	{
		JFrame win4=new JFrame();
		win4.setLayout(null);
		win4.setSize(1366,786);
		
		win4.setDefaultCloseOperation(win4.EXIT_ON_CLOSE);
		
		JLabel emg=new JLabel("Emergency");
		emg.setFont(new Font("Times New Roman", Font.BOLD, 30));

		emg.setBounds(500,5,683,100);
		win4.add(emg);
		
		JLabel pb=new JLabel("Enter Problem");
		pb.setBounds(455,40,683,100);
		win4.add(pb);
		
		JRadioButton ac=new JRadioButton("Accident");
		ac.setBounds(460,130,100,50);
		win4.add(ac);
		JRadioButton ha=new JRadioButton("Heart Attack");
		ha.setBounds(460,180,100,50);
		win4.add(ha);
		JRadioButton bps=new JRadioButton("Blood Pressure/Sugar");
		bps.setBounds(460,230,150,50);
		win4.add(bps);
		JRadioButton del=new JRadioButton("Delivery");
		del.setBounds(460,280,100,50);
		win4.add(del);
		ButtonGroup con=new ButtonGroup();
		con.add(ac);
		con.add(ha);
		con.add(bps);
		con.add(del);
		
		JLabel svrty=new JLabel("Enter Severity Level:");
		svrty.setBounds(530,320,683,100);
		win4.add(svrty);
		
		JRadioButton s1=new JRadioButton("Stage 1");
		s1.setBounds(460,400,100,50);
		win4.add(s1);
		JRadioButton s2=new JRadioButton("Stage 2");
		s2.setBounds(460,450,100,50);
		win4.add(s2);
		JRadioButton s3=new JRadioButton("Stage 3");
		s3.setBounds(460,500,100,50);
		win4.add(s3);
		
		ButtonGroup stage=new ButtonGroup();
		stage.add(s1);
		stage.add(s2);
		stage.add(s3);
		
		JLabel action=new JLabel("If Severity is Stage 3,Admit in ICU and Register Patient: ");
		action.setBounds(400,530,683,100);
		win4.add(action);
		
		JButton check=new JButton("Check");
		check.setBounds(800,580,150,70);
		win4.add(check);
		check.addActionListener(new EventHandler(ac,ha,bps,del,s1,s2,s3));
		
		JButton rg=new JButton("Registration");
		rg.setBounds(950,200,150,70);
		win4.add(rg);
		rg.addActionListener(new winEventHandler(win4));
		
		JButton menu=new JButton("Main Menu");
		menu.setBounds(1100,550,150,70);
		win4.add(menu);
		menu.addActionListener(new winEventHandler(win4));
		
		
		win4.setVisible(true);
		
	}
	
}


class Win5
{
	void window5()
	{
		JFrame win5=new JFrame();
		win5.setSize(1366,786);
		win5.setLayout(null);
		
		win5.setDefaultCloseOperation(win5.EXIT_ON_CLOSE);
		
		JLabel meet=new JLabel("Meeting a Patient");
		meet.setFont(new Font("Times New Roman", Font.BOLD, 30));

		meet.setBounds(500,3,683,100);
		win5.add(meet);
		
		JLabel name=new JLabel("Enter CNIC of Patient");
		name.setBounds(380,100,500,50);
		win5.add(name);
		
		JTextField nic=new JTextField();
		nic.setBounds(400,160,450,50);
		win5.add(nic);
		
		JButton find=new JButton("Find");
		find.setBounds(550,300,150,70);
		win5.add(find);
		find.addActionListener(new EventHandler(nic));
		
		JButton menu=new JButton("Main Menu");
		menu.setBounds(1100,550,150,70);
		win5.add(menu);
		menu.addActionListener(new winEventHandler(win5));
		
		
		win5.setVisible(true);
	}
	
}

class Win6
{
	void window6()
	{
		JFrame win6=new JFrame();
		win6.setLayout(null);
		win6.setSize(1366,786);
	
		win6.setDefaultCloseOperation(win6.EXIT_ON_CLOSE);
		
		JLabel data=new JLabel("Database");
		data.setFont(new Font("Times New Roman", Font.BOLD, 30));

		data.setBounds(500,3,683,100);
		win6.add(data);
		
	
		JButton regData=new JButton("RegisterationsD");
		regData.setBounds(300,350,150,50);	
		win6.add(regData);

		regData.addActionListener(new EventHandler());
		
		
		JButton appData=new JButton("AppointmentsD");
		appData.setBounds(800,350,150,50);	
		win6.add(appData);
		appData.addActionListener(new EventHandler());
	
		win6.setVisible(true);
		
		
		JButton menu=new JButton("Main Menu");
		menu.setBounds(1100,550,150,70);
		win6.add(menu);
		menu.addActionListener(new winEventHandler(win6));
		
	}
}