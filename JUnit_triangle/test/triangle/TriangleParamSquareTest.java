package triangle;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.*;

@RunWith(Parameterized.class)
public class TriangleParamSquareTest {
    public Triangle triangle;
    public boolean isValid;
	public double square;

  

    public TriangleParamSquareTest (int x, int y, int z, 
       double square) {
       this.triangle      = new Triangle(x, y, z);
       this.square = square;

    }

   @Parameters public static Collection<Object[]> parameters() {

        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {2, 2, 2, 1.73});
        list.add(new Object[] {3, 4, 5, 6});
        list.add(new Object[] {3, 3, 5, 4.14});
        list.add(new Object[] {5, 2, 6, 4.68});
        list.add(new Object[] {2, 7, 8, 6.43});
        list.add(new Object[] {3, 6, 5, 7.48});
        list.add(new Object[] {100, 101, 99, 4329.26});


        return list;
    }

   @Test public void TriangleSquareTest() { 
      assertEquals(triangle.getSquare(), square, 0.01); 
   }
}
