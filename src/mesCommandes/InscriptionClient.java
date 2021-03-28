package mesCommandes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("")
public class InscriptionClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String nomCookie=null, mdpCookie=null;
	
	public InscriptionClient() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie nomcookie = new Cookie("nom", request.getParameter("nom"));
		Cookie mdpcookie = new Cookie("mdp", request.getParameter("motdepasse"));
		String nomRecu = request.getParameter("nom") , mdpRecu = request.getParameter("motdepasse");
		
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (nomCookie == null && nomRecu == null) {
			out.println("<html>");
			out.println("<body>");
			out.println("<head>");
			out.println("<title> inscription d'un client </title>");
			out.println("</head>");
			out.println("<body bgcolor='white' >");
			out.println(nomRecu + " | " + mdpRecu + " | " + nomCookie + " | " + mdpCookie);
			out.println("<h3>" + "Bonjour, vous devez vous inscrire " + "</h3>");
			out.println("<h3>" + "Attention mettre nom et le mot de passe avec plus de 3 caracteres" + "</h3>");
			out.print(" <form action='' method='GET' > ");
			 out.println("nom");
			 out.println("<input type='text' size='20' name='nom' >");
			 out.println("<br>");
			 out.println("mot de passe");
			 out.println("<input type='password' size='20' name='motdepasse'> <br>");
			 out.println("<input type='submit' value='inscription'>");
			 out.println("</form>");
			 out.println("</body>");
			 out.println("</html>");
			
			
				 
		}
		else if (nomCookie==null && nomRecu!=null){
			 
			 response.addCookie(nomcookie);
			 response.addCookie(mdpcookie);
			 nomCookie = nomcookie.getValue();
			 mdpCookie = mdpcookie.getValue();
			 out.println("<html>");
				out.println("<body>");
				out.println("<head>");
				out.println("<title> inscription d'un client </title>");
				out.println("</head>");
				out.println("<body bgcolor='white' >");
				out.println(nomRecu + " | " + mdpRecu + " | " + nomCookie + " | " + mdpCookie);
				out.println("<h3>" + "Bonjour, vous êtes inscrit " + "</h3>");
				out.println("<a href=''>Sidentifier</a>");
				 out.println("</body>");
				 out.println("</html>");
			 
			
		}
		else if (identique(nomRecu,nomCookie) && identique(mdpRecu,mdpCookie)){
			System.out.println("doooooooone");
			response.sendRedirect(request.getContextPath() + "/achat");
		}
		else {
			out.println("<html>");
			out.println("<body>");
			out.println("<head>");
			out.println("<title> inscription d'un client </title>");
			out.println("</head>");
			out.println("<body bgcolor='white' >");
			out.println(nomRecu + " | " + mdpRecu + " | " + nomCookie + " | " + mdpCookie);
			out.println("<h3>" + "Bonjour, vous devez s'identifier " + "</h3>");
			out.print(" <form action='' method='GET' > ");
			 out.println("nom");
			 out.println("<input type='text' size='20' name='nom' value="+ nomCookie +">");
			 out.println("<br>");
			 out.println("mot de passe");
			 out.println("<input type='password' size='20' name='motdepasse' value = "+ mdpCookie +"> <br>");
			 out.println("<input type='submit' value='sidentifier'>");
			 out.println("</form>");
			 out.println("</body>");
			 out.println("</html>");			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	boolean identique (String recu, String cookie) {
		 return ((recu != null) && (recu.length() >3) && (cookie != null) && (recu.equals(cookie) ));
		 }
}
