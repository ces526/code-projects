#include "rt.h"
#include "vp.h"
#include <stdio.h>
#include <math.h>


// function to test shadows

static int shadow_test(VP_T int_pt, OBJ_T *object, VP_T origin, LIGHT_T light, OBJ_T* first){

    RAY_T shadow_ray;
    
    VP_T light_loc = light.loc;
    shadow_ray.origin = int_pt;

    VP_T shadow;
    shadow.X = light_loc.X - int_pt.X;
    shadow.Y = light_loc.Y - int_pt.Y;
    shadow.Z = light_loc.Z - int_pt.Z;

    shadow = normalize(shadow);

    shadow_ray.dir = shadow;

    OBJ_T *curr = first;
    double t;
    VP_T normal;

    int sum = 0;

    while(curr != NULL){

     if(curr != object){
      sum++;
      if((*curr -> intersect)(shadow_ray, curr, &t, &int_pt, &normal)){
       return (1);
      }
     
     }
     curr = curr -> next;
    }

    return(0);

    }

    




// function to determine lighting/color of each pixel
COLOR_T illuminate(OBJ_T object, VP_T int_pt, VP_T normal, LIGHT_T light_loc, RAY_T original, OBJ_T *first){

    COLOR_T result;

    COLOR_T obj_color = object.color1;

    if( object.checker && ((int)floor(int_pt.X) + 
        (int)floor(int_pt.Y) +
        (int)floor(int_pt.Z)) & 1) {
        obj_color = object.color2;
        }

        

    // ambient light
    result.red = 0.1 * obj_color.red;
    result.green = 0.1 * obj_color.green;
    result.blue = 0.1 * obj_color.blue;

    // shadow test 

    if (!(shadow_test(int_pt, &object, original.origin, light_loc, first))){
    

    // diffuse light
    VP_T L;

    L.X = light_loc.loc.X - int_pt.X;
    L.Y = light_loc.loc.Y - int_pt.Y;
    L.Z = light_loc.loc.Z - int_pt.Z;

    L = normalize(L);

     // calculating attenuation
    double dL = vec_len(L);
    double atten = (1/(0.002 + (dL*dL) + (0.02*dL) +0.2));

    double dp = dot(L, normal);

    if(dp > 0){
        result.red += dp * obj_color.red * atten;
        result.green += dp * obj_color.green * atten;
        result.blue += dp * obj_color.blue * atten;


        // specular light

        VP_T RR;
        RR.X = L.X - (normal.X * 2 * dp);
        RR.Y = L.Y - (normal.Y * 2 * dp);
        RR.Z = L.Z - (normal.Z * 2 * dp);

        VP_T R = normalize(RR);

        double dp2;
        dp2 = dot(R, original.dir);

        if(dp2 > 0){
            result.red +=  pow(dp2, 200) * atten;
            result.green +=  pow(dp2, 200) * atten;
            result.blue +=  pow(dp2, 200)  * atten;
        } 

    } 

   // } 

   // } 

    }
    
    // cap color channels
    if(result.red > 1.0){
        result.red = 1.0;
    }

    if(result.green > 1.0){
        result.green = 1.0;
    }

    if(result.blue > 1.0){
        result.blue = 1.0;
    }

    return(result);
}
