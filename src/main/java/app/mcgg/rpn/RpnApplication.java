package app.mcgg.rpn;

import app.mcgg.rpn.factory.Execute;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RpnApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RpnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter: ");
        Execute t = new Execute();
        while (scanner.hasNext()) {
            String formula = scanner.nextLine();
            t.parseFormula(formula);
            System.out.println("Enter: ");
        }

    }
}
