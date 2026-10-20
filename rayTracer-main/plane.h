#ifndef PLANEFILE

#define PLANEFILE

#include <math.h>
#include "vp.h"
#include "rt.h"


int intersect_plane(RAY_T ray, OBJ_T *object, double *t, VP_T *int_pt, VP_T * normal);

#endif