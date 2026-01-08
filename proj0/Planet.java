public class Planet {
    /* Create basic version of the Planet**/
    public double xxPos, yyPos,  xxVel, yyVel, mass;
    public static final double G = 6.67e-11;
    public String imgFileName;

    /* Initialize an instance of Planet**/
    public Planet(double xP, double yP, double xV,
                  double yV, double m,String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /* Initialize an identical Planet object**/
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /* Calculate the distance between two Planets**/
    public double calcDistance(Planet p){
        double dis;
        dis = Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) +
                (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
        return dis;
    }

    /* Calculate the force exerted on the planet by the given planet**/
    public double calcForceExertedBy(Planet p){
        double force;
        force = G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
        return force;
    }

    /* Calculate the net X force exerted by the planet**/
    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - this.xxPos;

        return this.calcForceExertedBy(p) * dx / this.calcDistance(p);
    }

    /* Calculate the net Y force exerted by the planet**/
    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - this.yyPos;

        return this.calcForceExertedBy(p) * dy / this.calcDistance(p);
    }

    /* Calculate the net X force exerted by all the planets in the array**/
    public double calcNetForceExertedByX(Planet[] p){
        double forceXall = 0;
        for(Planet currP : p){
            if (this.equals(currP)){
                continue;
            }
            forceXall += calcForceExertedByX(currP);
        }
        return forceXall;
    }

    /* Calculate the net Y force exerted by all the planets in the array**/
    public double calcNetForceExertedByY(Planet[] p){
        double forceYall = 0;
        for(Planet currP : p){
            if (this.equals(currP)){
                continue;
            }
            forceYall += calcForceExertedByY(currP);
        }
        return forceYall;
    }

    /* Calculate the acceleration, new velocity and position of the planet that be exerted by the forces**/
    public void update(double dt, double fX, double fY){
        /* Calculate the acceleration**/
        double xAcc = fX / this.mass;
        double yAcc = fY / this.mass;

        /* Calculate new velocity**/
        this.xxVel += xAcc * dt;
        this.yyVel += yAcc * dt;

        /* Calculate new position**/
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    /* Draw a planet**/
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}
