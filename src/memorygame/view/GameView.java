package memorygame.view;

import javax.swing.*;

import memorygame.control.CpuBrain;
import memorygame.model.Card;
import memorygame.control.Controller;

/**
 * Painel do jogo; invocado dentro da janela principal.
 * @author Leonardo
 * @version 2.5.0b - stable
 * @since 1.5.0b - stable
 */
public class GameView extends JPanel {

    /** Painel que contém as cartas. */
    final private JPanel card_panel;
    /** Cartas. */
    private Card []card;
    /** Botão de reset. */
    final private JButton reset_button;
    /** Botão para sair. */
    final private JButton exit_button;
    /** Painel que contém o placar */
    final private JPanel score_panel;
    /** Título do placar. */
    final private JLabel title_score;
    /** Placar. */
    final private JLabel score_actual;
    /** Indicador de quem é a vez. */
    final private JLabel title_whose_turn;

    /** Controle as operações e dados do jogo. */
    final private Controller control;
    /** CPU. */
    private CpuBrain cpu;

    /** Constante que guarda o número de pares; Alterá-la seria o primeiro passo para adaptar o jogo a aceitar mais do que 4 pares de cartas. */
    static final int NUMBER_OF_PAIRS = 4;

    /**
     * Inicializa o painel principal do jogo.
     */
    public GameView() {
        title_whose_turn = new JLabel();
        score_panel = new JPanel();
        title_score = new JLabel();
        score_actual = new JLabel();
        exit_button = new JButton();
        reset_button = new JButton();
        card_panel = new JPanel();

        this.control = new Controller(NUMBER_OF_PAIRS);
        this.card = new Card[NUMBER_OF_PAIRS * 2];
        this.card = this.control.start(NUMBER_OF_PAIRS, this.card);

        this.cpu = new CpuBrain(NUMBER_OF_PAIRS);

        this.initComponents();
        this.updateButtons();
    }

    /**
     * Reseta o jogo, reembaralhando as cartas.
     */
    private void reset(){
        Card[] temp;
        temp = this.control.reset(NUMBER_OF_PAIRS);

        for(Card c : temp){
            if(this.card[c.getPosition()].hasBeenRemoved()){
                this.card[c.getPosition()].undoRemove();
            }
            if(this.card[c.getPosition()].isFacingUp()){
                this.card[c.getPosition()].flip();
            }

            this.card[c.getPosition()].copyInfo(c);
        }

        this.cpu = new CpuBrain(NUMBER_OF_PAIRS);

    }

