package com.util;

import java.util.Map;
import java.util.Set;

import static com.conf.Constants.DELIMITER;
import static com.conf.Constants.PRINT_FLAG;
import static com.util.CacheUtil.*;


public class RepoUtil {


    /**
     * Add values of two given arrays
     *
     * @param firstArray
     * @param secondArray
     * @return
     */
    //	Add Array Values
    public static String[] addArrayValues(String[] firstArray, String[] secondArray) {
        int arrayCounter = 0;
        String[] returnArray = new String[6];

        for (String arg : firstArray) {
            try {
                returnArray[arrayCounter] = Integer
                        .toString(Integer.parseInt(arg) + Integer.parseInt(secondArray[arrayCounter]));
                arrayCounter++;
            } catch (Exception e) {

                returnArray[arrayCounter] = arg;
                arrayCounter++;

                /* To bypass user details in array index zero */
                continue;
            }
        }

        printArray(returnArray, "Return Array");

        return returnArray;
    }


    /**
     * System print for debugging
     *
     * @param ar
     * @param message
     */
    public static void printArray(String ar[], String message) {

        if (PRINT_FLAG) {
            System.out.println("<<<===== " + message + " =======>>>");
            for (String val : ar) {
                System.out.print(val + DELIMITER);
            }
        }

    }

    public static String extractEmailFromGitName(String gitName) {

        String email = gitName.substring(gitName.indexOf("<") + 1, gitName.indexOf(">"));
        /*Removed Left and Right Double Quoted from Strings*/
        if (email.contains("“")) {
            email = email.substring(1, email.length()-1);
        }
        return email;

    }

    public static String getValidUser(String userName) {
        String email = extractEmailFromGitName(userName).toLowerCase();
        Set<String> validUsers = getValidUserCache();
        String validUserName = null;

        if (email.contains("@pearson")) {
            if (validUsers.contains(email)) {
                validUserName = email;
            } else {
                throw new RuntimeException("Invalid User : " + userName);
            }
        } else {

            Map<String, String> userMap = getUserMappingCache();
            userName = userMap.get(email);

            if (validUsers.contains(userName)) {
                validUserName = userName;
            } else {
                throw new RuntimeException("Invalid User : " + userName);
            }
        }
        return validUserName;

    }


}
