package Controller;

import Model.UsersModel;
import Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "serController",urlPatterns = "/user")
public class UserController extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get list User
        ArrayList<UsersModel> userList = userService.getUserList();
        for (UsersModel user : userList){
            System.out.println("check fullName :"+user.getFullName());
        }
        req.setAttribute("userList",userList);
        req.getRequestDispatcher("/user-table.jsp").forward(req,resp);
    }
}
