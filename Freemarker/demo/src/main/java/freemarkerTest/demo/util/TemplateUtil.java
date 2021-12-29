package freemarkerTest.demo.util;


import freemarker.template.Template;
import freemarkerTest.demo.config.FreemarkerConfig;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class TemplateUtil {

    public String template(String templateName, Map<String, Object> data) {

        try {
            Template template = FreemarkerConfig.newFreeMarkerConfig().getTemplate(templateName + ".ftl");
            Writer out = new StringWriter();
            template.process(data, out);
            out.close();
            return out.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
