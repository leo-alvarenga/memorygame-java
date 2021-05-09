package memorygame.model;

import javax.swing.*;

/**
 * Representa uma carta e suas propriedades.
 * @author Leonardo
 * @version 2.4.7
 * @since 1.0.0
 */
public class Card extends JButton {
    private int card_id;
    private int pair_id;
    final private int position;

    private boolean is_facing_up;
    private boolean has_been_removed;

    final private String default_tooltip_msg;
    private String tooltip_msg;

    private String image;
    final private String cover;


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

        this.setIcon(new ImageIcon(getClass().getResource(this.cover)));
        this.setToolTipText(this.default_tooltip_msg);

        this.setVisible(true);
    }


    public Card flip(){
        if(this.isFacingUp() && !this.hasBeenRemoved()){
            this.setIcon(new ImageIcon(getClass().getResource(this.cover)));
            this.setToolTipText(this.default_tooltip_msg);
            this.is_facing_up = false;
        }
        else if(!this.isFacingUp() && !this.hasBeenRemoved()) {
            this.setIcon(new ImageIcon(getClass().getResource(this.image)));
            this.setToolTipText(this.tooltip_msg);
            this.is_facing_up = true;
        }

        return this;
    }

    public void remove(){
        this.has_been_removed = true;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setToolTipText("");
    }

    public void undoRemove(){
        this.has_been_removed = false;
        this.setOpaque(true);
        this.setContentAreaFilled(true);
        this.setBorderPainted(true);
        this.setToolTipText(this.default_tooltip_msg);

        this.is_facing_up = false;
        this.setIcon(new ImageIcon(getClass().getResource(this.cover)));
    }

    public void copyInfo(Card c){
        this.image = c.image;
        this.tooltip_msg = c.tooltip_msg;

        this.pair_id = c.pair_id;
        this.card_id = c.card_id;
    }

    public int getCardId() {
        return card_id;
    }

    public int getPairId() {
        return pair_id;
    }

    public int getPosition() {
        return position;
    }

    public boolean isFacingUp() {
        return is_facing_up;
    }

    public boolean hasBeenRemoved() {
        return has_been_removed;
    }
}
