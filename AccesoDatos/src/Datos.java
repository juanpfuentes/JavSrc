import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;




public class Datos {

	public static void main(String[] args) {
		  try {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
      
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql-trifulcas.alwaysdata.net:3306/trifulcas_code", "trifulcas_code", "TrifulcasForever");
			System.out.println("Conectado");
			Statement stmt = con.createStatement();
			
            ResultSet rs = stmt.executeQuery("select * from actor");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
            
            int result=stmt.executeUpdate("insert into actor (first_name, last_name) values ('Eva','Pi')");  
            
            
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
