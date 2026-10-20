// Cora Snyder
// April 19, 2024
// Computer Organization
// Assignment 9
//

#include <stdio.h>

// returns the absolute value of n
int ab(int n){
    if(n >= 0){
        return n;
    }
    else{
        return(0 - n);
    }
}

// computes and returns the product of x and y by repeatedly adding y to itself x times
int mult(int x, int y){
    int result = 0;
    while(x > 0){
        result += y;
        x--;
    }
    return result;
}

// computes and returns the product of rf(n-1) and rf(n-2) recursively with initial conditions
int rf(int n){
    if(n == 1){
        return(2);
    }
    else if(n == 0){
        return(1);
    }
    else{
        return(rf(n-1)*rf(n-2));
    }
}

void main(){

    int input[] = {-6, 4, 5, -7, 8, 0};

    int index = 0;
    while(input[index] != 0){

        printf("\n");

        int num = input[index];

        // print in decimal and hex
        printf("%7d   0x%08x", num, num);

        int av = ab(num);

        if(num%2){
            int square = mult(av, av);

            // print in decimal and hex
            printf("\n%7d   0x%08x", square, square);
        }
        else{
            int recValue = rf(av);

            // print in decimal and hex
            printf("\n%7d   0x%08x", recValue, recValue);
        }
        index++;
    }
}

