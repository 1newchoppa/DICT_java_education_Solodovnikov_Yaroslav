package CreditCalculator;

import java.util.Scanner;

public class CreditCalculator {

    public static void main(String[] args) {
        if (args.length > 0) {
            processCommandLine(args);
        } else {
            processInteractive();
        }
    }

    private static void processCommandLine(String[] args) {
        String type = "", principalStr = "", periodsStr = "", paymentStr = "", interestStr = "";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--type":
                    type = args[++i];
                    break;
                case "--principal":
                    principalStr = args[++i];
                    break;
                case "--periods":
                    periodsStr = args[++i];
                    break;
                case "--payment":
                    paymentStr = args[++i];
                    break;
                case "--interest":
                    interestStr = args[++i];
                    break;
                default:
                    break;
            }
        }

        if (type.isEmpty() || principalStr.isEmpty() || periodsStr.isEmpty() || paymentStr.isEmpty() || interestStr.isEmpty()) {
            System.out.println("Incorrect parameters");
            return;
        }

        double principal = Double.parseDouble(principalStr);
        int periods = Integer.parseInt(periodsStr);
        double payment = Double.parseDouble(paymentStr);
        double interestRate = Double.parseDouble(interestStr) / 100 / 12;

        if (type.equals("diff")) {
            calculateDifferentiatedPayments(principal, periods, interestRate);
        } else if (type.equals("annuity")) {
            calculateAnnuityPayment(principal, periods, interestRate);
        } else {
            System.out.println("Incorrect parameters");
        }
    }

    private static void processInteractive() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want to calculate?");
        System.out.println("type \"n\" for number of monthly payments,");
        System.out.println("type \"a\" for annuity monthly payment amount,");
        System.out.println("type \"p\" for loan principal,");
        System.out.println("type \"d\" for differentiated payments:");
        String choice = scanner.next();

        double principal = 0, monthlyPayment = 0, interestRate = 0;
        int numOfPayments = 0;

        switch (choice) {
            case "n":
                System.out.println("Enter the loan principal:");
                principal = scanner.nextDouble();
                System.out.println("Enter the monthly payment:");
                monthlyPayment = scanner.nextDouble();
                System.out.println("Enter the loan interest:");
                interestRate = scanner.nextDouble() / 100 / 12;

                if (principal <= 0 || monthlyPayment <= 0 || interestRate <= 0) {
                    System.out.println("Error: Please enter valid positive values for principal, monthly payment, and interest rate.");
                    return;
                }

                numOfPayments = (int) Math.ceil(Math.log(monthlyPayment / (monthlyPayment - interestRate * principal)) / Math.log(1 + interestRate));
                int years = numOfPayments / 12;
                int months = numOfPayments % 12;
                if (years > 0 && months > 0) {
                    System.out.println("It will take " + years + " years and " + months + " months to repay this loan!");
                } else if (years > 0) {
                    System.out.println("It will take " + years + " years to repay this loan!");
                } else {
                    System.out.println("It will take " + months + " months to repay this loan!");
                }
                break;

            case "a":
                System.out.println("Enter the loan principal:");
                principal = scanner.nextDouble();
                System.out.println("Enter the number of periods:");
                numOfPayments = scanner.nextInt();
                System.out.println("Enter the loan interest:");
                interestRate = scanner.nextDouble() / 100 / 12;

                if (principal <= 0 || numOfPayments <= 0 || interestRate <= 0) {
                    System.out.println("Error: Please enter valid positive values for principal, number of periods, and interest rate.");
                    return;
                }

                monthlyPayment = principal * (interestRate * Math.pow(1 + interestRate, numOfPayments)) / (Math.pow(1 + interestRate, numOfPayments) - 1);
                System.out.println("Your monthly payment = " + (int) Math.ceil(monthlyPayment) + "!");
                break;

            case "p":
                System.out.println("Enter the annuity payment:");
                monthlyPayment = scanner.nextDouble();
                System.out.println("Enter the number of periods:");
                numOfPayments = scanner.nextInt();
                System.out.println("Enter the loan interest:");
                interestRate = scanner.nextDouble() / 100 / 12;

                if (monthlyPayment <= 0 || numOfPayments <= 0 || interestRate <= 0) {
                    System.out.println("Error: Please enter valid positive values for monthly payment, number of periods, and interest rate.");
                    return;
                }

                principal = monthlyPayment * (1 - Math.pow(1 + interestRate, -numOfPayments)) / interestRate;
                System.out.println("Your loan principal = " + (int) Math.ceil(principal) + "!");
                break;

            case "d":
                System.out.println("Enter the loan principal:");
                principal = scanner.nextDouble();
                System.out.println("Enter the loan interest:");
                interestRate = scanner.nextDouble() / 100 / 12;

                if (principal <= 0 || interestRate <= 0) {
                    System.out.println("Error: Please enter valid positive values for principal and interest rate.");
                    return;
                }

                System.out.println("Enter the number of periods:");
                numOfPayments = scanner.nextInt();

                if (numOfPayments <= 0) {
                    System.out.println("Error: Please enter a valid positive value for the number of periods.");
                    return;
                }

                double totalPayments = 0;
                for (int i = 0; i < numOfPayments; i++) {
                    double payment = principal / numOfPayments + interestRate * (principal - principal * i / numOfPayments);
                    totalPayments += payment;
                    System.out.println("Payment " + (i + 1) + ": " + (int) Math.ceil(payment));
                }
                System.out.println("\nTotal payment: " + (int) Math.ceil(totalPayments));
                break;

            default:
                System.out.println("Error: Invalid option. Please select a valid option (n, a, p, d).");
                break;
        }

        scanner.close();
    }

    private static void calculateDifferentiatedPayments(double principal, int periods, double interestRate) {
        double totalPayments = 0;
        for (int i = 0; i < periods; i++) {
            double payment = principal / periods + interestRate * (principal - principal * i / periods);
            totalPayments += payment;
            System.out.println("Payment " + (i + 1) + ": " + (int) Math.ceil(payment));
        }
        System.out.println("\nTotal payment: " + (int) Math.ceil(totalPayments));
    }

    private static void calculateAnnuityPayment(double principal, int periods, double interestRate) {
        double monthlyPayment = principal * (interestRate * Math.pow(1 + interestRate, periods)) / (Math.pow(1 + interestRate, periods) - 1);
        System.out.println("Your monthly payment = " + (int) Math.ceil(monthlyPayment) + "!");
    }
}
