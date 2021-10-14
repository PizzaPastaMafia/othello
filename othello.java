public class othello {
    public static void main(String[] args) {
        int campo[][], mode, moveCounter = 0;
        campo = new int[11][11];
        mode = start();
        initCampo(campo);
        stampaCampo(campo);
        while(true){
            mode=1 ? routineM() : routineB();
            moveCounter++;
        }
    }

    static void routineM(){
        mossa();
        checkVittoria();
    }

    static void routineB(){
        mossa();
        mossaBot();
        checkVittoria();
    }

    static void mossa(){
        //TODO: implement this 
        //check available cells to move
        //input of user
    }

    static void mossaBot(){
        //TODO: implement this 
        //check available cells to move
        //calculate best moves
    }

    static int start(){
        System.out.println("1- single player\n 2- bot");
        return Leggi.unInt;
    }
    static void initCampo(int campo[][]){
        /*
        1 = NERO;
        2 = BIANCO;
        0 = NUIE;
        */
        for(int i=0; i<campo.length; i++){
            for(int j=0; j<campo.length; j++){
                campo[i][j] = 0;
            }
        }
        
        campo[4][4] = campo[5][5] = 1;
        campo[4][5] = campo[5][4] = 2;
    }

    static void stampaCampo(int campo[][]){
        for(int i=0; i<campo.length-1; i++){
            for(int j=0; j<campo.length-1; j++){
                System.out.print(campo[i][j]+ " ");
            }
            System.out.println();
        }
    }
}