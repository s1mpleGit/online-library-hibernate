package library.controller.servlet_book;

import library.persistence.model.Author;
import library.service.ServiceAuthor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/createAuthor")
public class ServletCreateAuthor extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author author = Author.builder()
                .name(request.getParameter("author"))
                .build();

        ServiceAuthor authorService = new ServiceAuthor();
        Author newAuthor = authorService.createAuthor2(author);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin.jsp");
        PrintWriter out = response.getWriter();
        if (newAuthor != null) {
            out.println("<font color=green>Author successfully created</font>");
        } else {
            out.println("<font color=red>Same author already exist</font>");
        }
        rd.include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
