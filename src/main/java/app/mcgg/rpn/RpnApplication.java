package app.mcgg.rpn;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.processor.Calculator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RpnApplication implements CommandLineRunner {

    private Calculator calculator;

    public RpnApplication(Calculator calculator) {
        this.calculator = calculator;
    }

    public static void main(String[] args) {
        SpringApplication.run(RpnApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please enter rpn formula: ");

        while (scanner.hasNext()) {
            String formula = scanner.nextLine();
            try {
                calculator.eval(formula);
            } catch (CalculatorException e) {
                // Exception has been already handle by Aspect
            }
//            System.out.println("Please enter rpn formula: ");
        }
    }
}
