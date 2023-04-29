package Repository;

import Config.MySqlConfig;
import Model.RolesModel;
import Model.UsersModel;
import Service.RoleService;
import Service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserRepository {
    private RoleRepository roleRepository = new RoleRepository();
    public ArrayList<UsersModel> findByEmailAndPassword(String email, String password){
        ArrayList<UsersModel> userArrayList = new ArrayList<>();
        String sql = "SELECT * FROM users u WHERE email =? and password =?";
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                UsersModel user = new UsersModel();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("fullname"));
                user.setAvatar(rs.getString("avatar"));
                ArrayList<RolesModel> listRole = new ArrayList<>();
                listRole = roleRepository.findById(rs.getString("role_id"));
                if(listRole.size()>0){
                    user.setRole(listRole.get(0));
                }
                user.setEmail(email);
                user.setPassword(password);
                userArrayList.add(user);
            }
            System.out.println("Executed :"+sql);

        }catch (Exception e){
            System.out.println("Error findByEmailAndPassword"+e.getMessage());
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return userArrayList;
    }
    public ArrayList<UsersModel> findUserList(){

        ArrayList<UsersModel> userArrayList = new ArrayList<>();
        String sql = "SELECT * FROM users u ;";
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                UsersModel user = new UsersModel();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("fullname"));
                user.setAvatar(rs.getString("avatar"));
               ArrayList<RolesModel> listRole = new ArrayList<>();
                listRole = roleRepository.findById(rs.getString("role_id"));
                if(listRole.size()>0){
                   user.setRole(listRole.get(0));
               }
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                userArrayList.add(user);
            }
            System.out.println("Executed :"+sql);

        }catch (Exception e){
            System.out.println("Error findListUser"+e.getMessage());
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return userArrayList;

    }
}
