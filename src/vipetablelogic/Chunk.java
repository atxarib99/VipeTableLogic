/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vipetablelogic;

import java.io.Serializable;

/**
 *
 * @author dhuka_844963
 */
public class Chunk implements Serializable{
    
    private int startIndex;
    private int endIndex;
    //used to determine spaces in grid
    public Chunk(int si, int ei) {
        this.startIndex = si;
        this.endIndex = ei;
    }
    //get the start of the spaces
    public int getStartIndex() {
        return startIndex;
    }
    //sets the start of the spaces
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
    //gets the end of the spaces
    public int getEndIndex() {
        return endIndex;
    }
    //sets the end of the spaces
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
    

    
    //Chunk holds the index refrences for a span of sector data
    @Override
    public String toString() {
        return "[" + startIndex + " - " + endIndex + "] ";
        //TODO: Code String output for Chunk Class
    }
}
