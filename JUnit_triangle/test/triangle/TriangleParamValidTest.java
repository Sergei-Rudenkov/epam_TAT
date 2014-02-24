package triangle;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.*;

@RunWith(Parameterized.class)
public class TriangleParamValidTest {
    public Triangle triangle;
    public boolean isValid;
	public String message;

    public TriangleParamValidTest (int x, int y, int z, 
       boolean isValid, String message) {
       this.triangle      = new Triangle(x, y, z);
       this.isValid       = isValid;
       this.message       = message;
    }

   @Parameters public static Collection<Object[]> parameters() {

        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {2, 2, 0, false, "c<=0"});
        list.add(new Object[] {0, 0, 0, false, "a<=0 b<=0 c<=0"});
        list.add(new Object[] {2, 2, 4, false, "a+b<=c"});
        list.add(new Object[] {-3, 4, 5, false, "a<=0"});
        list.add(new Object[] {-3, -3, -3, false, "a<=0 b<=0 c<=0"});
        list.add(new Object[] {1, 2, 3, false, "a+b<=c"});
        list.add(new Object[] {2, 2, 2, true, ""});
        list.add(new Object[] {3, 4, 5, true, ""});


        return list;
    }

   @Test public void TriangleValidTest() { 
      assertEquals(triangle.checkTriangle(),   isValid); 
      assertEquals(triangle.getMessage(), message);
   }
}