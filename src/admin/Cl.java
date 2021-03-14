package admin;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import login.BD;
import login.login;
import user.Suser;

public class Cl extends JPopupMenu {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel panel3 = new JPanel();


	public Cl(JTree cat_tree) {
	      JMenuItem itemDelete = new JMenuItem("Supprimer");
	      JMenuItem itemAddcard = new JMenuItem("Ajouter une carte");

	      itemDelete.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent ae) {
	        	 DefaultTreeModel model = (DefaultTreeModel) cat_tree.getModel();
	                TreePath[] paths = cat_tree.getSelectionPaths();
	                if (paths != null) {
	                    for (TreePath path : paths) {
	                        DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
	                        if (node.getParent() != null) {
	                            model.removeNodeFromParent(node);
	                        }
	                        String[] id= {"id"};
	        				String idb;
							try
							{
								idb = BD.fcSelect(id, "banque", "CodeAbrege='"+node+"' and iduser='"+login.getId()+"';").getString(1);
								String s="delete from banque where codeAbrege='"+ node +"' and iduser='"+login.getId()+"';";
		                        BD.execute(s);                   
		                        String s1="delete from carte where codeAbrege='"+ node +"' and idb='"+idb+"';";
		                         BD.execute(s1);
							} catch (SQLException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	         }
	                }
	               // String s="delete nomca from carte where nomca='"+ model +"'";
	         }
	                
	      });
	      itemAddcard.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent ae) {
		        	 System.out.println("Add card");
			            login.setcpt(1);		            
			            if(change.panel.isDisplayable()) {
							change.splitPane2.remove(change.panel);
							change.panel.removeAll();
			            }
			            if(bnq.panel.isDisplayable()) {
							change.splitPane2.remove(bnq.panel);
							bnq.panel.removeAll();
			            }
			            if(bnq.panel2.isDisplayable()) {
							change.splitPane2.remove(bnq.panel2);
							bnq.panel2.removeAll();
			            }
						panel3.add(Carte.Init(),"0");
						change.splitPane2.add(panel3);
						change.splitPane2.setDividerLocation(350);
						bnq.alreadyExecuted = false;					
		         }
		      });
	  
	      add(itemDelete);
	      add(new JSeparator());
	      add(itemAddcard);
	   }
}
