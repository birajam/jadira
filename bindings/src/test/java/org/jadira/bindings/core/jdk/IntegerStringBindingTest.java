/*
 *  Copyright 2010 Chris Pheby
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
package org.jadira.bindings.core.jdk;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntegerStringBindingTest {

    private static final IntegerStringBinding BINDING = new IntegerStringBinding();
    
    @Test
    public void testUnmarshal() {
     
        assertEquals(new Integer(0).toString(), BINDING.unmarshal("0").toString());
        assertEquals(new Integer(1).toString(), BINDING.unmarshal("1").toString());
        assertEquals(new Integer(2147483647).toString(), BINDING.unmarshal("" + Integer.MAX_VALUE).toString());
        assertEquals(new Integer(-2147483648).toString(), BINDING.unmarshal("" + Integer.MIN_VALUE).toString());
    }
    
    @Test
    public void testMarshal() {

        assertEquals("0", BINDING.marshal(new Integer(0)));
        assertEquals("1", BINDING.marshal(new Integer(1)));
        assertEquals("2147483647", BINDING.marshal(new Integer(Integer.MAX_VALUE)));
        assertEquals("-2147483648", BINDING.marshal(new Integer(Integer.MIN_VALUE)));
    }
}
