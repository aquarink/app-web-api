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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name="cities")
public class City extends Model
{
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @ManyToOne
    public Country country;

    public static final Finder<Long, City> find = new Finder<Long, City>(Long.class, City.class);

    public static City findByName(String name)
    {
        return find.where()
                .eq("name",
                        name)
                .findUnique();
    }

    public static City findByCountryName(String name) {
        Country country = Country.findByName(name);
        return find.where()
                .eq("country",
                        country)
                .findUnique();
    }
}