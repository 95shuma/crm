package com.project.crm.backend.services;


import com.project.crm.backend.dto.RoleDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void getAll() {

        //создаем тестовые данные
        RoleDTO p1 = new RoleDTO((long)1,"ROLE_ADMIN");
        RoleDTO p2 = new RoleDTO((long)2, "ROLE_SENIOR_DOCTOR");
        RoleDTO p3 = new RoleDTO((long)3,"ROLE_DOCTOR");
        RoleDTO p4 = new RoleDTO((long)4,"ROLE_JUNIOR_DOCTOR");
        RoleDTO p5 = new RoleDTO((long)5, "ROLE_PATIENT");

        //создаем список expected и заполняем его данными нашего метода
        List<RoleDTO> expected = roleService.getAll();

        List<RoleDTO> actual = new ArrayList<>();
        actual.add(p1);
        actual.add(p2);
        actual.add(p3);
        actual.add(p4);
        actual.add(p5);

        Assert.assertEquals(expected, actual);
    }

}
