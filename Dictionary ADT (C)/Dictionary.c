//-----------------------------------------------------------------------------
// Dictionary.c
// Dictionary
// C version of Dictionary ADT
// Khoa Hoang
// klhoang
// pa5
//-----------------------------------------------------------------------------


#ifndef _DICTIONARY_H_INCLUDE_
#define _DICTIONARY_H_INCLUDE_
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

//insertion, deletion, lookup

const int tableSize=101;

// NodeObj
typedef struct NodeObj{
	char* key;
	char* value;
	struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

//newNode()
// constructor of the Node type

Node newNode(char* k, char* v){
	Node N = malloc(sizeof(NodeObj));
	assert(N!=NULL);
	N->key = k;
	N->value = v;
	N->next = NULL;
	return(N);
}

//DictionaryObj
typedef struct DictionaryObj{
	int numItems;
	Node* table;
} DictionaryObj;

typedef DictionaryObj* Dictionary;

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
	Dictionary N = malloc(sizeof(DictionaryObj));
	assert(N!=NULL);
	N->numItems = 0;
	N->table = calloc(tableSize, sizeof(DictionaryObj));
	assert(N->table != NULL);
	return(N);
}


// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
	int sizeInBits = 8*sizeof(unsigned int);
	shift = shift & (sizeInBits - 1);
	if ( shift == 0 )
		return value;
	return (value << shift) | (value >> (sizeInBits - shift));
}
	
// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
	unsigned int result = 0xBAE86554;
	while (*input) {
	result ^= *input++;
	result = rotate_left(result, 5);
	}
 return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
	return pre_hash(key)%tableSize;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
	if (pD != NULL && *pD != NULL){
		free(*pD);
		*pD = NULL;
	}
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
	if( D==NULL ){
		fprintf(stderr, 
			"Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
   }
	return(D->numItems==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
	if( D == NULL){
		fprintf(stderr,
			"Dictionary Error: calling size() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	return (D->numItems);
}

// private helper function
Node findKey(Dictionary D, char* key){
	int keyArrayIndex = hash(key);
	Node N = D->table[keyArrayIndex];
	while( N != NULL){
		if (strcmp(N->key, key) == 0){
			return N;
		}
		N = N->next;
	}
		return NULL;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
	Node N = findKey(D, k);
	if (N == NULL){
		return NULL;
	}
	return N->value;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
	int keyArrayIndex = hash(k);
	Node M = findKey(D, k);
	if (M != NULL){
		fprintf(stderr,
			"Dictionary Error: cannot insert duplicate keys.\n");
		exit(EXIT_FAILURE);
	}
	
	Node N = D->table[keyArrayIndex];
	if(N == NULL){ // if the list is empty
		D->table[keyArrayIndex] = newNode(k, v);
	}else{
		Node P = newNode(k,v);
		P->next = D->table[keyArrayIndex];
		D->table[keyArrayIndex] = P;
	}
	
	D->numItems++;
	
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
	if (D == NULL){
		fprintf(stderr, 
			"Dictionary Error: calling delete() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
		
	}
	
	Node M = findKey(D, k);
	int keyArrayIndex = hash(k);
	Node N = D->table[keyArrayIndex];
	Node P = NULL;
	
	if(M == NULL){
		fprintf(stderr, 
			"Dictionary Error: cannot delete non-existent key\n");
		exit(EXIT_FAILURE);
	}

	if(strcmp(N->key, k) == 0){
		Node P = N->next;
		free(N);
		N->next = NULL;
		D->table[keyArrayIndex] = P;
	}else{
		N = D->table[keyArrayIndex];
		int stop = 0;
		while(stop == 0){
			if(N==NULL){
				stop = 1;
				break;
			}
			
			if(N->next == NULL){
				stop = 1;
				break;
			}
			if( strcmp(N->next->key, k ) == 0 ){
				stop = 1;
				P = N;
				break;
			}
			N = N->next;
		}
		// break lands here
		N = P->next;
		P->next = N->next;
		N->next = NULL;
		free(N);
		}
	   D->numItems--;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
	if( D==NULL ){
		fprintf(stderr, 
			"Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
   }
   Node N;
   Node temp;
   for(int i = 0; i<tableSize; i++){
	  N = D->table[i];
	  while (N != NULL){
		  temp = N;
		  N = N->next;
		  free(temp);
	  }
	   
	   
   }
	N = NULL;
	temp = NULL;
	D->numItems = 0;
	free(D->table);
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
	Node N;
	if (D == NULL){
		fprintf(stderr, 
			"Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
		
		
	}
	for(int i=0; i<tableSize;i++){
		for(N=D->table[i]; N!=NULL; N=N->next){
			fprintf(out, "%s %s", N->key, N->value);
			fprintf(out, "\n");
		}
	}
}

#endif