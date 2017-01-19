/*
 * Copyright 2012 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;
import com.avaje.ebean.Expr;

@Entity
@Table(name="countries")
public class Country extends Model
{
	private static final long serialVersionUID = 1L;

	@Id
    @Column(length=3)
    public String code;

    @Constraints.Required
    public String name;

    @Constraints.Required
    @ManyToOne(optional=false)
    @JsonIgnore
    public Currency currency;

    public static final Finder<String, Country> find = new Finder<String, Country>(String.class,
            Country.class);


    public static Country findByName(String name)
    {
        return find.where()
                .eq("name",
                        name)
                .findUnique();
    }
    
    public static Country findByCode(String code) {
    	return find.where().add(Expr.eq("code", code)).findUnique();
    }
    
    
}
