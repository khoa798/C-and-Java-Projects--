//-----------------------------------------------------------------------------
// Search.java
// Search
// Khoa Hoang
// klhoang
// Searches through an array of strings from an input file 
// to find a specified target value from the user
//-----------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;

class Search {
	
   public static int binarySearch(String[] A, int p, int r,  String target){
      int q;
      if(p > r) {
         return -1;
      }else{
         q = (p+r)/2;
         if(target.compareTo(A[q]) == 0){
            return q;
         }else if(target.compareTo(A[q]) < 0 ){
            return binarySearch(A, p, q-1, target);
         }else{ // target.compareTo(A[q] > 0 ( i.e the target string is "bigger" than current )
            return binarySearch(A, q+1, r, target);
         }
      }
   }
   

   static void mergeSort(String[] word, int[] lineNumber, int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2;
         mergeSort(word,lineNumber,p, q);
         mergeSort(word,lineNumber,q+1, r);
         merge(word,lineNumber, p, q, r);
      }
   
   }

   static void merge(String[] word, int[] lineNumber, int p, int q, int r){
		int n1 = q-p+1;
		int n2 = r-q;
      String[] L = new String[n1];
      String[] R = new String[n2];
	  int[] intL = new int[n1];
	  int[] intR = new int[n2];
      int i, j, k;

      for(i=0; i<n1; i++){
	   L[i] = word[p+i];
	   intL[i] = lineNumber[p+i];
	  }
      for(j=0; j<n2; j++){
		R[j] = word[q+j+1];
		intR[j] = lineNumber[q+j+1];
	  }
      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
            if( L[i].compareTo(R[j]) < 0 ){
               word[k] = L[i];
			   lineNumber[k] = intL[i];
               i++;
            }else{
				word[k] = R[j];
				lineNumber[k] = intR[j];
				j++;
            }
         }else if( i<n1 ){
            word[k] = L[i];
			lineNumber[k] = intL[i];
            i++;
         }else{ // j<n2
            word[k] = R[j];
			lineNumber[k] = intR[j];
            j++;
         }
      }
   }
   
	public static void main(String[] args) throws IOException {
		
		if(args.length<2){
			System.err.println("Usage: Search file target1 [target2 target3 ..]");
			System.exit(1);
		}
		
		
		Scanner in = new Scanner(new File(args[0]));
		in.useDelimiter("\\Z"); // matches the end of file character
		String s = in.next(); // read in whole file as a single String
		in.close();
		String[] lines = s.split("\n"); // split s into individual lines
		int lineCount = lines.length; // extract length of the resulting array
		String[] word = new String[lineCount];
		int[] line = new int[lineCount];
		for(int dummy = 0; dummy < lineCount; dummy++){
			int actualLineNumber = dummy + 1;
			// assigns the numbers in order into an array starting from 1
			line[dummy] = actualLineNumber;
		}
		
		Scanner in2 = new Scanner(new File(args[0]));
		// count the number of lines in file
		int lineNumber = 0;
		while( in2.hasNextLine() ){
			//creates array of words from input file
			word[lineNumber] = in2.nextLine();
			lineNumber++;
		}
		in2.close();
		
		//Merges array of words, while keeping track of its original line number
		mergeSort(word, line, 0, word.length-1); 
		
		for(int temp = 1; temp<args.length; temp++){ // starts at 1 because args[0] is input file
			int found = binarySearch(word, 0, word.length-1, args[temp]);
			if(found == -1){
				System.out.println(args[temp]+" not found");
			} else {
				System.out.println(args[temp]+" found on line "+line[found]);
			}
		}
		
	}
}
