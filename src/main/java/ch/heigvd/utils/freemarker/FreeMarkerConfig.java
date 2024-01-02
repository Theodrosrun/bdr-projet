package ch.heigvd.utils.freemarker;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/***
 * Les templates, dans le contexte du développement logiciel, sont des fichiers ou des morceaux de code
 * qui servent de modèle pour générer des contenus dynamiques.
 * Cela permet une réutilisation efficace du code et facilite la gestion des mises à jour de l'interface utilisateur.
 * Pour trouver les fichiers .ftlh (html), il suffit d'aller au chemin src/main/resources/templates
 */

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
