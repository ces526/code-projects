// Cora Snyder
// 3/28/24
// CS 304 Computer Organization
// Assignment 6 - Base Calculator
// This assignment is an extension of assignment 3 Base Calculator to include more operations and
// binary capabilities.


#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include "calc.h"

// method for taking in binary
unsigned short get_binary_op(bin_str bin){

    unsigned short result = 0;

    // checks to make sure there are 16 bits; if not, pads with zeros
    int full_spaces = 0;
    for(int s = 0; s<17;s++){
        if(bin[s] == '0' || bin[s] == '1'){
            full_spaces += 1;
        }
    }
    if(full_spaces != 16){
        int offset = (16-full_spaces);
        char temp[20];
        for(int i = 0; i < 17; i++){
            if(i < offset){
                temp[i] = '0';
            }
            else{
                temp[i] = bin[i-offset];
            }
        }

        for(int r = 0; r < 17; r++){
            bin[r] = temp[r];
        }
    }

    // actual conversion into number value
        for (int i = 0; i < 17; i++) {
            if (bin[i] == '1') {
                int power = 1;
                for (int c = 15 - i; c > 0; c--) {
                    power = (power * 2);
                }
                result += power;
            }
        }
    return result;
}

// method for converting val to binary str for output
 void convert_to_binary(short val, bin_str bin){

    if(val == 0) {
        for(int i = 0; i < 16; i++){
            bin[i]= '0';
        }
    }

    // if the value is positive
    else if(val > 0){
        int n = 0;
        while(n < 16){
            short int remainder = (val % 2);
            char c[1];
            if(remainder == 1){
                c[0] = '1';
            }
            if(remainder == 0){
                c[0] = '0';
            }
            bin[15-n] = c[0];
            val = (val/2);
            n++;
        }
    }

    // if the value is negative
    else if(val < 0){
        int new_value = (65536 + val);

        int n = 0;
        while(n < 16){
            int remainder = (new_value % 2);
            char c[1];
            if(remainder == 1){
                c[0] = '1';
            }
            if(remainder == 0){
                c[0] = '0';
            }
            bin[15-n] = c[0];
            new_value = (new_value/2);
            n++;
        }
    }

    // final formatting of string with spaces
    char temp[20];

    for(int x = 0; x < 17; x++){
        temp[x] = bin[x];
    }
    bin[4] = ' ';
    for(int i = 0; i < 4; i++){
        bin[i] = temp[i];
    }
    bin[9] = ' ';
    for(int j = 5; j < 9; j++){
        bin[j] = temp[j-1];
    }
    bin[14] = ' ';
    for(int k = 10; k < 14; k++){
        bin[k] = temp[k-2];
    }
    bin[19] = '\0';
    for(int l = 15; l < 19; l++){
        bin[l] = temp[l-3];
    }
}


// method for taking in number value
short get_operand(char mode){

    short i;
    short n = 0;

    //proceeds based on the mode we are in
    switch(mode){

        // case: user has opted to shift
        case 'L' : {
            printf("Enter number of positions to left shift value: ");
            scanf("%hd", &i);
            printf("%hd\n", i);
            return(i);
        }

        case 'R' : {
            printf("Enter number of positions to right shift value: ");
            scanf("%hd", &i);
            printf("%hd\n", i);
            return(i);
        }

        // case: mode is binary
        case 'B' : {
            printf("Enter binary value: ");
            bin_str bin;
            scanf("%17s", bin);
            printf("%s\n", bin);
            return(get_binary_op(bin));
        }

        // case: mode is hex
        case 'H' : {
            printf("Enter hex value: ");
            scanf("%hX", &i);
            printf("%hX\n", i);
            return(i);
        }

        // case : mode is octal
        case 'O' : {
            printf("Enter octal value: ");
            scanf("%ho", &i);
            printf("%ho\n", i);
            return(i);
        }

        // case : mode is decimal
        case 'D' : {
            printf("Enter decimal value: ");
            scanf("%hd", &i);
            printf("%hd\n", i);
            return(i);
        }

        // this is a catch-all for everything else (prevents crash)
        default : {
            printf("Sorry, something went wrong! Please try again!");
            return(n);
        }
    }
}

// method for adding
void add (short *val, char mode){
    short original = *val;
    short with_value = get_operand(mode);
    *val = (*val + with_value);

    // checking for overflow
    if((original <= 32767 && original >= 0) && (with_value <= 32767 && with_value >= 0) && (*val) < 0){
        printf("Positive Overflow\n");
    }
    if(original < 0 && with_value < 0 && ((*val) <= 32767 && (*val) >= 0)) {
        printf("Negative Overflow\n");
    }
}


//method for subtracting
void subtract  (short *val, char mode){
    const short original = *val;
    short with_value = get_operand(mode);
    *val = (*val - with_value);

            // checking for overflow
            if((original <=  32767 && original >= 0) && (with_value < 0) && (*val) < 0){
                printf("Positive Overflow\n");
            }
            if(original < 0 && (with_value <= 32767 && with_value >= 0) && ((*val) <= 32767 && (*val) >= 0)){
                printf("Negative Overflow\n");
            }
    }

