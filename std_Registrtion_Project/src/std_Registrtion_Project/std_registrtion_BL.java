package std_Registrtion_Project;

import java.io.PrintWriter;
import java.sql.*;
 

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

public class std_registrtion_BL extends HttpServlet {

	
	 PrintWriter out ; 	
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
	 {
		 try {
			 
			 out = response.getWriter();
			 boolean isConnected=ConnectionStringMethod();
			 
			
			 
			 
			 if(isConnected==true)
			 {
				 
				  String message="Hello Java World";
					 
				   String SFname= request.getParameter("SFname");
				   String LFname= request.getParameter("LFname");
				 int PhNo=Integer.parseInt(request.getParameter("PhNo"));
				 String Email= request.getParameter("Email");
					
				int insertCount= InsertDataMethod(10,SFname,LFname,PhNo,Email);
				 
				
				if(insertCount==1)
				{
					
				      out.println("<h1> Data Inserted Successfully </h1>");
				}
				
				else
				{
					 
				      out.println("<h1> Data Not Inserted Successfully</h1>");
				}
				 
				 
			 }
			  
			 
			 else
			 {
				 out.println("<h1>Please Check Connection String</h1>");
			 }
			 
			 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	 }

	
	 Connection con;
		
		boolean ConnectionStringMethod()
		{
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");  
				  con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/db_student","root","1234"); 
				  
				  return true;
				
			} catch (Exception e)
			{
				
				 System.out.println("Exception at ConnectionString Method WIth Error Msg : "+e);
				 return false;
			}
		
		}
		
		
		int InsertDataMethod(int id,String S_firstname,String S_lastname,long S_phno,String Email)
		{
			int insertCount=0;
			try{  
				 
				 ConnectionStringMethod();
				//here test_schema is database name, root is username and password is 1234 
				//Ref URL :https://www.javatpoint.com/example-to-connect-to-the-mysql-database 
				 
	 		
			 	String query="insert into tb_studentdetails values(?,?,?,?,?)";
			//	String query="delete from   tb_studentdetails  where id=(?)";
				//String query="update     tb_studentdetails set S_firstname=(?)  where id=(?)";
				PreparedStatement stmt= con.prepareStatement(query); 
				
	 		stmt.setInt(1,id);
	 		stmt.setString(2,S_firstname);
	 			stmt.setString(3,S_lastname);
	 			stmt.setLong(4,S_phno);
	 			stmt.setString(5,Email);
				  insertCount =stmt.executeUpdate();  
				 
				out.println(insertCount+" rows Affected");  
				 con.close(); 
				 return insertCount;
				}catch(Exception e){ 
					out.println(e);
				return insertCount;
				}  
			 


		
		}
		
}
