package org.w4tracking.representations.idm;

import java.util.List;

public class CompaniesRepresentation {

    private List<CompanyRepresentation.CompanyData> data;

    public CompaniesRepresentation() {

    }

    public CompaniesRepresentation(List<CompanyRepresentation.CompanyData> data) {
        this.data = data;
    }

    public List<CompanyRepresentation.CompanyData> getData() {
        return data;
    }

    public void setData(List<CompanyRepresentation.CompanyData> data) {
        this.data = data;
    }
}
