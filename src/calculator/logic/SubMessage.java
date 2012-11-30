package calculator.logic;

public class SubMessage extends Message {
	void action(Brain brain) {
		brain.operate(new Subtractor());
	}
}
