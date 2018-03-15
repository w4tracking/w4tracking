package org.w4tracking.models.utils;

import org.w4tracking.models.CompanyModel;
import org.w4tracking.models.Model;
import org.w4tracking.models.ModelType;
import org.w4tracking.representations.idm.AttributesRepresentation;
import org.w4tracking.representations.idm.CompanyAttributesRepresentation;
import org.w4tracking.representations.idm.DataRepresentation;
import org.w4tracking.representations.idm.LinksRepresentation;

public class ModelToRepresentation {

    public static CompanyAttributesRepresentation toRepresentation(CompanyModel model, boolean internal) {
        CompanyAttributesRepresentation rep = new CompanyAttributesRepresentation();

        rep.setName(model.getName());

        return rep;
    }
}
