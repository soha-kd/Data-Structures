import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;

class Hanoi extends JPanel implements Runnable, ActionListener {

    int n = Integer.parseInt( JOptionPane.showInputDialog(Hanoi.this, "Enter numbers of disks "));
    int delay = Integer.parseInt(JOptionPane.showInputDialog("enter delay "));
    protected final BufferedImage buffer;
    protected int[][] disKSituation;
    protected int[] height;
    protected int Dmove,Tmove;
    public Display d;
    protected final JButton run,nextMove,reDo;
    protected int status;
    protected static final int PAUSE = 0;
    protected static final int RUN = 1;
    protected static final int REDO = 2;
    protected static final int NEXT = 3;


    private class Display extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int x = (getWidth() - buffer.getWidth())/2;
            int y = (getHeight() - buffer.getHeight())/2;
            g.drawImage(buffer, x, y, null);
        }
    }

    public Hanoi() {
        buffer = new BufferedImage(430,143,BufferedImage.TYPE_INT_RGB);
        d = new Display();
        d.setPreferredSize(new Dimension(630,143));
        d.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
        d.setBackground(Color.ORANGE);

        setLayout(new BorderLayout());
        add(d, BorderLayout.CENTER);

        JPanel buttonBar = new JPanel();
        add(buttonBar, BorderLayout.NORTH);
        buttonBar.setLayout(new GridLayout(1,0));

        run = new JButton("Run");
        run.setBackground(Color.gray);
        run.addActionListener(this);
        buttonBar.add(run);

        nextMove = new JButton("Next Move");
        nextMove.addActionListener(this);
        nextMove.setBackground(Color.gray);
        buttonBar.add(nextMove);

        reDo = new JButton("Start Again");
        reDo.addActionListener(this);
        reDo.setBackground(Color.gray);
        reDo.setEnabled(false);
        buttonBar.add(reDo);

        new Thread(this).start();
    }


    synchronized public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == run) {
            if (status == RUN) {
                status = PAUSE;
                nextMove.setEnabled(true);
                run.setText("Run");
            }
            else {
                status = RUN;
                nextMove.setEnabled(false);
                run.setText("Pause");
            }
        }
        else if (evt.getSource() == nextMove) {
            status = NEXT;
        }
        else if (evt.getSource() == reDo) {
            status = REDO;
        }
        notify();
    }


    public void run() {
        while (true) {
            run.setText("Run");
            nextMove.setEnabled(true);
            reDo.setEnabled(false);
            make();

            status = PAUSE;
            checkStatus();
            reDo.setEnabled(true);
            try {

                TOHAction(n,0,1,2);
            }
            catch (IllegalStateException e) {

            }
        }
    }
    synchronized private void checkStatus() {
        while (status == PAUSE) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }

        if (status == REDO)
            throw new IllegalStateException("Restart");

    }






    synchronized private void make() {
        Dmove= 0;

        disKSituation = new int[3][n];
        for (int i = 0; i < n; i++)
            disKSituation[0][i] = n - i;
        height = new int[n];
        height[0] = n;
        if (buffer != null) {
            Graphics g = buffer.getGraphics();
            draw(g);
            g.dispose();
        }
        d.repaint();
    }










    private void TOHAction(int disks, int from, int to, int helper) {

        if (disks == 1) {
            moveJob(from, to);
        }
        else {
            TOHAction(disks-1, from, helper, to);
            moveJob(from,to);
            TOHAction(disks-1, helper, to, from);
        }
    }




    synchronized private void moveJob(int from, int to) {
        Dmove = disKSituation[from][height[from]-1];
        Tmove = from;

        delay(delay);
        height[from]--;
        diskShow(Color.red,Dmove,Tmove);
        delay(delay);
        diskShow(Color.ORANGE,Dmove,Tmove);
        delay(delay);
        Tmove = to;
        diskShow(Color.red,Dmove,Tmove);
        delay(delay);
        diskShow(Color.YELLOW,Dmove,Tmove);
        if (n==1){
            int x=0;
            disKSituation[1][x] = Dmove;
            x++;
        }
        else {
            disKSituation[to][height[to]] = Dmove;
            height[to]++;
            Dmove = 0;
        }

        if (status == NEXT)
            status = PAUSE;
        checkStatus();
    }



    synchronized private void delay(int milliseconds) {
        try {
            wait(milliseconds);
        }
        catch (InterruptedException e) {
        }
    }



    private void diskShow(Color color, int disk, int t) {
        Graphics g = buffer.getGraphics();
        System.out.println(t);

        g.setColor(color);
        if(n==1 && t==1){
            height[0]=0;
            g.fillRoundRect(75+140*t - 5*disk - 5, 116, 10*disk+10, 10, 10, 10);
        }

        else{
            g.fillRoundRect(75 + 140 * t - 5 * disk - 5, 116 - 12 * height[t], 10 * disk + 10, 10, 10, 10);
        }
        g.dispose();
        d.repaint();
    }




    synchronized private void draw(Graphics g) {

        g.setColor(Color.ORANGE);
        g.fillRect(0,0,430,143);
        g.setColor(Color.gray);
        if (disKSituation == null)
            return;
        g.fillRect(10,128,130,5);
        g.fillRect(150,128,130,5);
        g.fillRect(290,128,130,5);
        g.setColor(Color.YELLOW);

        for (int t = 0; t < n; t++) {
            for (int i = 0; i < height[t]; i++) {
                int disk = disKSituation[t][i];
                g.fillRoundRect(75 + 140 * t - 5 * disk - 5, 116 - 12 * i, 10 * disk + 10, 10, 10, 10);
            }
        }

        if (Dmove > 0) {
            g.setColor(Color.red);
            g.fillRoundRect(75+140*Tmove - 5*Dmove - 5, 116-12*height[Tmove],
                    10*Dmove+10, 10, 10, 10);
        }
    }



}