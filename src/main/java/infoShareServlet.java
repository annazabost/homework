import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/infoShareAcademy")
public class infoShareServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(infoShareServlet.class.getName());

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html><html><body>");
        writer.println("<script src=\"https://code.jquery.com/jquery-3.4.0.min.js\"></script>");
        writer.println("<h1>HOMEWORK<h2>");
        writer.println("<div>");
        writer.println("<p>Anna Zabost</p>");
        writer.println("<p>jjdd6-czfureczka</p>");
        LocalDateTime now = LocalDateTime.now();
        writer.println("<p>" + now + "</p>");
        writer.println("</div>");
        writer.println("<a href=\"#\" class=\"infoShareAcademy\">post</a>");
        writer.println("<script src=\"/homework5/js/infoShareAcademy.js\"></script>");
        writer.println("</body></html>\n");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "infoShareAcademy.ftlh");
        Map<String, Object> model = new HashMap<>();
        String param = req.getQueryString();

        model.put("result", param);
        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            logger.severe(e.getMessage());
        }
    }

}
