package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.PopulateTestDb;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SetupAndTearDownForServices {
    static EntityManagerFactory emf;

    @BeforeAll
    static void setUp() {
        emf = Persistence.createEntityManagerFactory("CrashBooks_test");
        PopulateTestDb tDB = new PopulateTestDb();
        tDB.populateTestDB(emf);
    }

    @AfterAll
    static void tearDown() {
        emf.close();
    }
}
