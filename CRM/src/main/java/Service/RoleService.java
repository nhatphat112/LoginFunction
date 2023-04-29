package Service;

import Model.RolesModel;
import Repository.RoleRepository;
import Repository.UserRepository;

import java.util.ArrayList;

public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();
    public ArrayList<RolesModel> getRoleListById (String id){
        return roleRepository.findById(id);
    }
}
