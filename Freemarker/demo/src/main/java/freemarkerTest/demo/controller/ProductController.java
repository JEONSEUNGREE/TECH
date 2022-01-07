package freemarkerTest.demo.controller;

import freemarker.template.Template;
import freemarkerTest.demo.config.FreemarkerConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class ProductController {

    @RequestMapping(value = "/product")
    public String product(Model model) {

        Map<String, Object> product = new LinkedHashMap<>();
        product.put("test", "testItem");

        model.addAttribute("test", template("product", product));
        return "test";
    }

    private String template(String templateName, Map<String, Object> data) {

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
