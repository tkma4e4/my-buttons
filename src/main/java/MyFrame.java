
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class MyFrame extends JFrame implements ActionListener {

    static URL url;
    static HttpURLConnection conn;
    JButton kssButton;
    JButton snkButton;
    JButton attButton;
    JButton zzzButton;

    MyFrame() throws IOException {

        ImageIcon heartIcon = new ImageIcon(new ImageIcon("src/main/resources/Heart_Eyes.png").getImage().getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH));
        ImageIcon eyesIcon = new ImageIcon(new ImageIcon("src/main/resources/eyes.png").getImage().getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH));
        ImageIcon sleepyIcon = new ImageIcon(new ImageIcon("src/main/resources/sleepy.png").getImage().getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH));
        ImageIcon snackIcon = new ImageIcon(new ImageIcon("src/main/resources/snackie.png").getImage().getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH));

        kssButton = new JButton();
        kssButton.setBounds(0,0,249,249);
        kssButton.addActionListener(this);
        kssButton.setBackground(Color.decode("#f40b3f"));
        kssButton.setFocusable(false);
        kssButton.setIcon(heartIcon);

        snkButton = new JButton();
        snkButton.setBounds(250,0,249,249);
        snkButton.addActionListener(this);
        snkButton.setBackground(Color.decode("#ff9a00"));
        snkButton.setFocusable(false);
        snkButton.setIcon(snackIcon);

        attButton = new JButton();
        attButton.setBounds(0,250,249,249);
        attButton.addActionListener(this);
        attButton.setBackground(Color.decode("#8fe494"));
        attButton.setFocusable(false);
        attButton.setIcon(eyesIcon);

        zzzButton = new JButton();
        zzzButton.setBounds(250,250,249,249);
        zzzButton.addActionListener(this);
        zzzButton.setBackground(Color.decode("#6c00ff"));
        zzzButton.setFocusable(false);
        zzzButton.setIcon(sleepyIcon);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,525);
        this.setResizable(false);
        this.setVisible(true);
        this.add(kssButton);
        this.add(snkButton);
        this.add(attButton);
        this.add(zzzButton);

    }

    public static void makeConn(String endpoint) {
        try {
            url = new URL("http://localhost:9090/api/" + endpoint);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            } else if (conn.getResponseCode() == 200) {
                System.out.println("Connection was made");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Making Request...");
        if (e.getSource() == kssButton) {
            makeConn("kiss");
        } else if (e.getSource() == snkButton) {
            makeConn("snack");
        } else if (e.getSource() == attButton) {
            makeConn("attention");
        } else if (e.getSource() == zzzButton) {
            makeConn("bedtime");
        }
    }
}
