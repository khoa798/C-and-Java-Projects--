//-----------------------------------------------------------------------------
// DictionaryClient.c
// Test client for the Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   char* k;
   char* v;
   char* word1[] = {"one","two","three","four","five","six","seven"};
   char* word2[] = {"yo","this","is","12b","and","I","win"};
   int i;

   for(i=0; i<7; i++){
      insert(A, word1[i], word2[i]);
   }

   printDictionary(stdout, A);

   for(i=0; i<7; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }

   // insert(A, "five", "glow"); // error: duplicate keys

  // printDictionary(stdout, A);
  //delete(A, "fake");
  // makeEmpty(A);

   /*
   for(i=0; i<7; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }
*/
   // insert(A, "five", "glow"); // error

   //delete(A, "one");
 // delete(A, "three");
   //delete(A, "seven");
/*
   printDictionary(stdout, A);


   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));

   printf("%s\n", (isEmpty(A)?"true":"false"));
*/
   //makeEmpty(A);
  // freeDictionary(&A);

  // return(EXIT_SUCCESS);
}