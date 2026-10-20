

#include "Plane.hpp"
#include <iostream>

//constructor

Plane::Plane(Vec v, double d, Color c1, bool check, Color c2){
    normal = v;
    D = d;
    color = c1;
    checker = check;
    if(checker == false){
        color2 = c1;
    }
    else{
        color2 = c2;
    }
}

// intersect function

bool Plane::intersect(RAY_T ray, double& t, Vec& int_pt, Vec& n){

    double dp;
    dp = normal.dot(ray.dir);

    if(dp == 0){
        return false;
    }
    else{
        double tVal;
        tVal = (-1)*(normal.dot(ray.origin) + D)*(1/(dp));

        if(tVal <= 0){
            return false;
        }
        else{
            Vec intersect = ray.origin + (ray.dir * tVal);

            int_pt = intersect;
            t = tVal;
            n = this -> normal;
            return true;
        }
    }

}