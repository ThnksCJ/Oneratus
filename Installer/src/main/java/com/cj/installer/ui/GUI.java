package com.cj.installer.ui;

import com.cj.shared.util.PopUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.cj.shared.util.LogUtil.log;
import static com.cj.shared.util.minecraft.MinecraftFiles.getMinecraft;

public class GUI implements ActionListener {

    private static JLabel userlabel;
    private static JTextField userText;
    private static JButton button;
    private static JLabel success;
    private Object BufferedReader;
    private Object InputStream;

    public static void main() {
        // GUI stuffs
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        //username button
        panel.setLayout(null);
        userlabel = new JLabel("USERNAME: ");
        userlabel.setBounds(10, 20, 80, 25);
        panel.add(userlabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        button = new JButton("INSTALL");
        button.setBounds(100, 80, 120, 25);
        button.addActionListener(new GUI());
        panel.add(button);


        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);
    }

    //event handler
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (userText.getText().equals("")) {
            PopUp.show("Umm Something Went Wrong", "Please Enter A Name Into The Text Field");
        } else {
            String usr = userText.getText();

            File oneratusFile = new File(getMinecraft() + "username.oneratus");
            try {
                if (oneratusFile.createNewFile()) {
                    log("Successfully Created The 'username.oneratus' File");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                FileWriter myWriter = new FileWriter(getMinecraft() + "username.oneratus");
                myWriter.write(usr);
                myWriter.close();
            } catch (IOException ignored) {
            }

            JComponent comp = (JComponent) actionEvent.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }
    }
}
