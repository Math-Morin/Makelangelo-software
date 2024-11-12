# Tâche \#3: test sur divers environnements 

## Membres de l'équipe:
**Mathieu Morin 20163634**
**Emeric Laberge 20220275**

## Liste des flags utilisés:

1. **`-Xmx512m`** : Limite la mémoire du tas à 512 Mo, prévenant une utilisation excessive de mémoire et assurant des ressources équilibrées dans un environnement restreint.

2. **`-XX:+UseG1GC`** : Active le garbage collector G1, adapté aux applications nécessitant une faible latence et une gestion efficace des pauses mémoire.

3. **`-XX:+UseStringDeduplication`** : Élimine les chaînes de caractères en double pour économiser de la mémoire, utile dans les applications générant beaucoup de chaînes identiques.

4. **`-XX:+UseCompressedOops`** : Compresse les pointeurs d'objet pour réduire la consommation de mémoire, idéal pour les tas jusqu'à 32 Go en augmentant l'efficacité sans perte de performance.

5. **`-XX:+HeapDumpOnOutOfMemoryError`** : Produit un dump du tas en cas d’erreur de mémoire, utile pour diagnostiquer et analyser les problèmes de mémoire après une panne.

6. **`-XX:+PrintGCDetails`** : Affiche des détails sur les opérations du GC, aidant à surveiller et optimiser les performances de gestion de la mémoire.


## Modifications apportées au code source:
Pour effectuer la tâche, nous avons modifié le fichier `./.github/workflows/test.yml` et le fichier `./.github/workflows/maven.yml` 
pour ajouter des actions qui exécutent les tests avec les flags spécifiés. 

Dans le fichier `./.github/workflows/test.yml` et
`./.github/workflows/maven.yml`, nous avons ajouté dans la section "jobs ->
build" le code suivant en dessous de la ligne "runs-on: ubuntu-latest":

### Code ajouté dans les 2 fichiers:
Nous avons ajouté dans la section "jobs -> build":
```yaml
    strategy:
      matrix:
        jvm-flags:
          - "-Xmx512m"
          - "-XX:+UseG1GC"
          - "-XX:+UseStringDeduplication"
          - "-XX:+UseCompressedOops"
          - "-XX:+HeapDumpOnOutOfMemoryError"
          - "-XX:+PrintGCDetails"
```
Nous avons également ajouté dans la section "jobs -> build -> steps":

```yaml
    - name: Print JVM Flags
      run: 'echo "Current JVM flags : $MAVEN_OPTS"'
```
et toujours dans la section "jobs -> build -> steps", nous avons ajouté :

```yaml
    - name: Print Coverage
      run: 'echo "Coverage: $COVERAGE%"'
```

### Code ajouté seulement dans le fichier `./.github/workflows/maven.yml`:
Dans la section "jobs -> build -> steps", nous avons ajouté une variable qui
permet de faire en sorte que différents jar soient de nom différent pour éviter
un conflit lors de l'éxecution des actions:
```yaml
    - name: Set Sanitized JVM Flag
      run: |
        SANITIZED_JVM_FLAG=$(echo "${{ matrix.jvm-flags }}" | sed 's/[^a-zA-Z0-9]/_/g')
        echo "SANITIZED_JVM_FLAG=${SANITIZED_JVM_FLAG}" >> $GITHUB_ENV
```
Et nous avons remplacer cette section:

```yaml
    - name: Upload artifact for package
      uses: actions/upload-artifact@v4.3.3
      with:
        name: jar
        retention-days: 1
        path: |
          src/main/package/jpackage*
          src/main/package/logo*
          LICENSE
          README
          target/package/*
```

par ceci:
```yaml
    - name: Upload artifact for package
      uses: actions/upload-artifact@v4.3.3
      with:
        name: jar-${{ env.SANITIZED_JVM_FLAG }}
        retention-days: 1
        path: |
          src/main/package/jpackage*
          src/main/package/logo*
          LICENSE
          README
          target/package/*
```

### Code ajouté seulement dans le fichier `./.github/workflows/test.yml`:
Aucun code n'a été ajouté uniquement dans ce fichier, les modifications
apportées sont celles mentionnées dans la section "Code ajouté dans les 2
fichiers".

## Comment voir les résultats des tests:

Pour voir les résultats des tests, dans la section "Actions" de ce dépôt,
cliquez sur le dernier test de la branche principale.

Pour chaque action: 
- Les flags sont affichés dans le log intitulé "Print JVM Flags".
- La couverture de code est affichée dans le log intitulé "Print Coverage".

## Notes supplémentaires: 
La partie **Matrix:package** de l'action **maven.yml** indique les 3 jobs
échouent mais en réalité c'était déjà le cas avant l'ajout des flags. C'est un
problème du repo distant qui n'a pas été corrigé.





