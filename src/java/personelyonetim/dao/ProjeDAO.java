/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personelyonetim.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import personelyonetim.model.Proje;
import personelyonetim.model.User;

public class ProjeDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	private static final String INSERT_PROJE_SQL = "INSERT INTO proje" + "  (ad, baslama_tarihi, bitis_tarihi, yonetici_kullanici_id, butce) VALUES "
			+ " (?, ?, ?, ?, ?);";

	private static final String SELECT_PROJE_BY_ID = "select id, ad, baslama_tarihi, bitis_tarihi, yonetici_kullanici_id, butce from proje where id =?";
	private static final String SELECT_ALL_PROJE = "select * from proje";
	private static final String DELETE_PROJE_SQL = "delete from proje where id = ?;";
	private static final String UPDATE_PROJE_SQL = "update proje set ad = ?,baslama_tarihi= ?, bitis_tarihi =?, yonetici_kullanici_id =?, butce =? where id = ?;";

	public ProjeDAO() {
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
            String dbDriver = "com.mysql.jdbc.Driver"; 
            Class.forName(dbDriver);
            return(Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }

	public void insertProje(Proje proje) throws SQLException ,ClassNotFoundException{
		System.out.println(INSERT_PROJE_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJE_SQL)) {
			preparedStatement.setString(1, proje.getAd());
			preparedStatement.setDate(2, proje.getBaslangicTarihi());
			preparedStatement.setDate(3, proje.getBitisTarihi());
			preparedStatement.setInt(4, proje.getYonetici().getId());
			preparedStatement.setLong(5, proje.getButce());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Proje selectProje(int id) {
		Proje proje = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String ad = rs.getString("ad");
				Date baslangic = rs.getDate("baslama_tarihi");
				Date bitis = rs.getDate("bitis_tarihi");
				Integer yoneticiId = rs.getInt("yonetici_kullanici_id");
				Long butce = rs.getLong("butce");
				UserDAO userDAO = new UserDAO();
				User yonetici = userDAO.selectUser(yoneticiId);
				
				proje = new Proje(id, ad, baslangic, bitis, yonetici, butce);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return proje;
	}

	public List<Proje> selectAllProje() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Proje> projeler = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROJE);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String ad = rs.getString("ad");
				Date baslangic = rs.getDate("baslama_tarihi");
				Date bitis = rs.getDate("bitis_tarihi");
				Integer yoneticiId = rs.getInt("yonetici_kullanici_id");
				Long butce = rs.getLong("butce");
				
				UserDAO userDAO = new UserDAO();
				User yonetici = userDAO.selectUser(yoneticiId);
				projeler.add(new Proje(id, ad, baslangic, bitis, yonetici, butce));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return projeler;
	}

	public boolean deleteProje(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PROJE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}catch (Exception e) {
            rowDeleted = false;}
		return rowDeleted;
	}

	public boolean updateProje(Proje proje) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PROJE_SQL);) {
			statement.setString(1, proje.getAd());
			statement.setDate(2, proje.getBaslangicTarihi());
			statement.setDate(3, proje.getBitisTarihi());
			statement.setInt(4, proje.getYonetici().getId());
			statement.setLong(5, proje.getButce());
			statement.setInt(6, proje.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}catch (Exception e) {
            rowUpdated = false;}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}

