package library.controller.servlet_user;

import library.persistence.model.Role;
import library.persistence.model.Status;
import library.persistence.model.User;
import library.service.ServiceUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/user")
public class ServletInfoUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("userId"));
        ServiceUser userService = new ServiceUser();
        User oldUser = userService.getUserInfo(id);
        String login = oldUser.getLogin();
        Role role = oldUser.getRole();
        Status status = oldUser.getStatus();
        String newPassword = request.getParameter("newPassword");
        String newName = request.getParameter("newName");
        String newSurname = request.getParameter("newSurname");
        String newEmail = request.getParameter("newEmail");
        String newPhone = request.getParameter("newPhone");
        User user = User.builder()
                .id(id)
                .login(login)
                .role(role)
                .status(status)
                .password(newPassword)
                .name(newName)
                .surname(newSurname)
                .email(newEmail)
                .phone(newPhone)
                .build();
        User newUser = userService.updateUserInfo(user);
        if (newUser != null) {
            request.getSession().setAttribute("user", userService.getUserInfo(id));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/user.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=green>New info successfully updated</font>");
            rd.include(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/user.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Do not use space bar to filling fields and fill empty fields</font>");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
    }
}
