// The backspace button message
package calculator.logic;

public class BackspaceMessage extends Message {
	void action(Brain b) {
		b.backspace();
	}
}
