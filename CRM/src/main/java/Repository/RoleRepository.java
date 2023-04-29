package Repository;

import Config.MySqlConfig;
import Model.RolesModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RoleRepository {
    public ArrayList<RolesModel> findById(String id){
        ArrayList<RolesModel> roleList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT * FROM roles WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                RolesModel rolesModel = new RolesModel();
                rolesModel.setId(id);
                rolesModel.setName(resultSet.getString("name"));
                rolesModel.setDescription(resultSet.getString("description"));
                roleList.add(rolesModel);
            }
            System.out.println("Execute :"+sql);
        }catch (Exception e){
            System.out.println("Error findById");
            e.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return roleList;
    }
}
