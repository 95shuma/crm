package com.project.crm.backend.dto.remediesDto;


import com.project.crm.backend.model.catalog.Remedy;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RemedyDTO {

    private Long id;
    private RemedyTypeDTO remedyType;
    private PharmacologicalGroupDTO pharmacologicalGroup;
    private InternationalNameDTO internationalName;
    private String name;
    private DosageDTO dosage;
    private RemediesFormDTO remediesForm;

    public static RemedyDTO from(Remedy remedy) {
        return builder()
                .id(remedy.getId())
                .remedyType(RemedyTypeDTO.from(remedy.getRemedyType()))
                .pharmacologicalGroup(PharmacologicalGroupDTO.from(remedy.getPharmacologicalGroup()))
                .internationalName(InternationalNameDTO.from(remedy.getInternationalName()))
                .name(remedy.getName())
                .dosage(DosageDTO.from(remedy.getDosage()))
                .remediesForm(RemediesFormDTO.from(remedy.getRemediesForm()))
                .build();
    }
}
