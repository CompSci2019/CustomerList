package customerlist;

import java.io.*;
import javax.swing.*;

public class CustomerList {

    public static void main(String[] args) throws IOException {
        String name, address, city, province, postalCode = "";
        String myLine = "";
        String numberCustomers = "";
        String info;
        int numLine = 0;
        int numCustomers = 0;

        //Open file and display current list of customers
        
        BufferedReader readFile = new BufferedReader(new FileReader("customerList.txt"));

        //Print out anything that is already in customerList.txt to screen
        while ((myLine = readFile.readLine()) != null) {
            System.out.println(myLine);
        }

        readFile.close();

        //Open the file for writing
        PrintWriter fileOut = new PrintWriter(new FileWriter("customerList.txt", true));

        boolean isError = true;
        //ask user how many customers does he/she want to input with error guard
        while (isError) {
            try {
                numberCustomers = JOptionPane.showInputDialog("How many more customers would you like to input?");
                numCustomers = Integer.parseInt(numberCustomers);
                isError = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a number");

            }
        }
        for (int i = 0; i < numCustomers; i++) {
            //ask user for the information
            name = JOptionPane.showInputDialog("What is the name of customer#" + (i + 1) + "?");
            address = JOptionPane.showInputDialog("What is the address of customer#" + (i + 1) + "?");
            city = JOptionPane.showInputDialog("What city does customer#" + (i + 1) + " live in?");
            province = JOptionPane.showInputDialog("What province does the customer#" + (i + 1) + " live in?");

            //Error guard the postal code
            isError = true;
            while (isError) {
                postalCode = JOptionPane.showInputDialog("What is the postal code of customer#" + (i + 1) + "?" + "\n" + "(Please remember to NOT include any spaces in the postal code)");

                //Check if use entered a correct postal code
                if ((Character.isLetter(postalCode.charAt(0))) && (Character.isDigit(postalCode.charAt(1)))
                        && (Character.isLetter(postalCode.charAt(2))) && (Character.isDigit(postalCode.charAt(3)))
                        && (Character.isLetter(postalCode.charAt(4))) && (Character.isDigit(postalCode.charAt(5)))) {
                    JOptionPane.showMessageDialog(null, "That is a correct postal code, your information has now been saved to customerList.txt" + "\n" + "A preview of your personal information will be displayed below");
                    isError = false;
                } else {
                    JOptionPane.showMessageDialog(null, "That is an incorrect postal code, please try again");
                }
            }
            //add all the info together
            info = "Name: " + name + "\n"
                    + "Address: " + address + "\n"
                    + "City: " + city + "\n"
                    + "Province: " + province + "\n"
                    + "Postal Code: " + postalCode;

            //Print the info to the screen
            System.out.println(info);
            System.out.println("------------------------------------------");

            //Print the info to txt document
            fileOut.println(info);
            fileOut.println("------------------------------------------");
        }
        //Close the file
        fileOut.close();
    }
}
