// This message tells the brain a digit of a number
package calculator.logic;

public class NumberMessage extends Message {
    int digit;
    String sdigit;
    
    public NumberMessage(int dig) {
	digit = dig;
    }
    
    public NumberMessage(String dig) {
	sdigit = dig;
    }


    void action(Brain brain) {
        if (sdigit == null) {
            brain.digit(digit);
        } else {
            brain.digit(sdigit);
        }
    }
}
