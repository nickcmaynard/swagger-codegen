package io.swagger.codegen.options;

public class JaxRSCDIServerOptionsProvider extends JavaOptionsProvider {
    @Override
    public boolean isServer() {
        return true;
    }

    @Override
    public String getLanguage() {
        return "jaxrs-cdi";
    }
}
