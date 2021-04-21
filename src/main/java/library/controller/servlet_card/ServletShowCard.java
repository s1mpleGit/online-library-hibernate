package library.controller.servlet_card;

import library.persistence.model.User;
import library.service.ServiceAuthor;
import library.service.ServiceBook;
import library.service.ServiceUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/card")
public class ServletShowCard extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
        ServiceUser userService = new ServiceUser();
        request.setAttribute("userBooks", userService.getUserBooks());
        if (user.getRole().toString().equalsIgnoreCase("ADMIN")) {
//            ServiceBook bookService = new ServiceBook();
//            request.setAttribute("books", bookService.getAllBooks());
//            request.setAttribute("users", userService.showAllUsers());
//            ServiceAuthor authorService = new ServiceAuthor();
//            request.setAttribute("authors", authorService.getAllAuthors());
        }
        getServletContext().getRequestDispatcher("/card.jsp").forward(request, response);
        } else {
            response.sendRedirect("/index.jsp");
        }
    }

}
