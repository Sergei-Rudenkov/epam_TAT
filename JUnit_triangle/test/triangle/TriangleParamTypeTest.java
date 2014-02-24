package triangle;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.*;

@RunWith(Parameterized.class)
public class TriangleParamTypeTest {
    public Triangle triangle;
    public boolean isValid;
	public int type;
	
	final static int TR_EQUILATERAL = 1; 
	final static int TR_ISOSCELES = 2;   
	final static int TR_ORDYNARY = 4;    
	final static int TR_RECTANGULAR = 8;

  

    public TriangleParamTypeTest (int x, int y, int z, 
       int TYPE) {
       this.triangle      = new Triangle(x, y, z);
       this.type = TYPE;

    }

   @Parameters public static Collection<Object[]> parameters() {

        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {2, 2, 2, TR_EQUILATERAL});
        list.add(new Object[] {3, 4, 5, TR_RECTANGULAR});
        list.add(new Object[] {3, 3, 5, TR_ISOSCELES});
        list.add(new Object[] {5, 2, 6, TR_ORDYNARY});
        list.add(new Object[] {10, 9, 2,TR_ORDYNARY});
        list.add(new Object[] {2, 7, 8, TR_ORDYNARY});
        list.add(new Object[] {3, 6, 5, TR_ORDYNARY});
        list.add(new Object[] {100, 101, 99, TR_ORDYNARY});


        return list;
    }

   @Test public void TriangleTypeTest() { 
      assertEquals(triangle.detectTriangle(), type); 
   }
}