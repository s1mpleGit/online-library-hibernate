package library.controller.servlet_book;

import library.service.ServiceAuthor;
import library.service.ServiceBook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/deleteBook")
public class ServletDeleteBook extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        ServiceBook bookService = new ServiceBook();
        boolean delete = bookService.deleteBook(bookId);
        if (delete) {
            getServletContext().getRequestDispatcher("/book").forward(request, response);
        } else {
            request.setAttribute("books", bookService.getAllBooks());
            ServiceAuthor authorService = new ServiceAuthor();
            request.setAttribute("authors", authorService.getAllAuthors());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/book.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Failed to delete book</font>");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/book.jsp").forward(request, response);
    }
}
