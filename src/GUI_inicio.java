import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI_inicio extends JFrame {
    private static final long serialVersionUID = 1L;
    private int selectedOption = 0;
    private String[] options = {"Jugar", "Mejores Tiempos", "Opciones", "Salir"};
    private ImageIcon[] icons = {
        new ImageIcon("jugar.png"),
        new ImageIcon("mejores-tiempos.png"),
        new ImageIcon("opciones.png"),
        new ImageIcon("salir.png")
    };

    public GUI_inicio() {
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    selectedOption = (selectedOption + 1) % options.length;
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    selectedOption = (selectedOption - 1 + options.length) % options.length;
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    selectOption();
                }
            }

            public void keyReleased(KeyEvent e) {}
        });
    }

    private void selectOption() {
        switch (selectedOption) {
            case 0:
                JOptionPane.showMessageDialog(null, "Acción Jugar");
                break;
            case 1:
                JFrame bestTimesFrame = new JFrame("Mejores Tiempos");
                bestTimesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                bestTimesFrame.setSize(400, 300);
                bestTimesFrame.setLayout(new GridBagLayout());

                BestTimesPanel bestTimesPanel = new BestTimesPanel();
                bestTimesFrame.add(bestTimesPanel);

                bestTimesFrame.setVisible(true);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Acción Opciones");
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("FlappyBird by: chocobollos", 100, 100);

        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString(">", 260, 200 + 50 * i);
            }
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString(options[i], 300, 220 + 50 * i);
            if (i < icons.length) {
                icons[i].paintIcon(this, g, 200, 200 + 50 * i);
            }
        }
    }

    public static void main(String[] args) {
        GUI_inicio frame = new GUI_inicio();
        frame.setVisible(true);
    }
}

class BestTimesPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public BestTimesPanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Mejores Tiempos", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(titleLabel, gbc);

        // Simulación de mejores tiempos
        String[] bestTimes = {"1. Juan - 100s", "2. María - 110s", "3. Pedro - 120s"};
        for (int i = 0; i < bestTimes.length; i++) {
            JLabel timeLabel = new JLabel(bestTimes[i], SwingConstants.CENTER);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            gbc.gridy = i + 1;
            add(timeLabel, gbc);
        }

        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(BestTimesPanel.this);
                frame.dispose();
            }
        });
        gbc.gridy = bestTimes.length + 1;
        add(closeButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}