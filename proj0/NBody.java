public class NBody {
    private static int N;
    private static double R;
    private static String BackgroundImage = "images/starfield.jpg";

    /* Read the Radius.**/
    public static double readRadius(String txtname){
        In in = new In(txtname);
        N = in.readInt();
        R = in.readDouble();
        return R;
    }

    public static Planet[] readPlanets(String filename){
        readRadius(filename);
        In in = new In(filename);
        Planet[] Planets = new Planet[N];
        /* Skip the first and second item**/
        in.readInt();
        in.readDouble();

        for(int i = 0; i < N; i++){
            Planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(),in.readDouble(), in.readString());
        }
        return Planets;
    }

    public static void main(String[] args){
        /* Store the input arguments**/
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        /* Read the files**/
        Planet[] Planets = readPlanets(filename);
        R = readRadius(filename);

        /* Animation**/
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(R * -1, R);
        StdDraw.clear();
        double time = 0;
        while(time < T){
            StdDraw.clear();
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for(int i = 0; i < N; i++){
                /* Calculate the net x and y forces for each planet.**/
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
            }

            /* Update each planet's position, velocity, acceleration.**/
            for(int i = 0; i< N; i++){
                Planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, BackgroundImage);// Draw the background.
            for(Planet currP : Planets){
                currP.draw(); // Draw each planet.
            }
            StdDraw.show(); // Show the offscreen.
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < Planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                    Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);
        }
    }
}
