/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vipetablelogic;

import java.util.ArrayList;

/**
 *
 * @author dhuka_844963
 */
public class Directory {

    ArrayList<VipeFile> files;
    int[] sectors = new int[600];

    public Directory(ArrayList<VipeFile> fs, int[] s) {
        files = new ArrayList<VipeFile>(fs);
        this.sectors = s;
    }

    public ArrayList<VipeFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<VipeFile> files) {
        this.files = files;
    }

    public int[] getSectors() {
        return sectors;
    }

    public void setSectors(int[] sectors) {
        this.sectors = sectors;
    }
    
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