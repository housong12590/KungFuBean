package com.iiseeuu.helper.entity;

import java.util.List;

/**
 * Author: 30453
 * Date: 2016/12/23 14:51
 */
public class Facilitys extends BaseList {

    private List<FacilityEntity> facility;

    public List<FacilityEntity> getFacility() {
        return facility;
    }

    public void setFacility(List<FacilityEntity> facility) {
        this.facility = facility;
    }
}
