import java.util.Scanner;

public class MortgageCalculator {

    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);

        double principleAmount, annualInterestRate;
        int loanLengthYears;
        // Prompting users for necessary values to calculate the monthly mortgage payment
        System.out.println("Input a value for the principle loan amount: ");
        principleAmount = scanner.nextDouble();

        System.out.println("Input a percentage for the annual interest rate: ");
        annualInterestRate = scanner.nextDouble();

        System.out.println("Input a value for the loan period in years: ");
        loanLengthYears = scanner.nextInt();
        // Calculating monthly mortgage payment
        int loanLengthMonths = loanLengthYears * 12;
        double monthlyInterestRate = (annualInterestRate / 100) / 12;
        double monthlyMortgagePayment = principleAmount * (monthlyInterestRate * Math.pow((1 + monthlyInterestRate), loanLengthMonths)) / (Math.pow((1 + monthlyInterestRate), loanLengthMonths) - 1);
        // Calculating other important static values to output in the terminal
        double totalInterest = (monthlyMortgagePayment * loanLengthMonths) - principleAmount;
        double totalPayment = principleAmount + totalInterest;

        System.out.println("\nThe monthly mortgage payment is $" + Math.round(monthlyMortgagePayment * 100) / 100.0);
        System.out.println("The number of monthly payments is " + loanLengthMonths);
        System.out.println("The total interest amount is $" + Math.round(totalInterest * 100) / 100.0);
        System.out.println("The total payment amount is $" + Math.round(totalPayment * 100) / 100.0);
        
        scanner.close();
    }
}
