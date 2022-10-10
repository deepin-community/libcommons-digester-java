/* $Id: TestRuleSet.java 471661 2006-11-06 08:09:25Z skitching $
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 


package org.apache.commons.digester;


/**
 * RuleSet that mimics the rules set used for Employee and Address creation,
 * optionally associated with a particular namespace URI.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 471661 $ $Date: 2006-11-06 03:09:25 -0500 (Mon, 06 Nov 2006) $
 */

public class TestRuleSet extends RuleSetBase {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct an instance of this RuleSet with default values.
     */
    public TestRuleSet() {

        this(null, null);

    }


    /**
     * Construct an instance of this RuleSet associated with the specified
     * prefix, associated with no namespace URI.
     *
     * @param prefix Matching pattern prefix (must end with '/') or null.
     */
    public TestRuleSet(String prefix) {

        this(prefix, null);

    }


    /**
     * Construct an instance of this RuleSet associated with the specified
     * prefix and namespace URI.
     *
     * @param prefix Matching pattern prefix (must end with '/') or null.
     * @param namespaceURI The namespace URI these rules belong to
     */
    public TestRuleSet(String prefix, String namespaceURI) {

        super();
        if (prefix == null)
            this.prefix = "";
        else
            this.prefix = prefix;
        this.namespaceURI = namespaceURI;

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The prefix for each matching pattern added to the Digester instance,
     * or an empty String for no prefix.
     */
    protected String prefix = null;


    // --------------------------------------------------------- Public Methods


    /**
     * Add the set of Rule instances defined in this RuleSet to the
     * specified <code>Digester</code> instance, associating them with
     * our namespace URI (if any).  This method should only be called
     * by a Digester instance.
     *
     * @param digester Digester instance to which the new Rule instances
     *  should be added.
     */
    public void addRuleInstances(Digester digester) {

        digester.addObjectCreate(prefix + "employee", Employee.class);
        digester.addSetProperties(prefix + "employee");
        digester.addObjectCreate("employee/address",
                "org.apache.commons.digester.Address");
        digester.addSetProperties(prefix + "employee/address");
        digester.addSetNext(prefix + "employee/address",
                "addAddress");

    }
    

}
