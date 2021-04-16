package library.controller.servlet_book;

import library.persistence.model.User;
import library.service.ServiceAuthor;
import library.service.ServiceBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/book")
public class ServletShowBook extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceBook bookService = new ServiceBook();
        request.setAttribute("books", bookService.getAllBooks());
        User user = (User) request.getSession().getAttribute("user");
            if (user != null && user.getRole().toString().equalsIgnoreCase("ADMIN")) {
                ServiceAuthor authorService = new ServiceAuthor();
                request.setAttribute("authors", authorService.getAllAuthors());
//                ServiceCard cardService = new ServiceCard();
//                request.setAttribute("cards", cardService.getAllCards());
                getServletContext().getRequestDispatcher("/book.jsp").forward(request, response);
            } else if (user != null && user.getStatus().toString().equalsIgnoreCase("BLOCK")) {
                response.sendRedirect("/block.jsp");
            }
            else getServletContext().getRequestDispatcher("/book.jsp").forward(request, response);
    }

}
