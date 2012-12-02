package calculator.logic;

import java.util.Stack;
import java.util.EmptyStackException;


// To implement some logic for the calculator extend brain in this package.
public abstract class Brain {
	Operand op = new Operand();
	String error = new String();

	public void receive(Message msg) {
		try {
			// Try to read the message. POLYMORPHISM!!!!
			// The message will call the correct
			msg.action(this);
		} catch (EmptyStackException e) {
			setError("Not enough numbers entered.");
		}
	}
	
	abstract void operate(Operator op);

	// abstract void add();
	
	// abstract void sub();
	
	// abstract void mul();
	
	// abstract void div();

	abstract void enter();
	
	void setError(String e) {
		error = e;
	}
	
	void c() {
		// Clear a number
		op.reset();
	}

	void digit(int num) {
		op.addDigit(num);
	}
        
        void digit(String num) {
            for (int i = 0; i<num.length() ; i++) {
                op.addDigit(num.charAt(i));
            }
        }

	void backspace() {
		op.deleteLastDigit();
	}

	public String currentValue() {
		// get the current value
		String tmperr = error;
		
		if (error.length() == 0) {
			return op.strValue();
		} else {
			error = new String();
			return tmperr;
		}
	}
}
