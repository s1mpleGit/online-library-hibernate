package library.controller.servlet_book;

import library.persistence.model.Author;
import library.persistence.model.Book;
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

@WebServlet(urlPatterns = "/createBook")
public class ServletCreateBook extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceBook bookService = new ServiceBook();
        ServiceAuthor authorService = new ServiceAuthor();
        int authorId = Integer.parseInt(request.getParameter("author"));
        Author author = authorService.getAuthorInfo(authorId);
        Book book = Book.builder()
                .title(request.getParameter("title"))
                .description(request.getParameter("description"))
                .imageUri(request.getParameter("imageUri"))
                .build();
//        author.addBook(book);
        book.setAuthor(author);
        Book newBook = bookService.createBook(book);
        request.setAttribute("books", bookService.getAllBooks());
        request.setAttribute("authors", authorService.getAllAuthors());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/book.jsp");
        PrintWriter out = response.getWriter();
        if (newBook != null) {
            out.println("<font color=green>Book successfully created</font>");
        } else {
            out.println("<font color=red>Same book already exist</font>");
        }
        rd.include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/book.jsp").forward(request, response);
    }
}
