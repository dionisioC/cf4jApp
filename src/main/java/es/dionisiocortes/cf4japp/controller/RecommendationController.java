package es.dionisiocortes.cf4japp.controller;

import es.dionisiocortes.cf4japp.model.Result;
import es.dionisiocortes.cf4japp.service.ItemKnnComparisonService;
import es.dionisiocortes.cf4japp.service.MatrixFactorizationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    MatrixFactorizationService matrixFactorizationService;
    ItemKnnComparisonService itemKnnComparisonService;

    public RecommendationController(MatrixFactorizationService matrixFactorizationService, ItemKnnComparisonService itemKnnComparisonService) {
        this.matrixFactorizationService = matrixFactorizationService;
        this.itemKnnComparisonService = itemKnnComparisonService;
    }

    @GetMapping("/matrixfactorization")
    public Collection<Result> getMatrixFactorization(@RequestParam String algorithm, @RequestParam Integer numFactors, @RequestParam Integer numIterations, @RequestParam Integer randomSeed) {
        return matrixFactorizationService.getFactorization(algorithm, numFactors, numIterations, randomSeed);
    }

    @GetMapping("/itemknncomparison")
    public Result getKnnComparison(@RequestParam String algorithm, @RequestParam Integer numNeighbors, @RequestParam String aggregationApproach) {
        return itemKnnComparisonService.getComparison(algorithm, numNeighbors, aggregationApproach);
    }

}
