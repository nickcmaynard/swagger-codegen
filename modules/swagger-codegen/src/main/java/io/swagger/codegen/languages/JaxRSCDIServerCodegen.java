package io.swagger.codegen.languages;

import io.swagger.codegen.*;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;

import java.io.File;
import java.util.*;

public class JaxRSCDIServerCodegen extends JaxRSServerCodegen implements CodegenConfig {
    protected String title = "Swagger Server";

    public void init() {
      sourceFolder = "src/gen/java";
      invokerPackage = "io.swagger.api";
      artifactId = "swagger-jaxrs-cdi-server";

      outputFolder = System.getProperty("swagger.codegen.jaxrs-cdi.genfolder", "generated-code/javaJaxRSCDI");
      modelTemplateFiles.put("model.mustache", ".java");
      apiTemplateFiles.put("api.mustache", ".java");
      // apiTemplateFiles.put("apiService.mustache", ".java");
      apiTemplateFiles.put("apiService.mustache", ".java");
      // apiTemplateFiles.put("apiServiceFactory.mustache", ".java");
      embeddedTemplateDir = templateDir = "JavaJaxRSCDI";
      apiPackage = System.getProperty("swagger.codegen.jaxrs-cdi.apipackage", "io.swagger.api");
      modelPackage = System.getProperty("swagger.codegen.jaxrs-cdi.modelpackage", "io.swagger.model");

      additionalProperties.put(CodegenConstants.INVOKER_PACKAGE, invokerPackage);
      additionalProperties.put(CodegenConstants.GROUP_ID, groupId);
      additionalProperties.put(CodegenConstants.ARTIFACT_ID, artifactId);
      additionalProperties.put(CodegenConstants.ARTIFACT_VERSION, artifactVersion);
      additionalProperties.put("title", title);


      languageSpecificPrimitives = new HashSet<String>(
              Arrays.asList(
                      "String",
                      "boolean",
                      "Boolean",
                      "Double",
                      "Integer",
                      "Long",
                      "Float")
      );
    }

    public String getName() {
        return "jaxrs-cdi";
    }

    public String getHelp() {
        return "Generates a Java JAXRS CDI Server application.";
    }

    @Override
    public String apiFilename(String templateName, String tag) {

        String result = super.apiFilename(templateName, tag);

        if (templateName.endsWith("Service.mustache")) {
            int ix = result.lastIndexOf('/');
            result = result.substring(0, ix) + "/impl" + result.substring(ix, result.length() - 5) + "Service.java";

            String output = System.getProperty("swagger.codegen.jaxrs.impl.source");
            if(output == null) {
                output = "src" + File.separator + "main" + File.separator + "java";
            }
            result = result.replace(apiFileFolder(), implFileFolder(output));
        }

        return result;
    }

}
