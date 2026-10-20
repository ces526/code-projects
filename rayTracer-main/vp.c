#ifndef VPCFILE

#define VPCFILE

#include "vp.h"
#include "rt.h"
#include <math.h>

// functions to find the dot product of two vectors
double dot(VP_T v1, VP_T v2){
    double theta = (v1.X * v2.X) + (v1.Y * v2.Y) + (v1.Z * v2.Z);
    return(theta);
}


double vec_len(VP_T vector){
    double x_factor = vector.X * vector.X;
    double y_factor = vector.Y * vector.Y;
    double z_factor = vector.Z * vector.Z;

    double length = sqrt(x_factor + y_factor + z_factor);
    return (length);
}



// function to normalize vectors
VP_T normalize(VP_T vector){

    double length = vec_len(vector);


    VP_T result;
    result.X = (vector.X / length);
    result.Y = (vector.Y / length);
    result.Z = (vector.Z / length);

    return(result);

}

#endif