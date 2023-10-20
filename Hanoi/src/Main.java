import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("Towers Of Hanoi");
        window.setContentPane(new Hanoi());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setResizable(true);
        window.setLocation(300, 200);
        window.setVisible(true);
    }
}