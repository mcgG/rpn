package app.mcgg.rpn;

import app.mcgg.rpn.exception.CalculatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RpnApplication implements CommandLineRunner {

    @Autowired
    private Calculator calculator;

    public static void main(String[] args) {
        SpringApplication.run(RpnApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter: ");

        while (scanner.hasNext()) {
            String formula = scanner.nextLine();
            try {
                calculator.eval(formula);
            } catch (CalculatorException e) {
            }
            System.out.println("Enter: ");
        }
//        String formula = "1 2 3 + sqrt 5 + 1 2 3 undo undo undo undo undo clear 3.2 9 /";
//        calculator.eval(formula);

    }
}
