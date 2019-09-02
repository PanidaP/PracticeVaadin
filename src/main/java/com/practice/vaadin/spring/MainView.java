package com.practice.vaadin.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

//********************** How to Run on Intellij idea **********************//
//First time run use [spring-boot:run]
//later use [hammer] icon on left side of run [L>] icon
//then open google chrome for path localhost:[your port]/ *default port is 8080*
//I don't know more direction to run vaadin.
//************************************************************************//
//MainView.Class just extends VerticalLayout is will show like [ TextField ] v
//                                                             [  Button1  ] v
//                                                             [  Button2  ] v
//                                                        on web app
//If it extends HorizontalLayout is will show [ TextField ] > [  Button1  ] > [  Button2  ]
//* [v],[>] is flow display.

@Route("home")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) {
        //set width size in padding
        this.setWidth("75%");
        //Display Something
        add(new H2("Vaadin!! Test!!! :P"));
        //Create button [ Click me ] and display notification from MessageBean.Class
        Button button = new Button("Click me",
                e -> Notification.show(bean.getMessage()));
        add(button);

        //*require* import com.vaadin.flow.component.textfield.TextField; for ues getValue()
        //Create field => Please type something [ typing here... ]
        TextField field = new TextField("Please type something");
        //Create button [ Display Notice ] and display notification from TextField
        Button buttonTest1 = new Button("Display Notice",
                event ->  Notification.show(field.getValue()));
        //Create button [ Display Label ] and display Label from TextField
        Button buttonTest2 = new Button("Display Label",
                event -> add(new Label(field.getValue())));
        //add all to Layout
        //In this case they are add to VerticalLayout because MainView extends VerticalLayout
        //*if don't add it will not display on wep app,and may be have some error.*
        add(field);
        add(buttonTest1);
        add(buttonTest2);

        //Create FormLayout and component for add to layout
        FormLayout testFormLayout = new FormLayout();
        TextField nameTextField = new TextField("Name");
        nameTextField.setPlaceholder("Typing here...");
        DatePicker testDateField = new DatePicker("Date");
        Button testButton = new Button("Create");
        Checkbox checkBox = new Checkbox("Please type something on the text field ," +
                "then click on the button if you want to dis play.", false);
        checkBox.addClickListener(event -> testButton.setEnabled(true));
        testButton.addClickListener(event -> {
            if (nameTextField.isEmpty()){
                nameTextField.isRequired();
                nameTextField.setErrorMessage("Please type something!!");
                Notification.show(nameTextField.getErrorMessage());

            } else {
                add(new Label("Name: " + nameTextField.getValue() +
                        " Date: " + testDateField.getValue()));
                testButton.setEnabled(false);
                checkBox.clear();
            }
        });
        testButton.setEnabled(false);
        //add component to FormLayout
        testFormLayout.add(nameTextField);
        testFormLayout.add(testDateField);
        testFormLayout.add(testButton);
        testFormLayout.add(checkBox);
        //add FromLayout to Main Layout (VerticalLayout in this case)
        this.add(testFormLayout);

    }

}
