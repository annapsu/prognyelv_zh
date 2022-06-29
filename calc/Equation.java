package zh.calc;

import zh.calc.util.SheetException;
import zh.calc.util.CellName;

public class Equation implements Evaluable {
	
	public String operand1;
	public String operand2;
	public char operator;

	public Equation (String kpl) {
		boolean op = false;
		int ind = 0;
		for (int i = 0; i < kpl.length(); i++) {
			if (kpl.charAt(i) == '+') {
				op = true;
				operator = kpl.charAt(i);
				ind = i;
			}
			else if (kpl.charAt(i) == '-') {
				op = true;
				operator = kpl.charAt(i);
				ind = i;
			}
			else if (kpl.charAt(i) == '*') {
				op = true;
				operator = kpl.charAt(i);
				ind = i;
			}
			else if (kpl.charAt(i) == '/') {
				op = true;
				operator = kpl.charAt(i);
				ind = i;
			}
		}
		if (op == false) {
			throw new IllegalArgumentException();
		}
		String[] ope = kpl.split(String.valueOf(operator));
		CellName cn = new CellName();
		if ((cn.isCellNameValid(ope[0]) && cn.isCellNameValid(ope[1])) || ((cn.isCellNameValid(ope[0]) && Integer.parseInt(ope[1]) >= 0)) || ((cn.isCellNameValid(ope[1]) && Integer.parseInt(ope[0]) >= 0))) {
			operand1 = ope[0];
			operand2 = ope[1];
		}
	}

	public int eval (Sheet s) throws SheetException {
		int fi = Sheet.constructIntFromOperandStr(operand1, s);
		int se = Sheet.constructIntFromOperandStr(operand2, s);
		int ret;
		switch (operator) {
			case '+' : {
				ret = fi + se;
				break;
			}
			case '-' : {
				if (fi - se < 0) {
					throw new ArithmeticException();
				}
				ret = fi - se;
				break;
			}
			case '*' : {
				ret = fi * se;
				break;
			}
			default : {
				if (se == 0) {
					throw new ArithmeticException();
				} else {
					ret = fi / se;
				}
			}
		}
		return ret;
	}

}
