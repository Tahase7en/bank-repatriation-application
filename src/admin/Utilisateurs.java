package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import login.Scripts;
import login.Utilisateur;
import login.login;
import login.BD;
import login.CustomDialog;


public class Utilisateurs
{
	private static JPanel Utilisateurs;
	static JSplitPane splitPane;
	static JTable table;
	static JButton Delete,ChangeName,ChangeUser,ChangePass,ChangeType;
	static float scalex=bnq.scalex;
	static float scaley=bnq.scaley;
	static boolean RowSelected;
	static int SelectedIduser;
	//static Connection connection=BD.dbConnector();


	public static JPanel Init()	
	{
		Utilisateurs=new JPanel();
		RowSelected=false;
				
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setForeground(Color.DARK_GRAY);
		table.setFont(new Font("Century Gothic", Font.PLAIN, (int)(scalex*14)));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds((int)(scalex*50), (int)(scaley*50), (int)(scalex*1600), (int)(scaley*750));
		Utilisateurs.add(scrollPane);
		scrollPane.setViewportView(table);
		ResultSet rs=Utilisateur.getUsers();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		Delete=Scripts.NewButton("Supprimer",Color.gray,20,200,40, 1685, 50);	Utilisateurs.add(Delete);
		Utilisateurs.add(Scripts.NewLabel("Changer :", 20, 1685, 110));
		ChangeName=Scripts.NewButton("Nom d'utilisateur",Color.gray,20,200,30, 1685, 190);	Utilisateurs.add(ChangeName);
		ChangePass=Scripts.NewButton("Mot de passe",Color.gray,20,200,30, 1685, 230);	Utilisateurs.add(ChangePass);
		ChangeType=Scripts.NewButton("Type de Compte",Color.gray,20,200,30, 1685, 270);	Utilisateurs.add(ChangeType);

		ChangeName.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(RowSelected)
				{
					SelectedIduser=Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
					CustomDialog customDialog = new CustomDialog(null,"nom");
					customDialog.pack();customDialog.setLocationRelativeTo(null);customDialog.setVisible(true);	
					System.out.println(SelectedIduser);
					System.out.println(customDialog.getValidatedText());
                    if(customDialog.getValidatedText()!=null)
                    {
                    	BD.ChangeName(SelectedIduser, customDialog.getValidatedText());
                    }
            		resetTable();
				}
			}
			
		});
		ChangePass.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(RowSelected)
				{
					SelectedIduser=Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
					CustomDialog customDialog = new CustomDialog(null,"mot de passe",true);
					customDialog.pack();customDialog.setLocationRelativeTo(null);customDialog.setVisible(true);
					String[] k= {"key"};
					try
					{
						String key=BD.fcSelect(k, "login", "id="+SelectedIduser).getString(1);
						System.out.println(SelectedIduser);
						System.out.println(customDialog.getValidatedText());

	                    if(customDialog.getValidatedText()!=null)
	                    {
	    					System.out.println(key);

	                    	String password=Scripts.encrypt(customDialog.getValidatedText(),key+Utilisateur.secretKey1);
	                    	String pass=Scripts.encrypt(password,key+Utilisateur.secretKey2);
	    					System.out.println(pass);

	                    	BD.ChangePass(SelectedIduser, pass);                 	
	                    }
					} catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
            		resetTable();
				}
			}
		});
		ChangeType.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(RowSelected)
				{
					SelectedIduser=Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
					System.out.println("aaaaaaa");
					if(SelectedIduser==Integer.parseInt(login.getId()))
					{
						JOptionPane.showMessageDialog(null, "Vous ne pouvez pas changer le type du compte auquel vous êtes connecté","Erreur",JOptionPane.ERROR_MESSAGE);
					}
					else 
					{
						int gid=Utilisateur.getGID(SelectedIduser);
						if(gid==0)
						{
							BD.ChangeType(SelectedIduser, "Utilisateur");
						}
						else
						{
							BD.ChangeType(SelectedIduser, "Administrateur");
						}
	
	                	
						setButtonColor(Color.GRAY);
						resetTable();
					}
				}
			}
		});
		
		Delete.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(RowSelected)
				{
					SelectedIduser=Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
					if(SelectedIduser==Integer.parseInt(login.getId()))
					{
						JOptionPane.showMessageDialog(null, "Vous ne pouvez pas supprimer le compte auquel vous êtes connecté","Erreur",JOptionPane.ERROR_MESSAGE);
					}
					else 
					{
						Utilisateur.Delete(SelectedIduser);
						
						resetTable();
					}
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter(){
	        public void mousePressed(MouseEvent e) 
	        {
		        RowSelected=true;
				setButtonColor(new Color(100,150,215));
	        }
	    });
		
		String[] Types= {"Administrateur","Utilisateur"};
		
		Utilisateurs.add(Scripts.NewLabel("Ajouter :", 		20, 50, 860));	
		
		Utilisateurs.add(Scripts.NewLabel("Nom d'utilisateur :",20, 520, 810));
		JTextField User=Scripts.NewTextField(200, 35, 500, 850); Utilisateurs.add(User);
		
		Utilisateurs.add(Scripts.NewLabel("Mot de passe :", 20, 820, 810));
		JTextField Pass=Scripts.NewTextField(200, 35, 800, 850); Utilisateurs.add(Pass);
		
		Utilisateurs.add(Scripts.NewLabel("Type de Compte :",20, 1120, 810));
		JComboBox<String> Type=Scripts.NewComboBox(Types,220,35, 1100,850); Utilisateurs.add(Type);
		
		JButton AddUser=Scripts.NewButton("Ajouter",new Color(100,150,215),20,150,40, 1425, 850);
		Utilisateurs.add(AddUser);
		AddUser.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				int IdUser=Utilisateur.GetuserCount()+1;
				String username=User.getText();
				String password=Pass.getText();
	            System.out.println(password);  

				int GroupId=Type.getSelectedIndex();
				if( username.length()>0 && Pass.getText().length()>0 )
				{
					try{
						Utilisateur.Add(IdUser,username,password, GroupId);
						ResultSet rs=Utilisateur.getUsers();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						//connection.commit();
						User.setText("");Pass.setText("");
						//JOptionPane.showMessageDialog(null, "done");
					}
					catch(Exception ex) {
			            System.out.println(ex.toString());  

						JOptionPane.showMessageDialog(null, "Veuillez Vérifier vos entrées.","Erreur",JOptionPane.ERROR_MESSAGE);}
				}
				else {JOptionPane.showMessageDialog(null, "Veuillez Vérifier vos entrées.","Erreur",JOptionPane.ERROR_MESSAGE);}
				
			}
		});
		
		Utilisateurs.add(Scripts.NewImage("res/User/AdminBackground.png", 1920,1080, 0, 0));
		
		Utilisateurs.setLayout(null);
		return Utilisateurs;
	}
	
	public static void setButtonColor(Color color)
	{
		Delete.setBackground(color);
		ChangeName.setBackground(color);
		ChangePass.setBackground(color);
		ChangeType.setBackground(color);
	}	
	
	public static void resetTable()
	{
		ResultSet rs=Utilisateur.getUsers();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		table.clearSelection();
		setButtonColor(Color.GRAY);
		RowSelected=false;
	}
}

