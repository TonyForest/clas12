/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.root.series;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import org.root.base.IDrawableDataSeries;
import org.root.pad.GraphAxis;

/**
 *
 * @author gavalian
 */
public class DataSeriesText implements IDrawableDataSeries {
    private ArrayList<String> paveText = new ArrayList<String>();
    private Double            coordX   = 0.0;
    private Double            coordY   = 0.0;
    private Font              labelFont = new Font(Font.MONOSPACED,Font.PLAIN,12);
    
    public DataSeriesText(){
        
    }
    
    public DataSeriesText(String[] lines){
        this.setText(lines);
    }
    
    public DataSeriesText(String[] lines, double x, double y){
        this.setText(lines);
        this.coordX = x;
        this.coordY = y;
    }
    public void setXY(double x, double y){ this.coordX = x; this.coordY = y;}
    public void setText(String[] lines){
        this.paveText.clear();
        for(String item : lines){
            this.paveText.add(item);
        }
    }
    
    public void addText(String[] lines){
        for(String item : lines){
            this.paveText.add(item);
        }
    }
    
    public void addLine(String line){
        this.paveText.add(line);
    }
    public void setFontSize(int size){
        this.labelFont = new Font(Font.MONOSPACED, Font.PLAIN, size);
    }
    public double getMinX() {
        return 0.0;
    }

    public double getMaxX() {
        return 0.0;
    }

    public double getMinY() {
        return 0.0;
    }

    public double getMaxY() {
        return 0.0;
    }

    public void draw(GraphAxis xaxis, GraphAxis yaxis, Graphics2D g2d) {
        //System.out.println("Painting the label");
        g2d.setFont(labelFont);
        FontMetrics fm = g2d.getFontMetrics();
        double firstX   = xaxis.getOriginX()+(this.coordX*xaxis.getLength());
        double firstY   = yaxis.getOriginY()-(this.coordY*yaxis.getLength())+fm.getHeight();
        
        for(int loop = 0; loop < this.paveText.size();loop++){
            g2d.drawString(this.paveText.get(loop), (int) firstX, (int) firstY);
            firstY += 1.0*fm.getHeight();
        }
    }

    public Object dataObject() {
        return null;
    }

    public IDrawableDataSeries fit(String function, String options) {
        return null;
    }

    public String[] getStatText() {
        return new String[0];
    }
    
}
