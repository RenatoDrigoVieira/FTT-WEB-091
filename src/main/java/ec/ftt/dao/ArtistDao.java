package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ec.ftt.model.Artist;
import ec.ftt.util.DBUtil;

public class ArtistDao implements Dao<Artist> {
	
	private Connection connection;
	
	//Constructor
	public ArtistDao() {
		this.connection = DBUtil.getConnection();
	}

	@Override
	public void insert(Artist artist) {

		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO ftt.artists (NAME, UF) VALUES (?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, artist.getName());
            preparedStatement.setString(2, artist.getUf());
            
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
	}

	@Override
	public void update(Artist artist) {
		
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE ftt.artists SET NAME=?, " 
                    		                           + "UF=?, "
                                                + "WHERE ID=?");

            preparedStatement.setString(1, artist.getName());
            preparedStatement.setString(2, artist.getUf());
            preparedStatement.setInt(3, artist.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	} //update

	@Override
	public void delete(Artist artist) {

		try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM ftt.artists WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setInt(1, artist.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        		e.printStackTrace();
        } //try
		
	} //delete 

	@Override
	public Artist find(Artist artist) {
		
		Artist a = new Artist();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM ftt.artists WHERE ID=?");
            
            preparedStatement.setLong(1, artist.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                a.setId(rs.getInt("ID"));
                a.setName(rs.getString("NAME"));
                a.setUf(rs.getString("UF"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return a;
		
	} 

	@Override
	public ArrayList<Artist> findAll() {
		
		ArrayList<Artist> aList = new ArrayList<Artist>();
        
        try {
        	
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM ftt.artists");
            
            while (rs.next()) {
                
            	Artist a = new Artist();
                
            	a.setId(rs.getInt("ID"));
                a.setName(rs.getString("NAME"));
                a.setUf(rs.getString("UF"));

                aList.add(a);
                
            } //while
            
        } catch (SQLException e) {
            e.printStackTrace();
        } //try

        return aList;
		
	} //findAll
	
	public int count() {
		
		int count = -1;
		
		try {
			
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT COUNT(1) QTD FROM ftt.artists");
            
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
                    prepareStatement("SELECT MAX(ID) MAX_ID FROM artists");
            
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
