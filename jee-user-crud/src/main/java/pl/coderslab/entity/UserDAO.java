package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDAO {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String READ_ALL_USERS_QUERY =
            "SELECT * FROM users";

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public User read(int userId) {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement =
                    conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(User user){
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int userId){
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public User[] findAll() {
        User[] allUsers = new User[0];
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_ALL_USERS_QUERY);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User tmpUser = new User();
                 tmpUser.setId(rs.getInt("id"));
                 tmpUser.setUserName(rs.getString("username"));
                 tmpUser.setEmail(rs.getString("email"));
                 tmpUser.setPassword(rs.getString("password"));
                 allUsers = addToArray(tmpUser, allUsers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    private User[] addToArray (User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }
}