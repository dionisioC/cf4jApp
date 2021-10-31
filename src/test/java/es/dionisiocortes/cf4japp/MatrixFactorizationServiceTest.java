package es.dionisiocortes.cf4japp;

import es.dionisiocortes.cf4japp.model.MatrixFactorizationTypes;
import es.dionisiocortes.cf4japp.service.MatrixFactorizationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MatrixFactorizationServiceTest {

    @Autowired
    private MatrixFactorizationService service;

    @Test
    @DisplayName("Given an incorrect algorithm then factorization fails")
    void given_incorrect_aggregation_algorithm_then_fails() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.getFactorization("Fake", 1, 1, 1));

        String expectedMessage = "Expected one of the followings algorithms: [BIASEDMF, URP, BEMF, NMF, PMF, BNMF, CLIMF, HPF, SVDPLUSPLUS] but Fake found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @DisplayName("Test all algorithms and approaches are working")
    void given_all_algorithms_and_approaches_they_work() {
        for (MatrixFactorizationTypes algorithm : MatrixFactorizationTypes.values()) {
            service.getFactorization(algorithm.toString(), 1, 1, 1);
        }
    }

}
