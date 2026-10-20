#ifndef VECTORPOINT

#define VECTORPOINT

// vector point type
typedef struct {
    double X;
    double Y;
    double Z;
} VP_T;


double dot(VP_T v1, VP_T v2);

double vec_len(VP_T vector);

VP_T normalize(VP_T vector);

#endif