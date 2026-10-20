// December 6 2024
// Cora Snyder
// CS 312 Principles of Programming Languages
// This is a C++ program which uses info about rays/objects/colors to render an image pixel by pixel


#include "Plane.hpp"
#include "Sphere.hpp"
#include "Light.hpp"
#include "rt.hpp"
#include "Color.hpp"
#include "scene.hpp"
#include <cstdio>


// initialization



static void init(SCENE_T& scene, Light& l){


 scene.start_x = (double)(-(640.0/480.0)/2);
 scene.start_y = 0.5;
 scene.pixel_size = (double)(1/(480.0));

 Object* node;
 //node -> next = nullptr;

 FILE *fp;
 fp = fopen("scene.txt", "r");

 double a;
 double b;
 double e;
 char c;

 
  while (fscanf(fp, "%c", &c) != EOF){

     if(c == 'S' || c == 's'){

         fscanf(fp, "%lf%lf%lf", &a, &b, &e);
         Vec center(a, b, e);

         fscanf(fp,"%lf", &a);
         double rad = a;

         fscanf(fp, "%lf%lf%lf", &a, &b, &e);

         Color c_one(a, b, e);

         node = new Sphere(center, rad, c_one, false, c_one);

        }
     else if(c == 'P' || c == 'p'){

        fscanf(fp, "%lf%lf%lf", &a, &b, &e);
    
        Vec norm(a, b, e);

        fscanf(fp, "%lf", &e);
        double d = e;


        fscanf(fp, "%lf%lf%lf", &a, &b, &e);
     
        Color c_one(a, b, e);
    

        fscanf(fp, "%lf%lf%lf", &a, &b, &e);
        Color c_two(a, b, e);

       node = new Plane(norm, d, c_one, true, c_two);


        }
     else if(c == 'l'){

        fscanf(fp, "%lf%lf%lf", &a, &b, &e);
    
        l.set(a, b, e);
     }

     if(node != nullptr){

        node -> next = scene.objs;
        scene.objs = node;
        node = nullptr;

     }

    }  

    fclose(fp);

}





// tracing

static int trace(RAY_T ray, Light light, SCENE_T& scene, Color& color){

    double closest_t = 10000;
    Vec closest_int;
    Vec closest_normal;
    Object* closest_obj;

    Object* curr = scene.objs;



    double t;
    Vec int_pt;
    Vec normal;

    int count = 0;

    while(curr != nullptr){
     if(curr -> intersect(ray, t, int_pt, normal)){
            if(t < closest_t){

                closest_t = t;
                closest_int = int_pt;
                closest_normal = normal;
                closest_obj = curr;

           } 
        } 

        curr = curr -> next;
    }


    if(closest_t == 10000){
        return(0);
    }

    color = light.illuminate(ray, scene, *closest_obj, closest_int, closest_normal);

    return(1);

}




// main


int main(){

    // initialize variables/scene
    SCENE_T scene;
    Light light(Vec(0,0,0));

    init(scene, light);

    

     // set output file header
    FILE *fptr;
    fptr = fopen("img.ppm", "w");
    fprintf(fptr, "P6\n%d %d\n%d\n", 640, 480, 255);


    for(double y = 0; y<480; y++){
        for(double x = 0; x<640; x++){

            Vec origin(0,0,-10);

            Vec dir(((scene.start_x) + (x*scene.pixel_size)), (scene.start_y - (y*scene.pixel_size)), 1);

            RAY_T ray;
            ray.origin = origin;
            ray.dir = dir.normalize();

            Color color(0.3, 0.3, 0.5);

            trace(ray, light, scene, color);

            fprintf(fptr, "%c%c%c", (unsigned char)(color.getRed() * 255), (unsigned char)(color.getGreen() * 255), (unsigned char)(color.getBlue() * 255));


        }
    }

    fclose(fptr);
}




