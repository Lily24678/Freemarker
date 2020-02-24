package freemarker.demo.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.demo.domain.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerDemo1Controller {
	public static void main(String[] args) throws IOException, TemplateException {
		dataModel();
	}
	
	public static void dataModel() throws IOException, TemplateException {
        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File("F:\\workspace\\freemarker\\src\\main\\resources\\ftl"));
        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);



        /* Create a data-model */
        Map<String,Object> root = new HashMap<String, Object>();
        root.put("user", "Big Joe");
        Product latest = new Product();
        latest.setUrl("products/greenmouse.html");
        latest.setName("green mouse");
        root.put("latestProduct", latest);
        root.put("item", "ABCDEFG");

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("freemarker_demo1.ftl");

        /* Merge data-model with template */
        FileWriter writer = new FileWriter(new File("F:/TEST.HTML"));
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, writer);
	}

}
