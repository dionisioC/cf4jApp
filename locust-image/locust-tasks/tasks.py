from locust import HttpUser, task, between

class MetricsTaskSet(HttpUser):
    wait_time = between(1, 2)

    @task
    def item_knn_comparison(self):
        for i in range(10):
            self.client.get("/recommendations/itemknncomparison?algorithm=ADJUSTEDCOSINE&numNeighbors=10000&aggregationApproach=mean", name="ADJUSTEDCOSINE")
            self.client.get("/recommendations/itemknncomparison?algorithm=CORRELATION&numNeighbors=10000&aggregationApproach=mean", name="CORRELATION")
            self.client.get("/recommendations/itemknncomparison?algorithm=COSINE&numNeighbors=10000&aggregationApproach=mean", name="COSINE")
            self.client.get("/recommendations/itemknncomparison?algorithm=JACCARD&numNeighbors=10000&aggregationApproach=mean", name="JACCARD")
            self.client.get("/recommendations/itemknncomparison?algorithm=JMSD&numNeighbors=10000&aggregationApproach=mean", name="JMSD")
            self.client.get("/recommendations/itemknncomparison?algorithm=MSD&numNeighbors=10000&aggregationApproach=mean", name="MSD")
            self.client.get("/recommendations/itemknncomparison?algorithm=SINGULARITIES&numNeighbors=10000&aggregationApproach=mean", name="SINGULARITIES")
            self.client.get("/recommendations/itemknncomparison?algorithm=SPEARMANRANK&numNeighbors=10000&aggregationApproach=mean", name="SPEARMANRANK")

