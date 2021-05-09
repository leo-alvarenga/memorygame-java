package memorygame.view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private GameView game_view;

    public MainWindow(){
        super();

        this.game_view = new GameView();
        this.add(this.game_view, BorderLayout.CENTER);

//        this.getContentPane();

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setMinimumSize(screenSize);

        this.pack();

    }

}
