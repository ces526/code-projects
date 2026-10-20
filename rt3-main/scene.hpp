// header

#ifndef SCENE_CLASS
#define SCENE_CLASS

#include "Object.hpp"


typedef struct{

    Object* objs;
    double start_x;
    double start_y;
    double pixel_size;

} SCENE_T;


#endif