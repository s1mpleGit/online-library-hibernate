package library.controller.servlet_util;

import library.persistence.model.Role;
import library.persistence.model.Status;
import library.persistence.model.User;
import library.service.ServiceRole;
import library.service.ServiceStatus;
import library.service.ServiceUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/index")
public class ServletLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        ServiceUser userService = new ServiceUser();
        User user = userService.checkUserLoginPassword(login, password);
//        ServiceRole roleService = new ServiceRole();
//        ServiceStatus statusService = new ServiceStatus();

//        Role roleUser = roleService.getRoleById(1);
//        Role roleAdmin = roleService.getRoleById(2);
//        Status statusActive = statusService.getStatusById(1);
//        Status statusBlock = statusService.getStatusById(2);

        if (user != null && user.getRole().toString().contains("USER") && user.getStatus().toString().contains("ACTIVE")) {
            request.getSession().setAttribute("user", userService.getUserInfo(user.getId()));
            getServletContext().getRequestDispatcher("/book").forward(request, response);
        } else if (user != null && user.getStatus().toString().contains("BLOCK")) {
            request.getSession().setAttribute("user", userService.getUserInfo(user.getId()));
            response.sendRedirect("/block.jsp");
        } else if (user != null && user.getRole().toString().contains("ADMIN")) {
            request.getSession().setAttribute("user", userService.getUserInfo(user.getId()));
            getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Wrong username or password</font>");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/index.jsp");
    }
}
