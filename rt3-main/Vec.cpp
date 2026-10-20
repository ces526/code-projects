#include "Vec.hpp"

Vec::Vec(double x, double y, double z){
    X = x;
    Y = y;
    Z = z;
}



    Vec Vec::operator+(Vec v){
        Vec result;
        result.X = X + v.X;
        result.Y = Y + v.Y;
        result.Z = Z + v.Z;
        return result;
    }

    Vec Vec::add(Vec v){
        Vec result;
        result.X = X + v.X;
        result.Y = Y + v.Y;
        result.Z = Z + v.Z;
        return result;
    }

    // sub/operator- methods

    Vec Vec::operator-(Vec v){
        Vec result;
        result.X = X - v.X;
        result.Y = Y - v.Y;
        result.Z = Z - v.Z;
        return result;
    }

     Vec Vec::sub(Vec v){
        Vec result;
        result.X = X - v.X;
        result.Y = Y - v.Y;
        result.Z = Z - v.Z;
        return result;
    }

    // mult/operator* methods

     Vec Vec::operator*(Vec v){
        Vec result;
        result.X = X * v.X;
        result.Y = Y * v.Y;
        result.Z = Z * v.Z;
        return result;
    }

     Vec Vec::mult(Vec v){
        Vec result;
        result.X = X * v.X;
        result.Y = Y * v.Y;
        result.Z = Z * v.Z;
        return result;
    }

    // scalar_mult/operator* methods

     Vec Vec::operator*(double d){
        Vec result;
        result.X = X * d;
        result.Y = Y * d;
        result.Z = Z * d;
        return result;
    }

     Vec Vec::scalar_mult(double d){
        Vec result;
        result.X = X * d;
        result.Y = Y * d;
        result.Z = Z * d;
        return result;
    }

    // scalar_divide/operator/ methods

     Vec Vec::operator/(double d){
        Vec result;
        result.X = X / d;
        result.Y = Y / d;
        result.Z = Z / d;
        return result;
    }

     Vec Vec::scalar_divide(double d){
        Vec result;
        result.X = X / d;
        result.Y = Y / d;
        result.Z = Z / d;
        return result;
    }


    // sum of the floor of the components method

    int Vec::sum_floor_components(){

        int r = (int)floor(X) + (int)floor(Y) + (int)floor(Z);
        return r;
    }

    // normalize method

    Vec Vec::normalize(){

        double length = this -> len();

        return(this -> scalar_divide(length));

    }



