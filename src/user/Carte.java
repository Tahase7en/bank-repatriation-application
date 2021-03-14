package user;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import login.BD;
import login.Scripts;
import login.login;
import java.awt.Button;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.ComponentOrientation;

public class Carte
{
	private static JPanel cartes,filech;
	
	private static final long serialVersionUID = -7606180063062414950L;
	private static JTextField textNom,textCodeBq,textype,textFich,textNumCarte,textNumCarte1;
	private static JSlider slider,slider_2 ;
	private static JButton btnEn,btnEnvoyer,btnModifier;
	private static Button button;

	static String code,codec;


	static String[] col= {"nomca","codeAbrege","type","nf","cartea","cartea1"};
	static ArrayList<JTextField> list;	
	static String [] id= {"id"};
	//private static final JTextField textField = new JTextField();
	static String type;
	static String[] Types=new String[4];
	static int i=0;
	private static JComboBox comboBox;
	//static JComboBox<String> comboBox ;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel Init()	
	{		
		initComponents();
		createEvents();
		return cartes;
	}
	private static void initComponents()
	{
		
		System.out.println("carte");
		cartes = new JPanel();
		cartes.setBorder(new EmptyBorder(5, 5, 5, 5));
		list = new ArrayList<JTextField>(6);
		
		JLabel lblNewLabel = new JLabel("NOM CARTE :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblCodeBanque = new JLabel("CODE BANQUE :");
		lblCodeBanque.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblType = new JLabel("TYPE :");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNomFichier = new JLabel("NOM FICHIER :");
		lblNomFichier.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblCarteDappel = new JLabel("CARTE D'APPEL :");
		lblCarteDappel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textNom = new JTextField();
		textNom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textNom.setColumns(10);
		
		textCodeBq = new JTextField();
		textCodeBq.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCodeBq.setColumns(10);
		
		textype = new JTextField();
		textype.setColumns(10);

		textFich = new JTextField();
		textFich.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFich.setColumns(10);
		
		slider= new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(4);
		slider.setMaximum(40);
		slider.setPaintLabels(true);
		slider.setValue(20);

		slider_2 = new JSlider();
		slider_2.setValue(20);
		slider_2.setPaintTicks(true);
		slider_2.setPaintLabels(true);
		slider_2.setMaximum(40);
		slider_2.setMajorTickSpacing(4);
		
		code=bnq.getcode();
		System.out.println(code);
		textNumCarte = new JTextField();
		textNumCarte.setColumns(10);	
		textNumCarte1 = new JTextField();
		textNumCarte1.setColumns(10);

		textCodeBq.setText(code);
		textCodeBq.setEditable(false);
		String s="select type from carte";
		ResultSet rs= BD.executionQuery(s);
	 	try
		{
			while(rs.next()) {
			     type=rs.getString(1);
				boolean contains = Arrays.stream(Types).anyMatch(type::equals);

				if(!contains){
		    		 Types[i]=type;	
		    		 i++;}
			}
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox = new JComboBox(Types);
		comboBox.setSelectedIndex(0);
	 	//comboBox = new JComboBox<>(Types) ;
		btnEn = new JButton("enregistrer");
		btnEnvoyer = new JButton("envoyer");
		btnModifier = new JButton("modifier");
		

		button = new Button("browse");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					filech= new JPanel();
					final JFileChooser fc=new JFileChooser();
					fc.setDialogTitle("Specify a folder to save the file");  
					int resp=fc.showSaveDialog(filech);
					if(resp==JFileChooser.APPROVE_OPTION) {
						textFich.setText(fc.getSelectedFile().toString());
						//fileName=fc.getSelectedFile().toString();
					}
					else
						textFich.setText("");

			}
		});
		button.setActionCommand("browse");
		
