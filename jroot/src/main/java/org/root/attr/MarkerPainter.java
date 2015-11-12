/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.root.attr;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;

/**
 *
 * @author gavalian
 */
public class MarkerPainter {
    private Color markerFillColor = new Color(221,  75,  57);
    private Color markerStrokeColor = Color.BLACK;
    private Stroke markerStroke     = new BasicStroke(1);
    
    public MarkerPainter(){
        
    }
    
    
    public void set(){
        
    }
    
    public void drawMarker(Graphics2D g2d, int x, int y, int mtype, int msize, int mcolor,
            int linecolor, int linewidth){
        
        switch (mtype){
            case 1: {
                g2d.setColor(ColorPalette.getColor(mcolor));
                g2d.drawOval(x-msize/2, y-msize/2, msize, msize);
            } break;
                
            case 2: {
                this.markerFillColor   = ColorPalette.getColor(mcolor);
                this.markerStrokeColor = ColorPalette.getColor(linecolor);
                this.drawCircle(g2d, x, y, msize); 
                //g2d.setColor(ColorPalette.getColor(mcolor));                
            } break;
        }
    }
    
    public void drawMarker(Graphics2D g2d, int x, int y, int mtype, int msize){
        //g2d.setStroke(new BasicStroke(4));
        switch (mtype){
            case 1  : g2d.drawOval(x-msize/2, y-msize/2, msize, msize); break;
            case 2  : this.drawCircle(g2d, x, y, msize); break;
            case 3  : g2d.fillRect(x-msize/2, y-msize/2, msize, msize); break;
            case 4  : this.drawCross(g2d, x, y, msize); ; break;
            case 5  : this.drawTriangle(g2d, x, y, msize, 1); ; break;
            case 6  : this.drawTriangle(g2d, x, y, msize,-1); ; break;
            default : g2d.drawRect(x-msize/2, y-msize/2, msize, msize); break;
        }
    }
    
    public void drawCircle(Graphics2D g2d, int x, int y,int msize){
        g2d.setColor(markerFillColor);
        g2d.fillOval(x-msize/2, y-msize/2, msize, msize);
        g2d.setColor(markerStrokeColor);
        g2d.setStroke(markerStroke);
        g2d.drawOval(x-msize/2, y-msize/2, msize, msize);
    }
    
    public void drawCross(Graphics2D g2d, int x, int y,int msize){
        double xl = x - msize/2;
        double xr = x + msize/2;
        double yt = y - msize/2;
        double yb = y + msize/2;
        g2d.drawLine((int) xl, (int) yt, (int) xr, (int) yb);
        g2d.drawLine((int) xr, (int) yt, (int) xl, (int) yb);
    }
    
    
     public void drawMarkerBasic(
             Graphics2D g2d, int x, int y , int mtype, int msize,          
            int linewidth, int linecolor, int fillcolor){
             
        
        switch (mtype){
            case 1: {
                this.drawMarkerCircle(g2d, x, y, msize, linewidth, linecolor, fillcolor);
            } break;
                
            case 2: {
                this.drawMarkerSquare(g2d, x, y, msize, linewidth, linecolor, fillcolor);
            } break;
                
            case 3: {
                this.drawMarkerTriangle(g2d, x, y, msize, linewidth, linecolor, fillcolor);
            } break;
                
            case 4: {
                this.drawMarkerCross(g2d, x, y, msize, linewidth, linecolor, fillcolor);
            } break;
        }
    }
    
    public void drawMarkerTriangle(Graphics2D g2d, int x, int y , int msize,            
            int linewidth, int linecolor, int fillcolor){
        GeneralPath path = new GeneralPath();
        int    hc = (int) (msize*0.5); // This is marker size divided by 6
        path.moveTo(x , y - hc);
        path.lineTo(x - hc, y + hc);
        path.lineTo(x + hc, y + hc);
        path.lineTo(x , y - hc);
        g2d.setColor(ColorPalette.getColor(fillcolor));
        g2d.fill(path);
        g2d.setColor(ColorPalette.getColor(linecolor));
        g2d.setStroke(new BasicStroke(linewidth));
        g2d.draw(path);
    }
    
    public void drawMarkerCircle(Graphics2D g2d, int x, int y , int msize,            
            int linewidth, int linecolor, int fillcolor){
                
        g2d.setColor(ColorPalette.getColor(fillcolor));
        g2d.fillOval(x-msize/2, y-msize/2, msize, msize);
        
        g2d.setColor(ColorPalette.getColor(linecolor));
        g2d.setStroke(new BasicStroke(linewidth));
        g2d.drawOval(x-msize/2, y-msize/2, msize, msize);
    }
    
    public void drawMarkerSquare(Graphics2D g2d, int x, int y , int msize,            
            int linewidth, int linecolor, int fillcolor){
        GeneralPath path = new GeneralPath();
        int    hc = (int) (msize*0.5); // This is marker size divided by 6
        path.moveTo(x - hc, y - hc);
        path.lineTo(x - hc, y + hc);
        path.lineTo(x + hc, y + hc);
        path.lineTo(x + hc, y - hc);
        path.lineTo(x - hc, y - hc);
        
        g2d.setColor(ColorPalette.getColor(fillcolor));
        g2d.fill(path);
        g2d.setColor(ColorPalette.getColor(linecolor));
        g2d.setStroke(new BasicStroke(linewidth));
        g2d.draw(path);
    }
    
    public void drawMarkerCross(Graphics2D g2d, int x, int y , int msize,            
            int linewidth, int linecolor, int fillcolor){
        GeneralPath path = new GeneralPath();
        int    hc = (int) (msize*0.166667); // This is marker size divided by 6
        path.moveTo(x - hc, y - hc);
        path.lineTo(x - hc, y - 2*hc);
        path.lineTo(x + hc, y - 2*hc);
        path.lineTo(x + hc, y - hc);
        path.lineTo(x + 2*hc, y - hc);
        path.lineTo(x + 2*hc, y + hc);
        path.lineTo(x + hc, y + hc);
        path.lineTo(x + hc, y + 2*hc);
        path.lineTo(x - hc, y + 2*hc);
        path.lineTo(x - hc, y + hc);
        path.lineTo(x - 2*hc, y + hc);
        path.lineTo(x - 2*hc, y - hc);
        path.lineTo(x - hc, y - hc);
        g2d.setColor(ColorPalette.getColor(fillcolor));
        g2d.fill(path);
        g2d.setColor(ColorPalette.getColor(linecolor));
        g2d.setStroke(new BasicStroke(linewidth));
        g2d.draw(path);
    }
    
    
    public void drawTriangle(Graphics2D g2d, int x, int y, int msize, int factor){
        double xl = x - msize/2;
        double xr = x + msize/2;
        double yt = y - factor*msize/2;
        double yb = y + factor*msize/2;
        GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        path.moveTo(xr, yt);
        path.lineTo(xl, yt);
        path.lineTo(x , yb);
        path.closePath();
        g2d.draw(path);
    }
}
