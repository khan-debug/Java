import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ErgonomicFloor {
    static class Point {
        double x, y;
        Point(double x, double y) {
            this.x = x; 
            this.y = y;
        }
    }

    static class Wall {
        Point start, end;
        Wall(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
    }

    public static boolean hasLineOfSight(Point a, Point b, List<Wall> walls) {
     
        double offsetX = 0.0001;
        Line2D lineOfSight = new Line2D.Double(a.x + offsetX, a.y, b.x, b.y);
        for (Wall wall : walls) {
            Line2D wallSegment = new Line2D.Double(wall.start.x, wall.start.y, wall.end.x, wall.end.y);
            if (lineOfSight.intersectsLine(wallSegment)) {
                return false;
            }
        }
        return true;
    }

    public static boolean bpm(boolean[][] bpGraph, int u, boolean[] seen, int[] matchR) {
        for (int v = 0; v < bpGraph[u].length; v++) {
            if (bpGraph[u][v] && !seen[v]) {
                seen[v] = true;
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v], seen, matchR)) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    public static int maxBPM(boolean[][] bpGraph) {
        int[] matchR = new int[bpGraph[0].length];
        for (int i = 0; i < matchR.length; i++) {
            matchR[i] = -1;
        }
        int result = 0;
        for (int u = 0; u < bpGraph.length; u++) {
            boolean[] seen = new boolean[bpGraph[0].length];
            if (bpm(bpGraph, u, seen, matchR)) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//yaha sa input laina start hoga 
        System.out.println("Enter number of coordinates for floor plan:");
        int numCoords = scanner.nextInt();
        List<Point> floorPlan = new ArrayList<>();

        System.out.println("Please enter each coordinate in clockwise order (x y):"); //sir na kaha tha clockwise laina ha cordinates
        for (int i = 0; i < numCoords; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            floorPlan.add(new Point(x, y));
        }

        List<Wall> walls = new ArrayList<>();
        for (int i = 0; i < floorPlan.size() - 1; i++) {
            walls.add(new Wall(floorPlan.get(i), floorPlan.get(i + 1)));
        }

 
        walls.add(new Wall(floorPlan.get(0), floorPlan.get(1)));


        System.out.println("Enter number of light fixtures and switches:");
        int n = scanner.nextInt();
        Point[] fixtures = new Point[n];
        Point[] switches = new Point[n];

        System.out.println("Please enter each coordinate of each light fixture (x y):");
        for (int i = 0; i < n; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            fixtures[i] = new Point(x, y);
        }

        System.out.println("Please enter each coordinate of each switch (x y):");
        for (int i = 0; i < n; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            switches[i] = new Point(x, y);
        }

        boolean[][] bpGraph = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (hasLineOfSight(fixtures[i], switches[j], walls)) {
                    bpGraph[i][j] = true;
                }
            }
        }
//yaha condition match hogi

        int maxPairs = maxBPM(bpGraph);
        System.out.println("Maximum number of light fixtures with LoS to switches: " + maxPairs);

        if (maxPairs == n) {
            System.out.println("The floor plan is ergonomic.");
        } else {
            System.out.println("The floor plan is not ergonomic.");
        }

        scanner.close();
    }
}
//masla pata nhi kaha ha 
