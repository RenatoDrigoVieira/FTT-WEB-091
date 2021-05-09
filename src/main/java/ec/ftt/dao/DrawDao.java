package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import ec.ftt.model.Draw;
import ec.ftt.util.DBUtil;

public class DrawDao implements Dao<Draw> {
	
	private Connection connection;
	
	//Constructor
	public DrawDao() {
		this.connection = DBUtil.getConnection();
	}

	@Override
	public void insert(Draw draw) {

		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO ftt.draws (ARTISTID, TITLE, IMG, DESCRIP) VALUES (?, ?, ?, ?)");
            
            // Parameters start with 1
            preparedStatement.setInt(1, draw.getArtistId());
            preparedStatement.setString(2, draw.getTitle());
            preparedStatement.setString(3, draw.getImg());
            preparedStatement.setString(4, draw.getDesc());
            
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
	}

	@Override
	public void update(Draw draw) {
		
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE ftt.draws SET ARTISTID=?, " 
                    		                           + "TITLE=?, "
                    		                           + "IMG=?, "
                    		                           + "DESCRIP=?, "
                                                + "WHERE ID=?");

            preparedStatement.setInt(1, draw.getArtistId());
            preparedStatement.setString(2, draw.getTitle());
            preparedStatement.setString(3, draw.getImg());
            preparedStatement.setString(4, draw.getDesc());
            preparedStatement.setInt(5, draw.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	} //update

	@Override
	public void delete(Draw draw) {

		try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM ftt.draws WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setInt(1, draw.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        		e.printStackTrace();
        } //try
		
	} //delete 

	@Override
	public Draw find(Draw draw) {
		
		Draw d = new Draw();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM ftt.draws WHERE ID=?");
            
            preparedStatement.setLong(1, d.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                d.setId(rs.getInt("ID"));
                d.setTitle(rs.getString("TITLE"));
                d.setImg(rs.getString("IMG"));
                d.setDesc(rs.getString("DESCRIP"));
                d.setArtistId(rs.getString("ARTISTID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return d;
		
	} 

	@Override
	public ArrayList<Draw> findAll() {
		
		ArrayList<Draw> dList = new ArrayList<Draw>();
        
        try {
        	
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM ftt.draws");
            
            while (rs.next()) {
                
            	Draw d = new Draw();
                
            	 d.setId(rs.getInt("ID"));
                 d.setTitle(rs.getString("TITLE"));
                 d.setImg(rs.getString("IMG"));
                 d.setDesc(rs.getString("DESCRIP"));
                 d.setArtistId(rs.getString("ARTISTID"));

                 dList.add(d);
                
            } 
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return dList;
		
	} 
	
	public int count() {
		
		int count = -1;
		
		try {
			
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT COUNT(1) QTD FROM ftt.draws");
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                count = rs.getInt("QTD");
                
            } //if
            
        } catch (SQLException e) {
            e.printStackTrace();
        } //try

		return count;
		
	} //count
	
	public int maxId() {
		
		int maxId = -1;
		
		try {
			
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT MAX(ID) MAX_ID FROM draws");
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                maxId = rs.getInt("MAX_ID");
                
            } //if
            
        } catch (SQLException e) {
            e.printStackTrace();
        } //try

		return maxId;
		
	} //maxId

} //ClientDao
