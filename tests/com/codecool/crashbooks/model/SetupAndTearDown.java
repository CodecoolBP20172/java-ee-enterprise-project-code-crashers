package com.codecool.crashbooks.model;

import com.codecool.crashbooks.Main;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SetupAndTearDown {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CrashBooks");

    @BeforeAll
    static void setUp() {
        PopulateTestDb tDB = new PopulateTestDb();
        tDB.populateTestDB(emf);
    }

    @AfterAll
    static void tearDown() {
        emf.close();

    }
}
