/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vipetablelogic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dhuka_844963
 */
public class Directory implements Serializable{
    //ArrayList uses VipeFiles and sectors holds grid valies
    ArrayList<VipeFile> files;
    int[] sectors;
    //holds data for use
    public Directory() {
        files = new ArrayList<VipeFile>();
        sectors = new int[600];
    }
    //gets viles
    public ArrayList<VipeFile> getFiles() {
        return files;
    }
    //sets the files to the parameter
    public void setFiles(ArrayList<VipeFile> files) {
        this.files = files;
    }
    //gets the chunks from the VipeFile from given ID
    public ArrayList<Chunk> getChunks(int fileID) {
        ArrayList<Chunk> chunks = new ArrayList<>();
        boolean startedChunk = false;
        int start = 0;
        int end = 0;
        for (int i = 0; i < sectors.length; i++) {
            if (sectors[i] == fileID && !startedChunk) {
               start = i;
               startedChunk = true;
            }
            if (i < sectors.length - 1 && sectors[i+1] != fileID && startedChunk) {
                end = i;
                startedChunk = false;
                chunks.add(new Chunk(start + 1, end + 1));
            }
        }

        return chunks;
    }
    //return index of a file from its ID
    public int getFileFromID(int id) {
        for(int i = 0 ; i < files.size(); i++) {
            if(files.get(i).getFileID() == id)
                return i;
        }
        return -1;
    }
    //returns an array of the sectors
    public int[] getSectors() {
        return sectors;
    }
    //set the sectors
    public void setSectors(int[] sectors) {
        this.sectors = sectors;
    }
    //outputs the directory for debugging
    @Override
    public String toString() {
        String returnable = "";
        int count = 0;
        int[] localSectors = sectors;
       
        for (int i = 0; i < sectors.length; i++) {
            
            if(count % 20 == 0 && count != 0) {
                returnable += "\n";
            }
            returnable += localSectors[i];
            count++;
        }
        return returnable;
    }
}