package calculator.logic;

public class DivMessage extends Message {

	void action(Brain brain) {
		brain.operate(new Divisor(brain));
	}
}
