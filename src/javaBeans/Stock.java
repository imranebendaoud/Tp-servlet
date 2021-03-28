package javaBeans;

import java.io.PrintWriter;

public class Stock {
	static String[][] leStock = { { "Disque CD - AMOR TICINES", "15", "disque897TR566" },
			{ "Disque CD - Los Mayas", "19", "disque78UUNYT67" }, { "Disque CD - Dick Anglas", "25", "disque87YHG564" },
			{ "Disque CD - Frederic Angonas", "35", "disque98HUYU56" } };

	public static String[][] getLeStock() {
		return leStock;
	}

	public static void setLeStock(String[][] leStock) {
		Stock.leStock = leStock;
	}

	public static void vente(PrintWriter out) {
		out.println("<table border=1>");
		for (int i = 0; i < leStock.length; i++) {
			
			out.println("<tr> <td>" + leStock[i][0] + " " + leStock[i][1] + " Euros </td>");
			out.println(" <td><A HREF=\"commande?element=disque&code=");
			out.println(leStock[i][2] + "&ordre=ajouter&prix=" + leStock[i][1] + "\">");
			out.println("<IMG SRC=\"/fcexemple/images/panier.gif\" BORDER=0></A><br> </td> </tr>");
		}
		out.println("</table> </form>");
	}

	String titre;
	float prix;

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
}
