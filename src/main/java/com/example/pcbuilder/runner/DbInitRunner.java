package com.example.pcbuilder.runner;

import com.example.pcbuilder.common.dbinitial.DbRandomWriter;
import com.example.pcbuilder.common.fake.ClassFiller;
import com.example.pcbuilder.common.log.Log;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CpuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class DbInitRunner implements CommandLineRunner {

    private static final Scanner scanner = new Scanner(System.in);

    private final List<DbRandomWriter> randomWriters;

    @Autowired
    private ClassFiller<CpuDto> cpuFiller;

    @Autowired
    public DbInitRunner(List<DbRandomWriter> randomWriters) {
        this.randomWriters = randomWriters;
    }

    @Override
    public void run(String... args) throws Exception {
        Log.d("DataBase runner started");

        randomWriters.forEach((d) -> d.write(50));

        System.out.println("Done");
    }
}
