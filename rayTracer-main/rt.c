// November 6 2024
// Cora Snyder
// CS 312 Principles of Programming Languages
// Professor Davis
// This program uses information about rays/spheres/planes/colors to render an image pixel by pixel


#include "rt.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>


// init() initializes everything outside of the main function

SCENE_T init(){


 SCENE_T scene;
 scene.start_x = (double)(-(640.0/480.0)/2);
 scene.start_y = 0.5;
 scene.pixel_size = (double)(1/(480.0));

 OBJ_T *list = NULL;
 OBJ_T *node;

 FILE *fp;
 fp = fopen("scene.txt", "r");

 double a;
 double b;
 double e;
 char c;

 while (fscanf(fp, "%c", &c) != EOF){
 node = (OBJ_T*)malloc(sizeof(OBJ_T));

    if(c == 'S' || c == 's'){

     node -> type = 's';

     fscanf(fp, "%lf%lf%lf", &a, &b, &e);
     node -> sphere.center.X = a;
     node -> sphere.center.Y = b;
     node -> sphere.center.Z = e;

     fscanf(fp,"%lf", &a);
     node -> sphere.radius = a;

     fscanf(fp, "%lf%lf%lf", &a, &b, &e);
     node -> color1.red = a;
     node -> color1.green = b;
     node -> color1.blue = e;

     node -> checker = 0;

     node -> intersect = &intersect_sphere; 

     node -> next = list;
     list = node;

    }
    else if(c == 'P' || c == 'p'){
    
     node -> type = 'p';

     fscanf(fp, "%lf%lf%lf", &a, &b, &e);
     node -> plane.normal.X = a;
     node -> plane.normal.Y = b;
     node -> plane.normal.Z = e;


     fscanf(fp, "%lf", &e);
     node -> plane.D = e;


     fscanf(fp, "%lf%lf%lf", &a, &b, &e);
     node -> color1.red = a;
     node -> color1.green = b;
     node -> color1.blue = e;

    

     fscanf(fp, "%lf%lf%lf", &a, &b, &e);
     node -> color2.red = a;
     node -> color2.green = b;
     node -> color2.blue = e;

  

     node -> checker = 1;   
     node -> intersect = &intersect_plane;

     

     node -> next = list;
     list = node; 

    }
    else if(c == 'l'){

        fscanf(fp, "%lf%lf%lf", &a, &b, &e);
    
        scene.light.loc.X = a;
        scene.light.loc.Y = b;
        scene.light.loc.Z = e;

     }

    }
    scene.obj = list;

    fclose(fp);

    return(scene);
}




// trace function


int trace(SCENE_T scene, RAY_T ray, COLOR_T *color){

 COLOR_T final_color;
 final_color.red = 0.3;
 final_color.green = 0.3;
 final_color.blue = 0.5;

 double closest_t = 10000;
 VP_T closest_int;
 VP_T closest_normal;
 OBJ_T *closest_obj = NULL;

 OBJ_T *curr = scene.obj;

 double t;
 VP_T int_pt;
 VP_T normal;


 while(curr != NULL){
 
    if((*curr -> intersect)(ray, curr, &t, &int_pt, &normal)){
        if(t < closest_t){
             
          //  printf("closest is %c with a t of %lf\n", curr -> type, t);
              
            closest_t = t;
            closest_int = int_pt;
            closest_normal = normal;
            closest_obj = curr;
        }
    
    }

   // printf("\n");

   curr = curr -> next;
 }

 if(closest_t == 10000){
    return(0);
 }
  //printf("%d", closest_obj->checker);
  final_color = illuminate(*closest_obj, closest_int, closest_normal, scene.light, ray, scene.obj);

 *color = final_color;

 return(1);
}





// main function

void main(){


    // initialize variables/scene
    SCENE_T scene = init();

    // set output file header
    FILE *fptr;
    fptr = fopen("img.ppm", "w");
    fprintf(fptr, "P6\n%d %d\n%d\n", 640, 480, 255);

    //  DONT FORGET TO ASPECT RATIO STUFF, STAETING AND ENDING POINT

   // printf("P6\n%lf %lf\n%lf\n", 1000, 1000, 255);


    for(double y = 0; y<480; y++){
        for(double x = 0; x<640; x++){


            RAY_T ray;
            ray.origin.X = 0;
            ray.origin.Y = 0;
            ray.origin.Z = -10;

            ray.dir.X = (scene.start_x) + (x*scene.pixel_size);
            ray.dir.Y = scene.start_y - (y*scene.pixel_size);
            ray.dir.Z = 1;

            ray.dir = normalize(ray.dir);

        
            COLOR_T color_choice;
            color_choice.red = 0.3;
            color_choice.green = 0.3;
            color_choice.blue = 0.5;


            
            trace(scene, ray, &color_choice);

            fprintf(fptr, "%c%c%c", (unsigned char)(color_choice.red * 255), (unsigned char)(color_choice.green * 255), (unsigned char)(color_choice.blue * 255));

           
        }
    }
    
    fclose(fptr);
}