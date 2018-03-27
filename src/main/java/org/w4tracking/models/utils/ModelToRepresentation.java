package org.w4tracking.models.utils;

import org.w4tracking.models.CompanyModel;
import org.w4tracking.models.UserModel;
import org.w4tracking.representations.idm.CompanyAttributesRepresentation;
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

    public static CompanyAttributesRepresentation toRepresentation(CompanyModel model, boolean internal) {
        CompanyAttributesRepresentation rep = new CompanyAttributesRepresentation();

        rep.setName(model.getName());

        return rep;
    }
}
