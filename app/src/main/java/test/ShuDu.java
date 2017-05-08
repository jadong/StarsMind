package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zengwendong on 16/12/20.
 */
public class ShuDu {

    private static int[][] data = new int[9][9];
    private static int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static Map<String, Point[]> jiugongMap = new HashMap<>();

    public static void initData() {

        List<int[]> list = new ArrayList<>();
        getInts(list);
        //九宫格
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                jiugongMap.put(i + "-" + j, initJiuGong(i, i));
            }
        }

        for (int i = 0; i < list.size(); i++) {
            int[] row = list.get(i);
            for (int j = 0; j < row.length; j++) {
                data[i][j] = row[j];
                System.out.print(data[i][j] + "  ");

            }
            System.out.println("");
        }

        System.out.println("-------------------------------");

    }

    public static void logic() {


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = data[i][j];
                if (val == 0) {
                    Point[] row = getRow(i);
                    List<Point> row_houXuanPoint = getHouXuanPoint(row);

                    Point[] column = getColumn(j);
                    List<Point> column_houXuanPoint = getHouXuanPoint(column);

                    Point[] jiuGong = getJiuGong(i, j);
                    List<Point> jiuGong_houXuanPoint = getHouXuanPoint(jiuGong);

                }
            }
        }

    }

    private static Point[] getJiuGong(int i, int j) {
        Point[] points = jiugongMap.get(i + "-" + j);
        return points;
    }


    private static Point[] initJiuGong(int r, int c) {
        Point[] points = new Point[9];
        int index = 0;
        for (int i = (r - 1) * 3; i < r * 3; i++) {
            for (int j = (c - 1) * 3; j < c * 3; j++) {
                points[index] = new Point(i, j);
            }
            index++;
        }
        return points;
    }

    private static int getInt(Point point) {
        return data[point.getX()][point.getY()];
    }

    private static void getInts(List<int[]> list) {
        int[] row1 = {2, 0, 8, 3, 0, 0, 0, 9, 4};
        int[] row2 = {5, 0, 7, 6, 0, 2, 8, 0, 1};
        int[] row3 = {1, 3, 0, 0, 0, 9, 5, 0, 6};
        int[] row4 = {0, 7, 0, 0, 0, 0, 2, 0, 0};
        int[] row5 = {0, 0, 0, 0, 7, 3, 0, 1, 0};
        int[] row6 = {8, 1, 0, 0, 2, 4, 0, 0, 7};
        int[] row7 = {3, 0, 0, 1, 6, 0, 0, 7, 2};
        int[] row8 = {0, 4, 0, 2, 9, 0, 0, 0, 0};
        int[] row9 = {0, 0, 1, 0, 0, 0, 0, 6, 0};
        list.add(row1);
        list.add(row2);
        list.add(row3);
        list.add(row4);
        list.add(row5);
        list.add(row6);
        list.add(row7);
        list.add(row8);
        list.add(row9);
    }

    public Point[] getJiuGongBy(int x, int y) {
        int row = x >= 0 && x < 3 ? 1 : x >= 3 && x < 6 ? 2 : x >= 6 && x < 9 ? 3 : 0;
        int column = y >= 0 && y < 3 ? 1 : y >= 3 && y < 6 ? 2 : y >= 6 && y < 9 ? 3 : 0;
        Point[] points = jiugongMap.get(row + "-" + column);
        return points;
    }

    public static Point[] getRow(int x) {

        System.out.println("--------getRow---------------------");
        Point[] points = new Point[9];
        for (int i = 0; i < 9; i++) {
            points[i] = new Point(x, i);
            System.out.print(data[x][i] + "  ");
        }

        System.out.println("");
        return points;
    }

    public static Point[] getColumn(int y) {

        System.out.println("--------getColumn---------------------");
        Point[] points = new Point[9];
        for (int i = 0; i < 9; i++) {
            points[i] = new Point(i, y);
            System.out.println(data[i][y]);
        }

        System.out.println("");
        return points;
    }

    /**
     * 后选数
     */
    private static List<Point> getHouXuanPoint(Point[] points) {
        List<Point> houXuan = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            int val = data[points[i].getX()][points[i].getY()];
            if (val == 0) {
                houXuan.add(points[i]);
            }
        }
        return houXuan;
    }
}
