/*
 * Copyright (c) 2013 Kinvey Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.kinvey.business_logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author mjsalinger
 * @since 2.0
 */
public class KinveyEntity {

    private HashMap<String, Object> entity;

    private static final String KINVEY_ACL = "_acl";
    private static final String KINVEY_ACL_CREATOR = "creator";
    private static final String KINVEY_KMD = "_kmd";
    private static final String KINVEY_KMD_LMT = "lmd";
    private static final String KINVEY_KMD_ECT = "ect";

    public KinveyEntity(String appId) {
        entity = new HashMap<String, Object>();
        String isoDate = getIsoDate();

        HashMap<String, Object> acl = new HashMap<String, Object>();
        acl.put(KINVEY_ACL_CREATOR, appId);

        HashMap<String, Object> kmd = new HashMap<String, Object>();
        kmd.put(KINVEY_KMD_LMT, isoDate);
        kmd.put(KINVEY_KMD_ECT, isoDate);

        entity.put(KINVEY_ACL, acl);
        entity.put(KINVEY_KMD, kmd);
    }

    @SuppressWarnings("unchecked")
    public KinveyEntity(String appId, HashMap<String, Object> newEntity) {
        entity = new HashMap<String, Object>(newEntity);
        String isoDate = getIsoDate();

        if (!entity.containsKey(KINVEY_ACL)) {
            HashMap<String, Object> acl = new HashMap<String, Object>();
            acl.put(KINVEY_ACL_CREATOR, appId);
            entity.put(KINVEY_ACL, acl);
        }
        if (!entity.containsKey(KINVEY_KMD)) {
            HashMap<String,Object> kmd = new HashMap<String, Object>();
            kmd.put(KINVEY_KMD_ECT, isoDate);
            kmd.put(KINVEY_KMD_LMT, isoDate);
        } else {
            HashMap<String,Object> kmd = (HashMap<String, Object>) entity.get(KINVEY_KMD);
            if (!kmd.containsKey(KINVEY_KMD_ECT)) {
                kmd.put(KINVEY_KMD_ECT, isoDate);
            }

            kmd.put(KINVEY_KMD_LMT, isoDate);
        }
    }

    private String getIsoDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        return df.format(new Date());
    }

    public HashMap<String, Object> getEntity() {
        return entity;
    }

    @SuppressWarnings("unchecked")
    public void updateLastUpdated() {
        HashMap<String, Object> kmd = entity.containsKey(KINVEY_KMD) ?
                (HashMap<String, Object>) entity.get(KINVEY_KMD) : new HashMap<String, Object>();
        kmd.put(KINVEY_KMD_LMT, getIsoDate());
    }
}
