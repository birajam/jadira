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
package org.jadira.usertype.dateandtime.joda;

import org.jadira.usertype.dateandtime.joda.util.DateTimeZoneWithOffset;
import org.jadira.usertype.spi.shared.AbstractParameterizedMultiColumnUserType;
import org.jadira.usertype.spi.shared.DatabaseZoneConfigured;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

public abstract class AbstractMultiColumnDateTime extends AbstractParameterizedMultiColumnUserType<DateTime> implements DatabaseZoneConfigured<DateTimeZone> {

    private static final long serialVersionUID = 726719808559368002L;

    private DateTimeZone databaseZone = DateTimeZone.UTC;

    @Override
    protected DateTime fromConvertedColumns(Object[] convertedColumns) {

        LocalDateTime datePart = (LocalDateTime) convertedColumns[0];
        DateTimeZoneWithOffset offset = (DateTimeZoneWithOffset) convertedColumns[1];

        DateTime result;

        if (datePart == null) {
            result = null;
        } else {
            result = datePart.toDateTime(databaseZone == null ? offset.getStandardDateTimeZone() : databaseZone);
            
            if (databaseZone != null) {
            	result = result.withZone(offset.getStandardDateTimeZone());
            }
        }
        
        // Handling DST rollover
        if (result != null && offset.getOffsetDateTimeZone() != null &&
        		offset.getStandardDateTimeZone().getOffset(result) > offset.getOffsetDateTimeZone().getOffset(result)) {
        	return result.withLaterOffsetAtOverlap();
        }

        return result;
    }

    @Override
    protected Object[] toConvertedColumns(DateTime value) {

    	final DateTime myValue;
    	if (databaseZone == null) {
    		myValue = value;
    	} else {
    		myValue = value.withZone(databaseZone);
    	}
        return new Object[] { myValue.toLocalDateTime(), new DateTimeZoneWithOffset(value.getZone(), value.getZone().isFixed() ? null : DateTimeZone.forOffsetMillis(value.getZone().getOffset(value))) };
    }

	@Override
	public void setDatabaseZone(DateTimeZone databaseZone) {
		this.databaseZone = databaseZone;
	}

	@Override
	public DateTimeZone parseZone(String zoneString) {
		return DateTimeZone.forID(zoneString);
	}
}
