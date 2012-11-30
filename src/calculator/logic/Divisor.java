package calculator.logic;

public class Divisor extends Operator {
	Brain brain;

	Divisor(Brain b) {
		brain = b;
	}

	@Override
	Integer operate(Integer x, Integer y) {
		if (y == 0) {
			brain.setError("Zero division");
			return 0;
		} else {
			return x / y;
		}
	}

}
