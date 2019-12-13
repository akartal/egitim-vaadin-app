package com.uniyaz;

import javax.servlet.annotation.WebServlet;

import com.uniyaz.db.DbTransaction;
import com.uniyaz.domain.Person;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.uniyaz.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        FormLayout formLayout = new FormLayout();
        
        TextField nameField = new TextField();
        nameField.setCaption("Adı");
        formLayout.addComponent(nameField);

        TextField surnameField = new TextField();
        surnameField.setCaption("Soyadı");
        formLayout.addComponent(surnameField);

        EgitimTextField phoneField = new EgitimTextField();
        phoneField.setCaption("Telefon");
        formLayout.addComponent(phoneField);

        Button kaydet = new Button();
        kaydet.setCaption("Kaydet");
        kaydet.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {

                Person person = new Person();
                person.setName(nameField.getValue());
                person.setSurname(surnameField.getValue());
                person.setPhone(phoneField.getValue());

                DbTransaction dbTransaction = new DbTransaction();
                dbTransaction.addPerson(person);
                Notification.show("Kişi Eklendi");
            }
        });
        formLayout.addComponent(kaydet);
        
        setContent(formLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
