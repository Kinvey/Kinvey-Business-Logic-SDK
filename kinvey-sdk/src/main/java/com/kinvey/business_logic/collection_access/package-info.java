/**
 <h2><a id="CollectionAccess" href="#CollectionAccess">Collection Access</a></h2>
 <h3><a id="AccessingCollections" href="#AccessingCollections">Accessing Collections</a></h3>
 <p>Collections are accessed via the <code>Collection</code> class, utilizing a <code>Query</code> to filter the results.  To access a collection, you first instantiate a Collection object with the collection name that you need to retrieve or save data to/from, and then execute any number of methods against that collection.

 </p>
 <p>For example, to retrieve all data in a collection, instantiate a <code>Collection</code> object, and then call the <code>find</code> method with an unmodified default <code>Query</code> object:

 </p>
 <pre><code class="lang-java">Collection eventCollection = new Collection(&quot;events&quot;);
 Query q = new Query();
 List&lt;Document&gt; myResults = collection.find(q, null);</code></pre>
 <p>You can save any object of type <code>Document</code>.  Document is a convenience class that extends HashMap<String, Object>.  In the below example, we take the List of myResults that was generated above, add a key and value to each record, and save that record back to Mongo.

 </p>
 <pre><code class="lang-java">for (Document d : myResults) {
 d.put(&quot;leader&quot;,&quot;true&quot;);
 eventCollection.save(d, null);
 }</code></pre>
 <p><div class='info callout'>
 For details on all of the data access commands available, see the Business Logic for Google App Engine reference guide.<br></div>

 </p>
 <h3><a id="querying" href="#querying">Querying</a></h3>
 <p>Queries to the backend collections are represented as instancestances of <code>Query</code>. These queries are used with <code>Collection</code> methods to return a filtered result set.  <code>Query</code> provides a number of methods to add filters and modifiers to the query.  Instantiating a new  <code>Query</code> object returns a new instance of a <code>Query</code> object that returns all records in a collection by default:

 </p>
 <pre><code class="lang-java">Query query = new Query();</code></pre>
 <p>Under most circumstances, you want to fetch data from your backend collections that match a specific set of conditions. The <code>Query</code> class provides a mechanism to build up a query for retrieval of a list of items from a collection.  For example, you may want to retrieve the highest rated stores from your &quot;events&quot; collection:

 </p>
 <pre><code class="lang-java">Query q = new Query();
 q.greaterThan(&quot;rating&quot;,80);
 q.addSort(&quot;name&quot;, SortOrder.Asc);

 Collection eventsCollection = new Collection(&quot;events&quot;);
 List&lt;Document&gt; docs = Collection.find(q, null);</code></pre>
 <h4><a id="operators" href="#operators">Operators</a></h4>
 <h5><a id="Comparisonoperators" href="#Comparisonoperators">Comparison operators</a></h5>
 <p>In addition to exact match and null queries, you can query based upon several types of expressions: comparison, set match, string and array operations.  Each operation can be added to a query by simply calling its method on an existing <code>Query</code> object.

 </p>
 <ul>
 <li><code>equals</code> - matches where field value = the supplied value</li>
 <li><code>lessThan</code> - matches where field value is &lt; the supplied value</li>
 <li><code>lessThanEqualTo</code> - matches where field value is &lt;= the supplied value</li>
 <li><code>greaterThan</code> - matches where field value is &gt; the supplied value</li>
 <li><code>greaterThanEqualTo</code> - matches where field value is &gt;= the supplied value</li>
 <li><code>notEqual</code> - matches where field value is != the supplied value</li>
 </ul>
 <h5><a id="ArrayOperators" href="#ArrayOperators">Array Operators</a></h5>
 <ul>
 <li><code>all</code> - matches array fields that contain all of the supplied values</li>
 <li><code>size</code> - matches array fields with the exact number of items</li>
 <li><code>in</code> - matches a field whose value is in the specified array</li>
 <li><code>notIn</code> - matches a field whose value is not in the specified array</li>
 </ul>
 <h4><a id="stringqueries" href="#stringqueries">String Queries</a></h4>
 <p>You can perform textual searches on fields using Regular Expressions. This can be done with <code>Query.regex</code>. For example, to filter a table view by event name using a search bar:

 </p>
 <pre><code class="lang-java">Query query = new Query();
 query.regEx(&quot;name&quot;,&quot;searchText&quot;);</code></pre>
 <h5><a id="BeginningandEndofStrings" href="#BeginningandEndofStrings">Beginning and End of Strings</a></h5>
 <p>You can also search the beginning or end of a String field for a specific string pattern using <code>startsWith</code> or <code>endsWith</code>.  For example, to find all names that begin with &quot;Ki&quot;:

 </p>
 <pre><code class="lang-java">Query query = new Query();
 query.startsWith(&quot;name&quot;, &quot;Ki&quot;);</code></pre>
 <h4><a id="modifiers" href="#modifiers">Modifiers</a></h4>
 <p>Query modifiers control how query results are presented.

 </p>
 <h5><a id="Sort" href="#Sort">Sort</a></h5>
 <p>You can order the results returned by the queries you submit.

 </p>
 <pre><code class="lang-java">Query q = new Query();
 q.equals(&quot;name&quot;, storeName);
 q.addSort(&quot;score&quot;, SortOrder.desc);
 q.addSort(&quot;name&quot;, SortOrder.asc);</code></pre>
 <p>The enum <code>SortOrder</code> determines if the criteria is sorted Ascending or Descending.

 </p>
 <p><div class='info callout'>
 The results are sorted in the order that sort fields are added.  In the example above, the result is first sorted by Score in descending order, then by name in ascending order.
 </div>

 </p>
 <h4><a id="CompoundQueries" href="#CompoundQueries">Compound Queries</a></h4>
 <p>You can make expressive queries by stringing together several queries. For example, you can query for dates in a range or events at a particular day and place.

 </p>
 <p>Conditional operators can be strung together to create a query that is a combination of conditionals. For example, the following code uses <code>greaterThanEqualTo</code> and <code>lessThanEqualTo</code> to query for ages in a range.

 </p>
 <pre><code class="lang-java">// Search for ages between 18 and 35
 Query ageRangeQuery = new Query().greaterThanEqualTo(&quot;age&quot;,18).lessThanEqualTo(&quot;age&quot;, 35);</code></pre>
 <p>Multiple query conditions can either be chained together or executed sequentially.  The example above is functionally equivalent to:

 </p>
 <pre><code class="lang-java">Query ageRangeQuery = new Query();
 ageRangeQuery.greaterThanEqualTo(&quot;age&quot;, 18);
 ageRangeQuery.lessThanEqualTo(&quot;age&quot;, 35);</code></pre>
 <p><div class='warning callout'>

 </p>
 <p>Certain non-logical query conditions will not be accepted by the query engine.  For example, querying the same field for both = and &gt; would result in only the second condition being executed (&gt;).

 </p>
 <p></div>

 </p>
 <h5><a id="JoiningOperators" href="#JoiningOperators">Joining Operators</a></h5>
 <p>You can also combine queries using boolean operators <code>or</code> and <code>and</code>.

 </p>
 <pre><code class="lang-java">Query query1 = new Query().equals(&quot;state&quot;,&quot;MA&quot;);
 Query query2 = new Query().equals(&quot;state&quot;,&quot;CA&quot;);
 query1.or(query2);</code></pre>
 <p>Complex combinations of queries can be created and joined using either of these operators.

 </p>
 <h5><a id="NegatingQueries" href="#NegatingQueries">Negating Queries</a></h5>
 <p>Sometimes it&#39;s easier to write a negative query by first expressing the positive query and then negating it. This can be achieved by calling <code>not</code>.


 </p>
 */
package com.kinvey.business_logic.collection_access;