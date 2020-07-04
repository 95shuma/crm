package com.project.crm.backend.services;



import com.project.crm.backend.model.catalog.remediesCatalog.*;
import com.project.crm.backend.repository.*;
import com.project.crm.frontend.forms.RemedyRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemedyServiceTest {
    @InjectMocks
    private RemedyService remedyService;

    @Mock
    RemedyTypeRepo remedyTypeRepo;
    @Mock
    PharmacologicalGroupRepo pharmacologicalGroupRepo;
    @Mock
    MeasureRepo measureRepo;
    @Mock
    InternationalNameRepo internationalNameRepo;
    @Mock
    DosageRepo dosageRepo;
    @Mock
    RemediesFormRepo remediesFormRepo;

    private RemedyRegisterForm remedyRegisterForm;
    private String correctName;
    private String testString;
    private String testGroup;
    private String testIntName;
    private String testDosage;
    private String testForm;



    @Before
    public void setUp(){
        remedyRegisterForm = new RemedyRegisterForm();
        correctName = "арбидол";
        testString = "бады";
        testGroup="антибиотики";
        testIntName="арбидол";
        testDosage="250 mg";
        testForm="tabl";
    }
    @Test
    public void createRemedy_saveCorrectRemedy_expectSave(){
        when(remedyTypeRepo.findById((long) 1)).thenReturn(Optional.of(RemedyType.builder()
                .id((long) 1)
                .name(testString)
                .build())
        );
        when(pharmacologicalGroupRepo.findById((long) 1)).thenReturn(Optional.of(PharmacologicalGroup.builder()
                .id((long) 1)
                .name(testGroup)
                .build())
        );

        when(internationalNameRepo.findById((long) 1)).thenReturn(Optional.of(InternationalName.builder()
                .id((long) 1)
                .name(testIntName)
                .build())
        );
        when(remediesFormRepo.findById((long) 1)).thenReturn(Optional.of(RemediesForm.builder()
                .id((long) 1)
                .name(testForm)
                .build())
        );
     /*   when(DosageRepo.findById((long) 1)).thenReturn(Optional.of(Dosage.builder()
                .id((long) 1)
                .name(testDosage)
                .build())
        );*/
        remedyRegisterForm.setRemedyTypeId((long)1);
        remedyRegisterForm.setPharmacologicalGroupId((long) 1);
        remedyRegisterForm.setInternationalNameId((long) 1);
        remedyRegisterForm.setName(correctName);
        remedyRegisterForm.setDosageId((long) 1);
        remedyRegisterForm.setRemediesFormId((long) 1);
        remedyService.createRemedy(remedyRegisterForm);
    }
}