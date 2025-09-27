/*
 Author: Gina Asang Gana.
 Date: September 27, 2025.
 Lab Number: Lab 2 cohort A

 *Brief Explanation:
 This program simulates a health kiosk system where the user inputs
 their name, height, and weight. The program uses random characters and numbers to generate a unique ID.
 It calculates the BMI,determines the BMI category, and generates a unique health service code
  by combining elements from the ID, BMI, and other metrics depending on the health metric selected.
  The program demonstrates the use of user input, string manipulation,
  arithmetic operations, conditional statements, and random number generation in Java.
 */

import java.util.Scanner;
public class HealthKiosk {
    public static void main(String[] args) {
        System.out.println("\t\t\tWelcome to Ashesi Health Kiosk!!!!!");
        Scanner input = new Scanner(System.in);

        //Task 1

        //Prompting a user to enter a service code (P/L/T/C)
        System.out.print("Please enter service code(P/L/T/C): ");
        char serviceCode = input.next().charAt(0);

        //converting to Uppercase to handle case-insensitive handling
        serviceCode = Character.toUpperCase(serviceCode);

        String serviceDesk = switch (serviceCode) {
            case 'P' -> "Go to :Pharmacy Desk";
            case 'L' -> "Go to :Lab Desk";
            case 'T' -> "Go to :Triage Desk";
            case 'C' -> "Go to :Counseling Desk";
            default -> "Invalid Service code";
        };
        System.out.println(serviceDesk);

        //Task 2: Health Metric
        double bmiValue = 0;
        double metricValue = 0;

        System.out.print("Please enter Health Metric(1/2/3): ");
        int healthMetric = input.nextInt();

        switch(healthMetric){                            // BMI as the metric
            case 1:
                //getting user weight and height
            System.out.print("Please enter weight in kg: ");
            double weight = input.nextDouble();

            System.out.print("Please enter height in m: ");
            double height = input.nextDouble();

            //BMI Calculation
            double bmi = weight / Math.pow(height, 2);

            //rounding bmi to 1 dp and then finally to the nearest int for code display purposes
            bmiValue = Math.round(bmi * 10) / 10.0;
            metricValue = Math.round(bmiValue); // integer form for display code

            String category;
            if (bmiValue < 18.5){
                category = "Underweight";
            }
            else if (bmiValue <= 24.9) {
                category = "Normal";
            }

            else if (bmiValue <= 29.9){
                category = "Overweight";
            }
            else{
                category = "Obese";
            }
            System.out.printf("BMI: %.1f Category: %s", bmiValue, category);
            break;

            case 2:    //dosage round up
                System.out.print("Please enter required dosage in mg: ");
                double dosage = input.nextDouble();
                final int MAX_DOSAGE = 250;     // Note: the pharmacy dispenses tablets of 250 mg only
                int numberOfTablets = (int) (Math.ceil(dosage / MAX_DOSAGE));
                metricValue = numberOfTablets;
                System.out.printf("\nNo of tablets to get: %d", numberOfTablets);
                break;

            case 3:  //trig helper
                System.out.print("Please enter angle in degrees: ");
                double degrees = input.nextDouble();

                // Convert degrees to radians
                double angle = Math.toRadians(degrees);

                // Compute and round to 3 decimal places
                double sinValue = Math.round(Math.sin(angle) * 1000) / 1000.0;
                double cosValue = Math.round(Math.cos(angle) * 1000) / 1000.0;

                System.out.println("sin = " + sinValue);
                System.out.println("cos = " + cosValue);

                metricValue = Math.round(Math.sin(angle) *100);
                break;

            default:
                System.out.println("Invalid Health metric");
        }

        /*Task 3: ID Sanity check
        (focus: characters & strings)
        Student IDs at the kiosk are typed as a pattern: one letter followed by 4 digits
               (e.g., A1234). You’ll validate the format.*/

        // Generating a random character in uppercase
        char randomChar = (char)('A' + (int)(Math.random() * 26));

        //Generating 4 random numbers between 3 and 9 inclusive
        int randomNum1 = 3 + (int)(Math.random() * 7);
        int randomNum2 = 3 + (int)(Math.random() * 7);
        int randomNum3 = 3 + (int)(Math.random() * 7);
        int randomNum4 = 3 + (int)(Math.random() * 7);
        String idNumber = "" + randomChar + randomNum1 + randomNum2 + randomNum3 + randomNum4;

        System.out.printf("\nGenerated Id Number: %s",idNumber);

/*  Check the following:
o The length of the short code is 5
o charAt(0) is a letter (Character.isLetter)
o charAt(1) to char(4) are digits (Character.isDigit)*/

        if (idNumber.length() != 5){
            System.out.println("\nInvalid length");
        }
        else if (!(Character.isDigit(idNumber.charAt(1))&&   // A loop would have been applicable here but working according to constraint
                Character.isDigit(idNumber.charAt(2))&&
                Character.isDigit(idNumber.charAt(3))&&
                Character.isDigit(idNumber.charAt(1))
        )) {
            System.out.println("\nInvalid: last 4 must be digits");
        }
        else{
            System.out.println("\nID OK");
        }

/*Task 4: “Secure” Display Code (focus: char arithmetic & strings)
        Scenario: The kiosk shows a tiny, obfuscated code on the slip (not strong security—just to
        deter wandering eyes). Rules
         */

        //Taking  the first letter of the name, uppercase it.
        System.out.print("Please input your Name: ");
        String name = input.next();
        char firstLetter = Character.toUpperCase(name.charAt(0));

        // shift that letter forward by 2 positions in the alphabet (wrap Z→B).
        char letterPlus2 = (char)('A' + (firstLetter - 'A' + 2) % 26);
        String lastTwoChars = idNumber.substring(idNumber.length() - 2);

        String finalCode = letterPlus2 + lastTwoChars + "-" + (int) metricValue;
        System.out.printf("\nDisplay Code: %s",finalCode);


        //Task 5: summary
        String summary = switch (serviceCode) {
            case 'P' -> "\nPHARMACY | ID=" + idNumber + " | Code=" + finalCode;
            case 'L' -> "\nLAB | ID=" + idNumber + " | Code=" + finalCode;
            case 'T' -> "\nTRIAGE | ID=" + idNumber + " | BMI=" + bmiValue + " | Code=" + finalCode;
            case 'C' -> "\nCOUNSELING | ID=" + idNumber + " | Code=" + finalCode;
            default -> "\nSUMMARY NOT FOUND ";
        };
        System.out.printf("\nSummary: %s",summary);
        input.close();
    }
}

