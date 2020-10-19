package com.example.flowtextapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.Scanner;

public final class TextFlow {

    private static String end;
    private static int longest = 0; // Represents longest river length

    // Constructor
    public TextFlow() {
        end = "$#END";
    }

    // Setter for longest river length attribute.
    public static void setLongest(int length){
        longest = length;
    }

    // Getter for longest river length attribute.
    public static int getLongest(){
        return longest;
    }

    public static ArrayList<String> autoWrap(String sentence, int limit) {
        String newSentence = sentence + " " + end;
        String[] allWords = newSentence.split(" ");

        StringBuilder line = new StringBuilder();
        int wordLength = line.length();
        ArrayList<String> sentenceList = new ArrayList<String>();

        for (String word : allWords) {
            if (word.equals(end)) {
                sentenceList.add(line.toString());
                break;
            }

            wordLength = wordLength + word.length();

            if (wordLength <= limit) {
                line.append(word).append(" ");
                wordLength++;
            } else {
                sentenceList.add(line.toString());
                line = new StringBuilder();
                line.append(word).append(" ");
                wordLength = line.length();
            }
        }

        for (String string : sentenceList) {
            System.out.println(string);
        }

        return sentenceList;
    }

    public static String locateRivers(ArrayList<String> lineList) {
        ArrayList<ArrayList<Integer>> spaceList = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> potentialRiver = new ArrayList<ArrayList<Integer>>();
        int length = lineList.size();

        for (String line : lineList) {

            String newLine = line.trim();
            ArrayList<Integer> lst = new ArrayList<Integer>();

            // Checks for locations of whitespaces per line
            for (int x = 0; x < newLine.length(); x++) {
                if (newLine.charAt(x) == ' ') {
                    lst.add(x);
                }
            }

            spaceList.add(lst);
        }

        for (int a = 0; a < length; a++) {
            for (Integer index : spaceList.get(a)) {
                int spot = index;
                // Keeps track the the line number and index numbers.
                ArrayList<Integer> lst = new ArrayList<Integer>();
                lst.add(a); // This saves the line number where the river starts
                lst.add(spot); // represents the position of the rivers whitespace

                for (ArrayList<Integer> indexList : spaceList.subList(a + 1, spaceList.size())) {
                    if (indexList.contains(spot - 1)) {
                        spot = spot - 1;
                        lst.add(spot);
                    } else if (indexList.contains(spot)) {
                        lst.add(spot);
                    } else if (indexList.contains(spot + 1)) {
                        spot = spot + 1;
                        lst.add(spot);
                    } else {
                        break;
                    }
                }

                // Keeps track of the largest list of river locations.
                int number = lst.size();
                if (number > getLongest()) {
                    setLongest(number);
                }

                potentialRiver.add(lst);
            }

        }

        ArrayList<ArrayList<Integer>> rivers = new ArrayList<ArrayList<Integer>>();

        for (ArrayList<Integer> element : potentialRiver) {
            int listSize = element.size();
            // Adds 1 to the max to include the line number that was added
            if (listSize == getLongest()) {
                rivers.add(element);
            }
        }

        for (ArrayList<Integer> element : rivers) {
            for (int num = 0; num < length; num++) {
                if (num == element.get(0)) {
                    for (Integer index : element.subList(1, element.size())) {
                        String first = lineList.get(num).substring(0, index);
                        String asterisk = "*";
                        String second = lineList.get(num).substring(index + 1, lineList.get(num).length());

                        String newString = first + asterisk + second;

                        lineList.set(num, newString);
                        num++;
                    }
                }
            }
        }

        StringBuilder riverLines = new StringBuilder("");
        // Adds a println at the end of each line.
        for (String string : lineList) {
            String rLine = string + "\n";
            riverLines.append(rLine);
        }

        return riverLines.toString();
    }

}