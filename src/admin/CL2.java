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

public class CL2 extends JPopupMenu {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel panel2 = new JPanel();


	public CL2(JTree cat_tree) {
	      JMenuItem itemAdd = new JMenuItem("Ajouter une banque");
	      itemAdd.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent ae) {
	            System.out.println("Add ");
	            login.setcpt(1);
	            if(change.panel.isDisplayable()) {
					change.splitPane2.remove(change.panel);
					change.panel.removeAll();
	            }
	            if(bnq.panel3.isDisplayable()) {
					change.splitPane2.remove(bnq.panel3);
					bnq.panel3.removeAll();
	            }
	            if(Cl.panel3.isDisplayable()) {
					change.splitPane2.remove(Cl.panel3);
					Cl.panel3.removeAll();
	            }
	            if(bnq.panel.isDisplayable()) {
					change.splitPane2.remove(bnq.panel);
					bnq.panel.removeAll();
	            }
	            if(bnq.panel2.isDisplayable()) {
					change.splitPane2.remove(bnq.panel2);
					bnq.panel2.removeAll();
	            }
	            panel2.add(bnqaff.Init(),"0");
	            if(!panel2.isDisplayable()) {
				change.splitPane2.add(panel2);
				change.splitPane2.setDividerLocation(350);
	            }
	            else {
	            	bnqaff.createEvents();
				change.splitPane2.setDividerLocation(350);}	                    
	         }
	      }); 
	      add(itemAdd);
	   }

}
