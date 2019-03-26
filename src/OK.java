import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class OK {

	static int K;
	static int Q;
	static int m = 0;
	static Double time =0.0;
	static int qp;
	static int akt = 0;
	static int cost=0;
	/*static int x[] = new int[20000];
	static int y[] = new int[20000];
	static int q[] = new int[20000];
	static int e[] = new int[20000];
	static int l[] = new int[20000];
	static int d[] = new int[20000];
	static int s[] = new int[20000];
	static int wynik[] = new int[20000];*/
	static int[] x ;
	static int[] y ;
	static int[] q ;
	static int[] e ;
	static int[] l ;
	static int[] d;
	static int[] s ;
	static int[] wynik ;

	
	public static double odl(int i,int akt){
		return Math.sqrt((x[akt]-x[i])*(x[akt]-x[i])+(y[akt]-y[i])*(y[akt]-y[i]));
	}
	
	
	public static Boolean Checked(){
		
		for(int i = 1; i <= m; i++){
			if(s[i] == 0)  return false;
		}
		
		
		return true;
	}
	
	
public static Boolean CheckError(int licznik){
		
		int jest=0;
		
		for(int i = 0; i < licznik; i++){
			if(wynik[i]==-1 && jest == 1) return true;
			if(wynik[i]==-1) jest = 1;
			else jest = 0;
			
		}
		
		return false;
	}
	
	public static int min(){
		int min = akt;
		 
		for(int i = 1;i<=m;i++){
			if(s[i]==0 && q[i] <= qp && (qp - q[i]) >=0 && time+ odl(i,akt) <= l[i] /*&& (time + odl(i,akt)+ d[i] + odl(i,0) ) <= l[0] &&  (e[i]+ d[i] + odl(i,0) ) <= l[0]*/){
				//System.out.print(time + odl(i,akt)+ d[i] + odl(i,0) + " = qp " + l[0]+ " ");
				if(s[min] == 1 || min == 0 || e[i]<=e[min]||(e[i]==e[min] && odl(i,akt) < odl(min,akt))) min = i;
			}
		}
		if(s[min] == 1 || min == 0) return -1;
		 return min;
		
		
	}
	
	
	
	
	public static void SzukajSciezek() throws FileNotFoundException{
		int cap =0;
		int mp = 0;
		qp =Q;
		int akt2;
		int licznik=0;
		int trasy= 0;
		while(mp == 0){
	//	 System.out.print( 0 + " > ");
			trasy++;
		while (cap ==0){
		 akt2= akt;	
		 akt = min();
		 
		 if(akt == -1) {
			akt = akt2;
			 break;
		 	}
		 s[akt] = 1;
		 wynik[licznik] = akt;
		 licznik++;
		 time = time + odl(akt2,akt);
		 if(time < e[akt]) time = (double)e[akt];
		 time += d[akt];
		 qp = qp - q[akt];
		 if(qp ==0) cap = 1;
		 
	//	 System.out.print( akt + " + " +  time + " + " +  qp + " + " + s[akt] + " > " );
		}
		
		wynik[licznik] = -1;
		licznik++;
		if(CheckError(licznik)) break;
		
		cost += time+(int)odl(0,akt);
		
		time = 0.0;
		qp = Q;
		akt = 0;
		cap = 0;
	//	System.out.println("");
		if (Checked()== true ) mp = 1;
		}
	//	System.out.println("Koszt = " + cost + "\nliczba tras = " + trasy);
	//	for(int i = 0;i <= licznik; i++){
	//		if(wynik[i]>0) System.out.print(wynik[i] + " > ");
	//		else System.out.print("\n");
					
	//	}
	//	PrintWriter zapis = new PrintWriter("wynik.txt");
		//if(!CheckError(licznik)){
			//zapis.println(trasy );
			System.out.println(trasy );
			for(int i = 0;i <= licznik; i++){
			//	if(wynik[i]>0)zapis.print(wynik[i] + " ");
			//	else zapis.println("");
				if(wynik[i]>0)System.out.print(wynik[i] + " ");
				else if (wynik[i+1] > 0 ) System.out.println("");
					
			}
		//}
	//	else System.out.print("-1");
	//	else zapis.println("-1");
	//	zapis.close();
	}
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException {	
	

	//	try{
	//		File plik = new File("dane.txt");
			Scanner wejscie = new Scanner(System.in);
//		for (int a = 0; a <4; a++){
//			System.out.println(wejscie.nextLine());
//		}
			K = wejscie.nextInt();
			Q = wejscie.nextInt();
	//		for (int a = 0; a <4; a++){
	//			System.out.println(wejscie.nextLine());
	//		}
			x = new int[K];
			y = new int[K];
			q = new int[K];
			e = new int[K];
			l = new int[K];
			d = new int[K];
			s = new int[K];
			wynik = new int[3*K] ;
			int i;
			while(wejscie.hasNextInt()){
				i = wejscie.nextInt();
				x[i] = wejscie.nextInt();	
				
				y[i] = wejscie.nextInt();
				q[i] = wejscie.nextInt();
				e[i] = wejscie.nextInt();
				l[i] = wejscie.nextInt();
				d[i] = wejscie.nextInt();
				s[i] = 0;
				m = i;
			}
			wejscie.close();
	//	}
	//	catch(FileNotFoundException e){
	//		System.out.print("file not found");
	//	}
		
		
		SzukajSciezek();
		//System.out.print(x[2]+ "   " + y[2]+ "   " + q[2]+ "   " + a[2]+ "   " + b[2]+ "   " + s[2]+ "   " );
		
		
	}
	
}
