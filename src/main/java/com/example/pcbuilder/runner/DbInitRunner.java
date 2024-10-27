package com.example.pcbuilder.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DbInitRunner implements CommandLineRunner {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello world!");
        System.out.println(scanner.nextLine());
    }
}
