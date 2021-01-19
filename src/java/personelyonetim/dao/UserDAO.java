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

import personelyonetim.model.Departman;
import personelyonetim.model.User;

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	private static final String INSERT_USERS_SQL = "INSERT INTO personel" + "  (ad, soyad, email, telefon, sifre, cinsiyet, isManager, departman_id, dogum_tarihi, ise_baslama_tarihi) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,ad, soyad, email, telefon, sifre, cinsiyet, isManager, departman_id, dogum_tarihi, ise_baslama_tarihi from personel where id =?";
	private static final String SELECT_ALL_USERS = "select * from personel";
	private static final String DELETE_USERS_SQL = "delete from personel where id = ?;";
	private static final String UPDATE_USERS_SQL = "update personel set ad= ?, soyad= ?, email= ?, telefon= ?, sifre= ?, cinsiyet= ?, isManager= ?, departman_id= ?, dogum_tarihi= ?, ise_baslama_tarihi= ? where id = ?;";
	private static final String LOGIN_USER_QUERY = "select * from personel where email =? and sifre =?";
	
	public UserDAO() {
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
            String dbDriver = "com.mysql.jdbc.Driver"; 
            Class.forName(dbDriver);
            Class.forName ( "com.mysql.jdbc.Driver" );
            return(Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }

	public void insertUser(User user) throws SQLException, ClassNotFoundException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getAd());
			preparedStatement.setString(2, user.getSoyad());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getTelefon());
			preparedStatement.setString(5, user.getSifre());
			preparedStatement.setInt(6, user.getCinsiyet());
			preparedStatement.setBoolean(7, user.getIsManager());
			preparedStatement.setInt(8, user.getDepartman().getId());
			preparedStatement.setDate(9, user.getDogumTarihi());
			preparedStatement.setDate(10, user.getIseBaslamaTarihi());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public User authtenticateUser(String e, String s) throws SQLException {
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER_QUERY)) {
			preparedStatement.setString(1, e);
			preparedStatement.setString(2, s);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String ad = rs.getString("ad");
				String soyad = rs.getString("soyad");
				String email = rs.getString("email");
				String telefon = rs.getString("telefon");
				String sifre = rs.getString("sifre");
				Integer cinsiyet = rs.getInt("cinsiyet");
				Boolean isManager = rs.getBoolean("isManager");
				Integer departmanId = rs.getInt("departman_id");
				Date iseBaslangic = rs.getDate("ise_baslama_tarihi");
				Date dogumTarihi = rs.getDate("dogum_tarihi");
				
				Departman departman = new DepartmanDAO().selectDepartman(departmanId);

				user = new User(id, ad, soyad, telefon, email,sifre, isManager, cinsiyet, departman,dogumTarihi,iseBaslangic);
			}
			return user;
		} catch (SQLException ex) {
			printSQLException(ex);   
		}catch (Exception ex) {}
                return user;
	}

	public User selectUser(int id) {
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String ad = rs.getString("ad");
				String soyad = rs.getString("soyad");
				String email = rs.getString("email");
				String telefon = rs.getString("telefon");
				String sifre = rs.getString("sifre");
				Integer cinsiyet = rs.getInt("cinsiyet");
				Boolean isManager = rs.getBoolean("isManager");
				Integer departmanId = rs.getInt("departman_id");
				Date iseBaslangic = rs.getDate("ise_baslama_tarihi");
				Date dogumTarihi = rs.getDate("dogum_tarihi");
				
				Departman departman = new DepartmanDAO().selectDepartman(departmanId);

				user = new User(id, ad, soyad, telefon, email,sifre, isManager, cinsiyet, departman,dogumTarihi,iseBaslangic);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return user;
	}

	public List<User> selectAllUsers() {

		List<User> users = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String ad = rs.getString("ad");
				String soyad = rs.getString("soyad");
				String email = rs.getString("email");
				String telefon = rs.getString("telefon");
				String sifre = rs.getString("sifre");
				Integer cinsiyet = rs.getInt("cinsiyet");
				Boolean isManager = rs.getBoolean("isManager");
				Integer departmanId = rs.getInt("departman_id");
				Date iseBaslangic = rs.getDate("ise_baslama_tarihi");
				Date dogumTarihi = rs.getDate("dogum_tarihi");
				
				Departman departman = new DepartmanDAO().selectDepartman(departmanId);
				users.add(new User(id, ad, soyad, telefon, email,sifre, isManager, cinsiyet, departman,dogumTarihi,iseBaslangic));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return users;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}catch (Exception e) {
            rowDeleted = false;}
		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getAd());
			statement.setString(2, user.getSoyad());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getTelefon());
			statement.setString(5, user.getSifre());
			statement.setInt(6, user.getCinsiyet());
			statement.setBoolean(7, user.getIsManager());
			statement.setInt(8, user.getDepartman().getId());
			statement.setDate(9, user.getDogumTarihi());
			statement.setDate(10, user.getIseBaslamaTarihi());
			statement.setInt(11, user.getId());

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

