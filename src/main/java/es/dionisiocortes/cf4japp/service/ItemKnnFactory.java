package es.dionisiocortes.cf4japp.service;

import es.dionisiocortes.cf4japp.model.ItemKnnTypes;
import es.upm.etsisi.cf4j.data.DataModel;
import es.upm.etsisi.cf4j.recommender.Recommender;
import es.upm.etsisi.cf4j.recommender.knn.ItemKNN;

public interface ItemKnnFactory {
    Recommender create(ItemKnnTypes type, Integer numNeighbors, DataModel dataModel, ItemKNN.AggregationApproach aggregationApproach);
}
