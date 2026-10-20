// Cora Snyder
// 2/27/24
// CS 304 Computer Organization
// Assignment 3 Base Converter

#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <math.h>

// method for taking in number value
short get_operand(char mode){

    short i;
    short n = 0;

    //proceeds based on the mode we are in
    switch(mode){

        // case: mode is hex
        case 'H' : {
            printf("Enter hex value: ");
            scanf("%hX", &i);
            printf("%hX\n\n", i);
            return(i);
        }

        // case : mode is octal
        case 'O' : {
            printf("Enter octal value: ");
            scanf("%ho", &i);
            printf("%ho\n\n", i);
            return(i);
        }

        // case : mode is decimal
        case 'D' : {
            printf("Enter decimal value: ");
            scanf("%hd", &i);
            printf("%hd\n\n", i);
            return(i);
        }

        // this is a catch-all for everything else (prevents crash)
        default : {
            printf("Sorry, something went wrong! Please try again!");
            return(n);
        }
    }
}

// print bases method (everything inside the box)
void print_bases(short val, char mode){

    printf("****************************************");

    //creates all properly formatted number strings
    char oct_string[25] = {'\0'};
    sprintf(oct_string, "%06ho", val);
    char dec_string[25] = {'\0'};
    sprintf(dec_string,"%hd", val);
    char hex_string[25] = {'\0'};
    sprintf(hex_string, "%04hX", val);

    switch(mode){

        case 'H' : {

            // printing hex value

             printf("\n* Base Values:         Input Mode: Hex *");
             printf("\n*   Hex     :  %s", hex_string);

            int m = 23;
            while(hex_string[m] == '\0'){
                printf(" ");
                m--;
            }
             printf("*");

            // printing octal value

             printf("\n*   Octal   :  %s", oct_string);
             int c = 23;
             while(oct_string[c] == '\0'){
                printf(" ");
                c--;
             }
             printf("*");

             // printing decimal value

             printf("\n*   Decimal :  %s", dec_string);
             int d = 23;
             while(dec_string[d] == '\0'){
                printf(" ");
                d--;
             }
             printf("*");

             break;
        }

        default : {


            //printing hex value


            printf("\n* Base Values:         Input Mode: Dec *");
            printf("\n*   Hex     :  %s", hex_string);

            int h = 23;
            while(hex_string[h] == '\0'){
                printf(" ");
                h--;
            }
            printf("*");

            // printing octal value

            printf("\n*   Octal   :  %s", oct_string);
            int c = 23;
            while(oct_string[c] == '\0'){
                printf(" ");
                c--;
            }
            printf("*");

            // printing decimal value

            printf("\n*   Decimal :  %s", dec_string);
            int m = 23;
            while(dec_string[m] == '\0'){
                printf(" ");
                m--;
            }
            printf("*");

            break;
        }

        case 'O' : {

            //printing hexadecimal value

            printf("\n* Base Values:         Input Mode: Oct *");
            printf("\n*   Hex     :  %s", hex_string);
            int h = 23;
            while(hex_string[h] == '\0'){
                printf(" ");
                h--;
            }
            printf("*");

            // printing octal value

            printf("\n*   Octal   :  %s", oct_string);
            int m = 23;
            while(oct_string[m] == '\0'){
                printf(" ");
                m--;
            }
            printf("*");

            // printing decimal value

            printf("\n*   Decimal :  %s", dec_string);
            int d = 23;
            while(dec_string[d] == '\0'){
                printf(" ");
                d--;
            }
            printf("*");

            break;
        }
    }

    printf("\n****************************************\n");

}

// method for printing options and taking in option value

char print_menu(void){


        printf("\nPlease select one of the following options:");

        printf("\n\nO  Octal Mode");
        printf("\nH  Hexadecimal Mode");
        printf("\nD  Decimal Mode");

        printf("\n\nC  Clear Value");
        printf("\nS  Set Value");

        printf("\n\nQ  Quit");

        printf("\n\nOption: ");

        // takes in 10 char string

        char str[11];
        scanf("%10s", str);

        printf("%s", str);

        // checks to see if the string is either too long or not an option listed
        if(str[1] != '\0' || (toupper(str[0]) != 'O' && toupper(str[0]) != 'H' && toupper(str[0]) != 'D'
        && toupper(str[0]) != 'C' && toupper(str[0]) != 'S' && toupper(str[0]) != 'Q')){
            printf("\n\nInvalid option: %s\n", str);
            return('i');
        }

        printf("\n");

        return (toupper((str[0])));

}

// main method -- main switch statement and while loop

int main(void){

    int quit = 0;
    char mode = 'D';
    short val = 0000;

    print_bases(val,mode);

    // while loop allows program to quit when chosen
    while(!quit){

        switch(print_menu()){

            // each mode's case just prints out the selected mode, changes the value of mode, and prints
            case 'H' : {
                printf("Mode: Hexadecimal\n\n");
                mode = 'H';
                print_bases(val, mode);
                continue;
            }

            case 'O' : {
                printf("Mode: Octal\n\n");
                mode = 'O';
                print_bases(val, mode);
                continue;
            }

            case 'D' : {
                printf("Mode: Decimal\n\n");
                mode = 'D';
                print_bases(val, mode);
                continue;
            }

            // resets val to 0
            case 'C' : {
                printf("\n");
                val = 0000;
                print_bases(val, mode);
                continue;
            }

            case 'Q' : {
               quit = 1;
               continue;
            }
            case 'S' : {
                val = get_operand(mode);
                print_bases(val, mode);
                continue;
            }
            // catch all for preventing crashes
            default : {
                continue;
            }

        }

    }

}
