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

@WebServlet(urlPatterns = "/updateBook")
public class ServletUpdateBook extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String title = request.getParameter("title");
        int authorId = Integer.parseInt(request.getParameter("author"));
        String description = request.getParameter("description");
        String imageUri = request.getParameter("imageUri");

        ServiceAuthor authorService = new ServiceAuthor();
        ServiceBook bookService = new ServiceBook();

        Author author = authorService.getAuthorInfo(authorId);
        Book book = Book.builder()
                .id(bookId)
                .title(title)
                .description(description)
                .imageUri(imageUri)
                .build();
//        author.addBook(book);
        book.setAuthor(author);
        Book newBook = bookService.updateBook(book);
        request.setAttribute("books", bookService.getAllBooks());
        request.setAttribute("authors", authorService.getAllAuthors());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/book");
        PrintWriter out = response.getWriter();
        if (newBook != null) {
            out.println("<font color=green>Book successfully updated</font>");
        } else {
            out.println("<font color=red>Failed to update book</font>");
        }
        rd.include(request, response);
        request.removeAttribute("authors");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/book.jsp").forward(request, response);
    }
}
