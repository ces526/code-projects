// Vector Class

#ifndef VECTOR
#define VECTOR

#include <math.h>
#include <cstdio>

class Vec{
    double X;
    double Y;
    double Z;


    public:

    Vec(){
        X = 0.0;
        Y = 0.0;
        Z = 0.0;
    }


    Vec(double x, double y, double z);

    
    Vec operator+(Vec v);

    Vec add(Vec v);

    Vec operator-(Vec v);

    Vec sub(Vec v);

    Vec operator*(Vec v);

    Vec mult(Vec v);

    Vec operator*(double d);

    Vec scalar_mult(double d);

    Vec operator/(double d);

    Vec scalar_divide(double d);

    // sum of components method
    double sum_components(){
        double r;
        r = X + Y + Z;
        return r;
    }

    // sum of the floor of the components method
    int sum_floor_components();

    Vec normalize();

    // inline len and dot methods

    double len(){
        double x_factor = X * X;
        double y_factor = Y * Y;
        double z_factor = Z * Z;

        double length = sqrt(x_factor + y_factor + z_factor);
        return (length);
    }

    double dot(Vec v){
        double theta = (X * v.X) + (Y * v.Y) + (Z * v.Z);
        return theta; 
    }

    void set(char c, double d){
        if(c == 'x'){
            X = d;
        }
        if(c == 'y'){
            Y = d;
        }
        if(c == 'z'){
            Z = d;
        }
    }


};




#endif