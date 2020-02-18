import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
import java.io.*;

import java.util.Comparator;

public class P1 {
	public static void main(String[] args) {

		int nr_elements;
		Integer[] array;

		//citirea datelor
		try {
			MyScanner sc = new MyScanner(new FileInputStream("p1.in"));
			nr_elements = sc.nextInt();
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
			PrintWriter pw = new PrintWriter(new File("p1.out"));
			pw.printf("%d\n", max_dif1(nr_elements, array));
			pw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return;
	}
		
	private static int max_dif1(int nr_elements, Integer[] array) {
		//sortez vectorul descrescator
		//mereu Tuzgu ia prima valoare(adica cea care este mai mare)
		//iar Ritza pe urmatoarea mai mica
		Arrays.sort(array, new Comparator<Integer>(){
			@Override
			public int compare(Integer a, Integer b){
				return b.compareTo(a);
			}
		});

		int sum_Tuzgu = 0;
		int sum_Ritza = 0;

		for(int i = 0; i <= (nr_elements - 1); i += 2){
			//daca sirul are numar impar de elemente
			//pe ultimul il adaug in suma lui Tuzgu
			if(i == (nr_elements - 1)){
				sum_Tuzgu += array[i]; 
				break;
			}

			sum_Tuzgu += array[i];
			sum_Ritza += array[i + 1];
		}
			
		//datorita sortarii descrescatoare a sirului
		//diferenta finala reprezinta diferenta maxima pentru Tuzgu
		return (sum_Tuzgu - sum_Ritza);
			
	}


}



class MyScanner {
	BufferedReader br;
	StringTokenizer st;
		 
	public MyScanner(FileInputStream f) {
		br = new BufferedReader(new InputStreamReader(f));
	}

	String next() throws IOException {
		while (st == null || !st.hasMoreElements())
			st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}
		 
	int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	void close() throws IOException {
		br.close();
	}
}