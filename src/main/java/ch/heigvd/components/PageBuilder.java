package ch.heigvd.components;

import java.io.PrintWriter;

public class PageBuilder {
    private final String pageName;
    private final PrintWriter out;
    public PageBuilder(String pageName, PrintWriter out) {
        this.pageName = pageName;
        this.out = out;
        open();
    }

    private void open(){
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"fr\">");
        out.println(new Head(pageName).doGet());
        out.println("<body>");
        out.println(Preloader.doGet());
        out.println(Menu.doGet());
    }

    public void add(String content) {
        out.println(content);
    }

    public void close() {
        out.println(JSScripts.doGet());
        out.println(Footer.doGet());
        out.println("</body>");
        out.println("</html>");
    }
}
