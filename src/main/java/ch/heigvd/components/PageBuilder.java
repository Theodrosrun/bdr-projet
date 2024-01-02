package ch.heigvd.components;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class PageBuilder {
    private final String pageName;
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    public PageBuilder(String pageName, HttpServletRequest req, HttpServletResponse resp) {
        this.pageName = pageName;
        this.req = req;
        this.resp = resp;
        try {
            open();
        } catch (IOException ignored) {

        }
    }

    /***
     * Fonction executée lors du constructeur de la page web
     * @throws IOException exception
     */
    private void open() throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"fr\">");
        out.println(new Head(pageName).doGet());
        out.println("<body>");
        out.println(Preloader.doGet());
        out.println(Menu.doGet(req)); // logout est inséré ici
    }

    public void add(String content) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println(content);
    }

    public void close() throws IOException {
        PrintWriter out = resp.getWriter();
        out.println(JSScripts.doGet());
        out.println(Footer.doGet());
        out.println("</body>");
        out.println("</html>");
    }
}
