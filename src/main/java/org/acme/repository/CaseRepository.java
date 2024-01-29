package org.acme.repository;

import org.acme.model.Case;

import java.util.Collections;
import java.util.List;

public class CaseRepository {

    public static long deleteByCaseId(String caseId) {
        if(true) {
            return 1;
        }
        return 0;
    }

    public static List<Case> getListOfCasesByCaseNumber(String caseNumber) {
        return Collections.singletonList(new Case());
    }
}
