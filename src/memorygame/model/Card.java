package memorygame.model;

import javax.swing.*;
import java.util.Objects;

/**
 * Representa uma carta e suas propriedades; Herda JButton.
 * @author Leonardo
 * @version 2.5.0b - stable
 * @since 1.0.0b - stable
 */
public class Card extends JButton {
    /** Id da carta. */
    private int card_id;
    /** Id do par ao qual a carta pertence. */
    private int pair_id;
    /** Posição que a carta ocupa na tela (0 a (Número de pares * 2)). */
    final private int position;

    /** Armazena se a carta está com a imagem para cima (true), ou não (false). */
    private boolean is_facing_up;
    /** Armazena se a carta foi removida do jogo (true), ou não (false). */
    private boolean has_been_removed;

    /** Armazena a mensagem padrão que aparecem quando o mouse passa por cima da carta escondida. */
    final private String default_tooltip_msg;
    /** Armazena a mensagem que aparece quando o mouse passa por cima da carta virada. */
    private String tooltip_msg;

    /** Armazena o endereço da imagem da carta. */
    private String image;
    /** Armazena os endereço da imagem do verso da carta. */
    final private String cover;


    /**
     * Inicializa a carta de forma a deixá-la pronta para uso na interface.
     * @param id Id da carta.
     * @param pair Id do par ao qual a carta pertence.
     * @param position Posição que a carta ocupa na tela (0 a (Número de pares * 2)).
     * @param image Endereço da imagem da carta.
     * @param cover Endereço da imagem do verso da carta.
     * @param default_msg A mensagem padrão que aparecem quando o mouse passa por cima da carta escondida.
     * @param tooltip_msg A mensagem que aparece quando o mouse passa por cima da carta virada.
     */
    public Card(int id, int pair, int position, String image, String cover, String default_msg, String tooltip_msg){
        super();

        this.card_id = id;
        this.pair_id = pair;
        this.position = position;

        this.is_facing_up = false;
        this.has_been_removed = false;

        this.default_tooltip_msg = default_msg;
        this.tooltip_msg = tooltip_msg;

        this.image = image;
        this.cover = cover;

        this.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(this.cover))));
        this.setToolTipText(this.default_tooltip_msg);

        this.setVisible(true);
    }

    /**
     * Vira a carta, atualizando os valores, a imagem e o texto que deve ser mostrado.
     */
    public void flip(){
        if(this.isFacingUp() && !this.hasBeenRemoved()){
            this.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(this.cover))));
            this.setToolTipText(this.default_tooltip_msg);
            this.is_facing_up = false;
        }
        else if(!this.isFacingUp() && !this.hasBeenRemoved()) {
            this.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(this.image))));
            this.setToolTipText(this.tooltip_msg);
            this.is_facing_up = true;
        }

    }

    /**
     * Remove a carta do jogo.
     */
    public void remove(){
        this.has_been_removed = true;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setToolTipText("");
    }

    /**
     * Desfaz a remoção da carta.
     */
    public void undoRemove(){
        this.has_been_removed = false;
        this.setOpaque(true);
        this.setContentAreaFilled(true);
        this.setBorderPainted(true);
        this.setToolTipText(this.default_tooltip_msg);

        this.is_facing_up = false;
        this.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(this.cover))));
    }

    /**
     * Copia os dados da carta c para si.
     * @param c Carta da qual os dados serão copiados.
     */
    public void copyInfo(Card c){
        this.image = c.image;
        this.tooltip_msg = c.tooltip_msg;

        this.pair_id = c.pair_id;
        this.card_id = c.card_id;
    }

    /**
     * Retorna o Id da carta.
     * @return Id da carta.
     */
    public int getCardId() {
        return card_id;
    }

    /**
     * Retorna o Id do par ao qual a carta pertence.
     * @return Id do par.
     */
    public int getPairId() {
        return pair_id;
    }

    /**
     * Retorna a posição da carta.
     * @return Posição da carta.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Retorna se a carta está com a imagem para cima.
     * @return Se a carta está com a imagem para cima (true), ou não (false)..
     */
    public boolean isFacingUp() {
        return is_facing_up;
    }

    /**
     * Retorna se a carta foi removida do jogo.
     * @return Se a carta foi removida (true), ou não (false)..
     */
    public boolean hasBeenRemoved() {
        return has_been_removed;
    }

}
