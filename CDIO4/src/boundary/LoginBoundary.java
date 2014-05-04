package boundary;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class LoginBoundary implements IHttpBoundary {
	String loginError;
	String uid;
	String pword;
	String redirect;
	
	public void createOutput(HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>\n<head>\n<title>Login</title>\n</head>\n<body>\n");
		
		out.println("\t<div class=\"dialog\">");
		out.println("\t\t<h1>Intast bruger ID og kodeord </h1>");
		if (!loginError.equals(""))
			out.println("\t\t<div class=\"error\">" + loginError + "</div>");
		
		out.print(
			"\t\t<form method=\"post\">\n"+
				"\t\t\t<label for=\"userid\">Bruger ID</label>\n"+
				"\t\t\t<input type=\"text\" name=\"UserID\" id=\"userid\""+
					"value=\"" + uid + "\">\n"+
				"\t\t\t<label for=\"password\">Kodeord</label>\n"+
				"\t\t\t<input type=\"password\" name=\"Password\" id=\"password\""+
					"value=\"" + pword + "\">\n"+
				"\t\t\t<input type=\"hidden\" value=\"" + redirect + "\">\n"+
				"\t\t\t<input type=\"submit\" value=\"Login\">\n"+
			"\t\t</form>\n"+
		"\t</div>\n"+
		"</body>\n" + 
		"</html>\n");
		
	}
	
	public void setLoginError(String text){
		loginError = text;
	}
	
	public void setUserId(String text){
		uid = text;
	}
	
	public void setPassword(String text){
		pword = text;
	}
	
	public void setRedirect(String text){
		redirect = text;
	}
}
