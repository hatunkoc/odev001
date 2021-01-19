/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personelyonetim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import personelyonetim.model.Gorev;
import personelyonetim.model.Proje;
import personelyonetim.model.User;

public class GorevDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	private static final String INSERT_GOREV_SQL = "INSERT INTO proje_personel" + "  (proje_id, personel_id, gorev) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_GOREV_BY_ID = "select id, proje_id, personel_id, gorev from proje_personel where id =?";
	private static final String SELECT_ALL_GOREV = "select * from proje_personel";
	private static final String SELECT_GOREV_BY_ACCOUNT = "select id, proje_id, personel_id, gorev from proje_personel where personel_id =?";
	private static final String DELETE_GOREV_SQL = "delete from proje_personel where id = ?;";
	private static final String UPDATE_GOREV_SQL = "update proje_personel set proje_id = ?,personel_id= ?, gorev =? where id = ?;";

	public GorevDAO() {
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
            String dbDriver = "com.mysql.jdbc.Driver"; 
            Class.forName(dbDriver);
            return(Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }

	public void insertGorev(Gorev gorev) throws SQLException,ClassNotFoundException {
		System.out.println(INSERT_GOREV_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GOREV_SQL)) {
			preparedStatement.setInt(1, gorev.getProje().getId());
			preparedStatement.setInt(2, gorev.getPersonel().getId());
			preparedStatement.setString(3, gorev.getGorev());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Gorev selectGorev(int id) {
		Gorev gorev = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GOREV_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Integer projeId = rs.getInt("proje_id");
				Integer personelId = rs.getInt("personel_id");
				String sebep = rs.getString("gorev");

				User personel = new UserDAO().selectUser(personelId);
				Proje proje = new ProjeDAO().selectProje(projeId);
				gorev = new Gorev(id, sebep, proje, personel);
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (Exception e) {}
                  return gorev;
	}

	public List<Gorev> selectAllGorev() {

		List<Gorev> gorevler = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GOREV);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				Integer projeId = rs.getInt("proje_id");
				Integer personelId = rs.getInt("personel_id");
				String sebep = rs.getString("gorev");

				User personel = new UserDAO().selectUser(personelId);
				Proje proje = new ProjeDAO().selectProje(projeId);
				gorevler.add(new Gorev(id, sebep, proje, personel));
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (Exception e) {}
                    return gorevler;
	}

	public List<Gorev> selectAllGorevByAccount(Integer accountId) {

		List<Gorev> gorevler = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GOREV_BY_ACCOUNT);) {
			preparedStatement.setInt(1, accountId);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				Integer projeId = rs.getInt("proje_id");
				Integer personelId = rs.getInt("personel_id");
				String sebep = rs.getString("gorev");

				User personel = new UserDAO().selectUser(personelId);
				Proje proje = new ProjeDAO().selectProje(projeId);
				gorevler.add(new Gorev(id, sebep, proje, personel));
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (Exception e) {}
		return gorevler;
	}

	public boolean deleteGorev(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_GOREV_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}catch (Exception e) {
            rowDeleted = false;
        }
		return rowDeleted;
	}

	public boolean updateGorev(Gorev gorev) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_GOREV_SQL);) {
			statement.setInt(1, gorev.getProje().getId());
			statement.setInt(2, gorev.getPersonel().getId());
			statement.setString(3, gorev.getGorev());
			statement.setInt(4, gorev.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}catch (Exception e) {
            rowUpdated = false;
        }
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
   

