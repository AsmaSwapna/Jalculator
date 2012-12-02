package calculator.logic;

public class Divisor extends Operator {
	Brain brain;

	Divisor(Brain b) {
		brain = b;
	}

	@Override
	Double operate(Double x, Double y) {
		if (y == 0) {
			brain.setError("Zero division");
			return .0;
		} else {
			return x / y;
		}
	}

}
