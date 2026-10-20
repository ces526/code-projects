#include "rt.h"
#include <stdio.h>
#include <math.h>


// function to determine if ray intersects the spere
int intersect_sphere(RAY_T ray, OBJ_T *object, double *t, VP_T *int_pt, VP_T * normal){
   
    SPHERE_T sphere = object -> sphere;
    double A = 1;

    double B = 2 * ((ray.dir.X)*(ray.origin.X - sphere.center.X) + 
            (ray.dir.Y)*(ray.origin.Y - sphere.center.Y) + 
            (ray.dir.Z)*(ray.origin.Z - sphere.center.Z));

    double C = (ray.origin.X - sphere.center.X)*(ray.origin.X - sphere.center.X) + 
        (ray.origin.Y - sphere.center.Y)*(ray.origin.Y - sphere.center.Y) + 
        (ray.origin.Z - sphere.center.Z)*(ray.origin.Z - sphere.center.Z) - 
        ((sphere.radius) * (sphere.radius));

    double det = (B*B) - (4*A*C);

    if(det <= 0){
        return(0);
    }

    double t1 = ((-1)*B - sqrt(det)) / 2;
    double t2 = ((-1)*B + sqrt(det)) / 2;

    if((t1 <= 0) || (t2 <= 0)){
        return(0);
    }

    double tVal;

    if(t1 < t2){
        *t = t1;
        tVal = t1;

    }
    else{
        *t = t2;
        tVal = t2;
    }


    VP_T intersect;
    intersect.X = (ray.origin.X + ray.dir.X * tVal);
    intersect.Y = (ray.origin.Y + ray.dir.Y * tVal);
    intersect.Z = (ray.origin.Z + ray.dir.Z * tVal);

    *int_pt = intersect;

    VP_T norm;

    norm.X = intersect.X - sphere.center.X;
    norm.Y = intersect.Y - sphere.center.Y;
    norm.Z = intersect.Z - sphere.center.Z;

    *normal = normalize(norm);

    
    return(1);
}