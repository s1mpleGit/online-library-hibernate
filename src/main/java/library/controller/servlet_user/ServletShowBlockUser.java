package library.controller.servlet_user;

import library.service.ServiceBook;
import library.service.ServiceUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/blocked")
public class ServletShowBlockUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceUser userService = new ServiceUser();
        request.setAttribute("users", userService.showAllUsers());
        getServletContext().getRequestDispatcher("/block.jsp").forward(request, response);
    }
}
