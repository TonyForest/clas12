
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.root.func;
import java.util.ArrayList;
import org.root.attr.Attributes;
import org.root.data.DataSetXY;


/**
 *
 * @author gavalian
 */
public class Function1D  {
    
    private double     functionMin = 0;
    private double     functionMax = 0;
    private String  functionString = "g";
    
    private final ArrayList<RealParameter>  funcParams = new ArrayList<RealParameter>();
    
    private final Attributes attr = new Attributes();
    
    public Function1D(){
        this.setLineColor(4);
        this.setLineWidth(2);
        this.setLineStyle(1);
    }
    
    public int  getLineColor(){
        return Integer.parseInt(this.attr.getProperties().getProperty("line-color"));
    }
    
    public int  getLineWidth(){
        return Integer.parseInt(this.attr.getProperties().getProperty("line-width"));
    }
    
    public int  getLineStyle(){
        return Integer.parseInt(this.attr.getProperties().getProperty("line-style"));
    }
    
    public final void setLineColor(Integer color){
        this.attr.getProperties().put("line-color", color.toString());
    }
    
    public final void setLineWidth(Integer width){
        this.attr.getProperties().put("line-width", width.toString());
    }
    
    public final void setLineStyle(Integer style){
        this.attr.getProperties().put("line-style", style.toString());
    }
    
    public void setRange(double min, double max){
        functionMin = min;
        functionMax = max;
    }
    
    public double getMin(){ return functionMin; }
    public double getMax(){ return functionMax; }
    
    public void setNParams(int n){
        funcParams.clear();
        for(int loop = 0; loop < n; loop++){
            String parname = "p"+loop;
            RealParameter par = new RealParameter();
            par.setValue(0.0);
            par.setName(parname);
            funcParams.add(par);
        }
    }
    
    public void setFunction(String funcstring){
        functionString = funcstring;
    }
    
    public int getNParams(){
        return this.funcParams.size();
    }
    
    public ArrayList<RealParameter>  parameters(){ return this.funcParams;}
    
    public double[] getParameters(){
        double[] pars = new double[this.funcParams.size()];
        for(int loop = 0; loop < this.funcParams.size();loop++){
            pars[loop] = funcParams.get(loop).value();
        }
        return pars;
    }
    
    public void setParameters(double[] par){
        for(int loop = 0; loop < par.length;loop++){
            funcParams.get(loop).setValue(par[loop]);
        }
    }
    
    public RealParameter parameter(int index){
        return funcParams.get(index);
    }
    
    public double eval(double x){
        return 1;
    }
    
    public DataSetXY getDataSet(){
        return getDataSet(100);
    }
    
    public DataSetXY getDataSet(int npoints){
        double step = (functionMax - functionMin)/npoints;
        DataSetXY data = new DataSetXY();
        for(int loop = 0; loop < npoints; loop++){
            double xv = functionMin+loop*step;
            double yv = this.eval(xv);
            data.getDataX().add(xv);
            data.getDataY().add(yv);
        }
        return data;
    }
    
    
    public int    getNDF(DataSetXY data){
        int npoints = 0;
        for(int loop = 0; loop < data.getDataY().getSize();loop++){
            double xv = data.getDataX().getValue(loop);
            if(xv>=this.getMin()&&xv<=this.getMax()){
                npoints++;
            }
        }
        return npoints-this.getNParams();
    }
    
    public double getChiSquare(DataSetXY data){
        double chi2 = 0.0;
        for(int loop = 0; loop < data.getDataY().getSize();loop++){
            double xv = data.getDataX().getValue(loop);
            double yv = data.getDataY().getValue(loop);
            double fv = this.eval(xv);
            if(yv!=0&&xv>=this.getMin()&&xv<=this.getMax()){
                chi2 += (yv-fv)*(yv-fv)/(yv);
                //System.err.println("adding " + xv + " " + chi2);
            }
        }
        return chi2;
    }
    
    public void show(){
        StringBuilder str = new StringBuilder();
        str.append("FUNCTION : "); 
        str.append(functionString);
        str.append("\n");
        str.append("LIMITS   : "); 
        str.append(String.format("%e %e\n\n", this.getMin(),this.getMax())); 
        for(RealParameter par : funcParams){
            str.append(par.toString());
            str.append("\n");
        }
        System.err.println(str.toString());
    }
    
}
