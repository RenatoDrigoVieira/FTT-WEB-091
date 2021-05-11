package ec.ftt.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.model.Artist;
import ec.ftt.dao.ArtistDao;



@WebServlet("/artist")
public class ArtistApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		setAccessControlHeaders(response);
		
		ArtistDao artistDao = new ArtistDao();
		ArrayList<Artist> aList = artistDao.findAll();
		Gson gson = new Gson();
		response.getWriter().append(gson.toJson(aList));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		setAccessControlHeaders(response);
		Gson gson = new Gson();
		String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		Artist resp = gson.fromJson(json, Artist.class);
		Artist a = new Artist();
		
		
		a.setName(resp.getName());
		a.setUf(resp.getUf());
		
		ArtistDao artistDao = new ArtistDao();
		
		artistDao.insert(a);
		
		response.getWriter().append(a.toString());
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		Gson gson = new Gson();
		String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		Artist resp = gson.fromJson(json, Artist.class);
		Artist a = new Artist();
		
		a.setId(resp.getId());
		a.setName(resp.getName());
		a.setUf(resp.getUf());
		
		ArtistDao artistDao = new ArtistDao();
		
		artistDao.update(a);
		
		response.getWriter().append(a.toString());
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		Artist a = new Artist();
		
		a.setId(request.getParameter("id"));
		
		ArtistDao artistDao = new ArtistDao();
		
		artistDao.delete(a);
		
		
	}
	
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	          throws ServletException, IOException {
	      setAccessControlHeaders(resp);
	      resp.setStatus(HttpServletResponse.SC_OK);
	  }
	
	 private void setAccessControlHeaders(HttpServletResponse resp) {
		 System.out.println("Teste");
	      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	      resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	      resp.setHeader("Access-Control-Allow-Credentials", "true");
	      resp.setHeader("Access-Control-Allow-Headers", "Content-Type, X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Date, X-Api-Version, X-File-Name");
	  }

}
