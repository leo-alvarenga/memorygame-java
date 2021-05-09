package memorygame.view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Janela principal do jogo; Herda JFrame.
 * @author Leonardo
 * @version 2.5.0b - stable
 * @since 1.7.0b - stable
 */
public class MainWindow extends JFrame {

    /**
     * Inicializa a janela principal e começa o jogo.
     */
    public MainWindow(){
        super();

        GameView game_view = new GameView();
        this.add(game_view, BorderLayout.CENTER);


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setMinimumSize(screenSize);

        try{
            ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/cover.png")));
            this.setIconImage(img.getImage());
        }
        catch(NullPointerException e){
            // keep going...
        }

        this.startMessage();

        this.setTitle("Jogo da memória: Warhammer 40,000 edition - Version (b: 2.5.0, stable)");

        this.pack();

    }

    /**
     * Janela exibida antes do início do jogo.
     */
    private void startMessage(){
        int n;

        Object[] options = {"Pronto? Eu nasci pronto!", "Vou ter que amarelar viu..."};

        String desc;
        desc = "<html><h4>Bem vindo(a)!</h4> <p>Esse é um jogo da memória onde seu oponente é a máquina,<br> mas se prepare, pois a máquina é <strong>extremamente afiada</strong>.";
        desc += "<br><br>Tão afiada que, na verdade, por vezes nem mesmo o jogo<br> consegue mostrar as cartas que a máquina virou!";
        desc += "<br><br>Então, tenha certeza de que está pronto para o desafio!";
        desc += "</p></html>";

        n = JOptionPane.showOptionDialog(this,
                desc,
                "Sobre o jogo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if(n != 0 && n != -1){
            System.exit(3);
        }

    }

}
