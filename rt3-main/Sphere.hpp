

#ifndef SPHERE_CLASS
#define SPHERE_CLASS

#include "Object.hpp"

class Sphere: public Object{

    Vec center;
    double radius;


    public:

    Sphere(Vec, double, Color, bool, Color);

    bool intersect(RAY_T, double&, Vec&, Vec&);

};



#endif