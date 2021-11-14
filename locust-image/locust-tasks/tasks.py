from locust import HttpUser, task, between

class MetricsTaskSet(HttpUser):
    wait_time = between(1, 2)

    @task(1)
    def adjusted_cosine(self):
        self.client.get("/recommendations/itemknncomparison?algorithm=ADJUSTEDCOSINE&numNeighbors=10000&aggregationApproach=mean", name="ADJUSTEDCOSINE")

    @task(2)
    def correlation(self):
        self.client.get("/recommendations/itemknncomparison?algorithm=CORRELATION&amp;numNeighbors=10000&amp;aggregationApproach=mean", name="CORRELATION")

    @task(3)
    def cosine(self):
        self.client.get("/recommendations/itemknncomparison?algorithm=COSINE&amp;numNeighbors=10000&amp;aggregationApproach=mean", name="COSINE")

    @task(4)
    def jaccard(self):
        self.client.get("/recommendations/itemknncomparison?algorithm=JACCARD&amp;numNeighbors=10000&amp;aggregationApproach=mean", name="JACCARD")

    @task(5)
    def jmsd(self):
        self.client.get("/recommendations/itemknncomparison?algorithm=JMSD&amp;numNeighbors=10000&amp;aggregationApproach=mean", name="JMSD")

    @task(6)
    def msd(self):
        self.client.get("/recommendations/itemknncomparison?algorithm=MSD&amp;numNeighbors=10000&amp;aggregationApproach=mean", name="MSD")

    @task(7)
    def singularities(self):
        self.client.get("/recommendations/itemknncomparison?algorithm=SINGULARITIES&amp;numNeighbors=10000&amp;aggregationApproach=mean", name="SINGULARITIES")

    @task(8)
    def spearmanrank(self):
        self.client.get("/recommendations/itemknncomparison?algorithm=SPEARMANRANK&amp;numNeighbors=10000&amp;aggregationApproach=mean", name="SPEARMANRANK")

