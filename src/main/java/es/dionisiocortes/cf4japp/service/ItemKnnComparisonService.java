package es.dionisiocortes.cf4japp.service;

import es.dionisiocortes.cf4japp.model.ItemKnnTypes;
import es.dionisiocortes.cf4japp.model.Result;
import es.upm.etsisi.cf4j.data.BenchmarkDataModels;
import es.upm.etsisi.cf4j.data.DataModel;
import es.upm.etsisi.cf4j.qualityMeasure.QualityMeasure;
import es.upm.etsisi.cf4j.qualityMeasure.prediction.RMSE;
import es.upm.etsisi.cf4j.recommender.Recommender;
import es.upm.etsisi.cf4j.recommender.knn.ItemKNN;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class ItemKnnComparisonService {

    private final ItemKnnFactory factory;
    private final DataModel datamodel;

    public ItemKnnComparisonService(ItemKnnFactory factory) throws IOException {
        this.factory = factory;
        this.datamodel = BenchmarkDataModels.MovieLens100K();
    }

    private final Set<String> validAlgorithms = new HashSet<>(Arrays.asList("ADJUSTEDCOSINE",
            "CORRELATION",
            "COSINE",
            "JACCARD",
            "JMSD",
            "MSD",
            "PIP",
            "SINGULARITIES",
            "SPEARMANRANK"));

    public Result getComparison(String algorithm, Integer numNeighbors, String aggregationApproach) {

        Instant start = Instant.now();
        if (!aggregationApproach.equalsIgnoreCase("mean") && !aggregationApproach.equalsIgnoreCase("weighted_mean")) {
            throw new IllegalArgumentException("Expected mean or weighted_mean but " + aggregationApproach + " found");
        }

        if (!validAlgorithms.contains(algorithm.toUpperCase())) {
            throw new IllegalArgumentException("Expected one of the followings algorithms: " + validAlgorithms + " but " + algorithm + " found");
        }

        ItemKNN.AggregationApproach approach = ItemKNN.AggregationApproach.valueOf(aggregationApproach.toUpperCase());
        ItemKnnTypes type = ItemKnnTypes.valueOf(algorithm.toUpperCase());

        Recommender knn = factory.create(type, numNeighbors, this.datamodel, approach);

        knn.fit();

        QualityMeasure rmse = new RMSE(knn);
        double rmseScore = rmse.getScore();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        return new Result(rmseScore, timeElapsed.getNano());
    }
}
