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

import personelyonetim.model.Izin;
import personelyonetim.model.User;

public class IzinDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	private static final String INSERT_PERSONEL_SQL = "INSERT INTO personel_izin" + "  (baslama_tarihi, bitis_tarihi, personel_id, izin_sebebi) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_IZIN_BY_ID = "select id, baslama_tarihi, bitis_tarihi, personel_id, izin_sebebi from personel_izin where id =?";
	private static final String SELECT_IZIN_BY_ACCOUNT = "select id, baslama_tarihi, bitis_tarihi, personel_id, izin_sebebi from personel_izin where personel_id =?";
	private static final String SELECT_ALL_IZIN = "select * from personel_izin";
	private static final String DELETE_IZIN_SQL = "delete from personel_izin where id = ?;";
	private static final String UPDATE_IZIN_SQL = "update personel_izin set baslama_tarihi = ?,bitis_tarihi= ?, personel_id =?, izin_sebebi =? where id = ?;";

	public IzinDAO() {
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
            String dbDriver = "com.mysql.jdbc.Driver"; 
            Class.forName(dbDriver);
            return(Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }

	public void insertIzin(Izin izin) throws SQLException,ClassNotFoundException {
		System.out.println(INSERT_PERSONEL_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSONEL_SQL)) {
			preparedStatement.setDate(1, izin.getBaslangicTarihi());
			preparedStatement.setDate(2, izin.getBitisTarihi());
			preparedStatement.setInt(3, izin.getUser().getId());
			preparedStatement.setString(4, izin.getSebep());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Izin selectIzin(int id) {
		Izin izin = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IZIN_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Date baslangic = rs.getDate("baslama_tarihi");
				Date bitis = rs.getDate("bitis_tarihi");
				Integer personelId = rs.getInt("personel_id");
				String sebep = rs.getString("izin_sebebi");
				User personel = new UserDAO().selectUser(personelId);
				izin = new Izin(id, sebep, baslangic, bitis,personel);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return izin;
	}
		
	public List<Izin> selectAllIzinByAccount(Integer accountId) {

		List<Izin> personelIzinleri = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IZIN_BY_ACCOUNT);) {
			preparedStatement.setInt(1, accountId);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				Date baslangic = rs.getDate("baslama_tarihi");
				Date bitis = rs.getDate("bitis_tarihi");
				Integer personelId = rs.getInt("personel_id");
				String sebep = rs.getString("izin_sebebi");
				
				User personel = new UserDAO().selectUser(personelId);
				personelIzinleri.add(new Izin(id, sebep, baslangic, bitis,personel));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return personelIzinleri;
	}
	
	public List<Izin> selectAllIzin() {

		List<Izin> personelIzinleri = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_IZIN);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				Date baslangic = rs.getDate("baslama_tarihi");
				Date bitis = rs.getDate("bitis_tarihi");
				Integer personelId = rs.getInt("personel_id");
				String sebep = rs.getString("izin_sebebi");
				
				User personel = new UserDAO().selectUser(personelId);
				personelIzinleri.add(new Izin(id, sebep, baslangic, bitis,personel));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return personelIzinleri;
	}

	public boolean deleteIzin(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_IZIN_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}catch (Exception e) {
            rowDeleted = false;}
		return rowDeleted;
	}

	public boolean updateIzin(Izin izin) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_IZIN_SQL);) {
			statement.setDate(1, izin.getBaslangicTarihi());
			statement.setDate(2, izin.getBitisTarihi());
			statement.setInt(3, izin.getUser().getId());
			statement.setString(4, izin.getSebep());
			statement.setInt(5, izin.getId());

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

