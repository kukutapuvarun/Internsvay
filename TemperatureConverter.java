import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private final JTextField inputField;
    private final JLabel resultLabel;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        JButton celsiusToFahrenheitButton = new JButton("Celsius to Fahrenheit");
        JButton fahrenheitToCelsiusButton = new JButton("Fahrenheit to Celsius");
        resultLabel = new JLabel("Result: ");

        celsiusToFahrenheitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double celsius = Double.parseDouble(inputField.getText());
                    double fahrenheit = (celsius * 9 / 5) + 32;
                    resultLabel.setText("Result: " + fahrenheit + "°F");
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input");
                }
            }
        });

        fahrenheitToCelsiusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double fahrenheit = Double.parseDouble(inputField.getText());
                    double celsius = (fahrenheit - 32) * 5 / 9;
                    resultLabel.setText("Result: " + celsius + "°C");
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input");
                }
            }
        });

        add(inputField);
        add(celsiusToFahrenheitButton);
        add(fahrenheitToCelsiusButton);
        add(resultLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter();
            }
        });
    }
}
