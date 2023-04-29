package Filter;

import Model.UsersModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
@WebFilter(filterName = "authFilter",urlPatterns = "/*")
public class AuthFilter  implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getScheme()+"://" + req.getServerName()+":"+req.getLocalPort()+req.getContextPath();

        boolean loginSuccess = (req.getSession().getAttribute("user")!=null)?true:false;
        System.out.println("check login : "+loginSuccess);
        if(loginSuccess){
            System.out.println("check url /login from servletPath:"+req.getServletPath());
            if(req.getServletPath().startsWith("/login")){
               servletRequest.getRequestDispatcher("/user").forward(req,resp);
            } else {
                filterChain.doFilter(servletRequest,servletResponse);
            }

        } else {
            servletRequest.getRequestDispatcher("/login").forward(req,resp);
        }

    }
}
