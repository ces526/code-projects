#include "rt.h"
#include <stdio.h>
#include <math.h>

// function to determine if a ray intersects a sphere



int intersect_plane(RAY_T ray,  OBJ_T *object, double *t, VP_T *int_pt, VP_T * normal){

    PLANE_T plane = object -> plane;

    double dp;
    dp = dot(plane.normal, ray.dir);

    if(dp == 0){
        return(0);
    }
    else {
        double tVal;
        tVal = (-1)*((dot(plane.normal, ray.origin) + plane.D) / dp);

        if (tVal <= 0){
            return(0);
        }
        else {
             VP_T intersect;
             intersect.X = (ray.origin.X + ray.dir.X * tVal);
             intersect.Y = (ray.origin.Y + ray.dir.Y * tVal);
             intersect.Z = (ray.origin.Z + ray.dir.Z * tVal);

             *int_pt = intersect;
             *t = tVal;
             *normal = plane.normal;

             return(1);
        }
    }
}



