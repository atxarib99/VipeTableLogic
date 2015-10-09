/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vipetablelogic;

/**
 *
 * @author dhuka_844963
 */
public class Chunk {
    
    private int startIndex;
    private int endIndex;
    
    public Chunk(int si, int ei) {
        this.startIndex = si;
        this.endIndex = ei;
    }
    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

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