    /**
     * Código gerado pelo GUI Designer do NetBeans e utilizado como base para a implementação do painel do jogo;
     * Inicializa e posiciona os componentes dentro do painel.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(85, 45));

        title_whose_turn.setText("<html><h1>Vez do jogador!</h1></html>");

        title_score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_score.setText("<html><h1>&nbsp;Placar:</h1><h2>CPU <i>X</i> Player</h2></html>");

        score_actual.setText("<html><h2>0 <i>X</i> 0</h2></html>");
        score_actual.setToolTipText("");

        javax.swing.GroupLayout score_panelLayout = new javax.swing.GroupLayout(score_panel);
        score_panel.setLayout(score_panelLayout);
        score_panelLayout.setHorizontalGroup(
                score_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(score_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(title_score)
                                .addContainerGap())
                        .addGroup(score_panelLayout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(score_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(143, Short.MAX_VALUE))
        );
        score_panelLayout.setVerticalGroup(
                score_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(score_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(title_score, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(score_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        exit_button.setText("Reset");
        exit_button.setPreferredSize(new java.awt.Dimension(85, 45));
        exit_button.addActionListener(this::resetButtonActionPerformed);

        reset_button.setText("Sair");
        reset_button.setPreferredSize(new java.awt.Dimension(85, 45));
        reset_button.addActionListener(this::exitButtonActionPerformed);

        this.card[0].setPreferredSize(new java.awt.Dimension(150, 150));
        this.card[0].addActionListener(this::card1ActionPerformed);

        this.card[1].setPreferredSize(new java.awt.Dimension(150, 150));
        this.card[1].addActionListener(this::card2ActionPerformed);

        this.card[2].setPreferredSize(new java.awt.Dimension(150, 150));
        this.card[2].addActionListener(this::card3ActionPerformed);

        this.card[3].setPreferredSize(new java.awt.Dimension(150, 150));
        this.card[3].addActionListener(this::card4ActionPerformed);

        this.card[4].setPreferredSize(new java.awt.Dimension(150, 150));
        this.card[4].addActionListener(this::card5ActionPerformed);

        this.card[5].setPreferredSize(new java.awt.Dimension(150, 150));
        this.card[5].addActionListener(this::card6ActionPerformed);

        this.card[6].setPreferredSize(new java.awt.Dimension(150, 150));
        this.card[6].addActionListener(this::card7ActionPerformed);

        this.card[7].setPreferredSize(new java.awt.Dimension(150, 150));
        this.card[7].addActionListener(this::card8ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(card_panel);
        card_panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 678, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(this.card[4], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(this.card[5], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(this.card[6], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(this.card[7], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(this.card[0], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(this.card[1], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(this.card[2], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(this.card[3], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 381, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.card[0], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(this.card[1], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(this.card[2], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(this.card[3], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(57, 57, 57)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.card[5], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(this.card[4], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(this.card[6], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(this.card[7], javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(366, 366, 366)
                                .addComponent(score_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                                .addComponent(exit_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(reset_button, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(209, 209, 209)
                                                .addComponent(card_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(427, 427, 427)
                                                .addComponent(title_whose_turn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(reset_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(exit_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(121, 121, 121))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(score_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addComponent(title_whose_turn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(card_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método principal; Representa um movimento do jogador; acionado toda vez que alguma carta é pressionada.
     * @param position Posição da carta selecionada.
     */
    private void move(int position){
        this.playersMove(position);
        this.updateButtons();
        this.checkForMatch();
        this.updateButtons();

        this.cpusTurn();
        this.updateButtons();

    }

    /**
     * Jogado do jogador; Verifica se é sua vez e, se for, vira a carta selecionada desde que ela não esteja já virada e que existam duas cartas já viradas.
     * @param position Posição da carta selecionada.
     */
    public void playersMove(int position){
        if(this.control.isPlayersTurn()){
            if(!this.control.isReadyToCheck()){
                this.flipCard(position);
            }
        }
    }

    /**
     * Atualiza o texto do placar e do indicador de turno; Invoca a janela de fim de jogo quando todos os pares foram formados.
     */
    private void updateButtons(){
        if(this.control.getRemainingCards() == 0){
            this.gameOver();
        }

        this.title_whose_turn.setText(this.control.getTurnLabel());

        this.score_actual.setText(this.control.getScoreToString());
    }

    /**
     * Verifica se houve um acerto ou não quando duas cartas são reveladas.
     */
    private void checkForMatch(){

        if(this.control.isReadyToCheck()){
            this.control.wait(200);

            boolean was_a_match;
            was_a_match = this.control.checkForAMatch();

            if(!was_a_match){
                this.control.wait(500);
                this.unflipCards();

                this.control.endOfTurn(false);
                this.updateButtons();

            }
            else {
                int pos0, pos1;
                pos0 = this.control.getCardsFacingUp()[0].getPosition();
                pos1 = this.control.getCardsFacingUp()[1].getPosition();

                this.card[pos0].remove();
                this.card[pos1].remove();

                this.cpu.cardRemoved(this.card[pos0]);
                this.cpu.cardRemoved(this.card[pos1]);

                this.control.endOfTurn(true);
            }
        }
    }

    /**
     * Movimento da CPU; Vira duas cartas toda vez que é a vez da CPU.
     */
    private void cpusTurn(){
        int pos;

        while(!this.control.isPlayersTurn()){
            pos = this.cpu.makeAMove();
            this.flipCard(pos);

            pos = this.cpu.makeAMove();
            this.flipCard(pos);

            this.control.wait(500);

            this.checkForMatch();

            this.updateButtons();
        }
    }

