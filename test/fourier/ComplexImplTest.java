package fourier;

import data.Complex;
import data.ComplexImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexImplTest {
    Complex c1, c2, c3, c4, sol, sol1;
    @BeforeEach
    void before(){
        c1 = new ComplexImpl();
        c2 = new ComplexImpl(1,1);
        c3 = new ComplexImpl(-1,-1);
        c4 = new ComplexImpl(3,3);
    }

    @Test
    void add() {
        sol = c1.add(c2);
        assertEquals(1, sol.getReal());
        assertEquals(1, sol.getImaginary());

        sol = c1.add(c3);
        assertEquals(-1, sol.getReal());
        assertEquals(-1, sol.getImaginary());

        sol = c1.add(2);
        assertEquals(2, sol.getReal());
        assertEquals(0, sol.getImaginary());
    }

    @Test
    void substract() {
        sol = c1.substract(c2);
        assertEquals(-1, sol.getReal());
        assertEquals(-1, sol.getImaginary());

        sol = c1.substract(c3);
        assertEquals(1, sol.getReal());
        assertEquals(1, sol.getImaginary());

        sol = c1.substract(2);
        assertEquals(-2, sol.getReal());
        assertEquals(0, sol.getImaginary());
    }

    @Test
    void times() {
        sol = c1.times(c2);
        assertEquals(0, sol.getReal());
        assertEquals(0, sol.getImaginary());

        sol = c2.times(c3);
        assertEquals(0, sol.getReal());
        assertEquals(-2, sol.getImaginary());

        sol = c4.times(c4);
        assertEquals(0, sol.getReal());
        assertEquals(18, sol.getImaginary());

        sol = c2.times(2);
        assertEquals(2, sol.getReal());
        assertEquals(2, sol.getImaginary());
    }

    @Test
    void pow() {
        sol = c1.pow(5);
        assertEquals(0, sol.getReal());
        assertEquals(0, sol.getImaginary());

        sol = c3.pow(3);
        sol1 = c3.times(c3).times(c3);
        assertEquals(sol1.getReal(), sol.getReal());
        assertEquals(sol1.getImaginary(), sol.getImaginary());

        sol = c4.pow(0);
        assertEquals(1, sol.getReal());
        assertEquals(0, sol.getImaginary());

        sol = c4.pow(1);
        assertEquals(c4.getReal(), sol.getReal());
        assertEquals(c4.getImaginary(), sol.getImaginary());

        sol = c4.pow(5);
        sol1 = c4.times(c4).times(c4).times(c4).times(c4);
        assertEquals(sol1.getReal(), sol.getReal());
        assertEquals(sol1.getImaginary(), sol.getImaginary());
    }
}