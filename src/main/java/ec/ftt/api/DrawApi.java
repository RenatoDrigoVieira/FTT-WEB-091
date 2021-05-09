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
import ec.ftt.model.Draw;

import ec.ftt.dao.DrawDao;


@WebServlet("/draw")
public class DrawApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrawApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 setAccessControlHeaders(response);
		// TODO Auto-generated method stub
		
			DrawDao drawDao = new DrawDao();
			ArrayList<Draw> dList = drawDao.findAll();
			Gson gson = new Gson();
			
			response.getWriter().append(gson.toJson(dList));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 setAccessControlHeaders(response);
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		Draw resp = gson.fromJson(json, Draw.class);
		Draw d = new Draw();
		
		d.setArtistId(resp.getArtistId());
		d.setTitle(resp.getTitle());
		d.setImg(resp.getImg());
		d.setDesc(resp.getDesc());
		
		
		DrawDao drawDao = new DrawDao();
		
		drawDao.insert(d);
		response.getWriter().append(d.toString());
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 setAccessControlHeaders(response);
		 Gson gson = new Gson();
			String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			Draw resp = gson.fromJson(json, Draw.class);
		Draw d = new Draw();
		
		d.setId(resp.getId());
		d.setArtistId(resp.getArtistId());
		d.setTitle(resp.getTitle());
		d.setImg(resp.getImg());
		d.setDesc(resp.getDesc());
		
		DrawDao drawDao = new DrawDao();
		
		drawDao.update(d);
		
		response.getWriter().append(d.toString());
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 setAccessControlHeaders(response);
		Draw d = new Draw();
		
		d.setId(request.getParameter("id"));
		
		DrawDao drawDao = new DrawDao();
		
		drawDao.delete(d);
		
		response.getWriter().append(request.getParameter("id") + " Draw removed");
		
	}
	
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	          throws ServletException, IOException {
	      setAccessControlHeaders(resp);
	      resp.setStatus(HttpServletResponse.SC_OK);
	  }
	
	 private void setAccessControlHeaders(HttpServletResponse resp) {
	      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	      resp.setHeader("Access-Control-Allow-Methods", "GET");
	      resp.setHeader("Access-Control-Allow-Methods", "POST");
	      resp.setHeader("Access-Control-Allow-Methods", "PUT");
	      resp.setHeader("Access-Control-Allow-Methods", "DELETE");
	      resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
	  }

}
