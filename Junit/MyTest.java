// package fv;

/* Junit test class */
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class MyTest {
@Test
public void testSum() {
Sum mySum = new Sum(1, 1);
int sum = mySum.sum();
assertEquals(2, sum);
}
}