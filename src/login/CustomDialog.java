package login;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.beans.*; //property change stuff
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class CustomDialog extends JDialog implements ActionListener, PropertyChangeListener
{
	private String typedText = null;
	private String typed;

	private JTextField textField;
	private JPasswordField passwordField;
	private JOptionPane optionPane;

	private String btnString1 = "Valider";
	private String btnString2 = "Annuler";
	
	private boolean ispassword;

	public CustomDialog(Frame aFrame, String ValueToChange)
	{
		this(aFrame,ValueToChange,false);
	}
	
	public CustomDialog(Frame aFrame, String ValueToChange,boolean ispassword)
	{
		super(aFrame, true);
		this.ispassword=ispassword;

		setTitle("Modifier");

		String msgString1 = "Veuillez insérer le nouveau "+ValueToChange;
		textField = new JTextField(20);
		passwordField=new JPasswordField(20);
		Object[] array= {msgString1, textField};
		if(ispassword) 
			{
			array[1]=passwordField;
			}
		Object[] options ={ btnString1, btnString2 };
		
		optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options,options[0]);

		setContentPane(optionPane);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		textField.addActionListener(this);
		optionPane.addPropertyChangeListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		optionPane.setValue(btnString1);
	}

	public void clearAndHide()
	{
		textField.setText(null);
		setVisible(false);
	}

	public void propertyChange(PropertyChangeEvent e)
	{
		String prop = e.getPropertyName();

		if (isVisible() && (e.getSource() == optionPane) && (JOptionPane.VALUE_PROPERTY.equals(prop) || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop)))
		{
			Object value = optionPane.getValue();

			
			
			if (btnString1.equals(value) && (textField.getText()!=null || passwordField.getPassword()!=null ) )
			{
				if(ispassword)
				{
					typed=String.valueOf(passwordField.getPassword());
				}
				else 
				{
					typed=textField.getText();
				}
				
				typedText = typed;
				clearAndHide();
			}
			else
			{
				typedText = null;
				clearAndHide();
			}
		}
	}
	
	public String getValidatedText()
	{
		return typedText;
	}
}
