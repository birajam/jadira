/*
 *  Copyright 2010, 2011 Christopher Pheby
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.jadira.usertype.dateandtime.threeten.testmodel;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.dateandtime.threeten.PersistentOffsetDateTimeAsStringAndStringOffset;

@Entity
@Table(name = "offsetDateTimeAsStringAndStringOffset")
@TypeDef(name = "test_OffsetDateTimeAsStringAndStringOffsetJdk8Type", typeClass = PersistentOffsetDateTimeAsStringAndStringOffset.class)
public class OffsetDateTimeAsStringAndStringOffsetJdk8 implements Serializable {

    private static final long serialVersionUID = 5888505180004123768L;

    @Id
    private long id;

    @Column
    private String name;

    @Columns(columns = { @Column(name = "MY_DATE"), @Column(name = "MY_OFFSET") })
    @Type(type = "test_OffsetDateTimeAsStringAndStringOffsetJdk8Type")
    private OffsetDateTime offsetDateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getOffsetDateTimeAsString() {
        return offsetDateTime;
    }

    public void setOffsetDateTimeAsString(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }
}
