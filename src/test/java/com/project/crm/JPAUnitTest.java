package com.project.crm;

import com.project.crm.backend.model.catalog.RecordJournal;
import com.project.crm.backend.repository.RecordJournalRepo;
import com.project.crm.backend.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JPAUnitTest {

    @Autowired
    RecordJournalRepo recordJournalRepo;

    @Autowired
    UserRepo userRepo;

    @Test
    public void findAllTest() {

        Long userId = userRepo.findByInn(33333333333333L).get().getId();
        LocalDateTime startDate = LocalDate.now().atTime(6,0,0);
        LocalDateTime endDate = LocalDate.now().atTime(23,59,0);
        Pageable pageable =  PageRequest.of(0, 20);

        Page<RecordJournal> recordJournals = recordJournalRepo.findAllByDoctorIdAndDateTimeBetween(userId, startDate, endDate, pageable);

        assertThat(recordJournals.getContent()).isEmpty();
    }
}
