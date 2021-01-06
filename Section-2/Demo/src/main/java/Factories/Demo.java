package Factories;
/*enum CordinateSystem{
    CARTESIAN,
    POLAR
}*/

class Point{
    private double x,y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public static class Factory{
        public static Point newCartesianPoint(double x, double y){
            return new Point(x, y);
        }
        public static Point newPolarPoint(double rho, double thetha){
            return new Point(rho*Math.cos(thetha),
                    rho*Math.sin(thetha));
        }
    }

}

class Demo{
    public static void main(String[] args) {
        Point point = Point.Factory.newCartesianPoint(3,4);

    }
}

