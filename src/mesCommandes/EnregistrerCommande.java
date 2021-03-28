package mesCommandes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/enregistre")
public class EnregistrerCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connexion=null;
	 Statement stmt=null;
	 PreparedStatement pstmt=null;
  
    public EnregistrerCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = null;
		 int nbreProduit = 0;
		 Cookie[] cookies = request.getCookies();
		 boolean connu = false;
		 nom = Identification. chercheNom (cookies);
		 System.out.println(nom);
		 OuvreBase();
		 AjouteNomBase(nom);

		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 out.println("<html>");
		 out.println("<body>");
		 out.println("<head>");
		 out.println("<title> votre commande </title>");
		 out.println("</head>");
		 out.println("<body bgcolor=\"white\">");

		 out.println("<h3>" + "Bonjour " + nom + " voici ta nouvelle commande" + "</h3>");
		 HttpSession session = request.getSession();
		 Enumeration names = session.getAttributeNames();
		 while (names.hasMoreElements()) {
		 nbreProduit++;
		 String name = (String) names.nextElement();
		 String value = session.getAttribute(name).toString();
		 out.println(name + " = " + value + "<br>");
		 }
		 try {
			AjouteCommandeBase(nom,session);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 out.println("<h3>" + "et voici " + nom + " ta commande complete" + "</h3>");
		 try {
			MontreCommandeBase(nom, out);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 out.println("<A HREF='vider'> Vous pouvez commandez un autre disque </A><br> ");
		 out.println("</body>");
		 out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void OuvreBase() {
		 try {
		 Class.forName("com.mysql.jdbc.Driver");
		 connexion = DriverManager.getConnection("jdbc:mysql://mysql-25533-0.cloudclusters.net:25533/magasin","imrane","password");
		 connexion.setAutoCommit(true);
		 stmt = connexion.createStatement();
		 }
		 catch (Exception E) {
		 log(" -------- probeme " + E.getClass().getName() );
		 E.printStackTrace();
		 }
		 }
	
		 protected void fermeBase() {
		 try {
		 stmt.close();
		 connexion.close();
		 }
		 catch (Exception E) {
		 log(" -------- probeme " + E.getClass().getName() );
		 E.printStackTrace();
		 }
		 }
		 
		 protected void AjouteNomBase(String nom) {
			 try {
			 ResultSet rset = null;
			 pstmt= connexion.prepareStatement("select numero from personnel where nom=?");
			 pstmt.setString(1,nom);
			 rset=pstmt.executeQuery();
			 if (!rset.next())
			 stmt.executeUpdate("INSERT INTO personnel(nom) VALUES ('" + nom + "' )" );
			 }
			 catch (Exception E) {
			 log(" - probeme " + E.getClass().getName() );
			 E.printStackTrace();
			 }
			 }

		 protected void AjouteCommandeBase(String nom, HttpSession session ) throws SQLException {	
			
			PreparedStatement pstmtt = connexion.prepareStatement("select numero from personnel where nom='"+nom+"'");
			 ResultSet rsett = pstmtt.executeQuery();
			if(rsett.next()) {
				OuvreBase();		
				 for (String s : CommanderUnDisque.disque)
			      { 		      
					 String sql = "insert into commande(article,qui) values('"+s+"',"+rsett.getInt("numero")+")";
					try {
						stmt = connexion.createStatement();
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			      }
			}
			 
			 
		 
		 }
			 protected void MontreCommandeBase(String nom, PrintWriter out) throws SQLException {
				 PreparedStatement pstmtt = connexion.prepareStatement("select numero from personnel where nom='"+nom+"'");
				 ResultSet rsett = pstmtt.executeQuery();
				 if(rsett.next()) {
					 Statement stmt = connexion.createStatement();  
		             ResultSet rs = stmt.executeQuery("select * from commande where qui = "+rsett.getInt("numero")+"");  
		             out.println("<html><body>");  
		             out.println("<table border=1 width=50% height=50%>");  
		             out.println("<tr><th>Cammande</th><<tr>");  
		             while (rs.next()) 
		             {  
		                 String commande = rs.getString("article");  
		                 
		                   
		                 out.println("<tr><td>" + commande +"</tr>");   
		             }  
		             out.println("</table>");  
		             out.println("</html></body>"); 		
		             }
				 }
				 
}
