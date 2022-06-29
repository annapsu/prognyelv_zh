package zh.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import zh.calc.util.CellName;
import zh.calc.util.SheetException;
import zh.calc.Equation;
import zh.calc.Num;
import zh.calc.Sheet;

public class Tests {

	@Test
	public void testValidCellName() {
		CellName cn = new CellName();
		assertFalse(cn.isCellNameValid("C 3"));
		assertFalse(cn.isCellNameValid("c3"));
		assertFalse(cn.isCellNameValid("C-5"));
		assertTrue(cn.isCellNameValid("C4"));
		assertTrue(cn.isCellNameValid("C23"));
	}

	@Test
	public void testRowIndex() {
		CellName cn = new CellName();
		try {
			assertEquals(3, cn.getRowIndexFromCellName("C3"));
			assertEquals(13, cn.getRowIndexFromCellName("C13"));
		} catch (SheetException e) {
			System.out.println("hiba");
		}
	}

	@Test
	public void testColIndex() {
		CellName cn = new CellName();
		try {
			assertEquals(0, cn.getColIndexFromCellName("A3"));
			assertEquals(2, cn.getColIndexFromCellName("C13"));
		} catch (SheetException e) {
			System.out.println("hiba");
		}
	}
	
	@Test
	public void testElements() {
		Num n = new Num(3);
		try {
			assertEquals(3, n.eval(null));
		} catch (SheetException e) {
			System.out.println("hiba");
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testEl2() {
		Equation e = new Equation("D3 +");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEl3() {
		Equation e = new Equation("D3+c3");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEl4() {
		Equation e = new Equation("D3!D2");
	}

	@Test
	public void testprint() {
		String expected = "6 5 " + System.lineSeparator() + "2 6 " + System.lineSeparator() + "2 9 ";
		Sheet s = new Sheet (3,2);
		s.insertToSheet("A0", new Num(6));
		s.insertToSheet("B0", new Num(5));
		s.insertToSheet("A1", new Num(2));
		s.insertToSheet("B1", new Num(6));
		s.insertToSheet("A2", new Num(2));
		s.insertToSheet("B2", new Num(9));
		assertEquals(expected, s.toString());
	}
	
	@Test
	public void testEquation() {
		Sheet s = new Sheet (3,3);
		s.insertToSheet("A0", new Num(6));
		s.insertToSheet("B0", new Num(5));
		s.insertToSheet("A1", new Num(2));
		s.insertToSheet("B1", new Num(6));
		s.insertToSheet("A2", new Num(2));
		s.insertToSheet("B2", new Num(9));
		System.out.println(s);
		Equation e0 = new Equation("A0+B0");
		int val0 = e0.eval(s); 
		s.insertToSheet("C0", e0);
		s.insertToSheet("C1", new Equation("A1+B1"));
		s.insertToSheet("C2", new Equation("A2+B2"));
		/* assertEquals(11, s.getFromSheet("C0"));
		assertEquals(8, s.getFromSheet("C1"));
		assertEquals(11, s.getFromSheet("C2")); */

	}
}
