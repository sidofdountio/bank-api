package com.bank_afriland;

import com.bank_afriland.project.model.Bank;
import com.bank_afriland.project.repo.BankRepository;
import com.bank_afriland.project.request.BranchRequest;
import com.bank_afriland.project.service.BranchService;
import com.bank_afriland.security.UserRole;
import com.bank_afriland.security.model.Users;
import com.bank_afriland.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AfrilandApplication {
    @Value("${application.image}")
    String DEFAULT_USER_IMAGE_PROFILE;

    public static void main(String[] args) {
        SpringApplication.run(AfrilandApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, BankRepository bankRepository,
                                        BranchService branchService) {
        return (args -> {
            var ADMIN = Users.builder().lastName("admin").firstName("admin").email("admin@gmail.com").password("$2a$12$phLQVs8MxIhnaaBsZn1MTu5BB3qsrFeuxA9heJFwkqsCEB3K7lTFe").phone("+237683234534").enable(true).accountLocked(false).role(UserRole.ADMIN).imageUrl(DEFAULT_USER_IMAGE_PROFILE).build();
//            ADMIN.setUserId(0L);
            userRepository.save(ADMIN);

            Bank AFRILAND = Bank.builder()
                    .bankCode("10005")
                    .name("AFRILAND FIRST BANK")
                    .email("qualite@aîrilandfirstbank.eom")
                    .phoneNumber("222 23 30 68 1 222 22 63 27")
                    .swiftCode("CCEICMCX")
                    .ribKey("04")
                    .build();
            bankRepository.save(AFRILAND);

            BranchRequest request = new BranchRequest(0L,"00034","FIRST BANK MENDONG","DERRIERE LE LYCEE MENDONG, YAOUNDE","qualite@aîrilandfirstbank.eom");
            branchService.createBranch(request,"10005");
        });
    }

}
