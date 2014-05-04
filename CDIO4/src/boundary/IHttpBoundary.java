package boundary;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface IHttpBoundary {
	
	public void createOutput(HttpServletResponse response) throws IOException;

}
