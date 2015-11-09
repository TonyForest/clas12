/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.root.pad;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import org.root.base.IDataSet;

/**
 *
 * @author gavalian
 */
public class TGCanvas extends JFrame {
    private EmbeddedCanvas embCanvas = null;
    
    public TGCanvas(){
        super();
        embCanvas = new EmbeddedCanvas(600,600);
        this.add(embCanvas,BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
    
    public TGCanvas(String name, String title, int xs, int ys, int rows, int cols){
        super(title);
        embCanvas = new EmbeddedCanvas(xs,ys,rows,cols);
        this.add(embCanvas,BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
    
    public  EmbeddedCanvas getCanvas(){return this.embCanvas;}
    
    public void setAxisRange(double xmin, double xmax, double ymin, double ymax){
        this.getCanvas().setAxisRange(xmin, xmax, ymin, ymax);
    }
    
    public void cd(int pad){
        this.embCanvas.cd(pad);
    }
    
    public void draw(IDataSet ds){
        this.embCanvas.draw(ds);
    }
    
    public void draw(IDataSet ds, String option){
        this.embCanvas.draw(ds,option);
    }
    
    public void setLogZ(){
        this.embCanvas.setLogZ();
    }
    
    public void setLogX(boolean logFlag){
        this.embCanvas.setLogX(logFlag);
    }
    
    public void setLogY(boolean logFlag){
        this.embCanvas.setLogY(logFlag);
    }
    
    public void setLogZ(boolean logFlag){
        this.embCanvas.setLogZ(logFlag);
    }
    
    public void update(){
        this.embCanvas.update();
    }
    
    public void save(String filename){
        this.embCanvas.save(filename);
    }
}
