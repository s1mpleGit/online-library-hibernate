package library.controller.servlet_user;

import library.service.ServiceUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/blockUser")
public class ServletBlockUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        ServiceUser userService = new ServiceUser();
        boolean check = userService.blockUserById(userId);
        if (check) {
            request.setAttribute("userBooks", userService.getUserBooks());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/card.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=green>User successfully blocked</font>");
            rd.include(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/card.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Failed to block user</font>");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/card.jsp").forward(request, response);
    }
}
