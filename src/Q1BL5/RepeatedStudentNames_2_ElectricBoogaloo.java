package Q1BL5;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class RepeatedStudentNames_2_ElectricBoogaloo {
    public static void main(String[] args) throws IOException {
        header();


        File listFile = new File("SE2205Students.txt");
        if (!listFile.exists()) {
            System.out.println("The File " + listFile.getName() + " Does NOT Exists.\nPlease Make Sure You Entered In The Correct File Name & Extension\nAnd Make Sure The File Is In The Correct Directory");
            footer();
            System.exit(0);
        }

        TreeMap<String, Integer> nameList = new TreeMap<>();

        Scanner readFile = new Scanner(listFile);
        ArrayList<String> file = new ArrayList<>();
        while (readFile.hasNext())
            file.add(readFile.nextLine());
        readFile.close();

        Collections.sort(file);
        ListIterator<String> fileIterator = file.listIterator();

        String currentLine;
        ArrayList<String> fullNames = new ArrayList<>();
        while (fileIterator.hasNext()) {
            currentLine = fileIterator.next();

            if (!nameList.containsKey(currentLine.split("[ \\t]")[0]))
                nameList.put(currentLine.split("[ \\t]")[0], 1);


            else {
                nameList.put(currentLine.split("[ \\t]")[0], nameList.get(currentLine.split("[ \\t]")[0]) + 1);
                fileIterator.previous();
                currentLine = fileIterator.previous();
                if (!fullNames.contains(currentLine))
                    fullNames.add(currentLine);
                fileIterator.next();
                currentLine = fileIterator.next();
                fullNames.add(currentLine);
            }
        }



        Set<Map.Entry<String, Integer>> dupeLastNames = nameList.entrySet();
        System.out.printf("%s%n%s%n%9s%18s%12s%n%s%n",
                "List Of The Number Of Students With The Same Last Names:", "===========================================================================",
                "Last Name", "# Of Students", "Full Names",
                "===========================================================================");
        for (Map.Entry<String, Integer> dupeLastName : dupeLastNames)
            if (dupeLastName.getValue() > 1)
                System.out.printf("%9s%6d%-14s%s%n", dupeLastName.getKey(), dupeLastName.getValue(), " Students", printSelectedGroup(fullNames, dupeLastName.getKey()));

        nameList.clear();
        dupeLastNames.clear();
        fullNames.clear();

        footer();
    }

    public static String printSelectedGroup(ArrayList<String> fullNames, String nameToCheck){
        ArrayList<String> fullNamesAddendum = new ArrayList<>();
        for(String name : fullNames)
            if(name.contains(nameToCheck))
                fullNamesAddendum.add(name);

        return fullNamesAddendum.toString();
    }

    public static void header() {
        System.out.println();
        //general lab header
        int n = 81;
        for (int i = 0; i < n; i++) System.out.print("*");
        System.out.println("""

                \t\t\t\tHello User, My Name Is Ahmad Sami Abu Shawarib
                \t\t\t\t\t\tStudent Number: 251149713
                \t\t\t\t\t\t Welcome To Bonus Lab #5""");
        for (int i = 0; i < n; i++) System.out.print("*");

        //specific lab header
        System.out.println("""

                Using TreeSets And Map.Entry To Read From A File And Filler The Class's Students'
                \tNames By Last Names And Printing Out Only The Duplicate Names Plus Their Full Names""");
        for (int i = 0; i < n; i++) System.out.print("*");
        System.out.println("\n");
    }


    public static void footer() {
        System.out.printf("%n****************%7s~The Program Has Stopped, GoodBye!~%7s****************", "", "");
    }


}
