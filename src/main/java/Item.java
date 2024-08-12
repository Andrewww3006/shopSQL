
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;

public class Item {
    private int id;
    private String name;
    private int price;
    public LocalDate date;

    public Item(){};

    Item (int id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(){
        this.date = LocalDate.now();
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

    public LocalDate getDate(){
        return date;
    }

    public void isExpensive(int price){
        Predicate<Integer> isExpensive = x -> x > 12;
        if (isExpensive.test(price))
            System.out.println("Это дорогая вещь");
        else System.out.println("Это дешевая вещь");
    }



}
