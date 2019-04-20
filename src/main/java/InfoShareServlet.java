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
import java.util.*;
import java.util.logging.Logger;

@WebServlet("/infoShareAcademy")
public class InfoShareServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(InfoShareServlet.class.getName());

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html><html><body>");
        writer.println("<h3>HOMEWORK</h3>");
        writer.println("<div>");
        writer.println("<p>Anna Zabost</p>");
        writer.println("<p>jjdd6-czfureczka</p>");
        LocalDateTime now = LocalDateTime.now();
        writer.println("<p>" + now + "</p>");
        writer.println("</div>");
        writer.println("<form method=\"post\" action=\"/infoShareAcademy\">");
        writer.println("Parametr 1: <input type=\"text\" name=\"param1\"/><br/>");
        writer.println("Parametr 2: <input type=\"text\" name=\"param2\"/><br/>");
        writer.println("<input type=\"submit\"/><br/><br/>");
        writer.println("</form>");
        writer.println("</body></html>\n");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Template template = templateProvider.getTemplate(getServletContext(), "infoShareAcademy.ftlh");

        Map<String, Object> model = new HashMap<>();
        Map<String, String[]> param = req.getParameterMap();
        Map<String, String> helpMap = new HashMap<>();

        param.entrySet().forEach(entry -> {
            String key = entry.getKey();
            String value = String.join(", ", entry.getValue());
            helpMap.put(key, value);
        });

        model.put("params", helpMap);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            logger.severe(e.getMessage());
        }
    }

}
