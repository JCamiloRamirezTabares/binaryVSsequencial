package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class MainBinary {
	public static int[] bP;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data/InPut_32_Casos.txt"));	
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/OutPut_Binary.txt"));
		
		System.out.println("Welcome to Exact Sum with Binary Search");
		
		
		String line= br.readLine();
		int c = 0;
		while(line != null) {
			c++;
			
			int N = Integer.parseInt(line);
			bP = new int[N];
			
			
			line=br.readLine();
			String[] parts= line.split(" ");
			
			for(int i = 0; i < N; i++) {
				bP[i] = Integer.parseInt(parts[i]);
			}
			
			Arrays.sort(bP);
			
			line=br.readLine();
			int money = Integer.parseInt(line);
			
			int book1 = 0;
			int book2 = 0;
			int count = 0;
			for(int i = 0; i < N; i++) {

				int numb = money-bP[i];
				int pos= binarySearch(numb, i);
				//System.out.println(pos);
				int temp1=0;
				int temp2=0;
				if(pos>=0) {
					if(bP[i]< bP[pos]) {
						temp1 = bP[i];
						temp2 = bP[pos];
					}else {
						temp1 = bP[pos];
						temp2 = bP[i];
					}

					count++;

					if(count==1) {
						book1=temp1;
						book2=temp2;			
					}else if(count>1) {
						if((temp2-temp1)<(book2-book1)) {
							book1=temp1;
							book2=temp2;	
						}
					}
				}

			}
			bw.write(c + ". Peter should buy books whose prices are "+book1+" and "+book2+".\n\n");
			line=br.readLine();
			line=br.readLine();
		}
		
		br.close();
		bw.close();
		System.out.println("OutPut_Binary.txt actualizado con " + c + " casos");
		
	}

	public static int binarySearch(int num, int a){
		int pos = -1;
		int i = 0;
		int j = bP.length-1;
		
		while(i<=j && pos < 0){
	
			int m= (i+j)/2;
			
			if(m!=a && bP[m]==num){
				pos =m;
			}else if(bP[m]>num){
				j=m-1;
			}else{
				i=m+1;
			}
		}
		return pos;
	}

}
