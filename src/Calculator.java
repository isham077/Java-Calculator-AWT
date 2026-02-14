import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {

    TextField display;

    public Calculator() {

        setTitle("Simple Calculator");
        setSize(350, 500);
        setLayout(null);

        // Close button working
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // Creates the text field and places it.
        display = new TextField();
        display.setBounds(50, 50, 250, 40);
        add(display);

        // Button names
        String[] buttons = {
                "AC", "⌫", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "="
        };

        int x = 50;
        int y = 120;

        for (int i = 0; i < buttons.length; i++) {

            Button b = new Button(buttons[i]);
            b.setBounds(x, y, 60, 40);
            b.addActionListener(this);
            add(b);

            x += 70;

            if ((i + 1) % 4 == 0) {
                x = 50;
                y += 60;
            }
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        // Numbers & operators → just add to display
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9')
                || command.equals(".")
                || command.equals("+")
                || command.equals("-")
                || command.equals("*")
                || command.equals("/")) {

            display.setText(display.getText() + command);
        }

        // AC
        else if (command.equals("AC")) {
            display.setText("");
        }

        // Backspace
        else if (command.equals("⌫")) {
            String text = display.getText();
            if (text.length() > 0) {
                display.setText(text.substring(0, text.length() - 1));
            }
        }

        // Change sign
        else if (command.equals("+/-")) {
            try {
                double value = Double.parseDouble(display.getText());
                value = value * -1;
                display.setText("" + value);
            } catch (Exception ex) {
                display.setText("Error");
            }
        }

        // Percentage
        else if (command.equals("%")) {
            try {
                double value = Double.parseDouble(display.getText());
                value = value / 100;
                display.setText("" + value);
            } catch (Exception ex) {
                display.setText("Error");
            }
        }

        // Equals
        else if (command.equals("=")) {

            try {

                String text = display.getText();

                double num1 = 0;
                double num2 = 0;
                char op = ' ';

                // Find operator
                for (int i = 0; i < text.length(); i++) {

                    char ch = text.charAt(i);

                    if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                        op = ch;

                        String part1 = text.substring(0, i);
                        String part2 = text.substring(i + 1);

                        num1 = Double.parseDouble(part1);
                        num2 = Double.parseDouble(part2);

                        break;
                    }
                }

                double result = 0;

                if (op == '+') {
                    result = num1 + num2;
                }
                else if (op == '-') {
                    result = num1 - num2;
                }
                else if (op == '*') {
                    result = num1 * num2;
                }
                else if (op == '/') {

                    if (num2 == 0) {
                        display.setText("Cannot divide by 0");
                        return;
                    }

                    result = num1 / num2;
                }

                display.setText("" + result);

            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }
}