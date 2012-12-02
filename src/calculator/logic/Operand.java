package calculator.logic;

import java.lang.StrictMath;

class Operand {
    Double val;
    boolean fpMode;
    int fpDigits;

    static final int MAX_FP_DIGITS = 10;
    
    Operand() {
	this(0);
    }

    Operand(int v){
        reset(v);
    }

    void addDigit(char c) {
        if (c == '.') {
            fpMode = true;
        } else {
            addDigit( Character.digit(c, 10) ); // (int)c - (int)'0'
        }
    }

    void addDigit(int c) {
        if (fpMode) {
            fpDigits++;
            val += (new Double(c)) / StrictMath.pow(10, fpDigits);
        } else {
            val = val * 10 + c;
        }
    }

    Double value() {
        reapplyFpSize();
	return val;
    }
    
    String strValue() {
        if (fpMode && fpDigits > 0) {
            return value().toString();
        } else if (fpMode && fpDigits == 0) {
            return (new Long(StrictMath.round(value()))).toString() + ".";
        } else {
            return (new Long(StrictMath.round(value()))).toString();
        }
    }
    
    void assessFpSize() {
        Double tval = new Double(val);
        Double expected = StrictMath.floor(tval);
        
        System.out.println("Floating point digits:");
        for (fpDigits = 0; fpDigits<MAX_FP_DIGITS && expected.compareTo(tval) != 0; fpDigits++) {
            tval *= 10;
            expected = StrictMath.floor(tval);
            
            System.out.println(expected + " == "+tval+"?");
            if (expected == tval){
                break;
            }
        }
        
        if (fpDigits > 0) {
            fpMode = true;
        }
    }

    // this assumes that all decimals are useless
    void reapplyFpSize() {
        val *= StrictMath.pow(10, fpDigits);
        val = ((new Double(StrictMath.floor(val))) / StrictMath.pow(10, fpDigits)) ;      
    }
    
    void deleteLastDigit() {
        if (fpMode) {
            if (fpDigits <= 0) {
                fpMode = false;
                fpDigits = 0;
            } else {
                fpDigits--;
            }
            
            reapplyFpSize();
        } else {
            val = (new Double(StrictMath.floor(val/10)));
        }    
    }

    void reset(){
        reset(0);
    }
    
    void reset(int v) {
	val = new Double(v);
        
        // bug: work out the fp mode and fp digits
        fpMode = false;
        fpDigits = 0;
    }

    void set(Integer _val) {
        set(new Double(_val));
    }
    
    void set(Double _val) {
	val = _val;
        assessFpSize();
    }
}
