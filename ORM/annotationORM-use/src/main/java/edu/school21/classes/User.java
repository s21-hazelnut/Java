package edu.school21.classes;

import java.util.*;
import edu.school21.HtmlForm;
import edu.school21.HtmlInput;
import edu.school21.*;

@HtmlForm(fileName = "User.html", action = "/users", method = "post")
@OrmEntity(table = "simple_user")
public class User {
    @OrmColumnId()
    private Long id;

    @HtmlInput(type = "text", name = "firstName", placeholder = "Enter firstName", thisClass = "User")
    @OrmColumn(name = "firstName", length = 10)
    private String firstName;
    @HtmlInput(type = "text", name = "lastName", placeholder = "Enter lastName", thisClass = "User")
    @OrmColumn(name = "lastName", length = 10)
    private String lastName;
    @HtmlInput(type= "number", name = "height", placeholder = "Enter height", thisClass = "User")
    @OrmColumn(name = "height")
    private java.lang.Integer height;

    public User() {
        this.firstName = "Default first name";
        this.lastName = "Default last name";
        this.height = 0;
    }

    public User(String firstName, String lastName, int height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
    }

    public int grow(int value) {
        this.height += value;
        return height;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", edu.school21.classes.User.class.getSimpleName() + "[", "]")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("height=" + height)
                .toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getHeight() {
        return height;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
