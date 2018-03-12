package org.w4tracking.representations.idm;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ItemRepresentation<T extends Data> extends Representation<T>{

    public ItemRepresentation(Builder<T> builder) {
        super(builder);
    }

}
