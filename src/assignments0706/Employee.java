package assignments0706;

import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private int age;
    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Employee temp = (Employee) o;
        return this.getId() == temp.getId()
                && this.getName().equals(temp.getName())
                && this.getAge() == temp.getAge();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name,this.age);
    }

    @Override
    public String toString() {
        return "Employee: " + this.id + "Name: " + this.name + "Age: " + this.age;
    }
}
