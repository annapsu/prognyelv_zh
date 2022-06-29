package zh.calc;

import java.lang.IllegalArgumentException;
import zh.calc.util.SheetException;

public class Num implements Evaluable {
	
	public int num;

	public Num (int num) {
		if (num < 0) {
			throw new IllegalArgumentException();
		}
		this.num = num;
	}

	public int eval (Sheet s) throws SheetException {
		return num;
	}
}
