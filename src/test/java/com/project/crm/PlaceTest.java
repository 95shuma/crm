package com.project.crm;

import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.repository.PlaceRepo;
import com.project.crm.backend.services.PlaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class PlaceTest {

    @Autowired
    private PlaceService placeService;

    @Test
    void test_method_for_place_uniqueness(){
        long[] placeArray = new long[placeService.getAll().size()];
        long j = placeArray.length-1;
        for (long i = 0; i < placeArray.length-1; i++){
                placeArray[(int) i] = Long.parseLong(placeService.getById((i+/*Id последнего элемента в таблице place-772*/772)+1).getCodePlace());
                if (placeArray[(int) i] != placeArray[(int) j]){
                    if (j == 1){
                        System.out.println("Successfully");
                        break;
                    }
                    else if (i == j-1){
                        j--;
                        i = 0;
                    }
                }
                else{
                    System.out.println(i + " - " + j);
                }
        }
    }

}
