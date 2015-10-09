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
    Scanner keyboard;
    int[] sender = new int[600];
    public VipeTableLogic()
    {
        this.keyboard = new Scanner(System.in);
        dir = new Directory(new ArrayList<>(), sender);
        while (true) {
            System.out.println("VipeTable Functions:");
            System.out.println("\t 1) import Data");
            System.out.println("\t 2) add file");
            System.out.println("\t 3) delete file");
            System.out.println("\t 4) edit file");
            String choice = keyboard.next();
            switch (new Integer(choice)) {
                case 1: importData(); break;
                case 2: writeData(); break;
                case 3: deleteData(); break;
                case 4: editData(); break;
            }
        }
    }
    //returns a String representation of a file’s chunks
    private String getChunkString(int fileID) {
        return "";
    }
    //returns the total number of unmarked sectors
    private int getAvailableSize() {
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
            while (i < local.getFileSize()) {
                Chunk c = getNextChunk(local);
                int startIndex = c.getStartIndex();
                int endIndex = c.getEndIndex();
                while (startIndex <= endIndex && i < local.getFileSize()) {
                    dir.sectors[startIndex] = local.getFileID();
                    i++;
                    startIndex++;
                }
                local.chunks.add(c);
            }
            dir.files.add(local);
        }
    }
    //creates the 2DGraphic representation of the directory
    private void writeGrid() {
        
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

    private void writeData() {
        keyboard = new Scanner(System.in);
        System.out.println("HEY YOU THERE! WHAT DO YOU WANT TO NAME YOUR FILE? HUH?");
        String fileName = keyboard.next();
        System.out.println("HOW BIG YA WANT IT?");
        int fileSize = keyboard.nextInt();
        Color temp = new Color(255, 255, 255);
        System.out.println("GIVE THE FILE AN ID WILL YE?!");
        int fileID = keyboard.nextInt();
        while(!isIdAvailable(fileID)) {
            System.out.println("THAT ONE THERE IS TAKEN! TRY AGAIN WILL YA");
            fileID = keyboard.nextInt();
        }
        VipeFile sendable = new VipeFile(fileSize, fileName, temp, fileID);
        writeFile(sendable);
        System.out.println(dir);
    }

    private void deleteData() {
        keyboard = new Scanner(System.in);
        System.out.println("WHAT ID DO YE WANT TO DELETE THIS TIME?");
        int fileID = keyboard.nextInt();
        deleteFile(fileID);
        System.out.println(dir);
    }

    private void editData() {
        keyboard = new Scanner(System.in);
        System.out.println("WHAT FILE ID DO YE WANT TO CHANGE");
        int fileID = keyboard.nextInt();
        System.out.println("WHAT THE SIZE DIFFERENCE? REMEMBER IT CAN BE NEGATIVE TO MAKE THE FILE SMALLER.");
        int sizeChange = keyboard.nextInt();
        while(getFileFromID(fileID).fileSize + sizeChange < 1) {
            System.out.println("YER FILE CANT BE THAT SMALL BHAHAHA! TRY AGAIN.");
            sizeChange = keyboard.nextInt();
        }
        editFile(fileID, sizeChange);
        dir.toString();
    }

}
