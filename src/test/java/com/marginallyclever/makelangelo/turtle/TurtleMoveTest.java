package com.marginallyclever.makelangelo.turtle;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TurtleMoveTest {

    /*
    Ce test s'assure que la fonction setColor() change correctement la couleur du TurtleMove pour celle désirée. Dans le
    contexte d'un "MOVEMENT_TYPE.TOOL_CHANGE", la couleur est contenue dans le champs "x" de l'objet. Cette valeur est
    initialisée à 1 dans le constructeur.
     */
    @Test
    public void setColor() { 
        TurtleMove turtleMove = new TurtleMove(1.0,2.0, MovementType.TRAVEL);
        Color expectedColor = Color.RED;

        turtleMove.setColor(expectedColor);
        Color result = turtleMove.getColor();

        assertEquals(expectedColor, result);
    }

   /*
   Ce test vérifie que la fonction setDiameter() change correctement le diamètre. Dans le contexte d'un
   "MOVEMENT_TYPE.TOOL_CHANGE", le diamètre est contenue dans la variable y. Cette valeur est initialisée à 2 dans le
   constructeur.
    */
    @Test
    public void setDiameter() {
        TurtleMove turtleMove = new TurtleMove(1,2, MovementType.TRAVEL);
        double expectedDiameter = 5.0;

        turtleMove.setDiameter(expectedDiameter);

        assertEquals(expectedDiameter, turtleMove.getDiameter());
    }

    /*
    Ce test vérifie que la fonction equals() retourne vrai lorsqu'un objet TurtleMove est comparé avec lui-meme.
     */
    @Test
    public void equalsSame() {
        TurtleMove turtleMove = new TurtleMove(1,2, MovementType.TRAVEL);

        assertTrue(turtleMove.equals(turtleMove));
    }

    /*
    Ce test vérifie que la fonction equals() retourne faux lorsqu'un objet TurtleMove est comparé avec un objet d'une
    classe différente.
     */
    @Test
    public void equalsDifferentClass() {
        TurtleMove turtleMove = new TurtleMove(1,2, MovementType.TRAVEL);
        Turtle turtle = new Turtle();

        assertFalse(turtleMove.equals(turtle));
    }

    /*
    Ce test vérifie que la fonction equals() retourne faux lorsqu'un objet TurtleMove est comparé avec un objet null.
     */
    @Test
    public void equalsNull() {
        TurtleMove turtleMove = new TurtleMove(1,2, MovementType.TRAVEL);
        TurtleMove turtleNull = null;

        assertFalse(turtleMove.equals(turtleNull));
    }

    /*
    Ce test vérifie que la fonction equals() retourne faux si les deux objets TurtlesMove diffèrent par leur type de
    mouvement.
     */
    @Test
    public void equalsDifferentType() {
        TurtleMove turtleMove = new TurtleMove(1,2, MovementType.TRAVEL);
        TurtleMove turtleMove1 = new TurtleMove(1,2, MovementType.DRAW_LINE);

        assertFalse(turtleMove.equals(turtleMove1));
    }

    /*
    Ce test vérifie que la fonction equals() retourne faux si les deux objets TurtlesMove diffèrent par leur coordonnées
    en x.
     */
    @Test
    public void equalsDifferentX() {
        TurtleMove turtleMove = new TurtleMove(1,2, MovementType.TRAVEL);
        TurtleMove turtleMove1 = new TurtleMove(2,2, MovementType.TRAVEL);

        assertFalse(turtleMove.equals(turtleMove1));
    }
    /*
    Ce test vérifie que la fonction equals() retourne faux si les deux objets TurtlesMove diffèrent par leur coordonnées
    en y.
     */
    @Test
    public void equalsDifferentY() {
        TurtleMove turtleMove = new TurtleMove(1,2, MovementType.TRAVEL);
        TurtleMove turtleMove1 = new TurtleMove(1,1, MovementType.TRAVEL);

        assertFalse(turtleMove.equals(turtleMove1));
    }

    /*
    Ce test vérifie que le constructeur retourne bien un nouvel objet TurtleMove qui est considéré égal à l'objet
    qui lui a été passé en paramètre.
     */
    @Test
    public void TurtleMove() {
        TurtleMove turtleMove = new TurtleMove(1,2, MovementType.TRAVEL);

        TurtleMove turtleMove1 = new TurtleMove(turtleMove);

        assertEquals(turtleMove, turtleMove1);
    }
}
