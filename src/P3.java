import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class P3 {
    public static void main(String[] args) {
		int nr_elements;
		Long[] array;

        //citirea datelor
		try {
			Scanner sc = new Scanner(new File("p3.in"));
			nr_elements = sc.nextInt();
			array = new Long[nr_elements];
			for (int i = 0; i < nr_elements; i++) {
				array[i] = sc.nextLong();
			}
			sc.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    
        
        //scrierea output-ului
		try {
			PrintWriter pw = new PrintWriter(new File("p3.out"));
			pw.printf("%d\n", dif_max3(nr_elements, array));
			pw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

        return;
    }
    

    private static long dif_max3(int nr_elements, Long[] array){
        //  Imi creez o matrice n*n 
        //Va contine diferentele maxime pentru subvectori de 1, 2, ..., n elemente
        long[][] dp = new long[nr_elements][nr_elements];

        //  Conditie initiala: pe diagonala principala am elementele vectorului
        for(int index = 0; index < nr_elements; index ++)
            dp[index][index] = array[index];
        
        //  Completez jumatatea de jos a matricii, tot pe diagonala
        //  Diagonalele de sub reprezinta diferentele maxime  
        //pentru subvectori de lungimi 2, 3,...,n
        for(int length = 1; length < nr_elements; length++){
            //i repr coloana, j linia
            for(int i = 0, j = i + length; j < nr_elements; i++, j++){
                long left = array[i] - dp[j][i + 1];
                long right = array[j] - dp[j - 1][i];

                //  Diferenta maxima intre doi indecsi este maximul intre:
                //diferenta maxima pentru cazul in care se alege primul element
                //si diferenta maxima pentru cand se alege ultimul
                dp[j][i] = Math.max(left, right);
            }
        }

        return dp[nr_elements - 1][0];

    }
}