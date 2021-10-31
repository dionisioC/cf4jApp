package es.dionisiocortes.cf4japp.service;

import es.dionisiocortes.cf4japp.model.MatrixFactorizationTypes;
import es.dionisiocortes.cf4japp.model.Result;
import es.upm.etsisi.cf4j.data.BenchmarkDataModels;
import es.upm.etsisi.cf4j.data.DataModel;
import es.upm.etsisi.cf4j.qualityMeasure.QualityMeasure;
import es.upm.etsisi.cf4j.qualityMeasure.prediction.RMSE;
import es.upm.etsisi.cf4j.recommender.Recommender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatrixFactorizationService {

    private final Set<String> validAlgorithms = Arrays.stream(MatrixFactorizationTypes.values()).map(matrixFactorizationTypes -> matrixFactorizationTypes.toString().toUpperCase()).collect(Collectors.toSet());

    private final MatrixFactorizationFactory factory;
    private final DataModel datamodel;

    public MatrixFactorizationService(MatrixFactorizationFactory factory) throws IOException {
        this.factory = factory;
        this.datamodel = BenchmarkDataModels.MovieLens100K();
    }

    public Result getFactorization(String algorithm, Integer numFactors, Integer numIterations, Integer randomSeed) {


        Instant start = Instant.now();

        if (!validAlgorithms.contains(algorithm.toUpperCase())) {
            throw new IllegalArgumentException("Expected one of the followings algorithms: " + validAlgorithms + " but " + algorithm + " found");
        }

        MatrixFactorizationTypes type = MatrixFactorizationTypes.valueOf(algorithm.toUpperCase());

        Recommender matrixFactorization = factory.create(type, numFactors, numIterations, this.datamodel, randomSeed);

        matrixFactorization.fit();

        QualityMeasure rmse = new RMSE(matrixFactorization);
        double rmseScore = rmse.getScore();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        return new Result(rmseScore, timeElapsed.getNano());
    }
}
