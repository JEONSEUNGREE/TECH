package com.example.freemarker.config;


import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import lombok.NoArgsConstructor;

@org.springframework.context.annotation.Configuration
@NoArgsConstructor
public class FreemarkerConfig {

    private static FreemarkerConfig freemarkerConfig;

    public static synchronized Configuration newFreeMarkerConfig() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setClassForTemplateLoading(FreemarkerConfig.class, "/templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

        return cfg;
    }
}
