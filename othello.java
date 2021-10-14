public class othello {
    public static void main(String[] args) {
        int campo[][], mode, moveCounter = 0;
        campo = new int[11][11];
        mode = start();
        ClearConsole();
        initCampo(campo);
        while(true){
            ClearConsole();
            stampaCampo(campo);
            if(mode == 1){
                routineM(campo, moveCounter);
            } else {
                routineB(campo, moveCounter);
            }
            moveCounter++;
        }
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
        0
         = NUIE;
        1 = NERO;
        2 = BIANCO;
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