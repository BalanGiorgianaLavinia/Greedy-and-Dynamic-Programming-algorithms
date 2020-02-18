import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

public class P2 {
    public static void main(String[] args) {
		int nr_elements, nr_removes;
		Integer[] array;

		//citirea datelor
		try {
			Scanner sc = new Scanner(new File("p2.in"));
            nr_elements = sc.nextInt();
            nr_removes = sc.nextInt();
			array = new Integer[nr_elements];
			for (int i = 0; i < nr_elements; i++) {
				array[i] = sc.nextInt();
			}

			sc.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


		//scrierea output-ului
		try {
			PrintWriter pw = new PrintWriter(new File("p2.out"));
			pw.printf("%d\n", max_dif2(nr_elements, nr_removes, array));
			pw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

		


	private static int max_dif2(int nr_elements, int nr_removes, Integer[] array){
		//	Sortez vectorul descrescator
		Arrays.sort(array, new Comparator<Integer>(){
			@Override
			public int compare(Integer a, Integer b){
				return b.compareTo(a);
			}
		});
		
		int[] aux_arr1 = new int[nr_elements];
		int[] aux_arr2 = new int[nr_elements];


		//	Initializez vectorul initial aux_arr1
		//va contine diferenta maxima pentru Tuzgu la fiecare pas
		//adica pt fiecare subvector de 1, 2,...n elemente
		aux_arr1[0] = array[0];

		for(int index = 1; index < nr_elements; index++){
			//	Pe pozitiile impare este randul Ritzei,
			//ceea ce alege Ritza, pierde Tuzgu
			if(index % 2 == 1){
				aux_arr1[index] = aux_arr1[index - 1] - array[index];
			}else{
				aux_arr1[index] = aux_arr1[index - 1] + array[index];
			}
		}


		int var_aux;
		//	Pentru fiecare eliminare ce trebuie facuta creez un nou vector,
		//aux_arr2 cu diferentele maxime pentru fiecare pas
		for(int k = 1; k <= nr_removes; k++){
			for(int index = k; index < nr_elements; index++){
				var_aux = ((index - k) % 2 == 0) ? aux_arr2[index - 1] + array[index] :
											   aux_arr2[index - 1] - array[index];

				//	Calculez diferenta maxima la fiecare pas 
				//ca fiind maximul a doua cazuri detaliate in README
				aux_arr2[index] = Math.max(aux_arr1[index - 1], var_aux);
			}

			//	Ultimul element din aux_ar2 este rezultatul final,
			// daca acesta va fi vectorul corespunzator ultimei eliminari
			if(k == nr_removes)	return aux_arr2[nr_elements - 1];

			//	Daca mai am eliminari de facut, 
			//pastrez vectorul de diferente maxime corespunzator ultimei eliminari
			//si reiau algorritmul
			for(int i = 0; i < nr_elements ; i++){
				aux_arr1[i] = aux_arr2[i];
				aux_arr2[i] = 0;
			}
		}
		
		return 1;
	}

}