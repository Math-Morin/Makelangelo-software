package com.marginallyclever.convenience;
import com.marginallyclever.convenience.helpers.StringHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;


public class TestStringHelper {
    @Test
    public void testPaddedHex() {
        assertEquals("000001", StringHelper.paddedHex(0x1));
        assertEquals("0007ff",StringHelper.paddedHex(0x7ff));
        assertEquals("100001",StringHelper.paddedHex(0x100001));
        assertEquals("1000001",StringHelper.paddedHex(0x1000001));
        assertEquals("100000",StringHelper.paddedHex(0x100000));
    }

  // Teste que la méthode toString de la classe Point2D retourne bel et bien le
  // bon format.
  @Test 
  public void LineSegment2DToString(){
    final Point2D start = new Point2D(0.0,0.0);
    final Point2D end = new Point2D(1.0,1.0);
    final Color color = new Color(0,0,0);
    final LineSegment2D lineSegment = new LineSegment2D(start,end,color);
    assertEquals("(0.0,0.0)-(1.0,1.0)",lineSegment.toString());
}
}
