package std_Registrtion_Project;

import java.io.PrintWriter;
import java.sql.*;
 

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login_BL extends HttpServlet {
	
	 PrintWriter out ; 	
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
	 {
		 try {
			 
			 String SFname= request.getParameter("SFname");
			 int PhNo=Integer.parseInt(request.getParameter("PhNo"));
			 
			 out = response.getWriter();
			 
				
			 
				 boolean IsRegisteredStudent=LoginCheckMethod(SFname,PhNo);
				 if(IsRegisteredStudent==true)
				 {
					 response.sendRedirect("http://localhost:8080/std_Registrtion_Project/std_regisration_UI.html");
					// out.println(SFname+"<h1>  Successfully Login</h1>"); 
				 }
				 else
				 {
					  out.println(SFname+"<h1>Invalid Credentialand Try Again  or if u r not registered  Please Register</h1>");
					 
					 
				 }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	 }

	 
	 Connection con;
	 Connection ConnectionStringMethod()
		{
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");  
				  con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/db_student","root","1234"); 
				  
				  return con;
				
			} catch (Exception e)
			{
				
				 System.out.println("Exception at ConnectionString Method WIth Error Msg : "+e);
				 return con;
			}
		
		}
		
	 
	 boolean LoginCheckMethod(String SFname,int PhNo)
	 {
		 boolean IsRegisteredStudent=false;
		 try {
			 Connection con=ConnectionStringMethod();
				 String query="Select * from tb_StudentDetails where S_phno="+PhNo+" and S_firstname='"+SFname+"'";
		        Statement st=con.createStatement();
		        ResultSet rs=st.executeQuery(query);
		        while(rs.next())  
		        	
		        {
		        	  
		         	
		        	IsRegisteredStudent=true;
		        }
		         
		        
		        st.close();
		        con.close();
		        
		        return IsRegisteredStudent;
			
		} catch (Exception e) {
			 return IsRegisteredStudent;
		}
		 
	 }

}
