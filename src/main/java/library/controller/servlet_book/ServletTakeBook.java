package library.controller.servlet_book;

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

@WebServlet(urlPatterns = "/takeBook")
public class ServletTakeBook extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        ServiceUser userService = new ServiceUser();
        User user = userService.addBookForUser(userId, bookId);
        if (user != null) {
            request.setAttribute("userBooks", userService.getUserBooksByUserId(userId));
            getServletContext().getRequestDispatcher("/card").forward(request, response);
        } else {
            ServiceBook bookService = new ServiceBook();
            request.setAttribute("books", bookService.getAllBooks());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/book.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>You already have this book</font>");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/book.jsp").forward(request, response);
    }
}
