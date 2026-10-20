// Cora Snyder
// 4/5/24
// Computer Organization
// Assignment 7

#include <stdio.h>

// function which uses the value n to find the summation
int summation (int n){

    int result = 0;

    // outer loop: total summation ((4i - 2) for i = 0 to i = n times)
    for(int i = 0; i <= n; i++){

        result = result - 2;

        // inner loop: adds 4 to result i times (4i)
        for(int a = i; a > 0; a--){

            result = result + 5;

        }
    }
    return(result);
}

// function which calls summation function and prints results
int main(){

    // loops for n = 0 to n = 10
    for(int x = 0; x <= 10; x++){

        // saves value of summation(n)
        int sum = summation(x);



        // prints n, decimal sum, and hex sum
        printf("%2d :    %3d   0x%08x\n", x, sum, sum);

    }
};
