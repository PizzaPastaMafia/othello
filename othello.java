public class othello {
    public static int[][] campo = new int[8][8];
    public static void main(String[] args) {
        int mode, moveCounter = 0;
       
        initCampo(campo);
        campo[0][0] = 3;
        campo[0][1] = 3;

        new GUI();


        /*while(true){
            if(mode == 1){
                routineM(campo, moveCounter);
            } else {
                routineB(campo, moveCounter);
            }
            moveCounter++;
        }*/
    }

    static void routineM(int campo[][], int moveCounter){
        mossa(campo, moveCounter);
        //checkVittoria();
    }

    static void routineB(int campo[][], int moveCounter){
        mossa(campo, moveCounter);
        mossaBot();
        //checkVittoria();
    }

    static void mossa(int campo[][], int moveCounter){
        //TODO: implement this 
        //check available cells to move
        
        if(moveCounter % 2 == 0){
            System.out.println("Giocatore 1 è il tuo turno");
        } else {
            System.out.println("Giocatore 2 è il tuo turno");
        }

        System.out.println("Riga:");
        int y = Leggi.unInt();
        System.out.println("Colonna");
        int x = Leggi.unInt();
        
        if(moveCounter % 2 == 0){
            campo[x][y] = 1;
        } else {
            campo[x][y] = 2;
        }
    }

    static void mossaBot(){
        //TODO: implement this 
        //check available cells to move
        //calculate best moves
    }

    static int start(){
        System.out.println("1- single player\n 2- bot");
        return Leggi.unInt();
    }
    static void initCampo(int campo[][]){
        /*
        0 = NUIE;
        1 = NERO;
        2 = BIANCO;
        3 = MOSSA POSSIBILE NERO
        4 = MOSSA POSSIBILE BIANCO
        5 = MOSSA POSSIBILE NERO E BIANCO
        */
        for(int i=0; i<campo.length; i++){
            for(int j=0; j<campo.length; j++){
                campo[i][j] = 0;
            }
        }
        
        campo[3][3] = campo[4][4] = 1;
        campo[3][4] = campo[4][3] = 2;
    }

    static void stampaCampo(int campo[][]){
        for(int i=0; i<campo.length-1; i++){
            for(int j=0; j<campo.length-1; j++){
                System.out.print(campo[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public static void ClearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
              
            if(operatingSystem.contains("Windows")){        
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            } 
        }catch(Exception e){
            System.out.println(e);
        }
    }
}