package es.dionisiocortes.cf4japp.service;

import es.dionisiocortes.cf4japp.model.ItemKnnTypes;
import es.upm.etsisi.cf4j.data.DataModel;
import es.upm.etsisi.cf4j.recommender.Recommender;
import es.upm.etsisi.cf4j.recommender.knn.ItemKNN;
import es.upm.etsisi.cf4j.recommender.knn.itemSimilarityMetric.*;
import org.springframework.stereotype.Component;

@Component
public class ItemKnnFactoryImpl implements ItemKnnFactory {

    @Override
    public Recommender create(ItemKnnTypes type, Integer numNeighbors, DataModel dataModel, ItemKNN.AggregationApproach aggregationApproach) {

        return switch (type) {
            case ADJUSTEDCOSINE -> new ItemKNN(dataModel, numNeighbors, new AdjustedCosine(), aggregationApproach);
            case CORRELATION -> new ItemKNN(dataModel, numNeighbors, new Correlation(), aggregationApproach);
            case COSINE -> new ItemKNN(dataModel, numNeighbors, new Cosine(), aggregationApproach);
            case JACCARD -> new ItemKNN(dataModel, numNeighbors, new Jaccard(), aggregationApproach);
            case JMSD -> new ItemKNN(dataModel, numNeighbors, new JMSD(), aggregationApproach);
            case MSD -> new ItemKNN(dataModel, numNeighbors, new MSD(), aggregationApproach);
            case PIP -> new ItemKNN(dataModel, numNeighbors, new PIP(), aggregationApproach);
            case SINGULARITIES -> new ItemKNN(dataModel, numNeighbors, new Singularities(new double[]{3, 4, 5}, new double[]{1, 2}), aggregationApproach);
            case SPEARMANRANK -> new ItemKNN(dataModel, numNeighbors, new SpearmanRank(), aggregationApproach);
        };

    }
}
