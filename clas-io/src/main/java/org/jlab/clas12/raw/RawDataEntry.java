/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jlab.clas12.raw;

import java.util.ArrayList;
import org.jlab.data.utils.EvioDataConvertor;

/**
 *
 * @author gavalian
 */
public class RawDataEntry {
    
    private Integer CRATE    = 0;
    private Integer SLOT     = 0;
    private Integer CHANNEL  = 0;
    
    private Integer SECTOR    = 0;
    private Integer LAYER     = 0;
    private Integer COMPONENT = 0;
    /**
     * Mode defines what will be in the Array.
     * MODE = 1 : raw Flash ADC mode
     * MODE = 7 : Pulse ADC mode
     * MODE = 4 : SVT mode
     */
    private Integer MODE      = 0;
    private final ArrayList<Object> dataArray = new  ArrayList<Object>();
    
    public RawDataEntry(int _cr, int _sl, int _ch){
        this.CRATE = _cr;
        this.SLOT  = _sl;
        this.CHANNEL = _ch;
    }
    
    public void setPulse(Short tdc, Integer adc, Short pulseMin, Short pulseMax){
        this.MODE = 7;
        this.dataArray.clear();
        this.dataArray.add(tdc);
        this.dataArray.add(adc);
        this.dataArray.add(pulseMin);
        this.dataArray.add(pulseMax);
    }
    
    public void setRawPulse(short[] shortData){
        this.MODE = 1;
        this.dataArray.clear();
        for(int loop = 0; loop < shortData.length; loop++){
            dataArray.add(shortData[loop]);
        }
    }
    
    public void setSVT(Byte half, Byte channel, Byte adc, Byte tdc){
        this.MODE = 4;
        this.dataArray.clear();
        this.dataArray.add(EvioDataConvertor.getShortFromByte(tdc));
        this.dataArray.add(EvioDataConvertor.getShortFromByte(adc));
        this.dataArray.add(EvioDataConvertor.getShortFromByte(half));
        this.dataArray.add(EvioDataConvertor.getShortFromByte(channel));
    }
    
    public int getCrate()   { return this.CRATE;}
    public int getSlot()    { return this.SLOT;}
    public int getChannel() { return this.CHANNEL;}
    
    public void setSectorLayerComponent(int _sc, int _lay, int _comp){
        this.SECTOR = _sc;
        this.LAYER = _lay;
        this.COMPONENT = _comp;
    }

    public Integer getADC(){
        if(this.MODE==7 || this.MODE==4){
            return (Integer) dataArray.get(1);
        }
        return 0;
    }
    
    public Short getTDC(){
        if(this.MODE==7 || this.MODE==4){
            return (Short) dataArray.get(0);
        }
        //Short result = 0;
        return 0;
    }
    
    public Integer getSVTChannel(){
        Short firstShort = (Short) this.dataArray.get(3);
        Integer trueChannel =  (firstShort & 0x0000007F);
        //Integer chipID      =  (firstShort & 0x00000700)>>8 ;
        //Integer half        =  (firstShort & 0x00000800)>>11;
        return trueChannel;
    }
    
    public Integer getSVTChipID(){
        Short firstShort = (Short) this.dataArray.get(2);
        Integer chipID      =  (firstShort & 0x00000007);
        return chipID;
    }
    
    public Integer getSVTHalf(){
        Short firstShort  = (Short) this.dataArray.get(2);
        Integer half      =  (firstShort & 0x00000008);
        return  half;
    }
    
    public Short getPulseMin(){
        if(this.MODE==7){
            return (Short) dataArray.get(2);
        }
        return 0;
    }
    
    public Short getPulseMax(){
        if(this.MODE==7){
            return (Short) dataArray.get(3);
        }
        return 0;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(String.format("C/S/C [%4d %4d %5d] ", this.CRATE,this.SLOT,this.CHANNEL));
        str.append(String.format("S/L/C [%4d %4d %5d] ", this.SECTOR,this.LAYER,this.COMPONENT));
        if(this.MODE==7){
            str.append(String.format(" T/A/Min/Max [%6d %6d %6d %6d] \n",this.getTDC(),this.getADC(),
                    this.getPulseMin(),this.getPulseMax()));
        }
        
        if(this.MODE==4){
            str.append(String.format(" SVT H/C/T/A [%6d %6d %6d %6d] \n",this.getSVTHalf(),
                    this.getSVTChannel(),
                    this.getPulseMin(),this.getPulseMax()));
        }
        
        return str.toString();
    }
}