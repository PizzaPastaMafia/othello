import java.util.Random;
import java.io.IOException;
public class othello {
    public static int dimensioneCampo = 8;
    public static int[][] campo = new int[dimensioneCampo][dimensioneCampo];
    public static int[][] campoConMosse = new int[dimensioneCampo][dimensioneCampo];
    public static int moveCounter = 0;
    public static int mode;
    public static int difficolta = 4;
    public static void main(String[] args) {
        initCampo(campo);
        controlloMosse(campoConMosse, campo, moveCounter);
        
        try {
            new GUI();
        }
        catch(IOException e) {
            e.printStackTrace();
        }        

    }
    
    static int getPscore(){
        int pscore = 0;
        for(int i = 0; i < dimensioneCampo; i++){
            for(int j = 0; j < dimensioneCampo; j++){
                if(campo[i][j] == 1){
                    pscore++;
                }
            }
        }
        return pscore;
    }

    static int getVscore(){
        int vscore = 0;
        for(int i = 0; i < dimensioneCampo; i++){
            for(int j = 0; j < dimensioneCampo; j++){
                if(campo[i][j] == 2){
                    vscore++;
                }
            }
        }
        return vscore;
    }
    
    static int controlloVittoria(int campoConMosse[][]){
        if(controlloMosse(campoConMosse, campo, moveCounter) == 0)
        {
            moveCounter = (moveCounter+1)%2;
        }

        if(controlloMosse(campoConMosse, campo, moveCounter) != 0)
        {
            return -1;
        }

        int uno = 0, due = 0, altro = 0;

        for(int i = 0; i < dimensioneCampo; i++)
        {
            for(int j = 0; j < dimensioneCampo; j++)
            {
                if(campo[i][j] == 1)
                {
                    uno++;
                }
                else if(campo[i][j] == 2)
                {
                    due++;
                } else {
                    altro++;
                }
            }
        }
        
        if(uno + due == 64 || altro == 0){
            if(uno > due)
            {
                return 1;
            }
            else if(due > uno)
            {
                return 2;
            }
            else
            {
                return 0;
            }
            
        }
        return -1;
        
    }
    
