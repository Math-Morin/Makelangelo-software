package com.marginallyclever.makelangelo.turtle;

import com.marginallyclever.convenience.Point2D;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TurtleTest {

    @Test
    public void empty() {
        // given
        Turtle turtle = new Turtle();

        // then
        assertEquals("[TOOL R0 G0 B0 A255 D1.000]", turtle.history.toString());
    }

    @Test
    public void travel() {
        // given
        Turtle turtle = new Turtle();

        // when
        turtle.penUp();
        turtle.moveTo(10, 12);
        turtle.moveTo(2, 3);

        // then
        assertEquals("[TOOL R0 G0 B0 A255 D1.000, TRAVEL X10.000 Y12.000, TRAVEL X2.000 Y3.000]", turtle.history.toString());
        assertFalse(turtle.getHasAnyDrawingMoves());
    }

    @Test
    public void penDownLinePenUp() {
        // given
        Turtle turtle = new Turtle();

        // when
        turtle.penDown();
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 12);
        turtle.penUp();
        turtle.moveTo(-15, -7);
        turtle.penUp();

        // then
        assertEquals("[TOOL R0 G0 B0 A255 D1.000, DRAW_LINE X20.000 Y30.000, DRAW_LINE X10.000 Y12.000, TRAVEL X-15.000 Y-7.000]", turtle.history.toString());
    }

    @Test
    public void moveAndDraw() {
        // given
        Turtle turtle = new Turtle();

        // when
        turtle.penDown();
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 15);

        // then
        assertEquals("[TOOL R0 G0 B0 A255 D1.000, DRAW_LINE X20.000 Y30.000, DRAW_LINE X10.000 Y15.000]", turtle.history.toString());
        assertTrue(turtle.getHasAnyDrawingMoves());
    }

    @Test
    public void angle() {
        // given
        Turtle turtle = new Turtle();

        // when
        turtle.setAngle(0);
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 15);
        turtle.turn(-45);
        turtle.forward(2);
        turtle.jumpTo(-15, -7);

        // then
        assertEquals("[TOOL R0 G0 B0 A255 D1.000, TRAVEL X20.000 Y30.000, TRAVEL X10.000 Y15.000, TRAVEL X11.414 Y13.586, TRAVEL X-15.000 Y-7.000]", turtle.history.toString());
        assertFalse(turtle.getHasAnyDrawingMoves());
    }

    @Test
    public void colorChange() {
        // given
        Turtle turtle = new Turtle(new Color(1,2,3));

        // when
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 15);
        turtle.setColor(new Color(4, 5, 6));
        turtle.jumpTo(-15, -7);

        // then
        assertEquals("[TOOL R1 G2 B3 A255 D1.000, TRAVEL X20.000 Y30.000, TRAVEL X10.000 Y15.000, TOOL R4 G5 B6 A255 D1.000, TRAVEL X-15.000 Y-7.000]", turtle.history.toString());
        assertEquals(new Color(1,2,3), turtle.getFirstColor());
    }

    @Test
    public void firstColor() {
        // given
        Turtle turtle = new Turtle(new Color(1,2,3));

        // then
        assertEquals("[TOOL R1 G2 B3 A255 D1.000]", turtle.history.toString());
    }

    @Test
    public void toolChange() {
        // given
        Turtle turtle = new Turtle(new Color(1,2,3));

        // when
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 15);
        turtle.setDiameter(10);

        // then
        assertEquals("[TOOL R1 G2 B3 A255 D1.000, TRAVEL X20.000 Y30.000, TRAVEL X10.000 Y15.000, TOOL R1 G2 B3 A255 D10.000]", turtle.history.toString());
    }

    @Test
    public void bounds() {
        // given
        Turtle turtle = new Turtle();

        // when
        turtle.penDown();
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 15);
        turtle.jumpTo(-15, -7);
        turtle.moveTo(3, 4);
        turtle.jumpTo(12, 18);

        // then
        Rectangle2D.Double r = turtle.getBounds();

        assertEquals(35, r.width);
        assertEquals(37, r.height);
        assertEquals(-15, r.x);
        assertEquals(-7, r.y);
    }

    @Test
    public void scale() {
        // given
        Turtle turtle = new Turtle();

        // when
        turtle.penDown();
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 15);
        turtle.jumpTo(-15, -7);
        turtle.moveTo(3, 4);
        turtle.jumpTo(12, 18);

        turtle.scale(2, 3);

        // then
        assertEquals("[TOOL R0 G0 B0 A255 D1.000, DRAW_LINE X40.000 Y90.000, DRAW_LINE X20.000 Y45.000, TRAVEL X-30.000 Y-21.000, DRAW_LINE X6.000 Y12.000, TRAVEL X24.000 Y54.000]", turtle.history.toString());
    }

    @Test
    public void translate() {
        // given
        Turtle turtle = new Turtle();

        // when
        turtle.penDown();
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 15);
        turtle.jumpTo(-15, -7);
        turtle.moveTo(3, 4);
        turtle.jumpTo(12, 18);

        turtle.translate(-10, 3);

        // then
        assertEquals("[TOOL R0 G0 B0 A255 D1.000, DRAW_LINE X10.000 Y33.000, DRAW_LINE X0.000 Y18.000, TRAVEL X-25.000 Y-4.000, DRAW_LINE X-7.000 Y7.000, TRAVEL X2.000 Y21.000]", turtle.history.toString());
    }

    @Test
    public void splitByToolChange() {
        // given
        Turtle turtle = new Turtle();

        // when
        turtle.penDown();
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 15);
        turtle.jumpTo(-15, -7);
        turtle.moveTo(3, 4);
        turtle.jumpTo(12, 18);
        turtle.setDiameter(2);
        turtle.jumpTo(-8, 4);
        turtle.moveTo(1, 6);

        // then
        List<Turtle> turtles = turtle.splitByToolChange();
        assertNotNull(turtles);
        assertEquals(2, turtles.size());
        assertEquals("[TOOL R0 G0 B0 A255 D1.000, DRAW_LINE X20.000 Y30.000, DRAW_LINE X10.000 Y15.000, TRAVEL X-15.000 Y-7.000, DRAW_LINE X3.000 Y4.000, TRAVEL X12.000 Y18.000]", turtles.get(0).history.toString());
        assertEquals("[TOOL R0 G0 B0 A255 D2.000, TRAVEL X-8.000 Y4.000, DRAW_LINE X1.000 Y6.000]", turtles.get(1).history.toString());
    }

    @Test
    public void addTurtle() {
        // given
        Turtle turtle = new Turtle();

        // when
        turtle.penDown();
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 15);
        turtle.jumpTo(-15, -7);
        turtle.moveTo(3, 4);

        Turtle turtle2 = new Turtle(new Color(6,7,8));
        turtle2.jumpTo(-8, 4);
        turtle2.moveTo(1, 6);

        turtle.add(turtle2);

        // then
        assertEquals("[TOOL R0 G0 B0 A255 D1.000, DRAW_LINE X20.000 Y30.000, DRAW_LINE X10.000 Y15.000, TRAVEL X-15.000 Y-7.000, DRAW_LINE X3.000 Y4.000, TOOL R6 G7 B8 A255 D1.000, TRAVEL X-8.000 Y4.000, DRAW_LINE X1.000 Y6.000]", turtle.history.toString());
    }

    @Test
    public void equalsTwoTurtles() {
        // given
        Turtle turtle = new Turtle();

        turtle.penDown();
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 15);
        turtle.jumpTo(-15, -7);
        turtle.moveTo(3, 4);

        Turtle turtle2 = new Turtle();
        turtle2.penDown();
        turtle2.moveTo(20, 30);
        turtle2.moveTo(10, 15);
        turtle2.jumpTo(-15, -7);
        turtle2.moveTo(3, 4);

        // then
        assertEquals(turtle, turtle2);
    }

    @Test
    public void notEqualsTwoTurtles() {
        // given
        Turtle turtle = new Turtle();
        turtle.penDown();
        turtle.moveTo(20, 30);
        turtle.moveTo(10, 15);
        turtle.jumpTo(-15, -7);
        turtle.moveTo(3, 4);

        Turtle turtle2 = new Turtle();
        turtle2.penDown();
        turtle2.moveTo(3, 4);

        // then
        assertNotEquals(turtle, turtle2);
    }


    @Test
    public void testInterpolate() {
        final double EPSILON = 1e-6;

        Turtle turtle = new Turtle();
        turtle.penDown();
        turtle.forward(1000);
        double d = turtle.getDrawDistance();
        assertEquals(1000,d);
        for(int i=0;i<=10;++i) {
            assertTrue(new Point2D(i * 100, 0).distance(turtle.interpolate(d*(double)i/10.0)) < EPSILON);
        }
    }

    /*
    Ce test vérifie que le constructeur de copie produit bien un objet Turtle considéré égal a celui passé en paramètre.
     */
    @Test
    public void turtleCopyConstructor() {
        Turtle initialTurtle = new Turtle();
        Color initialColor = Color.BLUE;
        double initialAngle = 12.3;
        double initialDiameter = 2.0;
        initialTurtle.setColor(initialColor);
        initialTurtle.setAngle(initialAngle);
        initialTurtle.setDiameter(initialDiameter);

        Turtle turtleCopy = new Turtle(initialTurtle);

        assertEquals(initialTurtle, turtleCopy);
    }

    /*
    Ce test vérifie que la fonction lock() vérouille bien la tortue.
     */
    @Test
    public void Lock() {
        Turtle turtle = new Turtle();
        boolean initialStateIsLocked = turtle.isLocked();

        turtle.lock();

        assertFalse(initialStateIsLocked);
        assertTrue(turtle.isLocked());
    }

    /*
    Ce test vérifie que la fonction unlock() dévérouille bien la tortue.
     */
    @Test
    public void Unlock() {
        Turtle turtle = new Turtle();
        turtle.lock();
        boolean initialStateIsLocked = turtle.isLocked();

        turtle.unlock();

        assertTrue(initialStateIsLocked);
        assertFalse(turtle.isLocked());
    }

    /*
    Ce test vérifie que la fonction getDiameter() retourne bien la bonne valeur.
     */
    @Test
    public void getDiameter() {
        Turtle turtle = new Turtle();
        double expectedDiameter = 3.4;
        turtle.setDiameter(expectedDiameter);

        double result = turtle.getDiameter();

        assertEquals(expectedDiameter, result);
    }

    /*
    Ce test vérifie que la fonction setX() change bien la coordonnée en x pour la valeur donnée.
     */
    @Test
    public void setX() {
        Turtle turtle = new Turtle();
        double newX = 1.2;

        turtle.setX(newX);

        assertEquals(newX, turtle.getX());
    }

    /*
    Ce test vérifie que la fonction setY() change bien la coordonnée en y pour la valeur donnée.
     */
    @Test
    public void setY() {
        Turtle turtle = new Turtle();
        double newY = 3.4;

        turtle.setY(newY);

        assertEquals(newY, turtle.getY());
    }
}
