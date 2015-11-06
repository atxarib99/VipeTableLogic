/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vipetablelogic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author dhuka_844963
 */
public class VipeTableLogic {

    Directory dir;
    int[] sender = new int[600];
    public VipeTableLogic()
    {
        dir = new Directory();
    }
    //returns a String representation of a file’s chunks
    public String getChunkString(int fileID) {
        return "";
    }
    //returns the total number of unmarked sectors
    public int getAvailableSize() {
        int count = 0;
        for (int i = 0; i < dir.sectors.length; i++) {
            if(dir.sectors[i] == 0)
                count++;
        }
        return count;
    }
    private boolean isIdAvailable(int fileID) {
        for(VipeFile file : dir.files) {
            if(file.getFileID() == fileID)
                return false;
        }
        return true;
    }
    //returns the next available chunk in the directory
    private Chunk getNextChunk(VipeFile file) {
        int[] local = new int[file.getFileSize()];
        int count = 0;
        int spot = 0;
        while (count < file.getFileSize()) {
            if(dir.sectors[count] == 0) {
                local[spot] = count;
                count++;
                spot++;
            }
            else
                count++;
        }
        return new Chunk(local[0], local[local.length - 1]);
    }
    //writes file to the directory's sector array
    private void writeFile(VipeFile file) {
        VipeFile local = file;
        if(file.getFileSize() > 0 && getAvailableSize() > local.getFileSize()) {
            int i = 0;
            int count = 0;
            while (i < local.getFileSize()) {
                Chunk c = getNextChunk(local);
                while(count < dir.sectors.length && i < local.getFileSize()) {
                    if(dir.sectors[count] == 0){
                        i++;
                        dir.sectors[count] = local.getFileID();
                        count++;
                    }
                    else
                        count++;
                }
                local.chunks.add(c);
            }
            dir.files.add(local);
        }
    }
    //creates the 2DGraphic representation of the directory
    private void writeGrid() {
        
    }
    //gets the color based on id
        private Color getColor(int id) {
        Color color;
        int useable = id%6;
        switch (useable) {
            case 1 : color = Color.red; break;
            case 2 : color = Color.green; break;
            case 3 : color = Color.blue; break;
            case 4 : color = Color.yellow; break;
            case 5 : color = Color.magenta; break;
            case 6 : color = Color.orange; break;
            default: color = Color.white;
        }
        return color;
    }
    //removes the file from the directory and calls writeGrid()
    private void deleteFile(int fileID) {
        for (int i = 0; i < dir.sectors.length; i++) {
            if (dir.sectors[i] == fileID)
                dir.sectors[i] = 0;
        }
        for (int i = 0; i < dir.files.size(); i++) {
            if (dir.files.get(i).fileID == fileID)
                dir.files.remove(i);
        }
        dir.toString();
    }
    //edits a existing file
    private void editFile(int fileID, int sectorChange)  {
        for (VipeFile file : dir.files) {
            if (file.fileID == fileID) {
                file.setFileSize(file.getFileSize() + sectorChange);
            }
        }
        dir.toString();
    }
    private VipeFile getFileFromID(int fileID) {
        for(VipeFile file : dir.files) {
            if (file.getFileID() == fileID)
                return file;
        }
        return null;
    }
     /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        VipeTableLogic vtl = new VipeTableLogic();
        
        
    }

    private void importData() {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
        
    }

    public void writeData(String fn, int fs) {
        String fileName = "";
        int fileSize = 0;
        int fileID = getAvailableID();
        Color temp = getColor(fileID);
        VipeFile sendable = new VipeFile(fileSize, fileName, temp, fileID);
        writeFile(sendable);
        System.out.println(dir);
    }

    public void deleteData(int id) {
        int fileID = id;
        deleteFile(fileID);
        System.out.println(dir);
    }

    public void editData(int id, int change) {
        int sizeChange = change;
        int fileID = id;
        editFile(fileID, sizeChange);
        dir.toString();
    }
    private int getAvailableID() {
        int avail = 1;
        int i = 0;
        while(avail == 1) {
            if(avail == dir.files.get(i).getFileID())
                avail++;
            i++;
        }
        return avail;
        
    }

}
