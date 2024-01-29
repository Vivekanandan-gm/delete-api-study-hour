package org.acme.service;

import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;
import org.acme.model.Case;
import org.acme.repository.CaseMongoRepository;
import org.acme.repository.CaseRepository;
import org.acme.repository.ComplaintRepository;
import org.acme.utils.CaseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaseService {

    public enum ErrorMapKeys { ERROR };

    public boolean deleteByCaseId(Case caseDetail) {
        Map<ErrorMapKeys, String> errorMap = new HashMap<>();
        boolean canBeDeleted = canBeDeleted(caseDetail, errorMap);
        if(!errorMap.isEmpty() && !canBeDeleted) {
            Log.fatal(errorMap.get(ErrorMapKeys.ERROR));
            return false;
        }

        try {
            deleteInDataStore(caseDetail);
            deleteAssociatedCases(caseDetail);
        } catch (RuntimeException rex) {
            return false;
        }

        return true;
    }

    public boolean canBeDeleted(Case caseDetail, Map<ErrorMapKeys, String> error) {
        boolean isValidStatus = CaseUtil.isValidStatus(caseDetail.status);
        if(!isValidStatus) {
            error.put(ErrorMapKeys.ERROR, String.format("Not a valid status: %s", caseDetail.status));
            return false;
        }

        return true;
    }

    @Transactional
    public void deleteInDataStore(Case caseDetail) {
        long deletedComplaintCountInSql = ComplaintRepository.deleteByComplaintId(caseDetail.complaintId);
        if(deletedComplaintCountInSql != 1) {
            Log.fatal(String.format("deleted %d complaints", deletedComplaintCountInSql));
            throw new RuntimeException();
        }

        long deletedCountInSql = CaseRepository.deleteByCaseId(caseDetail.caseId);
        if(deletedCountInSql != 1) {
            Log.fatal(String.format("deleted %d cases", deletedCountInSql));
            throw new RuntimeException();
        }

        long deletedCountInMongo = CaseMongoRepository.deleteByCaseId(caseDetail.caseId);
        if(deletedCountInMongo != 1) {
            Log.fatal(String.format("deleted %d cases", deletedCountInMongo));
            throw new RuntimeException();
        }
    }

    public void deleteAssociatedCases(Case caseDetail) {
        List<Case> caseList = CaseRepository.getListOfCasesByCaseNumber(caseDetail.caseNumber);
        for (Case associatedCase: caseList) {
            deleteInDataStore(associatedCase);
        }
    }
}
