
import java.util.ArrayList;
import java.util.Scanner;

public class TextFlow {

    private String end;

    public TextFlow() {
        this.end = "$#END";
    }

    public ArrayList<String> autoWrap(String sentence, int limit) {
        String newSentence = sentence + " " + this.end;
        String[] allWords = newSentence.split(" ");

        StringBuilder line = new StringBuilder();
        int wordLength = line.length();
        ArrayList<String> sentenceList = new ArrayList<String>();

        for (String word : allWords) {
            if (word.equals(this.end)) {
                sentenceList.add(line.toString());
                break;
            }

            wordLength = wordLength + word.length();

            if (wordLength <= limit) {
                line.append(word + " ");
                wordLength++;
            } else {
                sentenceList.add(line.toString());
                line = new StringBuilder();
                line.append(word + " ");
                wordLength = line.length();
            }
        }

        for (String string : sentenceList) {
            System.out.println(string);
        }

        return sentenceList;
    }

    public void locateRivers(ArrayList<String> lineList) {
        ArrayList<ArrayList<Integer>> spaceList = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> potentialRiver = new ArrayList<ArrayList<Integer>>();
        int length = lineList.size();

        for (String line : lineList) {

            String newLine = line.trim();
            ArrayList<Integer> lst = new ArrayList<Integer>();

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
                lst.add(a);
                lst.add(spot);

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
                potentialRiver.add(lst);
            }

            ArrayList<ArrayList<Integer>> rivers = new ArrayList<ArrayList<Integer>>();

            int riverLength = 0;
            for (ArrayList<Integer> element : potentialRiver) {
                int max = element.size();
                if (max > riverLength) {
                    riverLength = max;
                }
            }

            for (ArrayList<Integer> element : potentialRiver) {
                int listSize = element.size();
                if (listSize == riverLength) {
                    rivers.add(element);
                }
            }

            for (ArrayList<Integer> element : rivers) {
                for (int num = 0; num < length; num++) {
                    if (num == element.get(0)) {
                        for (Integer index : element.subList(1, element.size())) {
                            String first = lineList.get(num).substring(0, index);
                            String asterik = "*";
                            String second = lineList.get(num).substring(index + 1, lineList.get(num).length());
                            String newString = first + asterik + second;

                            lineList.set(num, newString);
                            num++;
                        }
                    }
                }
            }

        }

        // Prints each line.
        for (String string : lineList) {
            System.out.println(string);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello");
        TextFlow obj = new TextFlow();

        Scanner sc = new Scanner(System.in);
        System.out.println("* 1) A function to auto-wrap sentences.");
        System.out.println("* 2) A function to identify Rivers in auto-wrapped sentences.");

        System.out.println("\n* Enter a sentence.");
        String sentence = sc.nextLine();

        System.out.println("* Enter a limit.");
        String limitString = sc.nextLine();
        sc.close();
        int limit = Integer.parseInt(limitString);

        System.out.println("\nThe wrapped sentence is:");
        ArrayList<String> wrapped = obj.autoWrap(sentence, limit);

        System.out.println("\nThe Rivers in the sentence is:");
        obj.locateRivers(wrapped);
    }
}