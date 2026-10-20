

#ifndef PLANE_CLASS
#define PLANE_CLASS

#include "Object.hpp"

class Plane: public Object{

    Vec normal;
    double D;

    public:

    Plane(Vec, double, Color, bool, Color);

    bool intersect(RAY_T, double&, Vec&, Vec&);


};



#endif