package login;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import admin.change;
import login.Utilisateur;
import user.Suser;




public class login
{
	public static String username,pwd;
	private JCheckBox RememberUser,RememberPass;
	private int connectCounter;
	private long timeCounter;
	private boolean canEnter;
	private static int cpt;
	public static String id;
	static int i=0;


	public login()
	{
		JFrame frame = new JFrame();	
		
		connectCounter=0;
		canEnter=true;
		timeCounter= System.currentTimeMillis()/1000000000;
		
		Random r = new Random();
		int low = 10;
		int high = 60;
		int result = r.nextInt(high-low) + low;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		float scaley=(float)screenSize.getHeight()/1080;
		float scalex=(float)screenSize.getWidth()/1920;
		frame.setSize((int)(650*scalex), (int)(328*scaley));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setUndecorated(true);

		frame.add(Scripts.NewLabel("Nom d'utilisateur",20,290, 80));
		frame.add(Scripts.NewLabel("Mot de Passe",20,290, 130));
		frame.add(Scripts.NewImage("res/logo.png",(int)(650*3/7),328, 0, 0));
		
		JTextField User=Scripts.NewTextField(175,32,460,80);		frame.add(User);
		JPasswordField Pass=Scripts.NewPasswordField(175,32,460,130);	frame.add(Pass);
		User.setText(importUserName());
		Pass.setText(importPassword());
		
		User.addActionListener(ae -> 
		{
			Pass.grabFocus();
		});
		
		Pass.addActionListener(ae -> 
		{
			long currentTime=System.currentTimeMillis()/1000;
			if(!canEnter && currentTime>timeCounter+result)
			{
					canEnter=true;						
			}
			else
			{
				try
				{
					connect(User,Pass);
					connectCounter=0;
					frame.dispose();
				}
				catch (Exception e1)
				{
					if(connectCounter<3)
					{	JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer","Erreur",JOptionPane.ERROR_MESSAGE);
						connectCounter+=1;
					}
					else 
					{
						timeCounter= System.currentTimeMillis()/1000;
						connectCounter=0;
						canEnter=false;
						JOptionPane.showMessageDialog(null, "Vous avez entré un nom d'utilisateur ou un mot de passe incorrect plusieurs fois,"
								+ "\nVeuillez attendre "+(timeCounter+result-currentTime)+" secondes avant de réessayer la connection","Erreur",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		
		JButton exit=Scripts.NewButton("x",20,75,20 ,575,0);	frame.add(exit);
		RememberUser =Scripts.NewCheckBox("Rappeler mon nom d'utilisateur", 290, 180);	frame.add(RememberUser);
		RememberPass =Scripts.NewCheckBox("Rappeler mon mot de passe", 290, 210); 		frame.add(RememberPass);
		RememberPass.setVisible(false);
		RememberUser.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent arg0)
			{
				if (RememberUser.isSelected())
				{
					RememberPass.setVisible(true);
				} else
				{
					RememberPass.setSelected(false);
					RememberPass.setVisible(false);
				}
			}
		});
		
		if(User.getText().length()>0)
		{
			RememberUser.setSelected(true);
			if(Pass.getPassword().length>0)
			{
				RememberPass.setSelected(true);
			}
		}
		
		JButton login=Scripts.NewButton("Connexion",20,180,40,360,250);	frame.add(login);
		
		login.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				long currentTime=System.currentTimeMillis()/1000;
				if(connectCounter<3 && currentTime>timeCounter+result)
				{
					try
					{
						connect(User,Pass);
						frame.dispose();
					}
					catch (Exception e1)
					{connectCounter+=1;
					JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer","Erreur",JOptionPane.ERROR_MESSAGE);}
				}
				else
				{				
					System.out.println(connectCounter+"++"+(timeCounter+result-currentTime));
					timeCounter= System.currentTimeMillis()/1000;
					JOptionPane.showMessageDialog(null, "Vous avez entré un nom d'utilisateur ou un mot de passe incorrect plusieurs fois,"
							+ "\nVeuillez attendre "+(timeCounter+result-currentTime)+" secondes avant de réessayer la connection","Erreur",JOptionPane.ERROR_MESSAGE);
					connectCounter=0;

				}
			}
		});

		exit.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
		});
		
		frame.setVisible(true);
		///System.out.println(getId());

		//while(true) System.out.println(System.currentTimeMillis());
	}
public static void setId(JTextField User,JTextField pas) {
		
		try
		{
			String[] col= {"id"};
			System.out.println(User.getText());		
			String sql="select pwd, key from login where nom='"+User.getText()+"';";
			ResultSet rs=BD.executionQuery(sql);
			while(rs.next()) {
				System.out.println("ùùùùùùù");


			String p=Scripts.encrypt(pas.getText(),rs.getString(2)+Utilisateur.secretKey1);
			String pass=Scripts.encrypt(p,rs.getString(2)+Utilisateur.secretKey2);

			if(rs.getString(1).equals(pass)) {
				id=BD.fcSelect(col,"login","nom='"+User.getText()+"' and pwd='"+pass+"';").getString(1);
			System.out.println(id);
			}
			}
			//System.out.println(id);
			
			}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
	public static String getId() {
		return id;
	}
	void connect(JTextField User,JPasswordField Pass) throws Exception
	{
		username=User.getText();
		String passw=String.valueOf(Pass.getPassword());

		if (RememberUser.isSelected())
		{
			saveUserName(username);
		}
		if (RememberPass.isSelected())
		{
			savePassword(passw);
		}
		System.out.println("pwd");
		System.out.println(passw);

		if(Utilisateur.equal(username,passw))
		{
			setId(User,Pass);

			if(Utilisateur.getGID(Integer.parseInt(getId()))==0) {
				System.out.println("admin");
				change.init();

			}
			else if(Utilisateur.getGID(Integer.parseInt(getId()))==1) {
				System.out.println("user");

				Suser.init();
			}
		}
		//banques.init();
		else 
			throw new Exception("");		
	}
	public static void main(String[] args) 
	{
		new login();
		System.out.println("******");

	}

	
	public static int getcpt() {
		  return cpt;
	  }
	  public static void setcpt(int c) {
		  cpt=c;
	  }
	  
	static void saveUserName(String S)
	{
		try
		{
			ObjectOutputStream Out = new ObjectOutputStream(new FileOutputStream("UserName.bin"));
			Out.writeObject(S);
			Out.close();
		} catch (Exception e)
		{
		}
	}

	static String importUserName()
	{
		String UserName;
		try
		{
			ObjectInputStream In = new ObjectInputStream(new FileInputStream("UserName.bin"));
			UserName = (String) In.readObject();
			In.close();
		} catch (Exception e)
		{
			UserName = "";
		}
		saveUserName("");
		return UserName;
	}

	static void savePassword(String S)
	{
		try
		{
			ObjectOutputStream Out = new ObjectOutputStream(new FileOutputStream("Password.bin"));
			Out.writeObject(S);
			Out.close();
		} catch (Exception e)
		{
		}
	}

	static String importPassword()
	{
		String Password;
		try
		{
			ObjectInputStream In = new ObjectInputStream(new FileInputStream("Password.bin"));
			Password = (String) In.readObject();
			In.close();
		} catch (Exception e)
		{
			Password = "";
		}
		savePassword("");
		return Password;
	}

}

