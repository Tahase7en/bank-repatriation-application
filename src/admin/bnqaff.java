package admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import login.BD;
import login.login;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Button;
import java.awt.Font;

public class bnqaff
{

	private static JPanel contentPane;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static float scalex=(float)screenSize.getWidth()/1920;
	public static float scaley=(float)screenSize.getHeight()/1080;
	private static JTextField txtCode,txtIdBanque,txtNom,txtServer,txtData,txtPad;
	private static JButton btnEnregistrer, btnModifier;
	static String[] col= {"codeAbrege","idBq","Nom","NumServer","Data","NumPad"};
	static ArrayList<JTextField> list ;	
	//lblImg.setIcon(new ImageIcon(""));
	//lblImg.setBounds(0, -155, 1366, 768);
	
	static String code;

	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel Init()	
	{		
		initComponents();
		createEvents();
		
		return contentPane;
	}
	
	static void initComponents()
	{
		
		
	
		contentPane = new JPanel();
		//contentPane.setBackground(new Color(230, 230, 250));
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		list = new ArrayList<JTextField>(6);	
		btnEnregistrer = new JButton("enregistrer");
		
		JLabel lblCodeAbrege = new JLabel("CODE ABREGE:");
		lblCodeAbrege.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtCode = new JTextField();
		txtCode.setColumns(10);
		
		txtIdBanque = new JTextField();
		txtIdBanque.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		
		JLabel lblIdBanque = new JLabel("ID BANQUE:");
		lblIdBanque.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNomBanque = new JLabel("NOM BANQUE:");
		lblNomBanque.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNumServeur = new JLabel("NUM SERVEUR:");
		lblNumServeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblDonneesComplementaires = new JLabel("DONNEES COMPLEMENTAIRES:");
		lblDonneesComplementaires.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNumPad = new JLabel("NUM PAD :");
		lblNumPad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtServer = new JTextField();
		txtServer.setColumns(10);
		
		btnModifier = new JButton("modifier");
		
		txtData = new JTextField();
		txtData.setColumns(10);
		
		txtPad = new JTextField();
		txtPad.setColumns(10);
		
		list.add(txtCode);
		list.add(txtIdBanque);
		list.add(txtNom);
		list.add(txtServer);
		list.add(txtData);
		list.add(txtPad);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDonneesComplementaires)
										.addComponent(lblNumPad, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtPad, GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
										.addComponent(txtData, GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCodeAbrege, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
									.addGap(93)
									.addComponent(txtCode, GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
											.addComponent(lblIdBanque, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
											.addGap(118)
											.addComponent(txtIdBanque, GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE))
										.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblNomBanque, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNumServeur, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
											.addGap(93)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txtNom, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
												.addComponent(txtServer, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE))))
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(243)
							.addComponent(btnEnregistrer, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
							.addGap(84)
							.addComponent(btnModifier, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
							.addGap(140)))
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodeAbrege, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtCode, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtIdBanque, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIdBanque))
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNom, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomBanque))
					.addGap(59)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtServer, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumServeur))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDonneesComplementaires)
						.addComponent(txtData, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPad, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumPad))
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEnregistrer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnModifier, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
					.addGap(70))
		);
		contentPane.setLayout(gl_contentPane);
		
		
	}
	
	static void createEvents()
	{
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				validation();
		        System.out.println("ajout");  
		        DefaultTreeModel model = (DefaultTreeModel) bnq.cat_tree.getModel();
	            DefaultMutableTreeNode node = (DefaultMutableTreeNode)model.getRoot();   
	            node.add(new DefaultMutableTreeNode(list.get(0).getText()));
	            model.reload(node);
			}
		});		
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				BD.queryUpdate("banque", col, list, col[0]+" ='"+code+"' and iduser='"+login.getId()+"';");
		        System.out.println("modify");  
			}
		});		

		if (login.getcpt()==2) {
				try
				{
					code=bnq.getcode();
					System.out.println("mod");

					if(code!="Liste banque") 
					txtCode.setText(code);
					System.out.println(txtCode.getText());

					String[] col1= {"idBq","Nom","NumServer","Data","NumPad"};
					ResultSet rs=BD.fcSelect(col1,"banque","codeAbrege='"+code+"' and iduser='"+login.getId()+"'");
					txtIdBanque.setText(rs.getString(1));
					txtNom.setText(rs.getString(2));
					txtServer.setText(rs.getString(3));
					txtData.setText(rs.getString(4));
					txtPad.setText(rs.getString(5));
					System.out.println("!!!!!");

					System.out.println(list.get(0).getText());


				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
		}
		if (login.getcpt()==1) {
				txtCode.setText("");
				txtIdBanque.setText("");
				txtNom.setText("");
				txtServer.setText("");
				txtData.setText("");
				txtPad.setText("");
	}
		
	}

public static void validation(){                

    if(txtCode.getText().trim().isEmpty()){
		JOptionPane.showMessageDialog(null, "code abrege obligatoire!");
		
    } 
    else if(txtServer.getText().trim().isEmpty()){
		JOptionPane.showMessageDialog(null, "numero de serveur obligatoire!");
    } 
    else {
    	System.out.println("adding");
    	BD.queryInsert("banque", col,"iduser", list,login.getId());
		JOptionPane.showMessageDialog(null, "done");}
}

}
