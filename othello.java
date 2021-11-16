import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class othello {
    public static int[][] campo = new int[8][8];
    public static int[][] campoConMosse = new int[8][8];
    public static int moveCounter;
    public static void main(String[] args) throws IOException {
        int mode = start();
        
        //int[][] campoVecchio = new int[8][8];
        
        initCampo(campo);
        
        //initCampo(campoConMosse);
        
        controlloMosse(campoConMosse, campo, moveCounter);

        
        
        new GUI();

        
        int controlloFine = 0;
        
        while(controlloFine < 2){
            //ClearConsole();
            
            int n = controlloMosse(campoConMosse, campo, moveCounter);
            
            //System.out.println(n);
            
            if(n != 0)
            {
                controlloFine = 0;
                
                /*stampaCampo(campoConMosse);
                
                if(mode == 1){
                    routineM(campo, campoConMosse, moveCounter);
                } else {
                    routineB(campo, campoConMosse, moveCounter);
                }*/
                
            }
            else
            {
                controlloFine++;
            }
            /*for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < 8; j++)
                {
                    int cnt = 0;
                    
                    if(!ControlloMossa(campo, i, j, 1))
                    {
                        cnt = cnt+2;
                    }
                    
                    if(!ControlloMossa(campo, i, j, 2))
                    {
                        cnt++;
                    }
                    
                    if(cnt == 1)
                    {
                        campo[i][j] = 3;
                    }
                    else if(cnt == 2)
                    {
                        campo[i][j] = 4;
                    }
                    else if(cnt == 3)
                    {
                        campo[i][j] = 5;
                    }
                    
                }
            }
            
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }  
            */
           
            //checkVittoria();
            
            //char a = Leggi.unChar();
            
            moveCounter++;
        }
        
        ControlloVittoria(campo);
    }
    
    
    static void ControlloVittoria(int campoConMosse[][]){
        int uno = 0, due = 0;
        
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(campo[i][j] == 1)
                {
                    uno++;
                }
                else if(campo[i][j] == 2)
                {
                    due++;
                }
            }
        }
        
        if(uno > due)
        {
            System.out.println("Giocatore 1 ha vinto: "+ uno + " a " + due);
        }
        else if(due > uno)
        {
            System.out.println("Giocatore 2 ha vinto: "+ due + " a " + uno);
        }
        else
        {
            System.out.println("Pareggio: "+ uno + " a " + due);
        }
        
    }
    
    static int controlloMosse(int campoConMosse[][], int campo[][], int moveCounter){
        int nMosse = 0;
        
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                campoConMosse[i][j] = campo[i][j];
            }
        }
        
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                
                if(!ControlloMossa(campo, i, j, moveCounter%2, 0))
                {
                    if(moveCounter%2 == 0)
                    {
                        campoConMosse[i][j] = 3;
                    }
                    else
                    {
                        campoConMosse[i][j] = 4;
                    }
                    nMosse++;
                }
            }
        }
        
        return nMosse;
    }
    
    static void routineM(int campo[][], int campoConMosse[][], int moveCounter){
        mossa(campo, campoConMosse, moveCounter);
    }

    static void routineB(int campo[][], int campoConMosse[][], int moveCounter){
        mossa(campo, campoConMosse, moveCounter);
        mossaBot();
    }

    static void mossa(int campo[][], int campoConMosse[][], int moveCounter){
        
        if(moveCounter % 2 == 0){
            System.out.println("Giocatore 1 è il tuo turno");
        } else {
            System.out.println("Giocatore 2 è il tuo turno");
        }
        
        int x, y;
        
        boolean mossa = true;
        
        do
        {
            System.out.println("Riga:");
            x = Leggi.unInt();
            System.out.println("Colonna");
            y = Leggi.unInt();
            
            mossa = ControlloMossa(campo, x, y, moveCounter, 1);
            
        }
        while(mossa);
        
        
        
        if(moveCounter % 2 == 0){
            campo[x][y] = 1;
        } else {
            campo[x][y] = 2;
        }
    }
    
    static boolean ControlloMossa(int campo[][], int x, int y, int moveCounter, int cambio){
        
        int limSu = -1, limGiu = 1, limSin = -1, limDes = 1;
        
        if(x < 0 || x > campo.length || y < 0 || y > campo.length || (campo[x][y] > 0 && campo[x][y] < 3))
        {
            //ClearConsole();
            //System.out.println("caso 1");
            return true;
        }
        
        if(y == 0)
        {
            limSin = 0;
        }
        else if(y == campo.length-1)
        {   
            limDes = 0;
        }
        
        if(x == 0)
        {
            limSu = 0;
        }
        else if(x == campo.length-1)
        {   
            limGiu = 0;
        }
        
        int avver;
        
        if(moveCounter % 2 == 0){
            avver = 2;
        } else {
            avver = 1;
        }
        
        int cnt = 0;
        
        for(int i = limSu; i <= limGiu; i++)
        {
            for(int j = limSin; j <= limDes; j++)
            {
                if(campo[x+i][y+j] == avver && campo[x+i][y+j] > 0 && campo[x+i][y+j] < 3)
                {
                    cnt++;
                    
                    int tmp = 0;
                    
                    /*
                    1    2    3
                    
                    8   cen   4
                    
                    7    6    5

                    */
                    
                   switch(i+j){
                        case -2:
                        tmp = 1;
                        break;
                        
                        case -1:
                        if(i < 0)
                        {
                            tmp = 2;
                        }
                        else
                        {
                            tmp = 8;
                        }
                        break;
                            
                        case 0:
                        if(j > 0)
                        {
                            tmp = 3;
                        }
                        else
                        {
                            tmp = 7;
                        }
                        break;
                            
                        case 1:
                        if(i == 0)
                        {
                            tmp = 4;
                        }
                        else
                        {
                            tmp = 6;
                        }
                        break;
                            
                        case 2:
                        tmp = 5;
                        break;
                    }
                    
                    //System.out.print("tmp: "+tmp + "\n");
                    
                    
                    if(controllaComprese(campo, x, y, tmp, moveCounter%2))
                    {
                        //System.out.print("si");
                        if(cambio == 1)
                        {
                            cambiaColore(campo, x, y, tmp, moveCounter%2);
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        cnt--;
                    }
                }
            }
        }
        
        if(cnt == 0)
        {
            //ClearConsole();
            
            //System.out.println("caso 2");
            
            return true;
        }
        
        return false;
    }
    
    static void cambiaColore(int campo[][], int x, int y, int direz, int moveCounter){
        int giocato;
        
        if(moveCounter % 2 == 0){
            giocato = 1;
        } else {
            giocato = 2;
        }
        
        switch(direz){
            case 1:
                for(int i = x-1, j = y-1; i >= 0 && j >= 0 && campo[i][j] < 3; i--, j--)
                {
                    if(campo[i][j] != giocato)
                    {
                        campo[i][j] = giocato;
                    }
                    else
                    {
                        return;
                    }
                }
            case 2:
                for(int i = x-1; i >= 0 && campo[i][y] < 3; i--)
                {
                    if(campo[i][y] != giocato)
                    {
                        campo[i][y] = giocato;
                    }
                    else
                    {
                        return;
                    }
                }
            case 3:
                for(int i = x-1, j = y+1; i >= 0 && j < 8 && campo[i][j] < 3; i--, j++)
                {
                    if(campo[i][j] != giocato)
                    {
                        campo[i][j] = giocato;
                    }
                    else
                    {
                        return;
                    }
                }
            case 4:
                for(int i = y+1; i < 8 && campo[x][i] < 3; i++)
                {
                    if(campo[x][i] != giocato)
                    {
                         campo[x][i] = giocato;
                    }
                    else
                    {
                        return;
                    }
                }
            case 5:
                for(int i = x+1, j = y+1; i < 8 && j < 8 && campo[i][j] < 3; i++, j++)
                {
                    if(campo[i][j] != giocato)
                    {
                        campo[i][j] = giocato;
                    }
                    else
                    {
                        return;
                    }
                }
            case 6:
                for(int i = x+1; i < 8 && campo[i][y] < 3; i++)
                {
                    if(campo[i][y] != giocato)
                    {
                        campo[i][y] = giocato;
                    }
                    else
                    {
                        return;
                    }
                }
            case 7:
                for(int i = x+1, j = y-1; i < 8 && j >= 0 && campo[i][j] < 3; i++, j--)
                {
                    if(campo[i][j] != giocato)
                    {
                        campo[i][j] = giocato;
                    }
                    else
                    {
                        return;
                    }
                }
            case 8:
                for(int i = y-1; i >= 0 && campo[x][i] < 3; i--)
                {
                    if(campo[x][i] != giocato)
                    {
                        campo[x][i] = giocato;
                    }
                    else
                    {
                        return;
                    }
                }
            
        }
    }
    
    
    static boolean controllaComprese(int campo[][], int x, int y, int direz, int moveCounter){
        int controllo = 0;
        
        int avver, giocato;
        
        if(moveCounter % 2 == 0){
            avver = 2;
            giocato = 1;
        } else {
            avver = 1;
            giocato = 2;
        }
        
        switch(direz){
            case 1:
                for(int i = x-1 , j = y-1; i >= 0 && j >= 0 && campo[i][j] != 0 && controllo == 0; i--, j--)
                {
                    if(campo[i][j] == giocato && i < x-1/* && j < y-1*/)
                    {
                        controllo = 1;
                    }
                    else if(campo[i][j] > 2)
                    {
                        return false;
                    }
                }
                
                if(controllo == 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case 2:
                for(int i = x-1; i >= 0 && campo[i][y] != 0 && controllo == 0; i--)
                {
                    if(campo[i][y] == giocato && i < x-1)
                    {
                        controllo = 1;
                    }
                    else if(campo[i][y] > 2)
                    {
                        return false;
                    }
                }
                
                if(controllo == 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case 3:
                for(int i = x-1, j = y+1; i >= 0 && j < 8 && campo[i][j] != 0 && controllo == 0; i--, j++)
                {
                    if(campo[i][j] == giocato && i < x-1/* && j < y-1*/)
                    {
                        controllo = 1;
                    }
                    else if(campo[i][j] > 2)
                    {
                        return false;
                    }
                }
                
                if(controllo == 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case 4:
                for(int i = y+1; i < 8 && campo[x][i] != 0 && controllo == 0; i++)
                {
                    if(campo[x][i] == giocato && i > y+1)
                    {
                        controllo = 1;
                    }
                    else if(campo[x][i] > 2)
                    {
                        return false;
                    }
                }
                
                if(controllo == 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
                
            case 5:
                for(int i = x+1, j = y+1; i < 8 && j < 8 && campo[i][j] != 0 && controllo == 0; i++, j++)
                {
                    if(campo[i][j] == giocato && i > x+1/* && j < y-1*/)
                    {
                        controllo = 1;
                    }
                    else if(campo[i][j] > 2)
                    {
                        return false;
                    }
                }
                
                if(controllo == 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case 6:
                for(int i = x+1; i < 8 && campo[i][y] != 0 && controllo == 0; i++)
                {
                    if(campo[i][y] == giocato && i > x+1)
                    {
                        controllo = 1;
                    }
                    else if(campo[i][y] > 2)
                    {
                        return false;
                    }
                }
                
                if(controllo == 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case 7:
                for(int i = x+1, j = y-1; i < 8 && j >= 0 && campo[i][j] != 0 && controllo == 0; i++, j--)
                {
                    if(campo[i][j] == giocato && i > x+1/* && j < y-1*/)
                    {
                        controllo = 1;
                    }
                    else if(campo[i][j] > 2)
                    {
                        return false;
                    }
                }
                
                if(controllo == 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case 8:
                for(int i = y-1; i >= 0 && campo[x][i] != 0 && controllo == 0; i--)
                {
                    if(campo[x][i] == giocato && i < y-1)
                    {
                        controllo = 1;
                    }
                    else if(campo[x][i] > 2)
                    {
                        return false;
                    }
                }
                
                if(controllo == 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            
        }
        
        return true;
    }
    
    static void mossaBot(){
        //TODO: implement this 
        //check available cells to move
        //calculate best moves
    }

    static int start(){
        System.out.println("1- single player\n2- bot");
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
        char a = '◘', b = '○';
        
        for(int i=-1; i<campo.length; i++){
            if(i == -1)
            {
                System.out.print("    ");
            }
            else
            {
                System.out.print(i +"   ");
            }
            
            for(int j=0; j<campo.length; j++){
                if(i == -1)
                {
                    System.out.print(j + " ");
                }
                else
                {
                    if(campo[i][j] == 1)
                    {
                        System.out.print(a+ " ");
                    }
                    else if(campo[i][j] == 2)
                    {
                        System.out.print(b+ " ");
                    }
                    else if(campo[i][j] == 0)
                    {
                        System.out.print("  ");
                    }
                    else
                    {
                        System.out.print(campo[i][j]+ " ");
                    }
                }
            }
            if(i == -1)
            {
                System.out.println();
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