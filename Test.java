import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Test {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("BFS TRAVERSAL");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.getContentPane().setBackground(new Color(0,0,0));
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private JButton[] buttons = new JButton[]{
            new JButton("0"),
            new JButton("1"),
            new JButton("2"),
            new JButton("3"),
        };

        private List<Connection> connections;

        public TestPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 10, 10);
            add(buttons[0], gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            add(buttons[1], gbc);

            gbc.gridx = 2;
            gbc.gridy = 2;
            add(buttons[2], gbc);

            gbc.gridx = 2;
            gbc.gridy = 0;
            add(buttons[3], gbc);

            connections = new ArrayList<Connection>(25);
            connections.add(new Connection(buttons[0], buttons[2]));
            connections.add(new Connection(buttons[1], buttons[0]));
            connections.add(new Connection(buttons[1], buttons[3]));
            connections.add(new Connection(buttons[3], buttons[2]));
          
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.RED);
            for (Connection connection : connections) {
                JButton source = connection.getSource();
                JButton dest = connection.getDestination();

                if (source.getX() == dest.getX())//same column 
                {
                    g2d.drawLine(source.getX() + source.getWidth() / 2, source.getY(),
                                    dest.getX() + source.getWidth() / 2, dest.getY());
                } else if (source.getY() == dest.getY()) {
                   
                    g2d.drawLine(source.getX(), source.getY() + source.getHeight() / 2,
                                    dest.getX(), dest.getY() + dest.getHeight() / 2);
                } else {
                
                g2d.drawLine(source.getX(), source.getY() + source.getHeight() / 2,
                dest.getX(), dest.getY() + dest.getHeight() / 2);
                }
            }
            g2d.dispose();
        }

       


        public class Connection {

            private final JButton source;
            private final JButton destination;

            public Connection(JButton source, JButton destination) {
                this.source = source;
                this.destination = destination;
            }

            public JButton getSource() {
                return source;
            }

            public JButton getDestination() {
                return destination;
            }

        }

    }

}
