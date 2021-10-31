package es.dionisiocortes.cf4japp;

import es.dionisiocortes.cf4japp.model.ItemKnnTypes;
import es.dionisiocortes.cf4japp.service.ItemKnnComparisonService;
import es.upm.etsisi.cf4j.recommender.knn.ItemKNN;
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
class ItemKnnComparisonServiceTest {

    @Autowired
    private ItemKnnComparisonService service;

    @Test
    @DisplayName("Given an incorrect aggregation approach then comparison fails")
    void given_incorrect_aggregation_approach_then_fails() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.getComparison("Fake", 1, "aggregation"));

        String expectedMessage = "Expected mean or weighted_mean but aggregation found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Given an incorrect algorithm approach then comparison fails")
    void given_incorrect_aggregation_algorithm_then_fails() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.getComparison("Fake", 1, ItemKNN.AggregationApproach.MEAN.toString()));

        String expectedMessage = "Expected one of the followings algorithms: [SINGULARITIES, SPEARMANRANK, JMSD, PIP, CORRELATION, JACCARD, COSINE, ADJUSTEDCOSINE, MSD] but Fake found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @DisplayName("Test all algorithms and approaches are working")
    void given_all_algorithms_and_approaches_they_work() {

        for (ItemKNN.AggregationApproach approach : ItemKNN.AggregationApproach.values()) {
            for (ItemKnnTypes algorithm : ItemKnnTypes.values()) {
                service.getComparison(algorithm.toString(), 1, approach.toString());
            }
        }
    }

}
