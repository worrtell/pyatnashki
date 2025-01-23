package com.pyatnashki.gui.start;

import com.pyatnashki.data.BoardDataSource;
import com.pyatnashki.data.User;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;

@Setter
public class WaitFrame extends JFrame {
    JButton btn;
    static BoardDataSource ds;
    static User user;

    public WaitFrame(User user) {
        super("Waiting for the second player");

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        this.user = user;

        JLabel lblTitle = new JLabel("Waiting for the second player to join...", JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        ImageIcon loadingIcon = new ImageIcon("fun.gif");
        JLabel lblLoading = new JLabel(loadingIcon, JLabel.CENTER);
        add(lblLoading, BorderLayout.CENTER);

        ds = new BoardDataSource();

        System.out.println("new wait frame");

        btn = new JButton("test");
        //btn.addActionListener(it -> t());

        add(btn);
        setVisible(true);
        System.out.println("user " + user.toString());
    }

//    public static void t() {
//        try {
//            ds.onMove(user);
//        } catch (URISyntaxException | IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
