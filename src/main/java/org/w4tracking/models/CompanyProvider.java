package org.w4tracking.models;

import java.util.List;
import java.util.Optional;

public interface CompanyProvider {

    CompanyModel addCompany(UserModel owner, String name);

    Optional<CompanyModel> getCompany(String id);

    List<CompanyModel> getCompanies(String filterText);

    List<CompanyModel> getCompanies(String filterText, int offset, int limit);
}
