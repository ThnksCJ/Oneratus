package com.cj.bootstrap;

import com.cj.bootstrap.util.ThreadUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.function.BooleanSupplier;

import static com.cj.bootstrap.util.MinecraftFiles.getMinecraft;
import static com.cj.bootstrap.util.ThreadUtil.sleepUntil;

public class Login {

    private JFrame frame;
    private JPanel panel;
    private JButton btnSubmit;
    private JLabel lbResult;
    private JTextField tfInput;

    // Constructor
    public Login() {
        // Create the textfield to read input
        tfInput = new JTextField(30);
        tfInput.setBounds(10, 10, 240, 20);

        // Create the button to submit the value
        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new btnSubmitAction(this));
        btnSubmit.setBounds(260, 10, 100, 20);

        // Create the label to display the result
        lbResult = new JLabel("Username");
        lbResult.setBounds(10, 40, 320, 20);

        // Create the panel to hold the button, label, and textfield
        panel = new JPanel(null);
        panel.add(btnSubmit);
        panel.add(lbResult);
        panel.add(tfInput);
        panel.setPreferredSize(new Dimension(370, 70));

        // Create the frame which is a window
        frame = new JFrame("Oneratus Login");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public String getTextFieldInput() {
        return tfInput.getText();
    }

    public void setTextLabelResult(String s) {
        lbResult.setText(s);
    }

    public static void main() {
        new Login();
    }
}


class btnSubmitAction implements ActionListener {

    private Login g;
    public btnSubmitAction( Login g ) {
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String s = g.getTextFieldInput();

            File myObj = new File(getMinecraft() + "/username.oneratus");
        try {
            myObj.createNewFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            FileWriter myWriter = new FileWriter(getMinecraft() + "/username.oneratus");
            myWriter.write(s);
            myWriter.close();
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        } catch (IOException ignored) {
        }

    }
}
