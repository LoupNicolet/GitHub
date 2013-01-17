
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main (int argc, char *argv[]){
	 int retour = 0;
	char *arg = argv[1];
	int nb = strcmp (arg, "111111111111111");
	if(nb > 0){
		retour = 1;
	}else{
		retour = 0;
	}
	return(retour);
}