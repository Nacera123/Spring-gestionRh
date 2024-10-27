package getionRh.example.rh;

import getionRh.example.rh.service.implementation.candidature.CandidatureServiceImpl;

import org.junit.Test;
import org.springframework.boot.SpringApplication;

import static org.junit.Assert.*;

public class CandidatureTest {

    int nbPosteVcant = 5 ;
    int candidatureAccepte = 3;

    @Test
    public static void testCalculer() throws Exception {
        assertEquals("attention !  il y'a plus de candidats acceptés qu'il y'a de postes vacants",
                CandidatureServiceImpl.candidatueVsPosteVacant(5, 5));
    }

    public static void main(String[] args) {
        try {
            testCalculer();
            System.out.println("Test passed");
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        }
    }


}
