public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

class Cupcake {
    public double price;

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void type(){
        System.out.println("A basic, generic cupcake, with vanilla frosting");
    }
}

class RedVelvet extends Cupcake {
    public void type(){
        System.out.println("A red velvet based cupcake, with cream cheese frosting.");
    }
}

class Chocolate extends Cupcake {
    public void type(){
        System.out.println("A chocolate based cupcake, with chocolate frosting.");
    }
}
