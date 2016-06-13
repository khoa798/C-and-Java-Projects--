// Khoa Hoang
// Dictionary.java
// Dictionary
// Creates an ADT in the form of linked lists for strings.


import java.util.Scanner;
import java.io.*;


public class Dictionary implements DictionaryInterface{
	// private inner Node class
	private class Node{
		String key;
		String value;
		Node next;
		
		Node(String k, String v){
			key = k;
			value = v;
			next = null;
			
			
		}
	}

   // Fields for the Dictionary class
   private Node head;   // reference to first Node in List
   private int numItems;  // number of items in this Dictionary
   private Node tail;

   // Dictionary()
   // constructor for the Dictionary class
   public Dictionary(){
      head = null;
      numItems = 0;
   }
   // private helper function -------------------------------------------------

   // findKey()
   // returns a reference to the Node at position key in this Dictionary
	private Node findKey(String key){
		Node N = head;
		
		while( N != null){
			if (N.key.equals(key)){
				return N;
			}
			N = N.next;
		}
		return null;
   }

	
	// isEmpty()
   // pre: none
   // returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty(){
	   return (numItems == 0);

   }

   // size()
   // pre: none
   // returns the number of entries in this Dictionary
   public int size(){
	   return numItems;
   }

   // lookup()
   // pre: none
   // returns value associated key, or null reference if no such key exists
   public String lookup(String key){
	  Node N = findKey(key);
	  if (N == null){
		  return null;
	  }
	  return N.value;
	  
   }


   // insert()
   // inserts new (key,value) pair into this Dictionary
   // pre: lookup(key)==null
   public void insert(String key, String value) throws DuplicateKeyException{
	   Node M = findKey(key);
	   if(M != null){
		   throw new DuplicateKeyException("cannot insert duplicate keys");
		}
			if(numItems == 0){ // If the list is empty
				Node N = new Node(key, value);
				head = N;
				tail = N;
			}else{
				Node P = findKey(tail.key);
				P.next = new Node(key, value);
				tail = P.next;
			}
		numItems++;

   }

   // delete()
   // deletes pair with the given key
   // pre: lookup(key)!=null
	public void delete(String key) throws KeyNotFoundException{
		Node M = findKey(key);
		Node N = head;
		Node P = null;
		
		if(M == null){
			throw new KeyNotFoundException("cannot delete non-existent key");
		}
		if(N.key.equals(key)){
			N = head;
			head = head.next;
			N.next = null;
		}else{
			N = head;
			boolean stop = false;
			while(stop != true){
				if(N==null){
					stop = true;
					break;
				}
				if(N.next == null){
					stop = true;
					break;
				}
				if(N.next.key.equals(key)){
					stop = true;
					P = N;
					break;
				}
			N = N.next;
			}
		// break lands here
		N = P.next;
		//System.out.println("Node N = P.next complete");
		P.next = N.next;
		//System.out.println("P.next = N.next complete");
		N.next = null;
		//System.out.println("N.next = null complete");
			
		}
	   numItems--;
   }
   
   // makeEmpty()
   // pre: none
   public void makeEmpty(){
	   head = null;
	   numItems = 0;
	   tail = null;
   }

   // toString()
   // returns a String representation of this Dictionary
   // overrides Object's toString() method
   // pre: none
   public String toString(){
	  StringBuffer sb = new StringBuffer();
      Node N = head;

      for( ; N!=null; N=N.next){
         sb.append(N.key).append(" ").append(N.value).append("\n");
      }
      return new String(sb);
	   
   }


}	
		
		
		
		
	
	
	
	
	
	
