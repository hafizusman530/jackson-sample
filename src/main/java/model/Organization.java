package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "employees",
        "name",
        "address",
        "phoneNumbers"
})
public class Organization {

    @JsonProperty("employees")
    private List<Employee> employees = new ArrayList<Employee>();
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("phoneNumbers")
    private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("employees")
    public List<Employee> getEmployees() {
        return employees;
    }

    @JsonProperty("employees")
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Organization withEmployees(List<Employee> employees) {
        this.employees = employees;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Organization withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    public Organization withAddress(Address address) {
        this.address = address;
        return this;
    }

    @JsonProperty("phoneNumbers")
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    @JsonProperty("phoneNumbers")
    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Organization withPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Organization withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}