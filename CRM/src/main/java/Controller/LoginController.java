package Controller;

import Config.MySqlConfig;
import Model.UsersModel;
import Repository.UserRepository;
import Service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet (name = "loginController",urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private LoginService loginService = new LoginService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String remember = req.getParameter("remember");
        String message = "";
        String url = "/login.jsp";
        // get Parameter
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("Check email :"+email);
        System.out.println("Check password :"+password);
        boolean isSuccess = loginService.checkLogin(email,password);
        if(isSuccess){
            // create session
            ArrayList <UsersModel> userList = loginService.getUserList(email,password);
            boolean createUserSessionIsSuccess = loginService.createUserSession(userList,req);
            System.out.println("check createUserSessionIsSuccess"+createUserSessionIsSuccess);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        } else {
            PrintWriter writer  = resp.getWriter();
        }



    }


}
