package memorygame.control;

import memorygame.model.*;
import org.jetbrains.annotations.Contract;

import java.util.Random;

/**
 * Controller do jogo; Gera e repássa todas as informações personalizadas das cartas; Gerencia os placares, placares e condições do jogo.
 * @author Leonardo
 * @version 2.4.7
 * @since 1.3.0
 */
public class Controller {
    /** Armazena os endereços das imagens de cada par. */
    final private String[] images;
    /** Armazena as mensagens que aparecem quando o mouse passa por cima da carta virada de cada par. */
    final private String[] tooltip_msgs;

    /** Armazena os endereço da imagem do verso da carta. */
    final private String cover;
    /** Armazena a mensagem padrão que aparecem quando o mouse passa por cima da carta escondida. */
    final private String default_msg;

    /** Armazena uma cópia de cada carta que está virada para cima (com a imagem exposta). */
    private Card[] cards_facing_up;
    /** Armazena se é a vez do jogador (true), ou não (false). */
    private boolean is_players_turn;
    /** Armazena se é hora de checar para ver se existe um par formado (true), ou não (false). */
    private boolean ready_to_check;

    private int number_of_cards_facing_up;
    private int players_score;
    private int cpus_score;
    private int remaining_cards;

    public Controller(int number_of_pairs){
        this.images = new String[5];
        this.images[0] = "/1.png";
        this.images[1] = "/2.png";
        this.images[2] = "/3.png";
        this.images[3] = "/4.png";
        this.images[4] = "/5.png";

        this.tooltip_msgs = new String[5]; // e mensagens :)
        this.tooltip_msgs[0] = "<html><h3>In the grim darkness of the far future, <strong>there is only war</strong></h2></html>";
        this.tooltip_msgs[1] = "<html><h3>The Triumph of Saint Catherine</h3></html>";
        this.tooltip_msgs[2] = "<html><h3>A loyal guardsman serving in the name of the God-Emperor of Mankind</h3></html>";
        this.tooltip_msgs[3] = "<html><h3><i>The Fallen shall be forever remembered as the Emperor's finest</i></h3></html>";
        this.tooltip_msgs[4] = "<html><h3><i>No world shall be beyond my rule; no enemy shall be beyond my wrath.</i></h3></html>";

        this.default_msg = "<html><h4>Carta oculta</h4><u>Clique para revelar!</u></html>";

        this.cover = "/cover.png";

        this.cards_facing_up = new Card[2];
        this.is_players_turn = true;
        this.ready_to_check = false;

        this.number_of_cards_facing_up = 0;
        this.players_score = 0;
        this.cpus_score = 0;
        this.remaining_cards = (number_of_pairs * 2);
    }

    public Card[] start(int number_of_pairs, Card[] cards){
        cards = this.resetCards(number_of_pairs, cards);

        this.cards_facing_up[0] = null;
        this.cards_facing_up[1] = null;
        this.is_players_turn = true;
        this.ready_to_check = false;

        this.number_of_cards_facing_up = 0;
        this.players_score = 0;
        this.cpus_score = 0;
        this.remaining_cards = (number_of_pairs * 2);

        return cards;
    }

    public Card[] resetCards(int number_of_pairs, Card[] cards){
        int pair_id, position;
        pair_id = 0;

        int []occupied_positions;
        occupied_positions = this.generateArrayForPositions(number_of_pairs);

        Random r;
        r = new Random();

        for(int i = 0; i < (number_of_pairs * 2); ++i){
            position = r.nextInt(number_of_pairs * 2);
            while(this.isPositionOccupied(position, occupied_positions, i)){
                position = r.nextInt(number_of_pairs * 2);
            }

            occupied_positions[i] = position;

            cards[position] = new Card(i, pair_id, position, this.images[pair_id], this.cover, this.default_msg, this.tooltip_msgs[pair_id]);

            if(i % 2 != 0){
                ++pair_id;
            }

        }

        return cards;
    }

