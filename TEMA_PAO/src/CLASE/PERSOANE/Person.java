package CLASE.PERSOANE;

public abstract class Person {

    protected String Name;
    protected String Number;
    protected Integer age;

    public Person() {}

    public Person( String name, String number, Integer age) {
        this.Name = name;
        this.Number = number;
        this.age = age;
    }
    public abstract void scan();
    @Override
    public abstract String toString();
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getNumber() {
        return Number;
    }
    public void setNumber(String number) {
        Number = number;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
