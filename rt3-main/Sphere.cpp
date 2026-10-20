#include "Sphere.hpp"

#include <iostream>

//constructor



Sphere::Sphere(Vec v, double d, Color c1, bool check, Color c2){
    center = v;
    radius = d;
    color = c1;
    checker = check;
    if(checker == false){
        color2 = c1;
    }
    else{
        color2 = c2;
    }
}

bool Sphere::intersect(RAY_T ray, double& t, Vec& int_pt, Vec& n){



    double A = 1;

    double B = ((ray.dir) * ((ray.origin - center))).sum_components() * 2;

    double C = ((ray.origin - center)*(ray.origin - center)).sum_components() - (radius * radius);

    double det = (B*B) - (4*A*C);

    if(det < 0){
        return false;
    }

    double t1 = ((-1)*B - sqrt(det)) / 2;
    double t2 = ((-1)*B + sqrt(det)) / 2;

     if((t1 <= 0) & (t2 <= 0)){
        return false;
    }

    if(t1 < t2){
        t = t1;

    }
    else{
        t = t2;
    }

    int_pt = ray.origin + ray.dir * (t);

    n = (int_pt - center).normalize();

    return true;

}