    public Card[] reset(int number_of_pairs){
        this.cards_facing_up[0] = null;
        this.cards_facing_up[1] = null;
        this.is_players_turn = true;
        this.ready_to_check = false;

        this.number_of_cards_facing_up = 0;
        this.players_score = 0;
        this.cpus_score = 0;
        this.remaining_cards = (number_of_pairs * 2);

        Card[] cards;
        cards = new Card[number_of_pairs * 2];

        cards = this.resetCards(number_of_pairs, cards);

        return cards;
    }

    /**
     * Retorna um vetor do tamanho passado com todas as posições preenchidas com -1.
     * @param number_of_pairs Número de pares de cartas no jogo.
     * @return O vetor preenchido com -1.
     */
    @Contract(pure = true)
    private int[] generateArrayForPositions(int number_of_pairs){
        int []out;
        out = new int[number_of_pairs * 2];

        for(int i = 0; i < (number_of_pairs * 2); ++i){
            out[i] = -1;
        }

        return out;
    }

    /**
     * Verifica se a posição está ocupada com o auxílio do vetor occupied.
     * @param position Posição a ser verificada.
     * @param occupied Vetor de posições já ocupadas.
     * @param current_iteration Iteração atual do loop for do método reset.
     * @return Se a posição já foi ocupada (false) ou não (true).
     */
    private boolean isPositionOccupied(int position,int []occupied, int current_iteration){

        for(int i = 0; i <= current_iteration; ++i){
            if(occupied[i] == position){
                return true;
            }
        }

        return false;
    }

    public boolean checkForAMatch(Card []cards){
        boolean match;
        match = false;

        if(this.cards_facing_up[0] != null && this.cards_facing_up[1] != null){
            for(Card c : this.getCardsFacingUp()){
                for(Card b : this.getCardsFacingUp()){
                    if(b.getCardId() != c.getCardId() && b.getPairId() == c.getPairId()){
                        match = true;
                    }
                }
            }
        }

        this.wasAMatch(match, cards);

        return match;
    }

    private void wasAMatch(boolean was_it, Card []cards){
        if(was_it){
            this.remaining_cards -= 2;

            if(this.isPlayersTurn()){
                this.players_score++;
            }
            else {
                this.cpus_score++;
            }

            this.number_of_cards_facing_up = 0;
        }

        this.ready_to_check = false;
    }

    public void unflipCards(){
        this.cards_facing_up[0] = null;
        this.cards_facing_up[1] = null;
        this.number_of_cards_facing_up = 0;
    }

    public void endOfTurn(boolean was_a_match){
        this.unflipCards();

        if(!was_a_match){
            this.is_players_turn = !this.is_players_turn;
        }
    }

    public void wait(int miliseconds){
        try {
            Thread.sleep(miliseconds);
        }
        catch (InterruptedException ex){
            // ignore...
        }
    }

    public void setReadyToCheck(boolean is_it_ready){
        this.ready_to_check = is_it_ready;
    }

    public void setNumber_of_cards_facing_up(int number_of_cards_facing_up) {
        this.number_of_cards_facing_up = number_of_cards_facing_up;
    }

    public void flippedACard(Card c){
        this.cards_facing_up[this.number_of_cards_facing_up] = c;
    }

    public Card[] getCardsFacingUp() {
        return cards_facing_up;
    }

    public boolean isPlayersTurn() {
        return is_players_turn;
    }

    public boolean isReadyToCheck(){ return this.ready_to_check; }

    public int getNumberOfCardsFacingUp() {
        return number_of_cards_facing_up;
    }

    public int getPlayersScore() {
        return players_score;
    }

    public int getCpusScore() {
        return cpus_score;
    }

    public int getRemainingCards() {
        return remaining_cards;
    }

    public String getScoreToString() {
        return "<html><h2>" + this.getCpusScore() + " <i>X</i> " + this.getPlayersScore() + "</h2></html>";
    }

    public String getTurnLabel(){
        if(this.isPlayersTurn()){
            return "<html><h1>Vez do jogador!</h1></html>";
        }
        else{
           return "<html><h1>Vez da CPU!</h1></html>";
        }
    }

}
