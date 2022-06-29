package zh.calc.util;

public class CellName {
	
	public static final String colIndexes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public boolean isCellNameValid (String cellName) {
		boolean cont = false;
		char ind = cellName.charAt(0);
		for (int i = 0; !cont && i < CellName.colIndexes.length(); i++) {
			if (CellName.colIndexes.charAt(i) == ind) {
				cont = true;
			}
		}
		boolean nonneg = false;
		try {
			nonneg = Integer.parseInt(cellName.substring(1)) >= 0;
		} catch (NumberFormatException e) {
			System.out.println("rossz cellanév");
		}
		return cont && nonneg;
	}

	public int getRowIndexFromCellName (String cellName) throws SheetException {
		if (!this.isCellNameValid(cellName)) {
			throw new SheetException("rossz cellanév");
		}
		return Integer.parseInt(cellName.substring(1));
	}

	public int getColIndexFromCellName (String cellName) throws SheetException {
		if (!this.isCellNameValid(cellName)) {
			throw new SheetException("rossz cellanév");
		}
		return CellName.colIndexes.indexOf(cellName.charAt(0));
	}
}
