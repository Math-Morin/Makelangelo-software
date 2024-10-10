package com.marginallyclever.makelangelo.paper;

import com.marginallyclever.util.PreferencesHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;

public class PaperTest {
	@BeforeEach
	public void beforeEach() {
		PreferencesHelper.start();
	}

	@Test
	public void testPaperSettingChanges() {
		Paper a = new Paper();
		a.loadConfig();
		double w = a.getPaperWidth();
		double h = a.getPaperHeight();
		a.setPaperSize(w/2,h/2,0,0);
		a.saveConfig();
		Paper b = new Paper();
		b.loadConfig();
		Assertions.assertEquals(w/2, b.getPaperWidth());
		Assertions.assertEquals(h/2, b.getPaperHeight());
		a.setPaperSize(w,h,0,0);
		a.saveConfig();
		// TODO: this is a potentially destructive change if the test fails.
	}

	@Test
	public void testPaperLocation() {
		Paper a = new Paper();
		a.setPaperSize(200,100,0,0);
		a.setPaperMargin(0.9);

		Rectangle2D.Double rect = a.getMarginRectangle();		
		Assertions.assertEquals(180,rect.getWidth());
		Assertions.assertEquals(90,rect.getHeight());
		Assertions.assertEquals(-90,rect.getMinX());
		Assertions.assertEquals(-45,rect.getMinY());
		Assertions.assertEquals(90,rect.getMaxX());
		Assertions.assertEquals(45,rect.getMaxY());
		Assertions.assertEquals(0,a.getCenterX());
		Assertions.assertEquals(0,a.getCenterY());

		a.setPaperSize(200,100,50,100);
		rect = a.getMarginRectangle();
		Assertions.assertEquals(180,rect.getWidth());
		Assertions.assertEquals(90,rect.getHeight());
		Assertions.assertEquals(-90,rect.getMinX());
		Assertions.assertEquals(-45,rect.getMinY());
		Assertions.assertEquals(90,rect.getMaxX());
		Assertions.assertEquals(45,rect.getMaxY());
		Assertions.assertEquals(50,a.getCenterX());
		Assertions.assertEquals(100,a.getCenterY());
	}

// Teste la gestion des marges sur le papier
@Test 
public void testMargins() {
    Paper a = new Paper();
    a.setPaperSize(200,100,0,0);
    a.setPaperMargin(-0.1);
    Assertions.assertEquals(0, a.getPaperMargin());
    a.setPaperMargin(2);
    Assertions.assertEquals(1, a.getPaperMargin());
}

// Vérifie la position gauche du papier
@Test
public void testPaperLeft() {
    Paper a = new Paper();
    a.setPaperSize(200,100,0,0);
    Assertions.assertEquals(a.getPaperLeft(), -100);
}

// Vérifie la position droite du papier
@Test
public void testPaperRight() {
    Paper a = new Paper();
    a.setPaperSize(200,100,0,0);
    Assertions.assertEquals(a.getPaperRight(), 100);
}

// Vérifie la position supérieure du papier
@Test 
public void testPaperTop() {
    Paper a = new Paper();
    a.setPaperSize(200,100,0,0);
    Assertions.assertEquals(a.getPaperTop(), 50);
} 

// Vérifie la position inférieure du papier
@Test 
public void testPaperBottom() {
    Paper a = new Paper();
    a.setPaperSize(200,100,0,0);
    Assertions.assertEquals(a.getPaperBottom(), -50);
}


}
