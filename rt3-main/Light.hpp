// header

#include "Vec.hpp"
#include "Color.hpp"
#include "rt.hpp"
#include "scene.hpp"

#ifndef LIGHT_CLASS
#define LIGHT_CLASS

class Light{

    Vec loc;
    //ANY STATIC FUNCTIONS SHOULD GO HERE


    public:

    //constructor

    Light(Vec v);

    void set(double a, double b, double c){
        loc = Vec(a, b, c);
    }

    Color illuminate(RAY_T& ray, SCENE_T& scene, Object& obj, Vec& int_pt, Vec& normal);

    bool shadow_test(SCENE_T scene, Object* obj, Vec int_pt);




};


#endif