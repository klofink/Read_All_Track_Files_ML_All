package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ///////////////////////////////////////////////////////////////////// Get ML ALL entries
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static Float getFloatOdds(Float wkF) {
        // nest if statements to put odds at tot-board values
        if( wkF == 1.10f){
            wkF = 1.0f;
        }
        else if(wkF == 1.3f){
            wkF = 1.2f;
        }
        else if (wkF == 1.9f){
            wkF=1.8f;
        }
        else if(wkF == 1.7f){
            wkF=1.6f;
        }
        else if (wkF > 1.9f && wkF < 2.5f) {
            wkF = 2.0f;
        }// end if >2 < 2.5
        else if (wkF > 2.4f && wkF < 3.0f) {
            wkF = 2.5f;
        }//
        else if (wkF > 2.9f && wkF < 3.5f) {
            wkF = 3.0f;
        }//
        else if (wkF > 3.4f && wkF < 4.0f) {
            wkF = 3.5f;
        }//
        else if (wkF > 3.9f && wkF < 4.5f) {
            wkF = 4.0f;
        }//
        else if (wkF > 4.4f && wkF < 5.0f) {
            wkF = 4.5f;
        }//
        else if (wkF > 4.9f && wkF < 6.0f) {
            wkF = 5.0f;
        }//
        else if (wkF > 5.9f && wkF < 7.0f) {
            wkF = 6.0f;
        }//
        else if (wkF > 6.9f && wkF < 8.0f) {
            wkF = 7.0f;
        }//
        else if (wkF > 7.9f && wkF < 9.0f) {
            wkF = 8.0f;
        }//

        else if (wkF > 8.9f && wkF < 10.0f) {
            wkF = 9.0f;
        }//


        return wkF;

    } // end methos get FloatOdds
    private static String getFinish(String s) {
        // check name against finish
        // test name match - get name
        int wkLoc1 =0;
        int wkLoc2 =s.indexOf('.');
        int wkLoc3 =0;
        String wkS="";
        String wkS1="";
        String result="";
        Float val;
        Float wkF;
        // get odds
/*
        int typeOdds=1;
        if (typeOdds == 1) {
            wkLoc1 = s.indexOf(":");
            wkLoc2 = s.indexOf('.', wkLoc1 + 1);
            wkS = s.substring(wkLoc2 - 3, wkLoc2 + 3).trim();
        } else {
*//*

            wkLoc1 = s.indexOf(".");
            char testDot = s.charAt(wkLoc1-1);
            //testDot = ' ';
            if (testDot == ' '){
                wkLoc2 = s.indexOf('.',wkLoc1+1);
               // nameEnd = s.lastIndexOf(' ',wkLoc2);
            } else if(testDot > '9'){
                wkLoc2 = s.indexOf('.',wkLoc1+1);
               // nameEnd = s.lastIndexOf(' ',wkLoc2);
            }
*/
//        wkS = s.substring(wkLoc2 - 2,wkLoc2 + 3).trim();

//        wkF = Float.parseFloat(wkS);
       // wkF = getFloatOdds(wkF);
//        wkS = wkF.toString().trim();
        result ="Res,";
        // determine finish (i.e. W! 7.40 P! 4.40 S! 2.80)
        wkLoc1 = s.indexOf("!"); // win
        if(wkLoc1 > 0) {
            wkLoc2 = s.indexOf("!", wkLoc1 + 1); // place
            wkLoc3 = s.indexOf("!", wkLoc2 + 1); // show
            wkS = "0.00,";
            wkS1 = s.substring(wkLoc1 + 1, wkLoc2 - 1).trim();
            try { // win
                if (Float.parseFloat(wkS1) > 0.0f) {
                    wkS = wkS1 + ",";
                }
            } catch (NumberFormatException e) {
                val = 0.0f; // debug
            }
            result += wkS;
            // place
            wkS = "0.00,";
            wkS1 = s.substring(wkLoc2 + 1, wkLoc3 - 1).trim();
            try { // place
                if (Float.parseFloat(wkS1) > 0.0f) {
                    wkS = wkS1 + ",";
                }
            } catch (NumberFormatException e) {
                val = 0.0f; // debug
            }
            result += wkS;
            // show
            wkS = "0.00,";
            wkS1 = s.substring(wkLoc3 + 1).trim();
            try { // show
                if (Float.parseFloat(wkS1) > 0.0f) {
                    wkS = wkS1 + ",";
                }
            } catch (NumberFormatException e) {
                val = 0.0f; // debug
            }
            result += wkS;
        } // end if wkLoc1 > 0
        else{
            // no wps results
            result +="0.00,0.00,0.00,";
        }



        return result;
    } // end getFinish

    private static String getNameWin(String s) {
        int sp1 = s.indexOf('.');
        int sp2 = s.lastIndexOf(".");
        String s2 ="";
        char[] wkC = new char[s.length()];
        s = s.replace(' ',',');
        s.getChars(0,s.length(),wkC,0);
        for(int i=3; i < sp1; i++){
            if(wkC[i] == ',' && i < (sp1-3) ){
                wkC[i]=' ';
            } // end if
        } // end for loop
        for(int i=0; i < wkC.length; i++) {
            s2 += wkC[i];
        }
        return s2;


    } // end getNameWin

    public static ArrayList<String> getList( File path){

        ArrayList<String> list = new ArrayList<String>();
        try {
            Scanner scanResults = new Scanner(path);

            while(scanResults.hasNextLine()){
                list.add(scanResults.nextLine());
            }


            scanResults.close();


        } catch (IOException ex) {
//            Logger.getLogger(PDFBoxReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        } //  end catch

        return list;

    } // end get list

    public static void main(String[] args) {
        	// write your code here
        int cnt =0;
        int cnt1 =0;
        ArrayList<String> listResultsAllLines = new ArrayList<>();
        ArrayList<String> odds1st = new ArrayList<>();
        ArrayList<String> odds2nd = new ArrayList<>();
        ArrayList<String> odds3rd = new ArrayList<>();
        ArrayList<String> odds4th = new ArrayList<>();
        ArrayList<String> WinPlaceShow = new ArrayList<>();


        String s="";
        String wkS1="";
        String wkS2="";
        String dateTR="";
        String[][]  wps = new String[4][4];

        int linePnt = 0;
        int linePntcopy=0;
        boolean horse5 = false;
        Float wkF;

        StringBuilder sbwkFavs;  // create empty
        sbwkFavs = new StringBuilder();
        //       for(int sbML=0; sbML < sbwkFavs.length; sbML++) {
 //           sbwkFavs[sbML]=new StringBuilder();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        String resultsFolderPathPrefix = "C:\\Users\\Kurt\\FavExce\\";
//////////////////////////////////////////////////////////////////////////////////////////////////////////

        //      }


            System.out.println("Enter the path to Parent folder to search for track dirs/files");
            Scanner s1 = new Scanner(System.in);
            String folderPath = s1.next();
            File folder = new File(folderPath);
            if (folder.isDirectory()) {
                // listofFiles is an array of files
                File[] listOfFiles = folder.listFiles();
                if (listOfFiles.length < 1) System.out.println(
                        "There are no Track Folder(s)");
                else System.out.println("List of Track Folder(s)");
                for (File file : listOfFiles) {// loops thru all track folder names
                    folderPath= String.valueOf(file);
                    System.out.println(folderPath);


                    File Subfolder = new File(folderPath);
                    cnt++;
                    if (Subfolder.isDirectory()) {
                        // listofFiles is an array of files
                        File[] SublistOfFiles = Subfolder.listFiles();
                        if (SublistOfFiles.length < 1) System.out.println(
                                "There are no Race Files inside Folder");
                        else System.out.println("List of Race Files & Folder");
                        for (File subF : SublistOfFiles) {// loops thru all races
                            wkS1 = subF.getName();
                            horse5 = true;
                            int sH = wkS1.lastIndexOf('_');
                            int eH = wkS1.lastIndexOf('.');
                            wkS2 = wkS1.substring(sH+1,eH);
                            if(Integer.parseInt(wkS2) < 5)
                                horse5 = false;
                            if(wkS1.contains("_99")) // results only file no ML data
                                horse5 = false;

                            if(subF.getName().contains(".txt") && horse5) {// folderPath is a string Track and race
                                folderPath = String.valueOf(subF);
                                // only examine race with 5 or more horses
                                System.out.println(folderPath);

//								// At this point subF will read in all  the text files
                                // need methods to extract data from txt files
                                listResultsAllLines = getList(subF); // array list holds all line of race result file
                                int nameStart;
                                int nameEnd;
                                int wkLoc1 =0;
                                int wkLoc2 =0;
                                int wkLoc3 =0;
                                int allFavs=0;

                                String PP="";
                                String name="";

//                                if (!folderPath.contains("_99")) { // not a results only file if yes skip file NO Morning Line
                                    // not results only file
                                    s = listResultsAllLines.get(linePnt++);
                                    dateTR = s+","; // date yyyy/mm/dd
                                    //sbwkFavs.append(s+",");
                                    s = listResultsAllLines.get(linePnt++);
                                    dateTR += s+",";// track
                                   // sbwkFavs.append(s+",");

                                    s = listResultsAllLines.get(linePnt++); // track conditions
                                    // get conditions from folderPath after get fav results
                                    s = listResultsAllLines.get(linePnt++); // header Non-Sorted ML

                                // Loop thru until ML list of names
                                while (!s.contains("Sort by ML")){
                                        s=listResultsAllLines.get(linePnt++);
                                    }
                                // s is first Sorted ML entry name SCR removed
                                    allFavs=0;
                                   // s = listResultsAllLines.get(linePnt++);
                                   // while (!s.contains("Finish Res")){
                                        // loop thru all ML names
                                        // nest if statements thru all 4 choices



                                    // s is first Sorted ML entry name
                                // linePnt is location in ArrayList
                                // need copy of location
                                linePntcopy = linePnt;
                                        s = listResultsAllLines.get(linePnt++); //
                                        while (!s.contains("Finish Results")) {
                                            // loop thru all ML sorted ranked(1st ML fav thru # of horses)
                                            // Note choices
                                            sbwkFavs.append(dateTR);
                                            sbwkFavs.append(PP);
                                            nameStart = s.indexOf('(');
                                            // get name trim
                                            name = s.substring(0,nameStart).trim()+",odRk,";
                                            sbwkFavs.append(name);
                                            // get the rest of the line
                                            // test for payoffs
                                            wkLoc1 = s.indexOf(')');//last char of name and ML rank
                                            wkLoc2 = s.indexOf("W!",wkLoc1);
                                            if (wkLoc2 < 0) {
                                                // ML horse has no payoffs
                                                wkLoc2 = s.lastIndexOf('.');
                                                wkS1 = s.substring(wkLoc1+1,wkLoc2+3).trim();
                                                wkS1 = wkS1.replace(' ',',');
                                                int lastC = wkS1.length();
                                                char[] wkC = new char[lastC];
                                                wkS1.getChars(0,lastC,wkC,0);
                                                wkS2 = wkS1.substring(0,6);
                                                for (int i = 6; i < lastC; i++) {
                                                    if(wkC[i] == ',' && wkC[i+1] == ','){
                                                        // double ,,
                                                        wkS2 += ",";
                                                        i++;
                                                    }
                                                    else{
                                                        wkS2 += wkC[i];
                                                    }
                                                } // end for i
                                                // no payoff
                                                wkS2 += ",Res,0.00,0.00,0.00,";
                                                // odds Rank add ML Odds actual Odds and ratio
                                                sbwkFavs.append(wkS2);
                                            }
                                            else{
                                                // Ml horse has payoff
                                                // inch your way to end of line
                                                // start with odds rank
                                                wkLoc2=s.indexOf("M",wkLoc1);
                                                wkS1 = s.substring(wkLoc1+1,wkLoc2).trim()+",ML,";
                                                // Get ML odds
                                                wkLoc1 = s.indexOf('.',wkLoc2);
                                                wkS2=s.substring(wkLoc1-2,wkLoc1+2).trim()+",";
                                                wkS1 += wkS2;
                                                // get Actual Odds
                                                wkLoc2 = s.indexOf('.',wkLoc1+1);
                                                wkS2=s.substring(wkLoc2-2,wkLoc2+2).trim()+",";
                                                wkS1 += wkS2;
                                                // get Odds Ratio
                                                wkLoc1 = s.indexOf('.',wkLoc2+1);
                                                wkS2=s.substring(wkLoc1-2,wkLoc1+3).trim()+",";
                                                wkS1 += wkS2;
                                                wkLoc2 = s.indexOf('W',wkLoc1);
                                                wkS2 = s.substring(wkLoc2);

                                                wkS1 += getFinish(wkS2);




                                                sbwkFavs.append(wkS1);

                                            } // end else horse has payoffs
//


                                            // get all track conditions
                                            wkLoc1 = folderPath.indexOf('_');
                                            wkLoc2 = folderPath.indexOf(".",wkLoc1);
                                            wkS1=folderPath.substring(wkLoc1+1,wkLoc2);
                                            wkS1=wkS1.replace('_',',');
                                            sbwkFavs.append(wkS1+",");
                                            wkLoc3 = folderPath.lastIndexOf('R',wkLoc1);
                                            wkS1 = folderPath.substring(wkLoc3,wkLoc1);
                                            sbwkFavs.append(wkS1);
                                            //sbwkFavs.append(folderPath);
                                            // FOLLOWING NOT NEEDED all favs will be on this line
                                            switch (allFavs) { // allFavs is a pointer to  the current ML fav
                                                case 0:
                                                    odds1st.add(sbwkFavs.toString());
                                                    break;
                                                case 1:
                                                    odds2nd.add(sbwkFavs.toString());
                                                    break;
                                                case 2:
                                                    odds3rd.add(sbwkFavs.toString());
                                                    break;
                                                case 3:
                                                    odds4th.add(sbwkFavs.toString());
                                                    break;
                                                default:
                                                    cnt++; // error degub only

                                            } // end switch

                                            sbwkFavs = new StringBuilder(); // reset sb to null string
                                            allFavs++;
//                                            cnt++; // debug

                                            if (allFavs == 4){
                                                // done with list
                                                break; // gets of of while loop
                                            }



                                      //  sbwkFavs = new StringBuilder(); // reset to null string
                                        s = listResultsAllLines.get(linePnt++); // get next line

                                    } // end while NO Finish Results - loop thru all ML entries




                                if(allFavs != 4){
                                    cnt++; // debug
                                }




                                cnt++; // debug







//




                                cnt1++; // debug only
                            }
                            //folder = new File(folderPath);
                            cnt1++;
                        } // for loop of subF - loops thru all txt file in Child directory

                        } else System.out.println("There is no Child Folders @ given path :" + folderPath);
                }
            } else System.out.println("There is no Parent Folder @ given path :" + folderPath);

        try {
            // output files of each fav1-4 as txt files
            // need a folder path and file name to save data to file - bellow is outline of saving using PrintWriter
            String newPath=resultsFolderPathPrefix+"MLineFav1";
            File fout = new File(newPath);
            if (fout.mkdirs()) {
                System.out.println("Directory is created! "+fout);
            }
            String foutfname = newPath + "\\Aug2019.txt";
            if (foutfname.contains("txt")) {
                fout = new File(foutfname);
                PrintWriter output = new PrintWriter(fout);
                for (int line = 0; line < odds1st.size(); line++) {
                    output.println(odds1st.get(line)); // this works
                } // end for line
                //output.println("test it");
                output.close();
                System.out.println(foutfname);
            } else System.out.println("There is no txt file name " + foutfname);

            newPath=resultsFolderPathPrefix+"MLineFav2";
            fout = new File(newPath);
            if (fout.mkdirs()) {
                System.out.println("Directory is created! "+fout);
            }
            foutfname = newPath + "\\Aug2019.txt";
            if (foutfname.contains("txt")) {
                fout = new File(foutfname);
                PrintWriter output = new PrintWriter(fout);
                for (int line = 0; line < odds2nd.size(); line++) {
                    output.println(odds2nd.get(line)); // this works
                } // end for line
                //output.println("test it");
                output.close();
                System.out.println(foutfname);
            } else System.out.println("There is no txt file name " + foutfname);

            newPath=resultsFolderPathPrefix+"MLineFav3";
            fout = new File(newPath);
            if (fout.mkdirs()) {
                System.out.println("Directory is created! "+fout);
            }
            foutfname = newPath + "\\Aug2019.txt";
            if (foutfname.contains("txt")) {
                fout = new File(foutfname);
                PrintWriter output = new PrintWriter(fout);
                for (int line = 0; line < odds3rd.size(); line++) {
                    output.println(odds3rd.get(line)); // this works
                } // end for line
                //output.println("test it");
                output.close();
                System.out.println(foutfname);
            } else System.out.println("There is no txt file name " + foutfname);

            newPath=resultsFolderPathPrefix+"MLineFav4";
            fout = new File(newPath);
            if (fout.mkdirs()) {
                System.out.println("Directory is created! "+fout);
            }
            foutfname = newPath + "\\Aug2019.txt";
            if (foutfname.contains("txt")) {
                fout = new File(foutfname);
                PrintWriter output = new PrintWriter(fout);
                for (int line = 0; line < odds4th.size(); line++) {
                    output.println(odds4th.get(line)); // this works
                } // end for line
                //output.println("test it");
                output.close();
                System.out.println(foutfname);
            } else System.out.println("There is no txt file name " + foutfname);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }





//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        } // end try/catch




        }// end Method Main




} // end class Main
