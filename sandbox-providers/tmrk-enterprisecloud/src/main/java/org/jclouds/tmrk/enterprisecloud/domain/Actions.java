/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.tmrk.enterprisecloud.domain;

import com.google.common.collect.Sets;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Wraps individual Action elements.
 * Needed because parsing is done with JAXB and it does not handle Generic collections
 * @author Jason King
 */
@XmlRootElement(name = "Actions")
public class Actions {

    private LinkedHashSet<Action> actions = Sets.newLinkedHashSet();

    @XmlElement(name = "Action")
    void setAction(Action action) {
        this.actions.add(action);
    }

    public Set<Action> getActions() {
        return Collections.unmodifiableSet(actions);
    }

    public String toString() {
        return "["+ actions.toString()+"]";
    }

}
