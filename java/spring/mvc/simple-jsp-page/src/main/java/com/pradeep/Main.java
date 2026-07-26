package com.pradeep;

import com.pradeep.config.WebConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.jasper.servlet.JasperInitializer;
import org.apache.jasper.servlet.JspServlet;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8020);
        tomcat.getConnector();

        String contextPath = "";
        String baseDoc = new File("src/main/webapps").getAbsolutePath();
        Context tomcatContext = tomcat.addContext(contextPath, baseDoc);

        tomcatContext.addServletContainerInitializer(new JasperInitializer(), Set.of());
        tomcat.addServlet(tomcatContext, "jsp", new JspServlet());
        tomcatContext.addServletMappingDecoded("*.jsp", "jsp");
        tomcatContext.addServletMappingDecoded("*.jspx", "jsp");

        AnnotationConfigWebApplicationContext applicationContext =
                new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        tomcat.addServlet(tomcatContext, "dispatcherServlet", dispatcherServlet);
        tomcatContext.addServletMappingDecoded("/", "dispatcherServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}