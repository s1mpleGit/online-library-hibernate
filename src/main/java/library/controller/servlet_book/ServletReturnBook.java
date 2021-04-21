package library.controller.servlet_book;

import library.persistence.model.Book;
import library.persistence.model.User;
import library.service.ServiceBook;
import library.service.ServiceUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/returnBook")
public class ServletReturnBook extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userBookId = Integer.parseInt(request.getParameter("userBookId"));
        ServiceUser userService = new ServiceUser();
        boolean check = userService.removeBookFromUser(userBookId);
        if (check) {
            request.setAttribute("userBooks", userService.getUserBooks());
            getServletContext().getRequestDispatcher("/card.jsp").forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/card.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Failed to return book</font>");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/card.jsp").forward(request, response);
    }
}
