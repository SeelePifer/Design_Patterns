package Singleton;

import java.io.*;

class BasicSingleton implements Serializable {
    private BasicSingleton(){

    }
    private static final BasicSingleton INSTANCE=
            new BasicSingleton();
    public static BasicSingleton getInstance(){
        return INSTANCE;
    }
    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    protected Object readResolve(){ return INSTANCE;}
}


public class Demo {
    static void savetoFile(BasicSingleton singleton,
                           String filename) throws Exception
    {
        try(FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(singleton);
        }
    }
    static BasicSingleton readFromFile(String filename)
            throws Exception{
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn)){
            return (BasicSingleton) in.readObject();
        }
    }
    public static void main(String[] args) throws Exception {
        //1. Reflection
        //2. Serialization
        BasicSingleton instance = BasicSingleton.getInstance();
        instance.setValue(111);

        String filename = "Singleton.bin";
        savetoFile(instance,filename);

        instance.setValue(222);
        BasicSingleton singleton = readFromFile(filename);
        System.out.println(instance == singleton);
        System.out.println(instance.getValue());
        System.out.println(singleton.getValue());



    }
}
