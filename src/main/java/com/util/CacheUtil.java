package com.util;

import com.dao.FileDAO;

import java.util.*;

import static com.conf.Constants.USER_MAPPING_FILE;
import static com.conf.Constants.VALID_USERS_FILE;

public class CacheUtil {


    /**
     * Method unique users from different git mail id's and load in Map for file compare
     *
     * @return
     */

    public static Map<String, String> getUserMappingCache() {

        Map<String, String> mappingCache = new HashMap<String, String>();

        List<String[]> rowDataArray = FileDAO.fileReader(USER_MAPPING_FILE);

        for (String[] rowData : rowDataArray) {

            if (rowData.length != 2) {
                throw new RuntimeException("Not Valid Mapping File - Invlaid Data : "+rowData[0]);
            }

            if (mappingCache.containsKey(rowData[0])) {
                throw new RuntimeException("Not Valid Mapping File - Contains Dup Key : "+rowData[0]);
            }

            // Create CacheUtil Map
            mappingCache.put(rowData[0].toLowerCase(), rowData[1].toLowerCase());

        }

        return mappingCache;
    }

    /**
     * Create cache for valid users to be listed in report
     *
     * @return
     */
    public static Set<String> getValidUserCache() {

        Set<String> validUserCache = new HashSet<String>();

        List<String[]> validUserDataList = FileDAO.fileReader(VALID_USERS_FILE);

        for (String[] validUser : validUserDataList) {

            if (validUser.length != 2) {
                throw new RuntimeException("Not Valid Mapping File - Valid User Invlaid Data");
            }

            if (validUserCache.contains(validUser[0])) {
                throw new RuntimeException("Not Valid Mapping File - Valid User Contains Dup Key : "+validUser[0]);
//                System.out.println(validUser[0]);
            }

            // Create CacheUtil Map
            validUserCache.add(validUser[0]);

        }

        return validUserCache;

    }


}
