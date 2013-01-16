
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libxml\tree.h>
#include <libxml\parser.h>
#include <libxml\xpath.h>
#include <libxml\xpathInternals.h>


int main (int argc, char *argv[]){
	int retour = 0;
	xmlDocPtr fileXML;
	fileXML = xmlParseFile("XML.xlm");
	if(!fileXML) {
		fprintf(stderr, "Fichier XML vide ou introuvable !\n");
		return(EXIT_FAILURE);
	}
	printf("Stop");
	getchar();
	return(retour);
}