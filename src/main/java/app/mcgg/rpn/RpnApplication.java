package app.mcgg.rpn;

import app.mcgg.rpn.Test.factory.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
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

    @Override
    public void run(String... args) throws Exception {

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter: ");
        Execute t = new Execute();
        while (scanner.hasNext()) {
            String formula = scanner.nextLine();
            //t.parseFormula(formula);


            System.out.println("Enter: ");
        }

    }
}
