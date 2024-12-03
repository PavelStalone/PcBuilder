package com.example.pcbuilder.runner;

import com.example.pcbuilder.common.dbinitial.DbRandomWriter;
import com.example.pcbuilder.common.fake.ClassFiller;
import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.data.model.*;
import com.example.pcbuilder.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

//@Component
//public class DbInitRunner implements CommandLineRunner {
//
//    private static final Scanner scanner = new Scanner(System.in);
//
//    private final List<DbRandomWriter> randomWriters;
//
//    @Autowired
//    public DbInitRunner(List<DbRandomWriter> randomWriters) {
//        this.randomWriters = randomWriters;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Log.d("DataBase runner started");
//
//        System.out.println("Choose write mode: " +
//                "\n 1 - Auto fill" +
//                "\n 2 - Manually fill");
//
//        var input = scanner.nextInt();
//
//        switch (input) {
//            case 1 -> randomWriters.forEach((d) -> d.write(100));
//            case 2 -> System.out.println("Sorry this not be finished");
//            default -> System.out.println("I don`t know this command");
//        }
//
//        System.out.println("Done");
//    }
//}
