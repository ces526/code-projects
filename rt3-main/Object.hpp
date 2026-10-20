

#ifndef OBJ_CLASS
#define OBJ_CLASS

#include "Color.hpp"
#include "rt.hpp"

class Object{

    public:
    Color color;
    Color color2;
    bool checker;
    Object* next;

    virtual bool intersect(RAY_T, double&, Vec&, Vec&){
        return false;
    }

};

#endif