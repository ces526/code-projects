#ifndef RAYTYPE

#define RAYTYPE

#include "vp.h"

// color type 
typedef struct {
    double red;
    double green;
    double blue;
} COLOR_T;

// ray type
typedef struct {
    VP_T origin;
    VP_T dir;
} RAY_T;

// sphere type
typedef struct {
    VP_T center;
    double radius;
} SPHERE_T;

// plane type
typedef struct {
    VP_T normal;
    double D;
} PLANE_T;


// object type
typedef struct OBJ{
    union {
        SPHERE_T sphere;
        PLANE_T plane;
    };
    char type;
    COLOR_T color1;
    int checker;
    COLOR_T color2;
    struct OBJ *next;
    int(*intersect)(RAY_T ray, struct OBJ *obj, double *t, VP_T *int_pt, VP_T *normal);
} OBJ_T;

#include "sphere.h"
#include "plane.h"

// light type
typedef struct {
    VP_T loc;
} LIGHT_T;

#include "light.h"

// scene type
typedef struct {
    OBJ_T *obj;
    LIGHT_T light;
    double start_x;
    double start_y;
    double pixel_size;
} SCENE_T;


#endif