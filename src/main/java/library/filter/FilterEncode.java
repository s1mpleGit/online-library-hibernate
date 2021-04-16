package library.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8")})
public class FilterEncode implements Filter {

    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        String codeRequest = req.getCharacterEncoding();

        if (encoding != null && !encoding.equalsIgnoreCase(codeRequest)) {
            req.setCharacterEncoding(encoding);
            resp.setCharacterEncoding(encoding);
        }
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }

}