		GroupLayout gl_cartes = new GroupLayout(cartes);
		gl_cartes.setHorizontalGroup(
			gl_cartes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cartes.createSequentialGroup()
					.addGap(241)
					.addComponent(btnEnvoyer, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(132)
					.addComponent(btnEn, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(124)
					.addComponent(btnModifier, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(269, Short.MAX_VALUE))
				.addGroup(gl_cartes.createSequentialGroup()
					.addGroup(gl_cartes.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCarteDappel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCodeBanque, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomFichier, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_cartes.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBox, 0, 835, Short.MAX_VALUE)
						.addComponent(textNom, GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
						.addComponent(textCodeBq, 835, 835, Short.MAX_VALUE)
						.addGroup(gl_cartes.createSequentialGroup()
							.addGroup(gl_cartes.createParallelGroup(Alignment.TRAILING)
								.addComponent(textNumCarte, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
								.addComponent(textFich, GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
								.addComponent(slider, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
								.addComponent(textNumCarte1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
								.addComponent(slider_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGap(12)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		gl_cartes.setVerticalGroup(
			gl_cartes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_cartes.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_cartes.createParallelGroup(Alignment.BASELINE)
						.addComponent(textNom, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(gl_cartes.createParallelGroup(Alignment.BASELINE)
						.addComponent(textCodeBq, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCodeBanque, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(gl_cartes.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addGroup(gl_cartes.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_cartes.createSequentialGroup()
							.addGroup(gl_cartes.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFich, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNomFichier, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
							.addGap(52))
						.addGroup(gl_cartes.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(61)))
					.addGroup(gl_cartes.createParallelGroup(Alignment.BASELINE)
						.addComponent(textNumCarte, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCarteDappel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addComponent(slider, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(textNumCarte1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(slider_2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_cartes.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEnvoyer)
						.addComponent(btnEn)
						.addComponent(btnModifier))
					.addGap(594))
		);
		//static String[] col= {"nomca","codeAbrege","type","nf","cartea","cartea1"};

		cartes.setLayout(gl_cartes);
		list.add(textNom);
		list.add(textCodeBq);	
		textype.setText((String) comboBox.getSelectedItem());
		list.add(textype);
		list.add(textFich);
		list.add(textNumCarte);
		list.add(textNumCarte1);
			}
	
	static void createEvents()
	{
		
	/*	slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textNumCarte.setText(Integer.toString(slider.getValue()));		}
		});	*/
		if (login.getcpt()==2) {
			
			try
			{
				codec=bnq.getcodecard();
				System.out.println("mod");
				//System.out.println(codec);

				String[] col= {"type","nf","cartea","cartea1"};
				String s=BD.fcSelect(id, "banque", "CodeAbrege='"+code+"' and iduser='"+login.getId()+"';").getString(1);
				ResultSet rs=BD.fcSelect(col,"carte","nomca='"+codec+"' and idb='"+s+"';");			
				textNom.setText(codec);			
				System.out.println(textNom.getText());

				comboBox.setSelectedItem(rs.getString(1));
				textype.setText((String) comboBox.getSelectedItem());
				textFich.setText(rs.getString(2));
				textNumCarte.setText(rs.getString(3));
				textNumCarte1.setText(rs.getString(4));
				System.out.println("??????????");
				System.out.println(list.get(0).getText());
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
	}
	
	btnEn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			validation();
	        System.out.println("ajout");  
	        DefaultTreeModel model = (DefaultTreeModel) bnq.cat_tree.getModel();
            TreePath[] paths = bnq.cat_tree.getSelectionPaths();
            if (paths != null) {
                for (TreePath path : paths) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        	        System.out.println("node");  
        	        System.out.println(node);  
                    if (node.getParent() != null) {
            	        System.out.println(list.get(0).getText());  
                        node.add(new DefaultMutableTreeNode(list.get(0).getText()));
                        model.reload(node);
                    } 
                }}
         /*   DefaultMutableTreeNode node = (DefaultMutableTreeNode)model.getRoot();   
            node.add(new DefaultMutableTreeNode(list.get(0).getText()));
            */

		}
	});		
	btnModifier.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {	
				//String[] col= {"nomca","codeAbrege","type","nf","cartea","cartea1"};
			try
			{
		        System.out.println("getlist");  
		        System.out.println(list.get(0).getText());  
				String s=BD.fcSelect(id, "banque", "CodeAbrege='"+code+"' and iduser='"+login.getId()+"';").getString(1);

				String idc=BD.fcSelect(id, "carte",col[0]+" ='"+list.get(0).getText()+"' and idb='"+s+"';" ).getString(1);
				BD.queryUpdate("carte", col, list, "id='"+idc+"';");
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        System.out.println("modify");  
		}
	});
}
	
public static void validation(){   
	int c=0;
	for(int i=0; i<list.size(); i++) {
	JTextField textbox = list.get(i);
        if ( textbox.getText().trim().isEmpty() ) {
        	c++;
        }
	}
	if(c>0)
    	JOptionPane.showMessageDialog(null, "tous les champs sont obligatoires!");
	else {
	try
	{
		System.out.println("insert");
		String s=BD.fcSelect(id, "banque", "CodeAbrege='"+code+"' and iduser='"+login.getId()+"';").getString(1);
		BD.queryInsert("carte", col,"idb", list,s);
	} catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JOptionPane.showMessageDialog(null, "done!");
	}
}
}
