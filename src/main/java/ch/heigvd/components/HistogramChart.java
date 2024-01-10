package ch.heigvd.components;

import ch.heigvd.utils.controller.GeneralController;
import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistogramChart {
    public static String doGet() {
        try {
            Template template = FreeMarkerConfig.getConfig().getTemplate("histogram_chart.ftlh");
            StringWriter out = new StringWriter();
            Map<String, Object> data = new HashMap<>();
            List<HashMap<String, String>> frequencies = new GeneralController().getTodayAverageFrequency(1);
            // Temporary fix for the histogram chart
            for (HashMap<String, String> frequency : frequencies) {
                frequency.put("moyenne_personnes",
                        String.valueOf(1 - Math.exp(-Math.sqrt(Double.parseDouble(frequency.get("moyenne_personnes"))))));
            }
            data.put("frequencies", frequencies);
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
