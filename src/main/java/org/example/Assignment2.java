// Student name: Koichi Nakata (ID: 1963595)

package org.example;

import javax.swing.*;

public class Assignment2 {
    private static String title = "Assignment 2";

    public static void main(String[] args) {


        while (true) {
            double loanAmount = (double)askUserInput(true);
            int years = askUserInput(false);

            int response = confirmInformation(loanAmount, years);

            if (response == JOptionPane.YES_OPTION) {
                displayResult(loanAmount, years);
                break;
            } else if (response == JOptionPane.CANCEL_OPTION) System.exit(0);
        }

    }

    private static int askUserInput(boolean isLoanAmount) {
        int value;

        String message = "";
        if (isLoanAmount)  message = "Please enter the loan amount ($):";
        else message = "Please enter the loan period (years):";

        while (true) {
            String inputStr = JOptionPane.showInputDialog(null, message,
                    title, JOptionPane.INFORMATION_MESSAGE);

            if (inputStr == null) System.exit(0);

            try {
                value = Integer.parseInt(inputStr);
                if (value > 0) break;

                JOptionPane.showMessageDialog(null, "Invalid entry.. Please enter a positive integer.",
                        title, JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid entry.. Please enter a positive integer.",
                        title, JOptionPane.ERROR_MESSAGE);
            }
        }
        return value;
    }

    private static int confirmInformation(double loanAmount, int years) {
        String message = String.format("The loan amount is $%6.2f, and the loan duration is %2d year(s)." +
                "\nContinue?", loanAmount, years);
        int response = JOptionPane.showConfirmDialog(null, message,
                title, JOptionPane.INFORMATION_MESSAGE);

        return response;

    }

    private static void displayResult(double loanAmount, int years) {
        String message = String.format(
                "\n%16s%22s%22s\n", "Interest Rate (%)", "Monthly Payment ($)", "Total Payment ($)");

        for (int i = 0; i < 25; i++) {
            double annualRate = 5 + 0.125 * i;
            double monthlyPay = calcMonthlyPayment(loanAmount, years, annualRate);
            double totalPay = calcTotalPayment(monthlyPay, years);

            message += String.format("%20.3f%30.2f%27.2f\n", annualRate, monthlyPay, totalPay);
        }

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private static double calcMonthlyPayment(double loanAmount, int years, double annualRate) {
        double monthlyRate = annualRate / 12 / 100;
        double top = loanAmount * monthlyRate;
        double bottom = 1 - Math.pow(1 + monthlyRate, -years * 12);

        return top / bottom;
    }

    private static double calcTotalPayment(double monthlyPay, int years) {
        return monthlyPay * years * 12;
    }
}