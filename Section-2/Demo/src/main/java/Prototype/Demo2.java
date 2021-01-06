package Prototype;

class Adress{
    public String streetAddress,city,country;

    public Adress(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }
    public Adress(Adress other){
        this(other.streetAddress, other.city, other.country);
    }

    @Override
    public String toString() {
        return "Adress{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
class Employee{
    public String name;
    public Adress adress;

    public Employee(String name, Adress adress) {
        this.name = name;
        this.adress = adress;
    }
    public Employee(Employee other){
        name = other.name;
        adress = new Adress(other.adress);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", adress=" + adress +
                '}';
    }
}
class Demo2{
    public static void main(String[] args) {
        Employee john = new Employee("Jhon",
                new Adress("123 London Road","London","UK"));

        Employee chris = new Employee(john);
        chris.name= "Chris";
        System.out.println(john);
        System.out.println(chris);
    }
}
