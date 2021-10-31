package es.dionisiocortes.cf4japp.service;

import es.dionisiocortes.cf4japp.model.Result;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class MatrixFactorizationService {
    public Collection<Result> getFactorization(String algorithm, Integer numFactors, Integer numIterations, Integer randomSeed) {
        return Collections.emptyList();
    }
}
