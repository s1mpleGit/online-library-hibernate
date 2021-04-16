package library.controller.servlet_util;

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

@WebServlet(urlPatterns = "/registration")
public class ServletRegistration extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        User user = User.builder()
                .login(login)
                .password(password)
                .name(name)
                .surname(surname)
                .email(email)
                .phone(phone)
                .build();

        ServiceUser userService = new ServiceUser();
        int id = userService.checkUserLoginForRegistration(login);
        if (id == 0) {
            User newUser = userService.saveNewUser(user);
            if (newUser != null) {
                request.getSession().setAttribute("user", newUser);
                getServletContext().getRequestDispatcher("/book").forward(request, response);
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/registration.jsp");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Do not use space bar to filling fields and fill empty fields</font>");
                rd.include(request, response);
            }
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/registration.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Login already exist</font>");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/registration.jsp");
    }
}
