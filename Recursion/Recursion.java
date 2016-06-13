//-----------------------------------------------------------------------------
// Khoa Hoang
// Recursion
// Recursion.java
// Implements recursive methods
//-----------------------------------------------------------------------------

class Recursion{
	
   public static void main(String[] args){

	int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
	int[] B = new int[A.length];
	int[] C = new int[A.length];
	int minIndex = minArrayIndex(A, 0, A.length-1);
	int maxIndex = maxArrayIndex(A, 0, A.length-1);

	for(int x: A) System.out.print(x+" ");
	System.out.println();

	System.out.println( "minIndex = " + minIndex );
	System.out.println( "maxIndex = " + maxIndex );
	reverseArray1(A, A.length, B);
	for(int x: B) System.out.print(x+" ");
	System.out.println();

	reverseArray2(A, A.length, C);
	for(int x: C) System.out.print(x+" ");
	System.out.println();

	reverseArray3(A, 0, A.length-1);
	for(int x: A) System.out.print(x+" ");
	System.out.println();
	

	
	String s =("Hel");
	String b = ("llo");
	String n = s+b;
	System.out.println(n);
	String m = b+s;
	System.out.println(m);
	
 }
 
static void reverseArray1(int[] X, int n, int[] Y){
	 // if n == 0 do nothing
	 if ( n > 0){
		Y[n-1] = X[X.length-n];
		reverseArray1(X, n-1, Y);
	 }
	 
 }
 
static void reverseArray2(int[] X, int n, int[] Y){
	 // if n == 0 do mothing
	 if ( n > 0 ){
		 Y[X.length-n] = X[n-1];
		 reverseArray2(X, n-1, Y);
		 
	 }
	 
 }
 
 
static void reverseArray3(int[] X, int i, int j){
	 int temp = 0;
	 if ( i < j){
		temp = X[i];
		X[i] = X[j];
		X[j] = temp;
		reverseArray3(X, i+1, j-1);
		 
		 
		 
	 }
	 
 }
 
 
static int maxArrayIndex(int[] X, int p, int r){
	 int index = 0;
	 int value = 0;
	 for(p=0; p<r; p++){
		 if( X[p]>value){
			 index = p;
			 value = X[p];
		 }
	 }
	 return index;
 }
 
static int minArrayIndex(int[] X, int p, int r){
	int index = 0;
	int value = 0;
	for(p=0; p<r; p++){
		if( X[p]<value){
			index = p;
			value = X[p];
			 
			 
		}
	}
	return index;
 }
 
}