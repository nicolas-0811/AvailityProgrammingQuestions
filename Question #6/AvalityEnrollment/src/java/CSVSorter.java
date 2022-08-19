//Program that receives a .csv file as an input, then, creates a new .csv file for each of the health care providers.
//It also sorts the last names in ascending order, and removes any duplicates.
//For this program, I assumed the structure of the CSV file to be as follows:
//The first element of each line is the name of the health care provider
//The second element of each line is the full name of the patient
//The third element of each line is the user ID for each provider
//The fourth element is the version
//I also assumed that there's only four elements per each line. The code could be adapted
//to admit any number of elements per line, but I considered it added unnecessary complexity to the problem

import java.io.*;
import java.util.*;

public class CSVSorter {

    public static final String comma = ",";
    public static ArrayList<String[]> enrollees = new ArrayList<>();
    public static ArrayList<String> providers = new ArrayList<>();
    public static Formatter formatter;

    public static void main(String[] args) {
        readFile();
        getProviders();
        sortContents();
    }

    public static void readFile() {
        //Read the file and add the data to an ArrayList that will hold the information
        try {
            File inputFile = new File(System.getProperty("user.home") + "/Desktop" + "/File.csv");
            FileReader reader = new FileReader(inputFile);
            BufferedReader buffered = new BufferedReader(reader);
            String line = "";

            while ((line = buffered.readLine()) != null) {
                String[] tempArray = line.split(comma);
                enrollees.add(tempArray);
            }

            buffered.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getProviders() {

        //Adding the first element of the enrollees list. Since the providers list is empty. The first element is guaranteed
        //to not be a duplicate
        providers.add(enrollees.get(0)[0]);

        //Populating the providers list, which holds the names of all providers without duplicates
        for (int i = 1; i < enrollees.size(); i++) {
            //Checking whether the current provider has been already added to the list. If it's not found, then it's added
            if (providers.contains(enrollees.get(i)[0])){
                continue;
            } else {
                providers.add(enrollees.get(i)[0]);
            }
        }
    }

    public static void sortContents() {
        for (int i = 0; i < providers.size(); i++) {

            //Variable that holds the current provider
            String current = providers.get(i);
            //List that will contain the individual String arrays, which symbolize one line of the original file to be sorted
            ArrayList<String[]> newArrays = new ArrayList<>();

            for (int j = 0; j < enrollees.size(); j++) {
                if (enrollees.get(j)[0].equals(current)) {
                  //Since there's four elements per each line, the size of the new array is four
                    String[] newData = new String[4];
                    newData[0] = enrollees.get(j)[0];
                    newData[1] = enrollees.get(j)[1];
                    newData[2] = enrollees.get(j)[2];
                    newData[3] = enrollees.get(j)[3];
                    newArrays.add(newData);
                }
            }

            //Sorting the new List by alphabetical order, considering the element at index 1 (which is the last name of the patient)
            Collections.sort(newArrays, new Comparator<String[]>() {
                @Override
                public int compare(String[] lastName1, String[] lastName2) {
                    return lastName1[1].compareTo(lastName2[1]);
                    }
                });

          //If there's a single element in the new list, there's nothing to compare it to
            if (newArrays.size() != 1) {
                for (int k = 0; k < newArrays.size(); k++) {
                    //Making sure that the last element doesn't get compared with anything else
                    if (k != (newArrays.size() - 1)) {
                        //If there user ID (element 2 in the array) is equal to the ID of the next element, delete the current
                        //This works because the collections.sort already sorted the versions in ascending order
                        if (newArrays.get(k)[2].equals(newArrays.get(k + 1)[2])) {
                            newArrays.remove(k);
                        }
                    }
                }
            }

            //Printing every provider's list to an individual .csv file
            try {
                formatter = new Formatter(System.getProperty("user.home") + "/Desktop" + "/" + current + ".csv");
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int m = 0; m < newArrays.size(); m++) {
                formatter.format(Arrays.deepToString(newArrays.get(m)).replace("[", "").replace("]", ""));
                formatter.format("\n");
            }

            formatter.close();
        }
    }
}