package ch.heigvd.components;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class FreeMarkerConfig {
    private static final Configuration cfg;

    static {
        cfg = new Configuration(new Version("2.3.31"));
        cfg.setClassForTemplateLoading(FreeMarkerConfig.class, "/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
    }

    public static Configuration getConfig() {
        return cfg;
    }
}
