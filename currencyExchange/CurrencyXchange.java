package com.currencyExchange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CurrencyXchange extends JFrame {

    JTextField textRate = new JTextField(10);
    JTextField jTextFieldRate = new JTextField(13);
    JTextField jTextFieldRiel = new JTextField(13);
    String currency[] = {"Dollar", "Riel", "Euro", "Thai Baht", "Vietnamese Dong", ""};
    JComboBox jComboBox1 = new JComboBox(currency);
    JComboBox jComboBox2 = new JComboBox(currency);

    // Exchange rates
    float dollarToRiel = 4010;
    float dollarToEuro = 0.88F; // Example rate
    float euroToRiel = 4550; // Example rate
    float dollarToBaht = 31.5f; // Example rate
    float dollarToDong = 23000; // Example rate
    float bahtToRiel = 127.3f; // Example rate
    float dongToRiel = 0.18f; // Example rate

    // layout Constructor
    public CurrencyXchange() {
        layoutComponents(); // Call function

        textRate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateRates();
                convert(jTextFieldRate, jTextFieldRiel, jComboBox1, jComboBox2);
            }
        });

        // Events
        jTextFieldRate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                convert(jTextFieldRate, jTextFieldRiel, jComboBox1, jComboBox2);
            }
        });
        jTextFieldRiel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                convert(jTextFieldRiel, jTextFieldRate, jComboBox2, jComboBox1);
            }
        });
        jComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert(jTextFieldRate, jTextFieldRiel, jComboBox1, jComboBox2);
            }
        });
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert(jTextFieldRiel, jTextFieldRate, jComboBox2, jComboBox1);
            }
        });
    }

    final int DOLLAR = 0, RIEL = 1, EURO = 2, BAHT = 3, DONG = 4;

    public void updateRates() {
        // Assuming textRate contains the Dollar to Riel rate
        dollarToRiel = Float.parseFloat(textRate.getText());
        // Update other rates if necessary
    }

    public void convert(JTextField jTextField1, JTextField jTextField12, JComboBox jComboBox1, JComboBox jComboBox2) {
        int index1 = jComboBox1.getSelectedIndex();
        int index2 = jComboBox2.getSelectedIndex();

        if (index1 == index2 || jTextField1.getText().equals("")) {
            String text = jTextField1.getText();
            jTextField12.setText(text);
            return;
        }

        float inputAmount = Float.parseFloat(jTextField1.getText());
        float convertedAmount = 0;

        switch (index1) {
            case DOLLAR:
                if (index2 == RIEL) {
                    convertedAmount = inputAmount * dollarToRiel;
                } else if (index2 == EURO) {
                    convertedAmount = inputAmount * dollarToEuro;
                } else if (index2 == BAHT) {
                    convertedAmount = inputAmount * dollarToBaht;
                } else if (index2 == DONG) {
                    convertedAmount = inputAmount * dollarToDong;
                }
                break;
            case RIEL:
                if (index2 == DOLLAR) {
                    convertedAmount = inputAmount / dollarToRiel;
                } else if (index2 == EURO) {
                    convertedAmount = inputAmount / euroToRiel;
                } else if (index2 == BAHT) {
                    convertedAmount = inputAmount / bahtToRiel;
                } else if (index2 == DONG) {
                    convertedAmount = inputAmount / dongToRiel;
                }
                break;
            case EURO:
                if (index2 == DOLLAR) {
                    convertedAmount = inputAmount / dollarToEuro;
                } else if (index2 == RIEL) {
                    convertedAmount = inputAmount * euroToRiel;
                } else if (index2 == BAHT) {
                    convertedAmount = (inputAmount / dollarToEuro) * dollarToBaht;
                } else if (index2 == DONG) {
                    convertedAmount = (inputAmount / dollarToEuro) * dollarToDong;
                }
                break;
            case BAHT:
                if (index2 == DOLLAR) {
                    convertedAmount = inputAmount / dollarToBaht;
                } else if (index2 == RIEL) {
                    convertedAmount = inputAmount * bahtToRiel;
                } else if (index2 == EURO) {
                    convertedAmount = (inputAmount / dollarToBaht) * dollarToEuro;
                } else if (index2 == DONG) {
                    convertedAmount = (inputAmount / dollarToBaht) * dollarToDong;
                }
                break;
            case DONG:
                if (index2 == DOLLAR) {
                    convertedAmount = inputAmount / dollarToDong;
                } else if (index2 == RIEL) {
                    convertedAmount = inputAmount * dongToRiel;
                } else if (index2 == EURO) {
                    convertedAmount = (inputAmount / dollarToDong) * dollarToEuro;
                } else if (index2 == BAHT) {
                    convertedAmount = (inputAmount / dollarToDong) * dollarToBaht;
                }
                break;
        }

        jTextField12.setText(Float.toString(convertedAmount));
    }

    public void layoutComponents() {
        setTitle("Currency Exchange");
        JLabel rate = new JLabel("(Rate) $1= ");
        JLabel riel = new JLabel("Riels");

        JPanel p = new JPanel(new BorderLayout());

        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(rate);
        p1.add(textRate);
        p1.add(riel);
        p1.setBackground(Color.orange);
        p.add(p1, BorderLayout.NORTH);
        this.add(p);
        // Add to JFrame

        JPanel p2 = new JPanel(new GridLayout(1, 2, 5, 5));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        p2.setBackground(Color.white);
        p2.add(jTextFieldRate);
        p2.add(jTextFieldRiel);
        p.add(p2);
        this.add(p);

        // Add jComboBox1 and jComboBox2 to JFrame;
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p3.setBackground(Color.ORANGE);
        p3.add(jComboBox1);
        p3.add(jComboBox2);
        p.add(p3, BorderLayout.SOUTH);
        this.add(p);

        // Initialize values;
        textRate.setText("" + dollarToRiel);
        jComboBox2.setSelectedIndex(1);
        this.setBounds(200, 200, 300, 150);
        this.setAlwaysOnTop(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CurrencyXchange();
    }
}
