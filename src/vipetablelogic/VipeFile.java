/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vipetablelogic;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dhuka_844963
 */
/*VipeFile is a data class that keeps track of chunks as they are allocated across the directory 
When Displayed in the directory, a VipeFile will show a color and the fileID.
To serialize instance of the VipeFile, "fileID" will be declared static.
*/
//Defines what a file is
public class VipeFile implements Serializable{
    ArrayList<Chunk> chunks; //grid spaces
    int fileSize; //size of the file
    String fileName; //name of the file
    Color sectorColor; //determines the color
    int fileID; //id of the file
    
    public VipeFile(int fs, String fn, Color c, int fid) {
        fileSize = fs;
        fileName = fn;
        sectorColor = c;
        chunks = new ArrayList<Chunk>();
        //serialize fileID
        fileID = fid;
    } 
    //gets chunks from the arraylist
    public ArrayList<Chunk> getChunks() {
        return chunks;
    }
    //set the chunks
    public void setChunks(ArrayList<Chunk> chunks) {
        this.chunks = chunks;
    }
    //gets a single chunk
    public Chunk getChunk(int ind) {
        return chunks.get(ind);
    }
    //gets the size fo teh file
    public int getFileSize() {
        return fileSize;
    }
    //sets the size of the file
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    //returns the name of the file
    public String getFileName() {
        return fileName;
    }
    //set the name of the file
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    //gets the color of the sector
    public Color getSectorColor() {
        return sectorColor;
    }
    //sets the color of the sector
    public void setSectorColor(Color sectorColor) {
        this.sectorColor = sectorColor;
    }
    //gets the id of the file
    public int getFileID() {
        return fileID;
    }


    /*    public String toString() 
     <fileID> <fileName> (<sectorColor>, <fileSize>s)
     <chunks>
     Example:
     1 Quiz.doc (Red, 15s)
     [0-6] [12-18] [39-40]
 
     private String getChunkString()
     <Chunk1> <Chunk2> ….
     Example:
     [0-6] [12-18] [39-40]

     */ //unneeded toString but kept for reference
    @Override
    public String toString() {
        String output = "";
        output += getFileID() + " ";
        output += getFileName() + " ";
        output += "(" + getSectorColor() + "), ";
        output += getFileSize() + ")" + "\n";
        output += getChunkString();
        return output;
    }
    //Returns the String representation of all the chinks in a VipeFile
    private String getChunkString() {
        String output = "";
        for(int i = 0; i < chunks.size(); i ++) {
            output += chunks.get(i).toString();
        }
        return output;
    }
}
