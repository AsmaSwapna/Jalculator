/**
 * The Gui of the calculator. A CalculatorGui object must do NOTHING
 * but pass Message objects to the Brain when a button is pressed. Not
 * even call brain functions directly. In a later version even the
 * messages will be ignorant of the brain's functions. It is and
 * should be completely ignorant of the logic of the calculator.
 *
 * To add a new button you will need to call the addButton method,
 * give there the button name, the message it will pass to the Brain
 * and the position (row, col). Then, in the logic package, create a
 * Message type that extends Message and add functionality in the
 * Brain to receive this message.
 */

// TODO
// Move button creation logic to button subclasses.
package calculator;

import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.LinkedList;

import calculator.logic.Brain;
import calculator.logic.Message;
import calculator.logic.NumberMessage;
import calculator.logic.EnterMessage;
import calculator.logic.AddMessage;
import calculator.logic.CMessage;
import calculator.logic.BackspaceMessage;
import calculator.logic.SubMessage;
import calculator.logic.MulMessage;
import calculator.logic.DivMessage;

class CalculatorGui {

	public static final int BOFFSET_X = 65, BOFFSET_Y = 80, BWIDTH = 50,
			BHEIGHT = 30, SPACE = 10, ROWS = 4, COLUMNS = 5;

	TextField display;
	public List<Button> buttons = new LinkedList<Button>();
	Brain brain;
	Frame frame;

	/**
	 * Constructor
	 */
	public CalculatorGui(Brain b) {
		brain = b;

		frame = new Frame("CALCULATOR Exer6 V3");
		frame.setLayout(null);
		frame.setFont(new Font("Arial", Font.BOLD, 17));
		frame.setBackground(new Color(0x4924));

		// Buttons
		for (int i=0; i < 10; i++) {
			buttons.add(new NumberButton(this, i));
		}
                buttons.add(new PointButton(this));
                
		buttons.add(new EnterButton(this));
		buttons.add(new BackspaceButton(this));
		buttons.add(new ClearButton(this));
		buttons.add(new AddButton(this));
		buttons.add(new SubButton(this));
		buttons.add(new MulButton(this));
		buttons.add(new DivButton(this));
		buttons.add(new EqualsButton(this));

		setupDisplay();

		// Frame Settings
		frame.add(display);
		frame.setSize(420, 250);
		frame.setVisible(true);
		frame.toFront();
		frame.setResizable(false);
		// Closing the window should close tha application
		frame.addWindowListener(new CloseWindowAndExit());
	}

	void send(Message msg) {
		brain.receive(msg);
		updateDisplay();
	}

	// Synchronize the operand and the display.
	void updateDisplay() {
		display.setText(brain.currentValue());
	}

	void setupDisplay() {
		// Display Settings
		display = new TextField("0", 14);
		display.setEditable(false); // disable edditing
		display.setBounds(BOFFSET_X, BOFFSET_Y - SPACE - BHEIGHT, BWIDTH
				* COLUMNS + (COLUMNS - 1) * SPACE, BHEIGHT);
	}
}

class CloseWindowAndExit extends WindowAdapter {
	public void windowClosing(WindowEvent closeWindowAndExit) {
		System.exit(0);
	}
}

// Handlers:
class MessageHandler implements ActionListener {
	CalculatorGui gui;
	Message message;

	public MessageHandler(CalculatorGui g, Message msg) {
		message = msg;
		gui = g;
	}

	public void actionPerformed(ActionEvent e) {
		gui.send(message);
	}
}

class Pos {
	public int x, y;

	Pos(int _x, int _y) {
		x = _x;
		y = _y;
	}
}

class CalcButton extends Button {
	// Add yourself to te gui.
	CalcButton(CalculatorGui gui, String name, Message msg, int row, int col) {
		super(name);
		MessageHandler behaviour = new MessageHandler(gui, msg);
		this.addActionListener(behaviour);
		gui.frame.add(this);
		this.setBounds(button_x(col), button_y(row), CalculatorGui.BWIDTH,
				CalculatorGui.BHEIGHT);
	}

	CalcButton(CalculatorGui gui, String name, Message msg, Pos pos) {
		this(gui, name, msg, pos.x, pos.y);
	}

	static int button_x(int col) {
		return CalculatorGui.BOFFSET_X + (col)
				* (CalculatorGui.BWIDTH + CalculatorGui.SPACE);
	}

	static int button_y(int row) {
		return CalculatorGui.BOFFSET_Y + (CalculatorGui.ROWS - 1 - row)
				* (CalculatorGui.BHEIGHT + CalculatorGui.SPACE);
	}
}

class NumberButtonPos extends Pos {
	NumberButtonPos(int n) {
		super(((n + 2) / 3), n != 0 ? (n - 1) % 3 : 0);
	}
}

class NumberButton extends CalcButton {

	NumberButton(CalculatorGui gui, int num) {
		// Java sucks:
		super(gui, Integer.toString(num), new NumberMessage(num), new NumberButtonPos(num));
	}
}
class PointButton extends CalcButton {
	PointButton(CalculatorGui gui) {
		// Java sucks:
		super(gui, ".", new NumberMessage("."), 1, 4);
	}
}


class EnterButton extends CalcButton {
	EnterButton(CalculatorGui g) {
		super(g, "Enter", new EnterMessage(), 3, 4);
	}
}

class ClearButton extends CalcButton {
	ClearButton(CalculatorGui g) {
		super(g, "C", new CMessage(), 0, 2);
	}
}

class BackspaceButton extends CalcButton {
	BackspaceButton(CalculatorGui g) {
		super(g, "Backspace", new BackspaceMessage(), 0, 1);
	}
}

class AddButton extends CalcButton {
	AddButton(CalculatorGui g) {
		super(g, "+", new AddMessage(), 3, 3);
	}
}

class EqualsButton extends CalcButton {
	EqualsButton(CalculatorGui g) {
		super(g, "=", new EnterMessage(), 2, 4);
	}
}

class SubButton extends CalcButton {
	SubButton(CalculatorGui g) {
		super(g, "-", new SubMessage(), 2, 3);
	}
}

class MulButton extends CalcButton {
	MulButton(CalculatorGui g) {
		super(g, "*", new MulMessage(), 1, 3);
	}
}

class DivButton extends CalcButton {
	DivButton(CalculatorGui g) {
		super(g, "/", new DivMessage(), 0, 3);
	}
}

// class NumberHandler implements ActionListener {
// CalculatorGui gui;
// int number;

// public NumberHandler(CalculatorGui _gui, int num) {
// gui = _gui;
// number = num;
// }

// public void actionPerformed(ActionEvent e) {
// gui.send(new NumberMessage(num)); // operation
// }
// }
