// color class file

#ifndef COLOR_CLASS
#define COLOR_CLASS


// constructor receiving RGB
// constructor with no parameters

class Color {

    private:
    double R;
    double G;
    double B;

    public:

    // constructors

    Color();

    Color(double r, double g, double b);


    // inline get functions for R, G, B

    double getRed(){
        return R;
    }

    double getGreen(){
        return G;
    }

    double getBlue(){
        return B;
    }

    Color operator+ (Color c);

    Color add(Color c);

    Color operator+ (double d);

    Color add(double d);

    Color mult(double d);

    Color operator*(double d);

    void cap();

};



#endif