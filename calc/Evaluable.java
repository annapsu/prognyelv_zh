package zh.calc;

import zh.calc.util.SheetException;

public interface Evaluable {
	int eval (Sheet s) throws SheetException;
}
