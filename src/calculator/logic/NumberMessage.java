// This message tells the brain a digit of a number
package calculator.logic;

public class NumberMessage extends Message {
    int digit;
    public NumberMessage(int dig) {
	digit = dig;
    }

    void action(Brain brain) {
	brain.digit(digit);
    }
}
