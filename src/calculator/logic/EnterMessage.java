// Enter number typed so far

package calculator.logic;

public class EnterMessage extends Message {
	public void action(Brain brain) {
		brain.enter();
	}
}
