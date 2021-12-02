package bsuir.wt.lab3.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.StringJoiner;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "student", propOrder = {
        "id", "name", "surname", "age"
})
public class Student {
    private int id;
    private String name;
    private String surname;
    private int age;

    public Student() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("age=" + age)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;

        if (id != student.id) return false;
        if (age != student.age) return false;
        if (!name.equals(student.name)) return false;
        return surname.equals(student.surname);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + age;
        return result;
    }

    public static Student parse(String str) {
        var student = new Student();

        var paramsString = str.substring("Student".length(), str.length() - 1);
        var params = paramsString.split(", ");

        student.setId(Integer.parseInt(params[0].substring(params[0].indexOf("=") + 1)));
        student.setName(params[1].substring(params[1].indexOf("=") + 2, params[1].length() - 1));
        student.setSurname(params[2].substring(params[2].indexOf("=")  + 2, params[2].length() - 1));
        student.setAge(Integer.parseInt(params[3].substring(params[3].indexOf("=") + 1)));

        return student;
    }
}
