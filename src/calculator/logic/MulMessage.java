package calculator.logic;

public class MulMessage extends Message {

	void action(Brain brain) {
		brain.operate(new Multiplier());
	}
}
