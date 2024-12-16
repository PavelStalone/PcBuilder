package com.example.pcbuilder.runner;

import com.example.pcbuilder.common.dbinitial.DbRandomWriter;
import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.domain.entity.Role;
import com.example.pcbuilder.domain.repository.other.contract.UserRoleRepository;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class DbInitRunner implements CommandLineRunner {

    private static final Scanner scanner = new Scanner(System.in);

    private final List<DbRandomWriter> randomWriters;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public DbInitRunner(List<DbRandomWriter> randomWriters, UserRoleRepository userRoleRepository) {
        this.randomWriters = randomWriters;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Log.d("DataBase runner started");

        initRoles();
        randomWriters.forEach((d) -> d.write(50));

        System.out.println("Done");
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var adminRole = new Role(UserRoles.ADMIN);
            var normalUserRole = new Role(UserRoles.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(normalUserRole);
        }
    }
}
