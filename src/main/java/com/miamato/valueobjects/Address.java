package com.miamato.valueobjects;

import com.miamato.PropertyManager;

public class Address {
    public String name;
    public String lastName;
    public String company;
    public String addressLine1;
    public String addressLine2;
    public String city;
    public String state;
    public String postalCode;
    public String country;
    public String additionalInfo;
    public String homePhone;
    public String mobilePhone;
    public String addressAlias;

    public Address(PropertyManager propertyManager, String addressType) {
        this.name = propertyManager.getProperty(addressType + ".address.name");
        this.lastName = propertyManager.getProperty(addressType + ".address.last.name");
        this.company = propertyManager.getProperty(addressType + ".address.company");
        this.addressLine1 = propertyManager.getProperty(addressType + ".address.line1");
        this.addressLine2 = propertyManager.getProperty(addressType + ".address.line2");
        this.city = propertyManager.getProperty(addressType + ".address.city");
        this.state = propertyManager.getProperty(addressType + ".address.state");
        this.postalCode = propertyManager.getProperty(addressType + ".address.postal.code");
        this.country = propertyManager.getProperty(addressType + ".address.country");
        this.additionalInfo = propertyManager.getProperty(addressType + ".address.additional.info");
        this.homePhone = propertyManager.getProperty(addressType + ".address.home.phone");
        this.mobilePhone = propertyManager.getProperty(addressType + ".address.mobile.phone");
        this.addressAlias = propertyManager.getProperty(addressType + ".address.allias");
    }
}
