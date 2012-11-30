package calculator.logic;

import java.util.Stack;

public class RPNBrain extends Brain {
	Stack<Integer> st = new Stack<Integer>();

	void operate(Operator o) {
		Integer x1 = st.pop();
		Integer x2 = st.pop();
		Integer res = o.operate(x2,x1);
		st.push(res);
		op.set(res);
	}
	
/*	void add() {
		// Add the top two numbers
		Integer x1 = st.pop();
		Integer x2 = st.pop();
		Integer res = x1 + x2;
		st.push(res);
		op.set(res);
	}

	void sub() {
		// Add the top two numbers
		Integer x1 = st.pop();
		Integer x2 = st.pop();
		Integer res = x2 - x1;
		st.push(res);
		op.set(res);
	}

	void mul() {
		// Add the top two numbers
		Integer x1 = st.pop();
		Integer x2 = st.pop();
		Integer res = x1 * x2;
		st.push(res);
		op.set(res);
	}

	void div() {
		// Add the top two numbers
		Integer x1 = st.pop();
		Integer x2 = st.pop();
		Integer res = new Integer(0);
		
		if (x1 != 0) {
			res = x2 / x1;
		} else {
			setError("Zero division!");
		}
		st.push(res);
		op.set(res);
	}*/

	void enter() {
		// Enter number in the stack
		st.push(op.value());
		op.reset();
	}

}
