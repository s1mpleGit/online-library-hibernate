package library.controller.servlet_user;

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

@WebServlet(urlPatterns = "/writeMessage")
public class ServletWriteMessage extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String message = request.getParameter("message");

        ServiceUser userService = new ServiceUser();
        User user = userService.getUserInfo(userId);
        user.setMessage(message);
        userService.sendMessage(user);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/block.jsp");
        PrintWriter out = response.getWriter();
        if (message != null) {
            out.println("<font color=green>Message successfully sent</font>");
        } else {
            out.println("<font color=red>Failed to send message</font>");
        }
        rd.include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/block.jsp").forward(request, response);
    }
}
