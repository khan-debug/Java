import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ErgonomicFloorPlan1 {
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
        Line2D lineOfSight = new Line2D.Double(a.x, a.y, b.x, b.y);
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

        System.out.println("Enter number of co-ordinates for floor plan:");
        int numCoords = scanner.nextInt();
        List<Point> floorPlan = new ArrayList<>();

        System.out.println("Please enter each coordinate in clockwise order (x y):");
        for (int i = 0; i < numCoords; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            floorPlan.add(new Point(x, y));
        }

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

        // Create walls from floor plan coordinates
        List<Wall> walls = new ArrayList<>();
        for (int i = 0; i < floorPlan.size(); i++) {
            Point start = floorPlan.get(i);
            Point end = floorPlan.get((i + 1) % floorPlan.size());
            walls.add(new Wall(start, end));
        }

        boolean[][] bpGraph = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (hasLineOfSight(switches[i], fixtures[j], walls)) {
                    bpGraph[i][j] = true;
                }
            }
        }

        int maxMatching = maxBPM(bpGraph);
        if (maxMatching == n) {
            System.out.println("Given floor plan is ergonomic.");
        } else {
            System.out.printf("Given floor plan is not ergonomic. Only %d out of %d light fixtures can have LoS to switches.\n", maxMatching, n);
        }
        scanner.close();
    }
}
