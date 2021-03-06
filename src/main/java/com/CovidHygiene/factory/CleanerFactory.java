package com.CovidHygiene.factory;

import com.CovidHygiene.entity.Cleaner;

public class CleanerFactory {
        public static Cleaner buildCleaner(long cleanerNum, String firstName,String lastName, String address){
            return new Cleaner.Builder()
                    .setCleanerNum(cleanerNum)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setAddress(address)
                    .build();
        }
}