    /**
     * Vira uma carta baseando-se em sua posição.
     * @param position Posição da carta.
     */
    private void flipCard(int position){
        if(!this.card[position].isFacingUp() && this.control.getNumberOfCardsFacingUp() < 2){
            this.card[position].flip();
            this.control.flippedACard(this.card[position]);
            this.cpu.cardFlipped(this.card[position]);

            this.control.setNumber_of_cards_facing_up(this.control.getNumberOfCardsFacingUp() + 1);
        }

        if(this.control.getNumberOfCardsFacingUp() == 2){
            this.control.setReadyToCheck(true);

            if(!this.card[position].isFacingUp()){
                this.card[position].flip();
            }
        }
    }

    /**
     * Desvira todas as cartas que estão viradas e não foram removidas.
     */
    private void unflipCards(){
        for(int i = 0; i < NUMBER_OF_PAIRS * 2; ++i){
            if(this.card[i].isFacingUp() && !this.card[i].hasBeenRemoved()){
                this.card[i].flip();
            }
        }

        this.control.unflipCards();
    }

    /**
     * Janela de fim de jogo; Invocada quando todos os pares foram formados; Reseta o jogo quando é fechada.
     */
    private void gameOver(){
        String msg;
        msg = "<html><h2>";

        if(this.control.getCpusScore() > this.control.getPlayersScore()){
            msg = msg + "Vencedor: CPU</h2><h4>";
            msg = msg + this.control.getCpusScore() + "<i>X</i>" + this.control.getPlayersScore();
            msg = msg + "</h4></html>";
        }
        else if(this.control.getCpusScore() < this.control.getPlayersScore()){
            msg = msg + "Vencedor: Jogador</h2><h4>";
            msg = msg + this.control.getPlayersScore() + "<i>X</i>" + this.control.getCpusScore();
        }
        else{
            msg = msg + "EMPATE</h2><h4>";
            msg = msg + this.control.getPlayersScore() + "<i>X</i>" + this.control.getCpusScore();
        }

        JOptionPane.showMessageDialog(this, msg, "Fim de Jogo!", JOptionPane.PLAIN_MESSAGE);
        this.reset();
    }

    // ------------------------------------> Action Event daqui em diante
    //                                                     V

    /** Fecha o programa quando o botão de sair é pressionado. */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    /** Reseta o jogo quando o botão de reset é pressionado. */
    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.reset();
        this.updateButtons();
    }

    /** Chama move para a posição 0 quando a carta 1 é selecionada.
     * @see this.move
     * */
    private void card1ActionPerformed(java.awt.event.ActionEvent evt) {
        this.move(0);
    }

    /** Chama move para a posição 1 quando a carta 2 é selecionada.
     * @see this.move
     * */
    private void card2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.move(1);
    }

    /** Chama move para a posição 2 quando a carta 3 é selecionada.
     * @see this.move
     * */
    private void card3ActionPerformed(java.awt.event.ActionEvent evt) {
        this.move(2);
    }

    /** Chama move para a posição 3 quando a carta 4 é selecionada.
     * @see this.move
     * */
    private void card4ActionPerformed(java.awt.event.ActionEvent evt) {
        this.move(3);
    }

    /** Chama move para a posição 4 quando a carta 5 é selecionada.
     * @see this.move
     * */
    private void card5ActionPerformed(java.awt.event.ActionEvent evt) {
        this.move(4);
    }

    /** Chama move para a posição 5 quando a carta 6 é selecionada.
     * @see this.move
     * */
    private void card6ActionPerformed(java.awt.event.ActionEvent evt) {
        this.move(5);
    }

    /** Chama move para a posição 6 quando a carta 7 é selecionada.
     * @see this.move
     * */
    private void card7ActionPerformed(java.awt.event.ActionEvent evt) {
        this.move(6);
    }

    /** Chama move para a posição 7 quando a carta 8 é selecionada.
     * @see this.move
     * */
    private void card8ActionPerformed(java.awt.event.ActionEvent evt) {
        this.move(7);
    }

    // ------------------------------------------------------------------
}