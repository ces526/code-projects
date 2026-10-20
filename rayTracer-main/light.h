#ifndef LIGHTFILE

#define LIGHTFILE

#include <math.h>
#include "rt.h"
#include "vp.h"
#include <stdio.h>

static int shadow_test(VP_T int_pt, OBJ_T *object, VP_T origin, LIGHT_T light, OBJ_T *first);

COLOR_T illuminate(OBJ_T object, VP_T int_pt, VP_T normal, LIGHT_T light, RAY_T original, OBJ_T *);

#endif