package de.kp.spark.core.elastic
/* Copyright (c) 2014 Dr. Krusche & Partner PartG
* 
* This file is part of the Spark-Core project
* (https://github.com/skrusche63/spark-core).
* 
* Spark-Core is free software: you can redistribute it and/or modify it under the
* terms of the GNU General Public License as published by the Free Software
* Foundation, either version 3 of the License, or (at your option) any later
* version.
* 
* Spark-Core is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
* A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with
* Spark-Core. 
* 
* If not, see <http://www.gnu.org/licenses/>.
*/

import org.elasticsearch.common.xcontent.{XContentBuilder,XContentFactory}
import de.kp.spark.core.Names

class ElasticAmountBuilder {

  import de.kp.spark.core.Names._
  
  def createBuilder(mapping:String):XContentBuilder = {
    /*
     * Define mapping schema for index 'index' and 'type'
     */
    val builder = XContentFactory.jsonBuilder()
          .startObject()
            .startObject(mapping)
               .startObject("properties")

               /* timestamp */
               .startObject(TIMESTAMP_FIELD)
                 .field("type", "long")
               .endObject()
                    
               /* site */
               .startObject(SITE_FIELD)
                  .field("type", "string")
                  .field("index", "not_analyzed")
               .endObject()

               /* user */
               .startObject(USER_FIELD)
                  .field("type", "string")
                  .field("index", "not_analyzed")
               .endObject()//

               /* amount */
               .startObject(AMOUNT_FIELD)
                  .field("type", "float")
               .endObject()//

               .endObject() // properties
            .endObject()   // mapping
          .endObject()
                    
    builder

  }
  
  def createSourceJSON(params:Map[String,String]):XContentBuilder = {
    
    val site = params(Names.SITE_FIELD)
    val user = params(Names.USER_FIELD)
    
    val amount = params(Names.AMOUNT_FIELD).toFloat
    val timestamp = params(Names.TIMESTAMP_FIELD).toLong
    

    val builder = XContentFactory.jsonBuilder()
	builder.startObject()
    
	/* timestamp */
    builder.field(Names.TIMESTAMP_FIELD, timestamp)

    /* site */
	builder.field(Names.SITE_FIELD, site)
	
	/* user */
    builder.field(Names.USER_FIELD, user)
	
    /* amount */
    builder.field(Names.AMOUNT_FIELD, amount)
	  
    builder.endObject()
    builder
    
  }
 
}