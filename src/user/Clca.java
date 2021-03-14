package user;


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

public class Clca extends JPopupMenu {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel panel2 = new JPanel();

	public Clca(JTree cat_tree) {
	      JMenuItem itemDelete = new JMenuItem("Supprimer");
	      JMenuItem itema = new JMenuItem("Rappatrierement");
	      JMenuItem itemmp = new JMenuItem("Programmation");
	      itemDelete.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent ae) {
		        	 DefaultTreeModel model = (DefaultTreeModel) cat_tree.getModel();
		                TreePath[] paths = cat_tree.getSelectionPaths();
		                if (paths != null) {
		                    for (TreePath path : paths) {
		                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		                        System.out.println("node");
		        	            System.out.println(node);
		                        System.out.println("parent");
		        	            System.out.println(node.getParent());
		        	            String[]id= {"id"};
								try
								{
									System.out.println("parent");
			        	            System.out.println(node.getParent());
									String idb = BD.fcSelect(id, "banque", "CodeAbrege='"+node.getParent()+"' and iduser='"+login.getId()+"';").getString(1);
									String s="delete from carte where nomca='"+ node +"' and idb='"+idb+"';";
			                         BD.execute(s);
								} catch (SQLException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                        if (node.getParent() != null) {
		                            model.removeNodeFromParent(node);
		                        }
             
		         }
		                }
		         }
		                
		      });
	      itema.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent ae) {
		            System.out.println("rapp ");
		         }
		      });
	      itemmp.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent ae) {
		            System.out.println("pgm ");
		         }
		      });
	      
	  
	      add(itemDelete);
	      add(new JSeparator());
	      add(itema);
	      add(new JSeparator());
	      add(itemmp);
	      
	     
	   }
}
