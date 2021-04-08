package com.miamato.valueobjects;

import com.miamato.PropertyManager;

public class Customer {

    public String title;
    public String name;
    public String lastName;
    public String email;
    public String birthDay;
    public String birthMonth;
    public String birthYear;
    public String password;
    public Address address;

    public Customer(PropertyManager propertyManager, String customerType){
        this.title = propertyManager.getProperty(customerType + ".customer.title");
        this.name = propertyManager.getProperty(customerType + ".customer.name");
        this.lastName = propertyManager.getProperty(customerType + ".customer.last.name");
        this.birthDay = propertyManager.getProperty(customerType + ".customer.birth.day=");
        this.birthMonth = propertyManager.getProperty(customerType + ".customer.birth.month");
        this.birthYear = propertyManager.getProperty(customerType + ".customer.birth.year");
        this.password = propertyManager.getProperty(customerType + ".customer.password");
        this.address = new Address(propertyManager, customerType);
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAddress(Address address){
        this.address = address;
    }
}
