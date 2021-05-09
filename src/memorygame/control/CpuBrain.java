package memorygame.control;

import memorygame.model.Card;

/**
 * Emula o modo de pensar e agir de uma máquina.
 * @author Leonardo
 * @version 2.5.0b - stable
 * @since 2.0.0b - stable
 */
public class CpuBrain {
    /** Armazena uma cópia de cada carta vista pela CPU; a posição onde a carta é armazenada é igual à posição da carta no jogo;
     * o vetor é inicializado com "null" em todas as posições. */
    private final Card[] cards_seen;
    /** Armazena dois inteiros que correspondem às posições das próximas duas cartas a serem escolhidas pela CPU. */
    private final int[] next_move;
    /** Armazena o número de cartas viradas pela CPU no turno atual. */
    private int cards_flipped_this_turn;
    /** Armazena valores booleanos de acordo com o posição da carta, representada pelo índice no vetor;
     * true se a carta com posição == índice no vetor foi vista pela CPU, false do contrário. */
    private final boolean[] positions_seen;

    /**
     * Inicializa a Cpu baseando-se na número de pares diferentes que existem.
     * @param number_of_pairs Número de pares diferentes.
     */
    public CpuBrain(int number_of_pairs){
        this.cards_seen = new Card[number_of_pairs * 2];
        this.positions_seen = new boolean[number_of_pairs * 2];
        this.cards_flipped_this_turn = 0;

        this.next_move = new int[2];
        this.next_move[0] = -1;
        this.next_move[1] = -1;

        for(int i = 0; i < number_of_pairs * 2; ++i){
            this.cards_seen[i] = null;
            this.positions_seen[i] = false;
        }
    }

    /**
     * Avisa a Cpu que uma carta c foi virada; adiciona a carta ao vetor de cartas vistas.
     * @param c Cópia da carta virada.
     */
    public void cardFlipped(Card c){
        this.cards_seen[c.getPosition()] = c;
        this.positions_seen[c.getPosition()] = true;
    }

    /**
     * Avisa a Cpu que uma carta c foi removida; altera o status da carta para "removida" se ela estiver no vetor de cartas vistas.
     * @param c Cópia da carta removida.
     */
    public void cardRemoved(Card c){
        for(int i = 0; i < this.cards_seen.length; ++i){
            if(i == c.getPosition() && this.cards_seen[i] != null){
                this.cards_seen[i].remove();
                break;
            }
        }
    }

    /**
     * Método através do qual a Cpu decide qual das duas posições armazendas em "next_move" devem ser selecionadas.
     * @return A posição que será selecionada.
     */
    public int makeAMove(){
        // Antes de começarmos:
        // -> Uma jogada ideal é aquela jogada em que existe certeza de que irá resultar em um par formado

        // se nenhuma carta foi jogada ainda...
        if(this.cards_flipped_this_turn == 0){
            this.next_move[0] = -1;
            this.next_move[1] = -1;

            // procura por uma jogada ideal...
            this.checkForAMove();

            ++this.cards_flipped_this_turn;

            // se não existe uma jogada ideal...
            if(this.next_move[0] == -1){

                // escolhe a primeira posição não vista ainda que aparecer para ser selecionada...
                for(int i = 0; i < this.positions_seen.length; ++i){
                    if(!this.positions_seen[i]){
                        this.next_move[0] = i;
                    }
                }

                // se todas as cartas foram vistas e não existe uma jogada a ser feita, o jogo acabou.
                if(this.next_move[0] == -1){
                    return -2;
                }
            }

            // atualiza a posição que vai ser selecionada, já que ela vai ser vista...
            this.positions_seen[this.next_move[0]] = true;

            // retorna a primeira jogada da vez
            return this.next_move[0];
        }

        // se uma carta já foi jogada...
        else{

            // e da primeira vez, não houve uma jogada ideal...
            if(this.next_move[1] == -1){
                // procura por uma...
                this.checkForAMove();
            }

            // se foi procurada e ainda sim não existe uma jogada ideal...
            if(this.next_move[1] == -1){

                // escolhe a primeira posição não vista ainda que aparecer para ser selecionada...
                for(int i = 0; i < this.positions_seen.length; ++i){
                    if(!this.positions_seen[i]){
                        this.next_move[1] = i;
                    }
                }

                // se todas as cartas foram vistas e não existe uma jogada a ser feita, o jogo acabou.
                if(this.next_move[1] == -1){
                    return -2;
                }
            }

            int temp;
            temp = this.next_move[1];

            // atualiza a posição que vai ser selecionada, já que ela vai ser vista...
            this.positions_seen[temp] = true;

            // essa foi a última jogada da vez, hora de resetar...
            this.resetNextMove();

            // retorna a segunda jogada da vez
            return temp;
        }
    }

    /**
     * Procura por uma jogada ideal (uma jogada em que existe certeza de que irá resultar em um par formado);
     * Guarda as posições necessárias para essa jogada em "next_move", ou -1 em ambas posições se não houver tal jogada.
     */
    private void checkForAMove(){
        Card[] temp = new Card[this.cards_seen.length];

        int count = 0;

        // verifica se a prox jogada foi inviabilizada por conta de alguma jogada anterior
        // e gera um vetor auxiliar para verificacao da melhor prox jogada
        for(int i = 0; i < this.positions_seen.length; ++i){
            if(this.positions_seen[i]){ // se a carta foi vista...
                if(this.cards_seen[i] != null){ // e não é nula...
                    if(!this.cards_seen[i].hasBeenRemoved()){ // se carta n foi removida, pode ser parte da prox jogada...
                        temp[count] = this.cards_seen[i];
                        ++count;
                    }
                    else{ // se foi removida; verifica se estava na prox jogada gerada anteriormente, se estiver, cancela a jogada
                        if(this.next_move[0] == this.cards_seen[i].getPosition() || this.next_move[1] == this.cards_seen[i].getPosition()){
                            this.next_move[0] = -1;
                            this.next_move[1] = -1;
                        }
                    }
                }
            }
        }

        OUTER:
        for(int i = 0; i < count; ++i){ // busca por uma jogada ideal...
            for(int j = 0; j < count; ++j){
                // se a carta em i e a carta em j possuem o mesmo id de par e posições diferentes são uma par...
                if (temp[i].getPairId() == temp[j].getPairId() && temp[i].getPosition() != temp[j].getPosition()) {

                    // a jogada ideal foi encontrada!
                    this.next_move[0] = temp[i].getPosition();
                    this.next_move[1] = temp[j].getPosition();

                    break OUTER;
                }
            }
        }

        // se uma jogada ideal não foi encontrada, o vetor "next_move" será [-1, -1], já que ou esse valor foi assinalado em sua inicialização
        // ou uma jogada teve de ser cancelada

    }

    /**
     * Reseta o próximo movimento da máquina.
     */
    private void resetNextMove(){
        this.next_move[0] = -1;
        this.next_move[1] = -1;

        this.cards_flipped_this_turn = 0;
    }

    /**
     * Retorna um vetor com as cartas vistas pela máquina.
     * @return Cartas vistas.
     * @deprecated 2.1.2 Abandonado por ter caído em desuso após primeira otimização do programa.
     */
    public Card[] getCardsSeen() {
        return cards_seen;
    }

}
