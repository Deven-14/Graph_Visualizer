package frontEnd;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import backEnd.GraphThread;
import datastructure.Sync;

public class VisualizerPanel extends JPanel{

    JButton startBFS;
    Timer t1;
    Sync sync;
    Integer nodeId;
    boolean isTraversing;
    int[][] distXY;

    VisualizerPanel()
    {
        startBFS = new JButton("Start");
        startBFS.addActionListener(new StartBFS());

        t1 = new Timer(1500, new TraverseEachNode());
        isTraversing = false;
        this.add(startBFS);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        if(isTraversing == false)
            return;

        Circle c1;
        for(int i = 0; i < 4; ++i)
            c1 = new Circle(g2d, distXY[i][0], distXY[i][1], i);

        // Circle c2 = new Circle(g2d, 70, 230, 1);
        // Circle c3 = new Circle(g2d, 150, 230, 2);
        // Circle c4 = new Circle(g2d, 230, 230, 3);

        Circle c5;
        if(isTraversing)
            c5 = new Circle(g2d, distXY[nodeId][0], distXY[nodeId][1], nodeId, true);

    }


    private class StartBFS implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            sync = new Sync();
            GraphThread g = new GraphThread(sync);
            isTraversing = true;
            distXY = new int[4][2];
            distXY[0][0] = 150;
            distXY[0][1] = 150;
            distXY[1][0] = 70;
            distXY[1][1] = 230;
            distXY[2][0] = 150;
            distXY[2][1] = 230;
            distXY[3][0] = 230;
            distXY[3][1] = 230;
            g.start();
            t1.start();
            
        }

    }

    private class TraverseEachNode implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            sync.receive( id -> { nodeId = id; repaint(); });
            
            if(sync.isCompleted)
            {
                t1.stop();
                isTraversing = false;
            }
        }

    }

}
