package es.dionisiocortes.cf4japp.service;

import es.dionisiocortes.cf4japp.model.MatrixFactorizationTypes;
import es.upm.etsisi.cf4j.data.DataModel;
import es.upm.etsisi.cf4j.recommender.Recommender;
import org.springframework.stereotype.Component;

@Component
public class MatrixFactorizationFactory {

    Recommender create(MatrixFactorizationTypes type, Integer numFactors, Integer numIterations, DataModel dataModel, Integer seed) {

        return switch (type) {
            case BEMF -> new es.upm.etsisi.cf4j.recommender.matrixFactorization.BeMF(dataModel, numFactors, numIterations, 0.01, 0.08, new double[]{1, 2, 3, 4, 5}, seed);
            case BIASEDMF -> new es.upm.etsisi.cf4j.recommender.matrixFactorization.BiasedMF(dataModel, numFactors, numIterations, seed);
            case BNMF -> new es.upm.etsisi.cf4j.recommender.matrixFactorization.BNMF(dataModel, numFactors, numIterations, 0.2, 10, seed);
            case CLIMF -> new es.upm.etsisi.cf4j.recommender.matrixFactorization.CLiMF(dataModel, numFactors, numIterations, seed);
            case HPF -> new es.upm.etsisi.cf4j.recommender.matrixFactorization.HPF(dataModel, numFactors, numIterations, seed);
            case NMF -> new es.upm.etsisi.cf4j.recommender.matrixFactorization.NMF(dataModel, numFactors, numIterations, seed);
            case PMF -> new es.upm.etsisi.cf4j.recommender.matrixFactorization.PMF(dataModel, numFactors, numIterations, seed);
            case SVDPLUSPLUS -> new es.upm.etsisi.cf4j.recommender.matrixFactorization.SVDPlusPlus(dataModel, numFactors, numIterations, seed);
            case URP -> new es.upm.etsisi.cf4j.recommender.matrixFactorization.URP(dataModel, numFactors, new double[]{1.0, 2.0, 3.0, 4.0, 5.0}, numIterations, seed);
        };


    }
}
