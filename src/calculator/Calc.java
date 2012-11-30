// Author: Chris Perivolaropoulos
//
// This creates the brain and tha gui. The two are completely
// separate. CalculatorGui has no access to Brain and will send
// messages to the brain about whatever it wants to do.

package calculator;

import calculator.logic.RPNBrain;

public class Calc {
	static public void main(String[] arg) {
		RPNBrain brain = new RPNBrain();
		CalculatorGui gui = new CalculatorGui(brain);
	}
}
