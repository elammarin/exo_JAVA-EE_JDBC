package user;

import java.sql.*;

public class UserDaoSqlite implements UserDao {

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new Error(e);
		}
	}
	
	protected Connection conn;
	public UserDaoSqlite( String userFilePath ) throws SQLException {
		
		String jdbcUrl = "jdbc:sqlite:/home/m2gl/elammari/Desktop/apache-tomcat-8.5.71/webapps/exo201/WEB-INF/users.db";
		try {
			this.conn = DriverManager.getConnection(jdbcUrl, "root", "");
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void add(User user, String password) {
		String sql = "INSERT INTO USERS(firstname,lastname, email, password)  VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getLastname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, password);
			preparedStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	
	@Override
	public void update(User user, String password) {
		String sql = "UPDATE USERS SET firstname =?, lastname=?, email=? ,password=?  WHERE email=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,user.getFirstname());
			preparedStatement.setString(2,user.getLastname());
			preparedStatement.setString(3,user.getEmail());
			preparedStatement.setString(4, password);
			preparedStatement.setString(5,user.getEmail());
			preparedStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	
	@Override
	public User find(long id) {
		User user = new User();
		try {
			String sql = "SELECT * from USERS WHERE id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				user.setId(resultSet.getLong("id"));
				user.setFirstname(resultSet.getString("firstname"));
				user.setLastname(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
			}


		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return user;
	}
	
	@Override
	public User findByEmail(String email) {
		String sql = "SELECT * FROM USERS WHERE email=?";
		User user = new User();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,email);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				user.setId(resultSet.getLong("id"));
				user.setFirstname(resultSet.getString("firstname"));
				user.setLastname(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
			}

		}catch(SQLException e){
			//e.printStackTrace();
			user = null;
		}
		return user;
	}
	
	@Override
	public long checkPassword(String email, String password) {
		String sql = "Select * from users WHERE email=? and password=?";
		int returnValue =-1;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,email);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				returnValue = resultSet.getInt("id");
			}
		}catch(SQLException e){
			return returnValue;
		}
		return returnValue;
	}
	
	@Override
	public void delete(long id) {
		String sql ="DELETE FROM users WHERE id=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1,id);
			int row = preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}

	}
	
	@Override
	public long exists(String email) {
		int returnValue =-1;
		String sql ="SELECT id FROM users WHERE email=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,email);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				returnValue = resultSet.getInt("id");
			}
		}catch(SQLException e){
			return returnValue;
		}
		return returnValue;
	}
	
	

}
