package com.config.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MainWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext sc) throws ServletException {
 
        AnnotationConfigWebApplicationContext root =
          new AnnotationConfigWebApplicationContext();
         
        root.scan("com");
        sc.addListener(new ContextLoaderListener(root));
 
        ServletRegistration.Dynamic appServlet =
          sc.addServlet("/", new DispatcherServlet(new GenericWebApplicationContext()));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
        appServlet.addMapping("/logout");
        appServlet.addMapping("/user");
        appServlet.addMapping("/admin/add");
        appServlet.addMapping("/admin/remove");
        appServlet.addMapping("/admin/update");
        appServlet.addMapping("/admin/show");


    }
}