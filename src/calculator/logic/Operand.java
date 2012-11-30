package calculator.logic;

class Operand {
    Integer val;

    Operand() {
	val = new Integer(0);
    }

    Operand(int v){
	val = new Integer(v);
    }

    void addDigit(char c) {
	addDigit( Character.digit(c, 10) ); // (int)c - (int)'0'
    }

    void addDigit(int c) {
	val = val * 10 + c;
    }

    Integer value() {
	return val;
    }

    void deleteLastDigit() {
	val /= 10;
    }

    void reset() {
	val = new Integer(0);
    }

    void set(Integer _val) {
	val = _val;
    }
}
