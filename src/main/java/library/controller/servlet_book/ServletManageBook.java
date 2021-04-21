package library.controller.servlet_book;

import library.persistence.model.Book;
import library.service.ServiceAuthor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin")
public class ServletManageBook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String bookImageUri = request.getParameter("bookImageUri");
        String bookTitle = request.getParameter("bookTitle");
        String bookAuthor = request.getParameter("bookAuthor");
        int bookAuthorId = Integer.parseInt(request.getParameter("bookAuthorId"));
        String bookDescription = request.getParameter("bookDescription");
        Book book = Book.builder()
                .id(bookId)
                .imageUri(bookImageUri)
                .title(bookTitle)
                .description(bookDescription)
                .build();
        request.setAttribute("book", book);
        ServiceAuthor authorService = new ServiceAuthor();
        request.setAttribute("authors", authorService.getAllAuthors());
        request.setAttribute("authorId", bookAuthorId);
        request.setAttribute("bookAuthor", bookAuthor);
        getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
