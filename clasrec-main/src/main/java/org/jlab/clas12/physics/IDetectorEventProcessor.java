/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jlab.clas12.physics;


/**
 *
 * @author gavalian
 */
public interface IDetectorEventProcessor {   
    boolean  processDetectorEvent(DetectorEvent event);
}
