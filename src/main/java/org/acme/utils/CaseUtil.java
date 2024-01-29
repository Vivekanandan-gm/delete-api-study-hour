package org.acme.utils;

import java.util.ArrayList;
import java.util.List;

public class CaseUtil {

    public static boolean isValidStatus(String status) {
        List<String> disallowedStatus = new ArrayList<>();
        disallowedStatus.add("1000");
        disallowedStatus.add("1001");
        return disallowedStatus.contains(status);
    }
}
