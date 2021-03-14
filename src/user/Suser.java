package user;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


import login.Scripts;
import login.login;

public class Suser extends JFrame
{
	public Suser() {
	}

	static JFrame frame;
	static JPanel tabs,buttomPanel;
	static JPanel bq;
	
	static CardLayout cl;
	static JSplitPane splitPane;
	
	public static JPanel panel, panel2,panel3;
	public static CardLayout cl2;	
	public static JSplitPane splitPane2,splitPane3;
	static int i;
	public static void init()
	{
		frame=new JFrame();
		tabs=new JPanel();
		buttomPanel=new JPanel();
		cl=new CardLayout();
		

		Dimension s= Toolkit.getDefaultToolkit().getScreenSize();
		
		JButton BanqueTab=Scripts.NewButton("Banques",15, 120, 0);	tabs.add(BanqueTab);

		JButton Disconnect=Scripts.NewButton("Déconnecter",new Color(75,75,75),15,160,40,s.width-160*2, 0);	tabs.add(Disconnect); 
		JButton Exit=Scripts.NewButton("Quitter",new Color(75,75,75),15,160,40,s.width-160, 0);	tabs.add(Exit); 
		tabs.setLayout(null);
	
		buttomPanel.setLayout(cl);
		bq=new JPanel(new CardLayout());
		buttomPanel.add(bq,"1");

		cl.show(buttomPanel, "1");
		panel=new JPanel();
		panel2=new JPanel();

		panel.add(bnqaff.Init(),"0");
		panel2.add(bnq.Init(login.getId()),"1");
		//panel3.add(Carte.Init(),"2");

		splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel2,panel);
		splitPane2.setDividerLocation(350);
		splitPane2.setEnabled(false);
		bq.add(splitPane2);	
		
	
		BanqueTab.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				
				cl.show(buttomPanel, "1");
			}
		});
		
		Disconnect.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				login.main(null);
				frame.dispose();
			}
		});
		Exit.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
		});
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,tabs,buttomPanel);
		splitPane.setDividerLocation(40);
		splitPane.setEnabled(false);
		
		frame.getContentPane().add(splitPane);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
	}
}
