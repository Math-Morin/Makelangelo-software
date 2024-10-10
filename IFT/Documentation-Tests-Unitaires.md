# Documentation Tests Unitaires

Emeric Laberge 20220275
Mathieu Morin 20163634

## Classes TurtleMoveTest et TurtleTest
Une classe de test TurtleMoveTest a été ajoutée pour tester la classe TurtleMove, dont la couverture pouvait etre améliorée.
La classe TurtleTest existait déjà. Ces classes ont été choisies, car nous sommes familier avec le concept de tortue (IFT-1015),
ce qui peut aider à concevoir les tests.

### TurtleMoveTest
Tous les tests pour la classe TurtleMove se trouvent dans [TurtleMoveTest](../src/test/java/com/marginallyclever/makelangelo/turtle/TurtleMoveTest.java). <br>
Toutes les méthodes testées se trouvent dans [TurtleMove](../src/main/java/com/marginallyclever/makelangelo/turtle/TurtleMove.java).

#### Test 1 - setColor()
Ce test s'assure que la fonction setColor() change correctement la couleur du TurtleMove pour celle désirée. Dans le
contexte d'un "MOVEMENT_TYPE.TOOL_CHANGE", la couleur est contenue dans le champs "x" de l'objet. Cette valeur est
initialisée à 1 dans le constructeur.

<img src="images/setColor-Before.png">
<img src="images/setColor-After.png">

#### Test 2 - setDiameter()
Ce test vérifie que la fonction setDiameter() change correctement le diamètre. Dans le contexte d'un
"MOVEMENT_TYPE.TOOL_CHANGE", le diamètre est contenue dans la variable y. Cette valeur est initialisée à 2 dans le
constructeur.

<img src="images/setDiameter-Before.png">
<img src="images/setDiameter-After.png">

#### Test 3 - equalsSame()
La fonction equals a été sélectionnée, car plusieurs branches n'étaient pas couvertes.
Ce test vérifie que la fonction equals() retourne vrai lorsqu'un objet TurtleMove est comparé avec lui-meme.

<img src="images/setDiameter-Before.png">
<img src="images/setDiameter-1.png">

#### Test 4 - equalsDifferentClass()
Ce test vérifie que la fonction equals() retourne faux lorsqu'un objet TurtleMove est comparé avec un objet d'une classe différente.

<img src="images/equals-1.png">
<img src="images/equals-2.png">

#### Test 5 - equalsNull()
Ce test vérifie que la fonction equals() retourne faux lorsqu'un objet TurtleMove est comparé avec un objet null.

<img src="images/equals-2.png">
<img src="images/equals-3.png">

#### Test 6 - equalsDifferentType()
Ce test vérifie que la fonction equals() retourne faux si les deux objets TurtlesMove diffèrent par leur type de
mouvement.

<img src="images/equals-3.png">
<img src="images/equals-4.png">

#### Test 7 - equalsDifferentX()
Ce test vérifie que la fonction equals() retourne faux si les deux objets TurtlesMove diffèrent par leur coordonnées
en x.

<img src="images/equals-4.png">
<img src="images/equals-5.png">

#### Test 8 - equalsDifferentY()
Ce test vérifie que la fonction equals() retourne faux si les deux objets TurtlesMove diffèrent par leur coordonnées
en y.

<img src="images/equals-5.png">
<img src="images/equals-After.png">

#### Test 9 - TurtleMove()
Ce test vérifie que le constructeur retourne bien un nouvel objet TurtleMove qui est considéré égal à l'objet
qui lui a été passé en paramètre.

<img src="images/TurtleMove-Before.png">
<img src="images/TurtleMove-After.png">

### Classe TurtleTest
Tous les tests pour la classe Turtle se trouvent dans [TurtleTest](../src/test/java/com/marginallyclever/makelangelo/turtle/TurtleTest.java). <br>
Toutes les méthodes testées se trouvent __à la fin__ du fichier [Turtle](src/main/java/com/marginallyclever/makelangelo/turtle/Turtle.java).

#### Test 10 - turtleCopyConstructor()
Ce test vérifie que le constructeur de copie produit bien un objet Turtle considéré égal a celui passé en paramètre.

<img src="images/Turtle-Before.png">
<img src="images/Turtle-After.png">

#### Test 11 - lock()
Ce test vérifie que la fonction lock() vérouille bien la tortue.
<img src="images/lock-Before.png">
<img src="images/lock-After.png">

#### Test 12 - unlock()
Ce test vérifie que la fonction unlock() dévérouille bien la tortue.
<img src="images/unlock-Before.png">
<img src="images/unlock-After.png">

#### Test 13 - getDiameter()
Ce test vérifie que la fonction getDiameter() retourne bien la bonne valeur.
<img src="images/getDiameter2-Before.png">
<img src="images/getDiameter2-After.png">

#### Test 14 - setX()
Ce test vérifie que la fonction setX() change bien la coordonnée en x pour la valeur donnée.
<img src="images/setX-Before.png">
<img src="images/setX-After.png">

#### Test 15 - setY()
Ce test vérifie que la fonction setY() change bien la coordonnée en y pour la valeur donnée.
<img src="images/setY-Before.png">
<img src="images/setY-After.png">