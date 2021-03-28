package mesCommandes;

import javax.servlet.http.Cookie;

import java.io.PrintWriter;

import javax.servlet.http.*;


public class Identification {
	static String chercheNom (Cookie [] cookies) {
		for(int i = 0 ; i<cookies.length;i++){
			   if(cookies[i].getName().equals("nom")) {
				   return cookies[i].getValue();
			   }
			   else return "cookie not found";
			     
			  }
		return null;
		
	}
}
