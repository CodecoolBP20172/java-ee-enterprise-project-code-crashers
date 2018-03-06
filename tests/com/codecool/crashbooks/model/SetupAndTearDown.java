package com.codecool.crashbooks.model;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SetupAndTearDown {
    static EntityManagerFactory emf;

    @BeforeAll
    static void setUp() {
        /*emf = Persistence.createEntityManagerFactory("CrashBooks_test");
        PopulateTestDb tDB = new PopulateTestDb();
        tDB.populateTestDB(emf);*/
    }

    @AfterAll
    static void tearDown() {
        emf.close();
    }
}
