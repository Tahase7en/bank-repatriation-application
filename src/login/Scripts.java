package login;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.swing.*;

import admin.bnq;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;



public class Scripts
{

	static float scalex = bnq.scalex;
	static float scaley = bnq.scaley;
	
	private static SecretKeySpec secretKey;
    private static byte[] key;

	// -------------------NewLabel--------------------------------------------------------------------------------------
	public static JLabel NewLabel(String text, Color color, int size, int width, int height, int x, int y)
	{
		JLabel Label = new JLabel(text);
		Label.setFont(new Font("Century Gothic", Font.ITALIC, (int) (scalex * size)));
		Label.setForeground(color);
		Label.setBounds((int) (scalex * x), (int) (scaley * y), (int) (scalex * width), (int) (scaley * height));
		Label.setHorizontalAlignment(SwingConstants.LEFT);
		return Label;
	}

	public static JLabel NewLabel(String text, int size, int x, int y)
	{
		return NewLabel(text, Color.BLACK, size, x, y);
	}

	public static JLabel NewLabel(String text, Color color, int size, int x, int y)
	{
		return NewLabel(text, color, size, (int) (text.length() * size / 1.3), (int) (size * 1.2), x, y);
	}

	// --------------------NewButton------------------------------------------------------------------------------------
	public static JButton NewButton(String text, Color color, int size, int width, int height, int x, int y)
	{
		JButton Button = new JButton(text);
		Button.setBackground(color);
		Button.setFont(new Font("Century Gothic", Font.BOLD, (int) (scalex * size)));
		Button.setForeground(Color.WHITE);
		Button.setLocation((int) (scalex * x), (int) (scaley * y));
		Button.setSize((int) (scalex * width), (int) (scaley * height));
		Button.setFocusPainted(false);

		return Button;
	}

	public static JButton NewButton(String test, int x, int y)
	{
		return NewButton(test, 20, 120, 40, x, y);
	}

	public static JButton NewButton(String test, int size, int x, int y)
	{
		return NewButton(test, size, 120, 40, x, y);
	}

	public static JButton NewButton(String test, Color color, int x, int y)
	{
		return NewButton(test, color, 20, 120, 40, x, y);
	}

	public static JButton NewButton(String test, Color color, int size, int x, int y)
	{
		return NewButton(test, color, size, 120, 40, x, y);
	}

	public static JButton NewButton(String text, int size, int width, int height, int x, int y)
	{
		return NewButton(text, new Color(51, 100, 255), size, width, height, x, y);
	}

	// ----------------------NewTextPane--------------------------------------------------------------------------------
	public static JTextField NewTextField(int width, int height, int x, int y)
	{
		JTextField TextField = new JTextField();
		TextField.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * (height - 13))));
		TextField.setLocation((int) (scalex * x), (int) (scaley * y));
		TextField.setSize((int) (scalex * width), (int) (scaley * height));
		return TextField;
	}

	public static JPasswordField NewPasswordField(int width, int height, int x, int y)
	{
		JPasswordField PasswordFiel = new JPasswordField();
		PasswordFiel.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * (height - 13))));
		PasswordFiel.setLocation((int) (scalex * x), (int) (scaley * y));
		PasswordFiel.setSize((int) (scalex * width), (int) (scaley * height));
		return PasswordFiel;
	}

	// -------------------NewImage--------------------------------------------------------------------------------------
	public static JLabel NewImage(String path, int w, int h, int x, int y)
	{
		ImageIcon img = new ImageIcon(path);
		JLabel label = new JLabel();

		Image image = img.getImage();
		Image newimg = image.getScaledInstance((int) (scalex *w),(int) (scaley * h), java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg);
		label.setBounds(x, y, img.getIconWidth(),img.getIconHeight());
		label.setIcon(img);
		return label;
	}

	public static JLabel NewImage(String path, int x, int y)
	{
		ImageIcon img = new ImageIcon(path);
		return NewImage(path, img.getIconWidth(), img.getIconHeight(), x, y);
	}

	// --------------------NewComboBox----------------------------------------------------------------------------------
	public static JComboBox<String> NewComboBox(String[] list, int w, int h, int x, int y)
	{
		JComboBox<String> ComboBox = new JComboBox<>(list);
		ComboBox.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * 18)));
		ComboBox.setBounds((int) (scalex * x), (int) (scaley * y), (int) (scalex * w), (int) (scaley * h));
		ComboBox.setBackground(Color.white);
		return ComboBox;
	}

	public static JComboBox<String> NewComboBox(String[] list, int x, int y)
	{
		return NewComboBox(list, 350, 35, x, y);
	}

	// --------------------NewCheckBox----------------------------------------------------------------------------------

	public static JCheckBox NewCheckBox(String text,int x,int y)
	{
		
		JCheckBox CheckBox = new JCheckBox(text);
		CheckBox.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * 18)));
		CheckBox.setBounds((int) (scalex * x),(int) (scalex * y),(int) (scalex * text.length()*12),(int) (scalex * 20));
		return CheckBox;
	}

	
	public static String CharCoding(String line)
	{
		String ret="";
		for(char c:line.toCharArray())
		{
			if((int)c>0 && (int)c<255)
			{
				ret+=c;
			}
		}
		return ret;
	}
	
	//////////cryptage aes
	

private static final SecureRandom RAND = new SecureRandom();

public static Optional<String> generateSalt (final int length) {

  if (length < 1) {
    System.err.println("error in generateSalt: length must be > 0");
    return Optional.empty();
  }

  byte[] salt = new byte[length];
  RAND.nextBytes(salt);
  return Optional.of(Base64.getEncoder().encodeToString(salt));
}

    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 24);
            secretKey = new SecretKeySpec(key, "AES");
        	
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    

 
    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            setKey(secret);
            
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}