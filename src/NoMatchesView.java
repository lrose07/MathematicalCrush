import javax.swing.*;
import java.awt.*;

class NoMatchesView {

    private CrushController cont;

    NoMatchesView(CrushController cont) {
        JFrame frame = new JFrame("Game Over");
        frame.setPreferredSize(new Dimension(200, 100));
        frame.setMinimumSize(new Dimension(200, 100));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 100));
        panel.setMinimumSize(new Dimension(200, 100));

        JLabel endScore = new JLabel("Final score: " + cont.getScore());
        JButton restart = new JButton("Play again");

        restart.addActionListener(e -> {
            cont.resetGame();
            frame.dispose();
        });

        panel.add(endScore);
        panel.add(restart);

        frame.add(panel);

        frame.setVisible(true);
    }
}
