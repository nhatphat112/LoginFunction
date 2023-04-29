package Service;

import Model.UsersModel;
import Repository.UserRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class LoginService {
    private UserRepository  userRepository = new UserRepository();
    public boolean checkLogin (String email,String password){
        ArrayList<UsersModel> userList = userRepository.findByEmailAndPassword(email,password);
       return userList.size() >0;
    }
    public ArrayList<UsersModel> getUserList(String email,String password){
        return userRepository.findByEmailAndPassword(email,password);
    }
    public boolean createUserSession(ArrayList <UsersModel> userList, HttpServletRequest req){
        HttpSession session = req.getSession();
        for (UsersModel user :userList){
            session.setAttribute("user",user);
            return true;
        }
        return false;
    }
}
