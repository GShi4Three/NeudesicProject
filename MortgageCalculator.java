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
        // Calculculating monthly mortgage payment
        int loanLengthMonths = loanLengthYears * 12;
        double monthlyInterestRate = (annualInterestRate / 100) / 12;
        double monthlyMortgagePayment = principleAmount * (monthlyInterestRate * Math.pow((1 + monthlyInterestRate), loanLengthMonths)) / (Math.pow((1 + monthlyInterestRate), loanLengthMonths) - 1);

        scanner.close();
    }
}
