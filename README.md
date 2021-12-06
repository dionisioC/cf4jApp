# Análisis, mejora del código y pruebas de la librería CF4J: Collaborative Filtering for Java

![Master Cloud apps ](./imgs/masterCloudApps.png)

This repository has all the work done for the master final thesis of
"[Máster Cloud Apps. Desarrollo y despliegue de aplicaciones en la nube](https://www.codeurjc.es/mastercloudapps/)" of the Rey Juan Carlos university.

This final project has two main parts. One consists in the analysis and improvement of the [cf4j](https://github.com/ferortega/cf4j) library which is forked [here](https://github.com/dionisioC/cf4j).
The second part consist in the development of an app that wraps the library with a rest API in order to do some performance tests locally and in the cloud. 

The improvement has done using sonarQube to see the status of the project and mutation testing techniques to check the strength of the tests.

The app is a springboot 2.6.1 app with java 17 that uses [cf4j](https://github.com/ferortega/cf4j) library. To deploy the app there are [kubernetes files](./k8s) and a [helm chart](./helm/cf4jApp).

The load testing is done with Jmeter locally and with locust in the cloud. [Here](./src/main/resources/application.properties) is the Jmeter file and [here](./locust-image) is the locust image.

## Sync the original fork 

As the first part is not a greenfield project, we need to sync the forks. In order to do that

Check the remotes.

```bash
git remote -v
```

```
origin    git@github.com:dionisioC/cf4j.git (fetch)
origin    git@github.com:dionisioC/cf4j.git (push)
```

Add the forked remote 

```bash
git remote add upstream git@github.com:ferortega/cf4j.git
```

Check again the remote

```bash
git remote -v
```

```
origin    git@github.com:dionisioC/cf4j.git (fetch)
origin    git@github.com:dionisioC/cf4j.git (push)
upstream    git@github.com:ferortega/cf4j.git (fetch)
upstream    git@github.com:ferortega/cf4j.git (push)
```

We fetch the upstream

```bash
git fetch upstream
```

```
remote: Enumerating objects: 414, done.
remote: Counting objects: 100% (363/363), done.
remote: Compressing objects: 100% (113/113), done.
remote: Total 276 (delta 121), reused 251 (delta 97), pack-reused 0
Receiving objects: 100% (276/276), 5.90 MiB | 3.20 MiB/s, done.
Resolving deltas: 100% (121/121), completed with 53 local objects.
From github.com:ferortega/cf4j
* [new branch]      master     -> upstream/master
* [new branch]      snapshot   -> upstream/snapshot
* [new tag]         v2.1.2     -> v2.1.2
```

We do the merge and fix the conflicts

```bash
git merge upstream/master
```

```
Auto-merging src/test/java/es/upm/etsisi/cf4j/data/TestUserTest.java
Auto-merging src/test/java/es/upm/etsisi/cf4j/data/TestItemTest.java
Auto-merging src/main/java/es/upm/etsisi/cf4j/util/process/Parallelizer.java
Auto-merging src/main/java/es/upm/etsisi/cf4j/qualityMeasure/recommendation/Recall.java
Auto-merging src/main/java/es/upm/etsisi/cf4j/qualityMeasure/recommendation/Precision.java
Auto-merging src/main/java/es/upm/etsisi/cf4j/qualityMeasure/recommendation/NDCG.java
CONFLICT (content): Merge conflict in src/main/java/es/upm/etsisi/cf4j/qualityMeasure/recommendation/NDCG.java
Auto-merging src/main/java/es/upm/etsisi/cf4j/qualityMeasure/recommendation/F1.java
Auto-merging pom.xml
Automatic merge failed; fix conflicts and then commit the result.
```

## Mutation testing

After adding the needed dependencies into the original pom to perform mutation testing, we can run it with:

```bash
mvn clean test org.pitest:pitest-maven:mutationCoverage
```

## Local load testing

With the app running we execute jmeter as follows

```bash
jmeter -n -t src/main/resources/Matrix\ factorization.jmx -l results/result.csv -e -o results
```

If the report is not generated, we can do it manually

```bash
jmeter -g results/result.csv -o results/report
```


## Create the Google cloud cluster

First we initialize the gcloud app

```bash
gcloud init
```
Once we have all initialized, we need to grant permission to container.googleapis.com with:

```bash
gcloud services enable container.googleapis.com
```

We create a cluster with 3 to 10 nodes with:

```bash
gcloud container clusters create gke-load-test \
--zone us-central1-b \
--scopes "https://www.googleapis.com/auth/cloud-platform" \
--enable-autoscaling --min-nodes "3" --max-nodes "10" \
--scopes=logging-write,storage-ro \
--addons HorizontalPodAutoscaling,HttpLoadBalancing
```

We config our local kubectl with:

```bash
gcloud container clusters get-credentials gke-load-test \                                                                                     
--zone us-central1-b \
--project tfmurjc-331614
```

We add the locust image to the Google registry:

```bash
gcloud builds submit --tag gcr.io/tfmurjc-331614/locust-tasks:latest locust-image
```

With all this we have the cluster up and running, now we have to deploy the apps.

## Deploy kubernetes files

Inside the [kubernetes folder](./k8s) we perform:

```bash
kubectl apply -f cf4japp.yaml
kubectl apply -f locust-master.yaml
kubectl apply -f locust-worker.yaml
```

## Deploy with helm

Inside the [helm chart folder](./helm/cf4jApp) we perform:

```bash
helm install v1 .
```

To uninstall:

```bash
helm uninstall v1 .
```

To upgrade:

```bash
helm upgrade v1 .
```
