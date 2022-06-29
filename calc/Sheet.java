package zh.calc;

import java.lang.IllegalArgumentException;
import java.lang.StringBuilder;

import zh.calc.util.CellName;
import zh.calc.util.SheetException;

public class Sheet {
	
	public int numOfRows;
	public int numOfCols;

	Evaluable[][] sh;

	public Sheet (int r, int c) {
		if (c > 26) {
			throw new IllegalArgumentException();
		}
		if (c < 0) {
			throw new IllegalArgumentException();
		}
		if (r < 0) {
			throw new IllegalArgumentException();
		}

		numOfCols = c;
		numOfRows = r;
		sh = new Evaluable[numOfRows][numOfCols];
	}

	public void insertToSheet (String cellName, Evaluable ref) {
		CellName cn = new CellName();
		try {
			sh[cn.getRowIndexFromCellName(cellName)][cn.getColIndexFromCellName(cellName)] = ref;
		} catch (SheetException e) {
			System.out.println("hiba");
		}
	}

	public Evaluable getFromSheet(String cellName) {
		CellName cn = new CellName();
		try {
			return sh[cn.getRowIndexFromCellName(cellName)][cn.getColIndexFromCellName(cellName)];
		} catch (SheetException e) {
			System.out.println("hiba");
		}
		return null;
	}

	public static int constructIntFromOperandStr (String operandStr, Sheet ref) {
		boolean cont = false;
		char ind = operandStr.charAt(0);
		for (int i = 0; !cont && i < CellName.colIndexes.length(); i++) {
			if (CellName.colIndexes.charAt(i) == ind) {
				cont = true;
			}
		}
		if (cont) {
			try {
				return ref.getFromSheet(operandStr).eval(ref);
			} catch (SheetException e) {
				System.out.println("hiba");
			}
		} else {
			return Integer.parseInt(operandStr);
		}
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numOfRows-1; i++) {
			for (int j = 0; j < numOfCols; j++) {
				try {
					sb.append(sh[i][j].eval(this));
				} catch (SheetException e) {
					System.out.println("hiba");
				}
				sb.append(" ");
			}
			sb.append(System.lineSeparator());
		}
		for (int j = 0; j < numOfCols; j++) {
			try {
				sb.append(sh[numOfRows-1][j].eval(this));
			} catch (SheetException e) {
				System.out.println("hiba");
			}
			sb.append(" ");
		}
		return sb.toString();
	}
}
