package Controller;

import Config.MySqlConfig;
import Model.UsersModel;

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
        String sql = "SELECT * FROM users u WHERE email =? and password =?";
        Connection connection = MySqlConfig.getConnection();
        boolean isSuccess = false;
        ArrayList <UsersModel> usersModelArrayList = new ArrayList<UsersModel>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getInt("id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setRoleId(resultSet.getInt("role_id"));
                usersModel.setEmail(email);
                usersModel.setPassword(password);
                usersModelArrayList.add(usersModel);
            }
            System.out.println("check size list :"+usersModelArrayList.size());
            if(usersModelArrayList.size()>0) isSuccess = true;

        } catch (Exception e){
            System.out.println("Error Select Users in loginController.java");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        if(isSuccess){
            url = "/index.jsp";
          HttpSession session = req.getSession();
          session.setAttribute("user",usersModelArrayList.get(usersModelArrayList.size()-1));
          System.out.println("Create session  success!");
        }
        System.out.println("url :"+url);
        req.getRequestDispatcher(url).forward(req,resp);



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            Cookie [] cookies = req.getCookies();
            System.out.println("check length cookie :"+cookies.length);

            for (Cookie cookie : cookies){
                if(cookie.getName().equals("email")){
                    System.out.println("check email :"+cookie.getValue());
                    req.setAttribute("email",cookie.getValue());
                }
                if(cookie.getName().equals("password")){
                    System.out.println("check password :"+cookie.getValue());
                    req.setAttribute("password",cookie.getValue());
                }

            }
       req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }
}
