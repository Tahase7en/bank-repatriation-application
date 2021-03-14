package login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;



public class Utilisateur
{
	static Connection conn=BD.dbConnector();
	
	public final static String secretKey1 = "b0dfhgjhkjg-\"-gjhkkD_JgvjhjkklF[~Rg3cK2CxpvgH36kw07u4LvVNZqTONHQ3LvOKWRzZuQKtYXgPx4AK1LblXvmqI7v7pBID11k2eWDSLG7RlP/gSY0LQlPFEpakBuNSUsQKqweNUP6E8aNhfuM7Yvm9X3R9HqGoOHBEUeq2V3Cj0M6A6CTjz04NFS14ipeP2p29OEjObVkm0ApTGqIaKRTdwpLlhy01fFFs3zg1vXC6iFPcUqqkRTWE+MwGPKFBGjDTnSE8Heeh8bGhLiDWlF90KifZk+4Ju6S4sFf8c4OVe0sZMx+Me9Pqc0PpslkrgKXhXUSi8YkYjpRbadPf7ZTFQDOOgWQgsRu8IZBw4slWp879KgC45mRLJyNqEyQsCg4BszNfdIiGNF1C7EYR/lTvzPbkxQmvnRoKBXSPLdclTCgm5cHZJf4wkI9hhxa7JFG0worH7tkVJ7E/A/Ab6WGNOJ4L0SJf0OJAwo4Jhu34Ju2gp0PDjcIvX0u15ywDWdxy2nit+yQTAJyzJvVVww5pvX1vaucXHT+Ieho0BvRF78d5GPOu7/ZZK3sM2W16wVVvob0iXI4502ocW3LC3qGyBa9N/BgufmhMYGh1qxAVh4j6UEXWPHNGGje/A4fiN7nt5s7eB9bsqM=";
	public final static String secretKey2 = "V+4ml9LolTmxny/eFsGGgeMGGQqhVPZwav3PN6AnKQjCIYWJwAP1aa+pAy3mK1zKjG91xD9xWFXmL35WXAumps/Yurl3zc9jSsnisoLCASa2Yj01vkALfLrsyaI0wNpbMWGiJr1JHQb96OjocRfc0EdiB9QCsg6Jfx5dSMzl/6bgaKIOlL+vQwh1MTOp7n8+PI+jWCcOcwu7KOnyPDpUf8CSG84QlMWNUXTa2AePl0lEx8J2TZ7+P8OwZEbsRmF0usSdF9ZLWCZVcKnDKYkcymE5qz2e+D0KK0D0+Now8Ck49uoR8Dq/22fYhAp6NDJlAkxCrvzeehyqxjItnvqqpCkeMv8S05cQSmlaOYTFynRCCaRP27NbslerqcLH+1XiWyuKSXji0jSU4NupSNUCGwF1ww0JgUJ1zWsBML23aHSP1xqzrWMVvDPHgBFm5ZqIkCeG8JS6A1r3S5uLANlRcRkB/uhSht9oeYA4KaI4fjhvMDnrqQslRzSv2+vPpOuDUPiQ2yKyA23PgagO5yZjVAXYgv9wxlINAi9rqdynI01Bd9tGMUbjvmGcR7/fZXJOwEkaE357MdXK0q+ChZRdpk9h8uGhTboHurP2pSMEavu7fts3OfVguUBA5ENI9J3M/KkqRIDsVXg99eOON7JrxGJJ/I0qMTpXJWCh534l5ck=\r\n";
	
	static String encryptedString,encryptedString1;
    static String[] keys=new String[5];


    
	public static boolean equal(String username,String password)
	{
		String query="SELECT nom,pwd,key from login";

		try
		{
			System.out.println("in");

			ResultSet rs=BD.executionQuery(query);
			System.out.println(rs.getString(2));
			System.out.println("rtyu");


			while(rs.next())
			{
				
				String pas=Scripts.decrypt(rs.getString(2),rs.getString(3)+secretKey2);
				String pas2=Scripts.decrypt(pas,rs.getString(3)+secretKey1);
				System.out.println(pas2);

				if(rs.getString(1).equals(username) && password.equals(pas2))
				{
					return true;
				}
			} 

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public static void Add(int IdUser,String nom,String password,int GroupId)  throws SQLException
	{
		///random char
		  //System.out.println("salt");

		String s=Scripts.generateSalt(512).get();
		
		 encryptedString=Scripts.encrypt(password,s+secretKey1);
		 encryptedString1=Scripts.encrypt(encryptedString,s+secretKey2);

			System.out.println("add");
			String Type="Utilisateur";
			if(GroupId==0)
			{
				Type="Administrateur";
			}
		String query="INSERT INTO login(id,nom,pwd,key,Type) Values ('"+IdUser+"','"+nom+"','"+ encryptedString1+"','"+s+"','"+Type+"');";
		BD.executionUpdate(query);
	}

	
	public static int getGID(int id)
	{
		try
		{
			String query="SELECT Type from login where id='"+id+"';";
			ResultSet rs=BD.executionQuery(query);
			System.out.println(rs.getString(1));
			if(rs.next())
			{
				if(rs.getString(1).equals("Administrateur"))
					return 0;
				return 1;
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	public static String getUser(int Iduser)
	{
		try
		{
			String query="SELECT nom from login where id='"+Iduser+"';";
			ResultSet rs=BD.executionQuery(query);
			if(rs.next())
			{
				return rs.getString(1);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static void Delete(int IdUser)
	{
		String query="Delete from login where id="+IdUser+";";
		 BD.execute(query);
	}
	public static ResultSet getUsers()
	{
		String query="Select id as 'ID',nom as 'Nom utilisateur',Type as 'Type de Compte' from login;";
		return BD.executionQuery(query);
	}
	public static int GetuserCount()
	{
		try
		{		
			String query="SELECT max(id) FROM login;";
			ResultSet resultSet= BD.executionQuery(query);
			int Count=0;
			if(resultSet.next())
			{
				Count=resultSet.getInt(1);
			}
			return Count;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}

}

