// This message tells the brain to add.
package calculator.logic;

public class AddMessage extends Message {
	public void action(Brain brain) {
		brain.operate(new Adder());
	}
}
