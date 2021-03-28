package mesCommandes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaBeans.Stock;


@WebServlet("/commande")
public class CommanderUnDisque extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ArrayList<String> disque = new ArrayList<String>();
   
    public CommanderUnDisque() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = null;
		int nbreProduit = 0;
		Cookie[] cookies = request.getCookies();
		nom = Identification.chercheNom(cookies);
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 out.println("<html>");
		 out.println("<body>");
		 out.println("<head>");
		 out.println("<title> votre commande </title>");
		 out.println("</head>");
		 out.println("<body bgcolor=\"white\">");
		 out.println("<h3>" + "Bonjour "+ nom + " voici votre commande" + "</h3>");
		 
		if(request.getParameter("ordre").equals("ajouter")){
			disque.add(request.getParameter("code"));
		} 
		 for (String s : disque)
	      { 		      
	           out.println(s);
	           out.println("<br>");
	      }
			
		 out.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> ");
		 out.println("<A HREF=enregistre> Vous pouvez enregistrer votre commande </A><br> ");
		 out.println("</body>");
		 out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
