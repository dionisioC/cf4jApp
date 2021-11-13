# cf4jApp

cf4j Example app with spring


## Sync the fork

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

