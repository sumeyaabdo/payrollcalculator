package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class PayrollCalculatorApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Ask for the input file
        System.out.print("Enter the name of the file to process: ");
        String inputFile = scanner.nextLine();

        // Ask for the output file
        System.out.print("Enter the name of the payroll file to create: ");
        String outputFile = scanner.nextLine();

        try {
            // Open the employee file
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufReader = new BufferedReader(fileReader);

            // Create the payroll file
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            String input;

            // Read each employee
            while ((input = bufReader.readLine()) != null) {

                // Split employee information
                String[] tokens = input.split("\\|");

                int employeeId = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);

                // Create Employee object
                Employee employee = new Employee(employeeId, name, hoursWorked, payRate);

                // Write employee payroll information to the file
                String payroll = String.format("%d|%s|%.2f%n",
                        employee.getEmployeeId(),
                        employee.getName(),
                        employee.getGrossPay());

                bufWriter.write(payroll);
            }

            // Close files
            bufReader.close();
            bufWriter.close();

            System.out.println("Payroll file created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
