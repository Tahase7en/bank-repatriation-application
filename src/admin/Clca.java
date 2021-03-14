package admin;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		                        if (node.getParent() != null) {
		                            model.removeNodeFromParent(node);
		                        }
		                        System.out.println("node");
		        	            System.out.println(node);
		                         String s="delete from carte where nomca='"+ node +"';";
		                         BD.execute(s);
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
