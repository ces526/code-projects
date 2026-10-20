#include "Color.hpp"


// constructors

Color::Color(double r, double g, double b){
    R = r;
    G = g;
    B = b;
}

Color::Color(){
    R = 0.0;
    G = 0.0;
    B = 0.0;
}


// overloaded add/operator+ methods

Color Color::operator+(Color c){
    Color result;
    result.R = R + c.R;
    result.G = G + c.G;
    result.B = B + c.B;
    return result;
}

Color Color::add(Color c){
    Color result;
    result.R = R + c.R;
    result.G = G + c.G;
    result.B = B + c.B;
    return result;
}

Color Color::operator+(double d){
    Color result;
    result.R = R + d;
    result.G = G + d;
    result.B = B + d;
    return result;
} 

Color Color::add(double d){
    Color result;
    result.R = R + d;
    result.G = G + d;
    result.B = B + d;
    return result;
} 

// overloaded mult_scalar/operator* methods

Color Color::mult(double d){
    Color result;
    result.R = R * d;
    result.G = G * d;
    result.B = B * d;
    return result;
}

Color Color::operator*(double d){
    Color result;
    result.R = R * d;
    result.G = G * d;
    result.B = B * d;
    return result;
}


// cap function

void Color::cap(){
    if(R > 1.0){
        R = 1.0;
    }
    if(G > 1.0){
        G = 1.0;
    }
    if(B > 1.0){
        B = 1.0;
    }
}



