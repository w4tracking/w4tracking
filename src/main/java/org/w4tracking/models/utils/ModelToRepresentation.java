package org.w4tracking.models.utils;

import org.w4tracking.models.CompanyModel;
import org.w4tracking.models.UserModel;
import org.w4tracking.representations.idm.CompanyRepresentation;
import org.w4tracking.representations.idm.UserRepresentation;

public class ModelToRepresentation {

    public static UserRepresentation.UserAttributesRepresentation toRepresentation(UserModel model, boolean fullInfo) {
        UserRepresentation.UserAttributesRepresentation rep = new UserRepresentation.UserAttributesRepresentation();

        rep.setUsername(model.getUsername());
        rep.setFullName(model.getFullName());
        rep.setEmail(model.getEmail());

        if (fullInfo) {
            rep.setIdentityId(model.getIdentityId());
            rep.setIdentityProvider(model.getIdentityProvider());
        }

        return rep;
    }

    public static CompanyRepresentation.CompanyAttributesRepresentation toRepresentation(CompanyModel model, boolean fullInfo) {
        CompanyRepresentation.CompanyAttributesRepresentation rep = new CompanyRepresentation.CompanyAttributesRepresentation();

        rep.setName(model.getName());

        if (fullInfo) {

        }

        return rep;
    }
}
