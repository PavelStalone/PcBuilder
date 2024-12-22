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

@Component
public class DbInitRunner implements CommandLineRunner {

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

        Log.d("DataBase runner finished");
    }

    private void initRoles() {
        var itemCounts = userRoleRepository.count();

        if (itemCounts == 0) {
            Log.d("Role not found, start writing...");

            var adminRole = new Role(UserRoles.ADMIN);
            var normalUserRole = new Role(UserRoles.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(normalUserRole);

            Log.d("Role finished");
        } else {
            Log.d("Role already have %d items, skipping...", itemCounts);
        }
    }
}
