#include "Light.hpp"
#include "rt.hpp"
#include "Color.hpp"
#include "scene.hpp"
#include <cstdio> 




Light::Light(Vec v){
    loc = v;
}


bool Light::shadow_test(SCENE_T scene, Object* obj, Vec int_pt){

    RAY_T shadow_ray;
    shadow_ray.origin = int_pt;

    Vec shadow((loc-int_pt));

    shadow.normalize();

    shadow_ray.dir = shadow;

    Object* curr = scene.objs;

    double t;
    Vec normal;

    while(curr != nullptr){

        if(curr != obj){

            if((curr -> intersect(shadow_ray, t, int_pt, normal))){
                return(false);
            }
        }
        curr = curr -> next;
    }

    return(true);

}



Color Light::illuminate(RAY_T& ray, SCENE_T& scene, Object& obj, Vec& int_pt, Vec& n){


    
    Color result;


    Color obj_color = obj.color;


    if(obj.checker && (int_pt.sum_floor_components() & 1)){
        obj_color = obj.color2;
    }


    //ambient light

    result = obj_color * 0.1;

    //shadow test
    if(!(shadow_test(scene, &obj, int_pt))){

         // diffuse light

    Vec L = loc - int_pt;
    L.normalize();


    //calculating attenuation

    double dL = L.len();
    double atten = (1/(0.002 + (dL*dL) + (0.02*dL) +0.2));
    double dp = L.dot(n);



    if(dp >= 0){


        result = ((obj_color * dp) * atten);

        //specular light

        Vec R = L - (n * (2 * dp));

        R.normalize();

        double dp2 = R.dot(ray.dir);

        if(dp2 > 0){
            result + (pow(dp2, 200) * atten);
        }   

    } 

    }
   
    result.cap();

    return(result);
}