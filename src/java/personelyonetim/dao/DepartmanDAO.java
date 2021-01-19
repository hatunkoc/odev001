package personelyonetim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import personelyonetim.model.Departman;

public class DepartmanDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_DEPARTMAN_SQL = "INSERT INTO departman" + "  (ad) VALUES (?);";

    private static final String SELECT_DEPARTMAN_BY_ID = "select id, ad from departman where id =?";
    private static final String SELECT_ALL_DEPARTMAN = "select * from departman";
    private static final String DELETE_DEPARTMAN_SQL = "delete from departman where id = ?;";
    private static final String UPDATE_DEPARTMAN_SQL = "update departman set ad = ? where id = ?;";

    public DepartmanDAO() {
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String dbDriver = "com.mysql.jdbc.Driver";
        Class.forName(dbDriver);
        return (Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertDepartman(Departman departman) throws SQLException, ClassNotFoundException {
        System.out.println(INSERT_DEPARTMAN_SQL);
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPARTMAN_SQL)) {
            preparedStatement.setString(1, departman.getAd());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Departman selectDepartman(int id) {
        Departman departman = null;
        
        try ( Connection connection = getConnection(); 
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMAN_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            
            ResultSet rs = preparedStatement.executeQuery();

         
            while (rs.next()) {
                String ad = rs.getString("ad");

                departman = new Departman(id, ad);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {}
        return departman;
    }

    public List<Departman> selectAllDepartman() {

        List<Departman> departmanlar = new ArrayList<>();
        try ( Connection connection = getConnection(); 
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEPARTMAN);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String ad = rs.getString("ad");

                departmanlar.add(new Departman(id, ad));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {}
        return departmanlar;
    }

    public boolean deleteDepartman(int id) throws SQLException {
        boolean rowDeleted;
        try ( Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(DELETE_DEPARTMAN_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            rowDeleted = false;
        }
        return rowDeleted;
    }

    public boolean updateDepartman(Departman departman) throws SQLException {
        boolean rowUpdated;
        try ( Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(UPDATE_DEPARTMAN_SQL);) {
            statement.setString(1, departman.getAd());
            statement.setInt(2, departman.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
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
