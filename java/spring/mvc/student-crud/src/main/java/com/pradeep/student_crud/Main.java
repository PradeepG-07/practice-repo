package com.pradeep.student_crud;

import com.pradeep.student_crud.config.AppConfig;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        // Tomcat Config
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8000);

        tomcat.getConnector();

        String contextPath = "/student-crud";
        String baseDoc = new File("src/main/java/webapps").getAbsolutePath();

        Context tomcatContext = tomcat.addContext(contextPath, baseDoc);

        // IoC Container Config
        AnnotationConfigWebApplicationContext webApplicationContext
                = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(AppConfig.class);

        // Mvc Config - DispatcherServlet + Mapping
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);

        Tomcat.addServlet(tomcatContext, "dispatcherServlet", dispatcherServlet);
        tomcatContext.addServletMappingDecoded("/", "dispatcherServlet");

        // Start tomcat and listen for http requests on port 8000
        tomcat.start();

        tomcat.getServer().await();
    }
}