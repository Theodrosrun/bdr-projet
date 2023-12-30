package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    public static String doGet(List<String> headers, List<HashMap<String, String>> entries, String title) {
        try {
            Template template = FreeMarkerConfig.getConfig().getTemplate("table.ftlh");
            StringWriter out = new StringWriter();
            Map<String, Object> data = new HashMap<>();
            List<List<String>> entriesList = new ArrayList<>();
            for (HashMap<String, String> entry : entries) {
                List<String> entryList = new ArrayList<>();
                for (String header : headers) {
                    entryList.add(entry.get(header));
                }
                entriesList.add(entryList);
            }
            data.put("title", title);
            data.put("headers", headers);
            data.put("entries", entriesList);
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
