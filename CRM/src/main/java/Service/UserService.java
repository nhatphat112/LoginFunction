package Service;

import Model.UsersModel;
import Repository.UserRepository;

import java.util.ArrayList;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    public ArrayList<UsersModel> getUserList(){
        return userRepository.findUserList();
    }

}