    static int controlloMosse(int campoConMosse[][], int campo[][], int moveCounter){
        int nMosse = 0;
        
        for(int i = 0; i < dimensioneCampo; i++)
        {
            for(int j = 0; j < dimensioneCampo; j++)
            {
                campoConMosse[i][j] = campo[i][j];
            }
        }
        
        for(int i = 0; i < dimensioneCampo; i++)
        {
            for(int j = 0; j < dimensioneCampo; j++)
            {
                
                if(!controlloMossa(campo, i, j, moveCounter%2, 0))
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
    
    static void routineBot(int campo[][], int campoConMosse[][], int moveCounter){
        controlloMosse(campoConMosse, campo, moveCounter);
        mossaBot(campo, campoConMosse, moveCounter);
    }
    
    static void mossaBot(int campo[][], int campoConMosse[][], int moveCounter){
        int[][] mosse = new int[40][3];
        
        svuotaMosse(mosse);
        
        riempiMosse(campoConMosse, mosse);
        
        calcolaMosse(campo, campoConMosse, mosse, moveCounter);
        
        int a = scegliMigliore(mosse);
        
        boolean inutile = controlloMossa(campo, mosse[a][0], mosse[a][1], moveCounter, 1);
        
        if(moveCounter % 2 == 0){
            campo[mosse[a][0]][mosse[a][1]] = 1;
        } else {
            campo[mosse[a][0]][mosse[a][1]] = 2;
        }
    }
    
    static int scegliMigliore(int mosse[][]){
        int max = mosse[0][2];
        
        int[] vet = new int[40];
        
        int maxIndice = 0;
        
        int cnt = 0;
        
        for(int i = 0; mosse[i][0] != -1; i++)
        {
            if(mosse[i][2] > max)
            {
                max = mosse[i][2];
                maxIndice = i;
            }
        }
        
        for(int i = 0; mosse[i][0] != -1; i++)
        {
            if(mosse[i][2] == max)
            {
                vet[cnt] = i;
                cnt++;
            }
        }
        
        Random rand = new Random();
        
        int n = rand.nextInt(cnt);
        
        return vet[n];
    }
    
    static void calcolaMosse(int campo[][], int campoConMosse[][], int mosse[][], int moveCounter){
        int[][] campoProva = new int [dimensioneCampo][dimensioneCampo];
        
        int avver;
        
        if(moveCounter % 2 == 0){
            avver = 2;
        } else {
            avver = 1;
        }
        
        for(int i = 0; i < mosse.length && mosse[i][0] != -1; i++)
        {
            copiaCampo(campo, campoProva);
            
            int somma = 0;
            
            if(difficolta > 1)
            {
                boolean inutile = controlloMossa(campoProva, mosse[i][0], mosse[i][1], moveCounter, 1);
                
                if(campoProva[1][1] == avver && campo[1][1] != avver)
                {
                    somma++;
                }
                
                if(campoProva[1][6] == avver && campo[1][6] != avver)
                {
                    somma++;
                }
                
                if(campoProva[6][1] == avver && campo[6][1] != avver)
                {
                    somma++;
                }
                
                if(campoProva[6][6] == avver && campo[6][6] != avver)
                {
                    somma++;
                }
                
                if(somma > 0 && difficolta > 3)
                {
                    mosse[i][2] = -3;
                }
                else if(mosse[i][0] == 0 && mosse[i][1] == 0 || mosse[i][0] == 0 && mosse[i][1] == dimensioneCampo-1 || mosse[i][0] == dimensioneCampo-1 && mosse[i][1] == 0 || mosse[i][0] == dimensioneCampo-1 && mosse[i][1] == dimensioneCampo-1)
                {
                    if(difficolta == 2)
                    {
                        mosse[i][2] = 1;
                    }
                    else
                    {
                        mosse[i][2] = 3;
                    }
                }
                else if(mosse[i][0] == 1 && mosse[i][1] == 1 || mosse[i][0] == 1 && mosse[i][1] == dimensioneCampo-2 || mosse[i][0] == dimensioneCampo-2 && mosse[i][1] == 1 || mosse[i][0] == dimensioneCampo-2 && mosse[i][1] == dimensioneCampo-2)
                {
                    if(difficolta > 2)
                    {
                        mosse[i][2] = -2;
                        
                        if(difficolta == 3)
                        {
                            mosse[i][2]++;
                        }
                    }
                    else
                    {
                        mosse[i][2] = 0;
                    }
                }
                else if(mosse[i][0] > 1 && mosse[i][1] > 1 && mosse[i][0] < dimensioneCampo-2 && mosse[i][1] < dimensioneCampo-2)
                {
                    mosse[i][2] = 0;
                }
                else if(mosse[i][0] == 0 || mosse[i][0] == dimensioneCampo-1 || mosse[i][1] == 0 || mosse[i][1] == dimensioneCampo-1)
                {
                    mosse[i][2] = 1;
                    
                    if(difficolta > 3)
                    {
                        mosse[i][2] += controllaBordi(campoConMosse, mosse, campo, i, moveCounter);
                    }
                }
                else
                {
                    if(difficolta > 2)
                    {
                        mosse[i][2] = -1;
                    }
                    else
                    {
                        mosse[i][2] = 0;
                    }
                }
            }
            else
            {
                if(difficolta == 0)
                {
                    mosse[i][2] = 0;
                }
                else
                {
                    mosse[i][2] = contaGirate(mosse, campo, i, moveCounter);
                }
            }
        }
    }
    
    static int contaGirate(int mosse[][], int campo[][], int i, int moveCounter){
        int[][] campoProva = new int [dimensioneCampo][dimensioneCampo];
        
        copiaCampo(campo, campoProva);
        
        int avver, giocat, somma = 0, somma2 = 0;
        
        if(moveCounter % 2 == 0){
            avver = 2;
            giocat = 1;
        } else {
            avver = 1;
            giocat = 2;
        }
        
        for(int y = 0; y < dimensioneCampo; y++)
        {
            for(int j = 0; j < dimensioneCampo; j++)
            {
                if(campoProva[y][j] == giocat)
                {
                    somma++;
                }
            }
        }
        
        boolean inutile = controlloMossa(campoProva, mosse[i][0], mosse[i][1], moveCounter, 1);
        
        for(int y = 0; y < dimensioneCampo; y++)
        {
            for(int j = 0; j < dimensioneCampo; j++)
            {
                if(campoProva[y][j] == giocat)
                {
                    somma2++;
                }
            }
        }
        
        return somma2-somma;
    }
    
    static void copiaCampo(int campo[][], int campoDaCopiare[][])
    {
        for(int y = 0; y < dimensioneCampo; y++)
        {
            for(int j = 0; j < dimensioneCampo; j++)
            {
                campoDaCopiare[y][j] = campo[y][j];
            }
        }
    }
    
    static int controllaBordi(int campoConMosse[][], int mosse[][], int campo[][], int i, int moveCounter){
        int[][] campoProva = new int [dimensioneCampo][dimensioneCampo];
        
        copiaCampo(campo, campoProva);
        
        int avver;
        
        int valore = 0;
        
        controlloMosse(campoConMosse, campo, moveCounter);
        
        int somma = 0;
        
        if(moveCounter % 2 == 0){
            avver = 2;
        } else {
            avver = 1;
        }
        
        boolean si = controlloMossa(campoProva, mosse[i][0], mosse[i][1], moveCounter, 1);

        
        if(mosse[i][1] == 0 || mosse[i][1] == dimensioneCampo-1)
        {
            if(campoConMosse[mosse[i][0]+1][mosse[i][1]] == avver || campoConMosse[mosse[i][0]-1][mosse[i][1]] == avver/* && (mosse[i][0]+mosse[i][1] == 1 || mosse[i][0]+mosse[i][1] == 13 || mosse[i][0]+mosse[i][1] == 6 || mosse[i][0]+mosse[i][1] == 8)*/)
            {
                
                if(campoProva[mosse[i][0]+1][mosse[i][1]] == avver || campoProva[mosse[i][0]-1][mosse[i][1]] == avver)
                {
                    return -2;
                }
                else
                {
                    return 1;
                }
            }
        }
        else
        {
            if(campoConMosse[mosse[i][0]][mosse[i][1]+1] == avver || campoConMosse[mosse[i][0]][mosse[i][1]-1] == avver/*  && (mosse[i][0]+mosse[i][1] == 1 || mosse[i][0]+mosse[i][1] == 13 || mosse[i][0]+mosse[i][1] == 6 || mosse[i][0]+mosse[i][1] == 8)*/)
            {
                if(campoProva[mosse[i][0]][mosse[i][1]+1] == avver || campoProva[mosse[i][0]][mosse[i][1]-1] == avver)
                {
                    return -2;
                }
                else
                {
                    return 1;
                }
            }
        }       
        return 0;
    }
    
    static void riempiMosse(int campoConMosse[][], int mosse[][]){
        int cnt = 0;
        
        for(int i = 0; i < campoConMosse.length; i++)
        {
            for(int j = 0; j < campoConMosse[0].length; j++)
            {
                if(campoConMosse[i][j] > 2)
                {
                    mosse[cnt][0] = i;
                    mosse[cnt][1] = j;
                    cnt++;
                }
            }
        }
    }
    
    static void svuotaMosse(int mosse[][]){
        for(int i = 0; i < mosse.length; i++)
        {
            for(int j = 0; j < mosse[0].length; j++)
            {
                if(j < 2)
                {
                    mosse[i][j] = -1;
                }
                else
                {
                    mosse[i][j] = 0;
                }
            }
        }
    }
    
    static boolean controlloMossa(int campo[][], int x, int y, int moveCounter, int cambio){
        
        int limSu = -1, limGiu = 1, limSin = -1, limDes = 1;
        
        if(x < 0 || x > campo.length || y < 0 || y > campo.length || (campo[x][y] > 0 && campo[x][y] < 3))
        {
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
    
                    if(controllaComprese(campo, x, y, tmp, moveCounter%2))
                    {
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
                for(int i = x-1, j = y+1; i >= 0 && j < dimensioneCampo && campo[i][j] < 3; i--, j++)
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
                for(int i = y+1; i < dimensioneCampo && campo[x][i] < 3; i++)
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
                for(int i = x+1, j = y+1; i < dimensioneCampo && j < dimensioneCampo && campo[i][j] < 3; i++, j++)
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
                for(int i = x+1; i < dimensioneCampo && campo[i][y] < 3; i++)
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
                for(int i = x+1, j = y-1; i < dimensioneCampo && j >= 0 && campo[i][j] < 3; i++, j--)
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
                    if(campo[i][j] == giocato && i < x-1)
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
                for(int i = x-1, j = y+1; i >= 0 && j < dimensioneCampo && campo[i][j] != 0 && controllo == 0; i--, j++)
                {
                    if(campo[i][j] == giocato && i < x-1)
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
                for(int i = y+1; i < dimensioneCampo && campo[x][i] != 0 && controllo == 0; i++)
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
                for(int i = x+1, j = y+1; i < dimensioneCampo && j < 8 && campo[i][j] != 0 && controllo == 0; i++, j++)
                {
                    if(campo[i][j] == giocato && i > x+1)
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
                for(int i = x+1; i < dimensioneCampo && campo[i][y] != 0 && controllo == 0; i++)
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
                for(int i = x+1, j = y-1; i < dimensioneCampo && j >= 0 && campo[i][j] != 0 && controllo == 0; i++, j--)
                {
                    if(campo[i][j] == giocato && i > x+1)
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
    
    static void initCampo(int campo[][]){
        /*
        0 = NUIE;
        1 = NERO;
        2 = BIANCO;
        3 = MOSSA POSSIBILE NERO
        4 = MOSSA POSSIBILE BIANCO
        /////////5 = MOSSA POSSIBILE NERO E BIANCO
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
        char a = 'O', b = 'X';
        
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
}