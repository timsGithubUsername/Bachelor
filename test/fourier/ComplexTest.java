package fourier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {
    ComplexMutable comp1 = new ComplexMutable(1,1);
    ComplexMutable comp2 = new ComplexMutable(2,2);
    ComplexMutable comp3 = new ComplexMutable(3,3);

    @Test
    void getImaginary() {
        assertEquals(comp1.getImaginary(), 1);
        assertEquals(comp2.getImaginary(), 2);
        assertEquals(comp3.getImaginary(), 3);
    }

    @Test
    void getReal() {
        assertEquals(comp1.getReal(), 1);
        assertEquals(comp2.getReal(), 2);
        assertEquals(comp3.getReal(), 3);
    }

    @Test
    void getMagnitude() {
        assertEquals(comp1.getMagnitude(), Math.sqrt(2));
        assertEquals(comp2.getMagnitude(), Math.sqrt(8));
        assertEquals(comp3.getMagnitude(), Math.sqrt(18));
    }

    @Test
    void add() {
        ComplexMutable comp4 = comp1.add(comp2);

        assertEquals(comp4.getReal(), 3);
        assertEquals(comp4.getImaginary(), 3);

        comp4 = comp2.add(comp3);

        assertEquals(comp4.getReal(), 5);
        assertEquals(comp4.getImaginary(), 5);

        comp4 = comp1.add(5);

        assertEquals(comp4.getReal(), 6);
        assertEquals(comp4.getImaginary(), 1);

        comp4 = comp1.add(-5);

        assertEquals(comp4.getReal(), -4);
        assertEquals(comp4.getImaginary(), 1);
    }

    @Test
    void times() {
        ComplexMutable comp4 = comp1.times(comp2);

        assertEquals(comp4.getImaginary(), 2 + 2);
        assertEquals(comp4.getReal(), 2 - 2);

        comp4 = comp2.times(new ComplexMutable(2, 0));

        assertEquals(comp4.getReal(), comp2.getReal() * 2);
        assertEquals(comp4.getImaginary(), comp2.getImaginary() * 2);

        comp4 = comp2.times(2);

        assertEquals(comp4.getReal(), comp2.getReal() * 2);
        assertEquals(comp4.getImaginary(), comp2.getImaginary() * 2);
    }
}