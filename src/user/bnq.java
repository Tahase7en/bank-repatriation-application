package user;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


import login.BD;
import login.login;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class bnq
{
	private static final long serialVersionUID = 6037323487666939712L;
	
    static JTextField textFieldBq, textFieldBq1;

	static String code,codec;		
	static JPanel panel2= new JPanel();
	static JPanel panel= new JPanel() ;
	static JPanel panel3 = new JPanel();


	static boolean alreadyExecuted;
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static float scalex=(float)screenSize.getWidth()/1920;
	public static float scaley=(float)screenSize.getHeight()/1080;
    static javax.swing.JTree cat_tree;
    public static javax.swing.JPanel jPanel1;
 
	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel Init(String id){
        jPanel1 = new javax.swing.JPanel();
 
        cat_tree = new javax.swing.JTree();
        textFieldBq = new JTextField();
        textFieldBq1 = new JTextField();
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(20)
        			.addComponent(cat_tree, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
        			.addGap(120))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addComponent(cat_tree, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        			.addGap(0))
        );
        jPanel1.setLayout(jPanel1Layout);
        textFieldBq.setBounds(221, 97, 56, 20);
		jPanel1.add(textFieldBq);
		textFieldBq.setColumns(10);
		
		textFieldBq1.setBounds(231, 168, 56, 20);
		jPanel1.add(textFieldBq1);
		textFieldBq1.setColumns(10);
		
        pop_tree(id); 
		Cl treePopup = new Cl(cat_tree);
		CL2 treePopup2 = new CL2(cat_tree);
		Clca treePopu = new Clca(cat_tree);
		cat_tree.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			     
			     if (SwingUtilities.isRightMouseButton(e)) {
			    	 DefaultMutableTreeNode s =(DefaultMutableTreeNode)cat_tree.getSelectionPath().getLastPathComponent();
			    	 if ( s.toString()=="Liste banque") {
				            
				            treePopup2.show(e.getComponent(), e.getX(), e.getY());
				          }		  
			    	 if ( !s.isLeaf() & s.toString()!="Liste banque") {
			            
			            treePopup.show(e.getComponent(), e.getX(), e.getY());
			          }		          
			    	 if(s.isLeaf() & s.getLevel()==1 ){
				        	treePopup.show(e.getComponent(), e.getX(), e.getY());
				          }
			    	 if(s.isLeaf() & s.getLevel()==2){
				        	treePopu.show(e.getComponent(), e.getX(), e.getY());
				          }
			    }
			 
			 };
			});
        
				
				cat_tree.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
					DefaultMutableTreeNode s =(DefaultMutableTreeNode)cat_tree.getSelectionPath().getLastPathComponent();					
					if(s.toString()!="Liste banque") {
						if (!s.isLeaf()) {		
							textFieldBq.setText(s.getUserObject().toString());
							textFieldBq1.setText("");	
							String c=textFieldBq.getText();
							setcode(c);
							login.setcpt(2);
							System.out.println("*********");
							 alreadyExecuted = false;

							if(panel3.isDisplayable()) 							
							{
								Suser.splitPane2.remove(panel3);
								
								panel3.removeAll();
						}
							if(panel2.isDisplayable()) 		{					
								Suser.splitPane2.remove(panel2);
								panel2.removeAll();
							}
							if(CL2.panel2.isDisplayable()) {
								Suser.splitPane2.remove(CL2.panel2);	
								CL2.panel2.removeAll();
							}
							if(Cl.panel3.isDisplayable()) {
								Suser.splitPane2.remove(Cl.panel3);								
								Cl.panel3.removeAll();
							}
							if(Suser.panel.isDisplayable()) {
								Suser.splitPane2.remove(Suser.panel);
								Suser.panel.removeAll();
							}

							if(!panel.isDisplayable()) {
							panel.add(bnqaff.Init(),"0");
							Suser.splitPane2.add(panel);
							Suser.splitPane2.setDividerLocation(350);}
							else {
								bnqaff.createEvents();
							}
							System.out.println("CAAA bq");
							}
						if (s.isLeaf() & s.getLevel()==1 ) {
							
							textFieldBq.setText(s.getUserObject().toString());
							textFieldBq1.setText("");
							String c=textFieldBq.getText();
							setcode(c);
							login.setcpt(2);
							System.out.println("//////");
							if(panel3.isDisplayable()) 	{						
								Suser.splitPane2.remove(panel3);
								panel3.removeAll();
						}
							System.out.println(panel.isDisplayable());
							if(panel.isDisplayable()) { 							
								Suser.splitPane2.remove(panel);
								panel.removeAll();
							}
							
							if(CL2.panel2.isDisplayable()) {
								Suser.splitPane2.remove(CL2.panel2);	
								CL2.panel2.removeAll();
							}
							if(Cl.panel3.isDisplayable()) {
								Suser.splitPane2.remove(Cl.panel3);								
								Cl.panel3.removeAll();
							}
							if(Suser.panel.isDisplayable()) {
								Suser.splitPane2.remove(Suser.panel);
								Suser.panel.removeAll();
							}	
							if(!panel2.isDisplayable()) {
								panel2.add(bnqaff.Init(),"2");
								Suser.splitPane2.add(panel2);
								Suser.splitPane2.setDividerLocation(350);}
							else {
								bnqaff.createEvents();
							}
							System.out.println("arb2");
						    //alreadyExecuted = true;		
							//bnqaff.createEvents();
						    alreadyExecuted = true;
							}
						if (s.isLeaf() & s.getLevel()==2 & !alreadyExecuted) {
							
								textFieldBq1.setText(s.getUserObject().toString());
								textFieldBq.setText("");
								System.out.println("שששששש");
								String c1=textFieldBq1.getText();
								setcodecard(c1);
								System.out.println(login.getcpt());
								
								if(CL2.panel2.isDisplayable()) {
									Suser.splitPane2.remove(CL2.panel2);	
									CL2.panel2.removeAll();
								}
								if(Cl.panel3.isDisplayable()) {
									Suser.splitPane2.remove(Cl.panel3);								
									Cl.panel3.removeAll();
								}
								if(Suser.panel.isDisplayable()) {
									Suser.splitPane2.remove(Suser.panel);
									Suser.panel.removeAll();
								}
								if(panel.isDisplayable()) {
									Suser.splitPane2.remove(panel);	
									panel.removeAll();
								}
								//System.out.println(panel3.isDisplayable());
								//Suser.splitPane2.remove(panel3);	
								 if(!panel3.isDisplayable()) {
									 panel3.add(Carte.Init(),"0");
										Suser.splitPane2.add(panel3);								
										Suser.splitPane2.setDividerLocation(350);}
								 else {
									 Carte.createEvents();
								 }

								//Suser.seti(1);// lorsqu'on doit ouvrir la fenetre des banques il faut faire l'appel a init et non pas seulement createEvents
								System.out.println("carte arbre");		
							    alreadyExecuted = true;		
						}
						if (s.isLeaf() & s.getLevel()==2 & alreadyExecuted) {
							textFieldBq1.setText(s.getUserObject().toString());
							textFieldBq.setText("");
							System.out.println("שש");
							login.setcpt(2);
							String c1=textFieldBq1.getText();
							setcodecard(c1);
							Carte.createEvents();
						}
					
						
		        	}
					}
				});
				textFieldBq.setVisible(false);
				textFieldBq1.setVisible(false);
				
					return jPanel1;
        }
        
    //System generated code
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    
  
    public final static void pop_tree(String id) {
        try {
            ArrayList list = new ArrayList();
            list.add("Liste banque");
            String [] col= {"id","codeAbrege"};
            ResultSet rs = BD.fcSelect(col, "banque", "iduser='"+id+"'");   
            while (rs.next()) {
                Object value[] = {rs.getString(1), rs.getString(2)};
                list.add(value);
            }
            Object hierarchy[] = list.toArray();
            DefaultMutableTreeNode root = processHierarchy(hierarchy, id);
 
            DefaultTreeModel treeModel = new DefaultTreeModel(root);
            
            cat_tree.setModel(treeModel);
        } catch (Exception e) {
        }
 
    }
 
    @SuppressWarnings("CallToThreadDumpStack")
    public static DefaultMutableTreeNode processHierarchy(Object[] hierarchy , String id) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(hierarchy[0]);
        try {
            int ctrow = 0;
            int i = 0;
            try {
            	String [] col= {"id","codeAbrege"};
                ResultSet rs = BD.fcSelect(col, "banque", "iduser='"+id+"'"); 
                while (rs.next()) {
                    ctrow = rs.getRow();
                }
                String L1Nam[] = new String[ctrow];
                String L1Id[] = new String[ctrow];
                ResultSet rs1 = BD.fcSelect(col, "banque", "iduser='"+id+"'");     
                while (rs1.next()) {
                    L1Nam[i] = rs1.getString("codeAbrege");
                    L1Id[i] = rs1.getString("id");
                    i++;
                }
                DefaultMutableTreeNode child, grandchild;
                for (int childIndex = 0; childIndex < L1Nam.length; childIndex++) {
                    child = new DefaultMutableTreeNode(L1Nam[childIndex]);
                    node.add(child);//add each created child to root
                	String [] col1= {"nomca"};
                    ResultSet rs3 =BD.fcSelect(col1, "carte", "idb='"+L1Id[childIndex]+"'");     
                    while (rs3.next()) {
                        grandchild = new DefaultMutableTreeNode(rs3.getString("nomca"));
                        child.add(grandchild);//add each grandchild to each child
                    }
                }
 
            } catch (Exception ex) {
                ex.printStackTrace();
            }
 
        } catch (Exception e) {
        }
 
        return (node);
    }
 

    
    public static String getcode() {
		  return code;
	  }
	  public static void setcode(String c) {
		  code=c;
	  }
	  
	  public static String getcodecard() {
		  return codec;
	  }
	  public static void setcodecard(String c) {
		  codec=c;
	  }
}
