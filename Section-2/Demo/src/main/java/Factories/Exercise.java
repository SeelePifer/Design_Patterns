package Factories;

class Person
{
    public int id;
    public String name;

    public Person(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}

class PersonFactory
{
    private int id=0;
    public Person createPerson(String name)
    {
        return new Person(id++,name);
    }
}

 class Exercise {
     public static void main(String[] args) {
         PersonFactory pf = new PersonFactory();
         Person person = pf.createPerson("Luis");
         Person person2 = pf.createPerson("Andrea");
         System.out.println(person);
         System.out.println(person2);

     }
}