// print bases method (everything inside the box)
void print_bases(short val, char mode){

    printf("****************************************");

    //creates all properly formatted number strings

    bin_str binary_string;
    convert_to_binary(val,binary_string);

    char oct_string[25] = {'\0'};
    sprintf(oct_string, "%06ho", val);
    char dec_string[25] = {'\0'};
    sprintf(dec_string,"%hd", val);
    char hex_string[25] = {'\0'};
    sprintf(hex_string, "%04hX", val);

    switch(mode){

        case 'B' : {

            printf("\n* Base Values:         Input Mode: Bin *");

            // printing binary value

            printf("\n*   Binary  : %20s     *", binary_string);

            // printing hex value

            printf("\n*   Hex     :  %s                    *", hex_string);

            // printing octal value

            printf("\n*   Octal   :  %s                  *", oct_string);

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

        case 'H' : {

            printf("\n* Base Values:         Input Mode: Hex *");

            // printing binary value

            printf("\n*   Binary  : %20s     *", binary_string);

            // printing hex value

             printf("\n*   Hex     :  %s                    *", hex_string);

            // printing octal value

             printf("\n*   Octal   :  %s                  *", oct_string);

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

            printf("\n* Base Values:         Input Mode: Dec *");

            // printing binary value

            printf("\n*   Binary  : %20s     *", binary_string);

            //printing hex value

            printf("\n*   Hex     :  %s                    *", hex_string);

            // printing octal value

            printf("\n*   Octal   :  %s                  *", oct_string);

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

        case 'O' : {

            printf("\n* Base Values:         Input Mode: Oct *");

            // printing binary value

            printf("\n*   Binary  : %20s     *", binary_string);

            //printing hexadecimal value

            printf("\n*   Hex     :  %s                    *", hex_string);

            // printing octal value

            printf("\n*   Octal   :  %s                  *", oct_string);

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

        printf("\n\nB  Binary Mode            &  AND with Value           +  Add to Value");
        printf("\nO  Octal Mode             |  OR  with Value           -  Subtract from Value");
        printf("\nH  Hexadecimal Mode       ^  XOR with Value           N  Negate Value");
        printf("\nD  Decimal Mode           ~  Complement Value");

        printf("\n\nC  Clear Value            <  Shift Value Left");
        printf("\nS  Set Value              >  Shift Value Right");

        printf("\n\nQ  Quit");

        printf("\n\nOption: ");

        // takes in 10 char string

        char str[11];
        scanf("%10s", str);

        printf("%s", str);

        // checks to see if the string is either too long or not an option listed
        if(str[1] != '\0' || (toupper(str[0]) != 'O' && toupper(str[0]) != 'H' && toupper(str[0]) != 'D'
        && toupper(str[0]) != 'C' && toupper(str[0]) != 'S' && toupper(str[0]) != 'Q' && str[0] != '|' && str[0] != '^' && str[0] != '~' && str[0] != '&'
        && str[0] != '-' && str[0] != '+' && toupper(str[0]) != 'N' && toupper(str[0]) != 'B' && str[0] != '<' && str[0] != '>')){
            printf("\n\nInvalid option: %s\n", str);
            return('i');
        }

        if(toupper(str[0] != 'Q')){
            printf("\n");
        }

        return (toupper((str[0])));

}

// main method -- main switch statement and while loop

int main(void){

    printf("\n");

    int quit = 0;
    char mode = 'D';
    short val = 0000;
    short with_value;

    print_bases(val,mode);

    // while loop allows program to quit when chosen
    while(!quit){

        switch(print_menu()){

            // each mode's case just prints out the selected mode, changes the value of mode, and prints

            // cases for each mode
            case 'B' : {
                printf("Mode: Binary\n\n");
                mode = 'B';
                print_bases(val, mode);
                continue;
            }

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

            // non-mode options

            // resets val to 0
            case 'C' : {
                printf("\n");
                val = 0000;
                print_bases(val, mode);
                continue;
            }

            // ends program
            case 'Q' : {
               quit = 1;
               continue;
            }

            // sets new value
            case 'S' : {
                val = get_operand(mode);
                printf("\n");
                print_bases(val, mode);
                continue;
            }

            // cases for operands
            case '&' : {

                with_value = get_operand(mode);
                val = val & with_value;
                printf("\n");
                print_bases(val, mode);
                continue;
            }
            case '|' : {

                with_value = get_operand(mode);
                val = val | with_value;
                printf("\n");
                print_bases(val, mode);
                continue;
            }
            case '^' : {

                with_value = get_operand(mode);
                val = val ^ with_value;
                printf("\n");
                print_bases(val, mode);
                continue;
            }
            case '~' : {

                printf("\n");
                val = ~(val);
                print_bases(val, mode);
                continue;
            }
            case 'N' : {

                printf("\n");
                val = -(val);
                print_bases(val, mode);
                continue;
            }
            case '+' : {

                add(&val, mode);
                printf("\n");
                print_bases(val, mode);
                continue;
            }
            case '-' : {

                subtract(&val, mode);
                printf("\n");
                print_bases(val, mode);
                continue;
            }
            case '<' : {
                with_value = get_operand('L');
                val = (val << with_value);
                printf("\n");
                print_bases(val, mode);
                continue;
            }
            case '>' : {
                with_value = get_operand('R');
                val = (val >> with_value);
                printf("\n");
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
