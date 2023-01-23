import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Order {

    public Order(ArrayList<Cupcake> cupcakeMenu, ArrayList<Drink> drinkMenu) {
        System.out.println("Hello customer. Would you like to place an order? (Y or N)");

        Scanner input = new Scanner(System.in);
        String placeOrder = input.nextLine();

        ArrayList<Object> order = new ArrayList<>();

        if (Character.toLowerCase(placeOrder.charAt(0)) == 'y') {
            order.add(LocalDate.now());
            order.add(LocalTime.now());
            System.out.println("Here is the menu.");

            System.out.println("CUPCAKES:");
            int itemNumber = 0;

            for (Cupcake cupcake : cupcakeMenu) {
                itemNumber++;
                System.out.print(itemNumber + ". ");
                cupcake.type();
                System.out.println("Price: $" + cupcake.getPrice());
                System.out.println();
            }

            System.out.println("DRINKS:");

            for (Drink drink : drinkMenu) {
                itemNumber++;
                System.out.print(itemNumber + ". ");
                drink.type();
                System.out.println("Price: $" + drink.getPrice());
                System.out.println();
            }

            boolean ordering = true;

            while (ordering) {
                System.out.println("What would you like to order? Please use the number associated with each item to order.");
                int orderChoice;

                try {
                    orderChoice = input.nextInt();
                } catch (InputMismatchException e) {
                    input.next();
                    System.out.println("That's not a valid selection. Please enter a number from 1 to 6 to select items.");
                    continue;
                }

                input.nextLine();

                if (orderChoice >= 1 && orderChoice <= 3) {
                    order.add(cupcakeMenu.get(orderChoice - 1));
                    System.out.println("Cupcake item #" + orderChoice + " added to order");
                } else if (orderChoice >= 4 && orderChoice <= 6) {
                    order.add(drinkMenu.get(orderChoice - 4));
                    System.out.println("Drink item #" + orderChoice + " added to order");
                } else {
                    System.out.println("Sorry, we don’t seem to have that on the menu.");

//                    System.out.println("Would you like to continue ordering? (Y/N)");
//                    placeOrder = input.nextLine();
//                    if (placeOrder.toLowerCase().charAt(0) != 'y') {
//                        ordering = false;
//                    }
                }
                System.out.println("Would you like to continue ordering? (Y/N)");
                placeOrder = input.nextLine();
                if (placeOrder.toLowerCase().charAt(0) != 'y') {
                    ordering = false;
                }
            }

            System.out.println("Date: " + order.get(0));
            System.out.println("Time: " + order.get(1));

            double subtotal = 0.0;

            for (int i = 2; i < order.size(); i++) {
                for (int c = 0; c < cupcakeMenu.size(); c++) {
                    if (order.get(i).equals(cupcakeMenu.get(c))) {
                        cupcakeMenu.get(c).type();
                        subtotal += cupcakeMenu.get(c).getPrice();
                    }
                    if (order.get(i).equals(drinkMenu.get(c))) {
                        drinkMenu.get(c).type();
                        subtotal += drinkMenu.get(c).getPrice();
                    }
                }
            }
            System.out.println("Your subtotal is: $" + subtotal + "\n");

            new CreateFile();
            new WriteToFile(order);

        } else {
            System.out.println("Have a nice day then.");
        }
        input.close();
    }
}

class CreateFile {
    public CreateFile() {
        try {
            File salesData = new File("salesData.txt");
            if (salesData.createNewFile()) {
                System.out.println("File created: " + salesData.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }
}

class WriteToFile {
    public WriteToFile(ArrayList<Object> order) {
        try {
            FileWriter fw = new FileWriter("salesData.txt", true);

            PrintWriter salesWriter = new PrintWriter(fw);

            for (Object o : order) {
                salesWriter.println(o);
            }

            salesWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred");
        }

    }
}
