/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novaes.rectangle.overlap;

/**
 *
 * @author andre
 */
public class Solution {

    public boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
        Rectangle rec1 = new Rectangle(l1, r1);
        Rectangle rec2 = new Rectangle(l2, r2);
        return rec1.overlaps(rec2.bottomLeft) || rec1.overlaps(rec2.bottomRight) || rec1.overlaps(rec2.topLeft) || rec1.overlaps(rec2.topRight)
                || rec2.overlaps(rec1.bottomLeft) || rec2.overlaps(rec1.bottomRight) || rec2.overlaps(rec1.topLeft) || rec2.overlaps(rec1.topRight) ||
                rec1.verticalLeft.overlaps(rec2.horizontalTop) || rec1.verticalRight.overlaps(rec2.horizontalBottom) || 
                rec1.verticalRight.overlaps(rec2.horizontalTop) || rec1.verticalLeft.overlaps(rec2.horizontalBottom) ||
                rec2.verticalLeft.overlaps(rec1.horizontalTop) || rec2.verticalRight.overlaps(rec1.horizontalBottom) || 
                rec2.verticalRight.overlaps(rec1.horizontalTop) || rec2.verticalLeft.overlaps(rec1.horizontalBottom);
    }
    
    public boolean doOverlap2(Point l1, Point r1, Point l2, Point r2) {
        //above
        if(r1.y > l2.y || r2.y > l1.y) {
            return false;
        }
        //side
        if(r1.x < l2.x || r2.x < l1.x) {
            return false;
        }
        
        return true;
    }

    static class Vertex {

        Point p1;
        Point p2;

        public Vertex(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public boolean overlaps(Vertex vertex) {
            return vertex.p1.x <= this.p1.x && vertex.p1.y <= this.p1.y
                    && vertex.p2.x >= this.p2.x && vertex.p2.y >= this.p2.y;
        }
    }

    static class Rectangle {

        Point topLeft;
        Point bottomLeft;
        Point topRight;
        Point bottomRight;
        public Vertex horizontalTop;
        public Vertex horizontalBottom;
        public Vertex verticalLeft;
        public Vertex verticalRight;

        public Rectangle(Point topLeft, Point bottomRight) {
            this.topLeft = topLeft;
            this.bottomRight = bottomRight;
            this.bottomLeft = new Point(topLeft.x, bottomRight.y);
            this.topRight = new Point(bottomRight.x, topLeft.y);
            this.horizontalTop = new Vertex(topLeft, topRight);
            this.horizontalBottom = new Vertex(bottomLeft, bottomRight);
            this.verticalLeft = new Vertex(topLeft, bottomLeft);
            this.verticalRight = new Vertex(topRight, bottomRight);

        }

        public boolean overlaps(Point point) {
            return this.bottomLeft.x <= point.x && this.bottomLeft.y <= point.y
                    && this.topLeft.x <= point.x && this.topLeft.y >= point.y
                    && this.topRight.x >= point.x && topRight.y >= point.y
                    && bottomRight.x >= point.x && bottomRight.y <= point.y;
        }

        

    }

    static class Point {

        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().doOverlap(new Point(0, 2), new Point(15, 1), new Point(8, 3), new Point(12, 0)));

    }
}
