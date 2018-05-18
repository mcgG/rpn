package app.mcgg.rpn;

import app.mcgg.rpn.processor.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class RpnApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RpnApplication.class, args);
    }

    @Autowired
    ApplicationContext ctx;

    @Autowired
    Parser parser;

    @Value("${operator.enable}")
    private String[] operators;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(Arrays.toString(operators));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter: ");

        while (scanner.hasNext()) {
            String formula = scanner.nextLine();
            //t.parseFormula(formula);
            parser.parse(formula);

            System.out.println("Enter: ");
        }

    }
}
