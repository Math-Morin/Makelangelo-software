package com.marginallyclever.makelangelo.makeart.turtlegenerator;

import com.marginallyclever.convenience.Point2D;
import com.marginallyclever.makelangelo.Translator;
import com.marginallyclever.util.PreferencesHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TurtleGeneratorTest {

    @Test
    public void testNoMissingGeneratorPanels() {
        PreferencesHelper.start();
        Translator.start();
        try {
            testNothingMissingInTreeNode(TurtleGeneratorFactory.available);
        } catch (Exception e) {
            fail("Missing panel! " + e.getLocalizedMessage());
        }
    }

    public void testNothingMissingInTreeNode(TurtleGeneratorFactory.TurtleGeneratorNode node) {
        JMenu menu = new JMenu(node.getName());
        for (TurtleGeneratorFactory.TurtleGeneratorNode child : node.getChildren()) {
            if (child.getChildren().isEmpty()) {
                new TurtleGeneratorPanel(child.getGenerator());
            } else {
                testNothingMissingInTreeNode(child);
            }
        }
    }

// Teste la transformation d'un point avec la méthode trans()
    @Test 
    public void testTrans() {
        Generator_Polyeder.Transform transform = new Generator_Polyeder.Transform();
        Point2D pt = new Point2D(10, 20);
        Point2D ptTrans = transform.trans(pt);
        assertEquals(pt.x, ptTrans.x, 0.001);
        assertEquals(pt.y, ptTrans.y, 0.001);
    }

    // Teste la méthode walk() pour déplacer l'origine de la transformation
    @Test
    public void testWalk() {
        Generator_Polyeder.Transform transform = new Generator_Polyeder.Transform();
        Point2D pt = new Point2D(10, 20);
        transform.walk(pt);
        assertEquals(10, transform.org.x, 0.001);
        assertEquals(20, transform.org.y, 0.001);
        
        transform.dump();
    }

    // Teste la rotation de la transformation avec un angle donné
    @Test
    public void testRotate() {
        Generator_Polyeder.Transform transform = new Generator_Polyeder.Transform();
        transform.rotate(0.5);
        assertEquals(0.87758256189, transform.x_x, 0.001);
        assertEquals(0.4794255386, transform.x_y, 0.001);
        assertEquals(-0.4794255386, transform.y_x, 0.001);
        assertEquals(0.87758256189, transform.y_y, 0.001);
        transform.dump();
    }

    // Teste la duplication d'une transformation et vérifie que les valeurs sont identiques
    @Test
    public void testDup() {
        Generator_Polyeder.Transform transform = new Generator_Polyeder.Transform();
        Point2D pt = new Point2D(10, 20);
        transform.walk(pt);
        transform.rotate(0.5);
        Generator_Polyeder.Transform transformDup = transform.dup();
        assertEquals(transform.org.x, transformDup.org.x, 0.001);
        assertEquals(transform.org.y, transformDup.org.y, 0.001);
        assertEquals(transform.x_x, transformDup.x_x, 0.001);
        assertEquals(transform.x_y, transformDup.x_y, 0.001);
        assertEquals(transform.y_x, transformDup.y_x, 0.001);
        assertEquals(transform.y_y, transformDup.y_y, 0.001);
        
        transform.dump();
        transformDup.dump();
    }
    
}



