package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.LinkedHashMap;
import java.util.List;
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

    public static final Finder<Long, City> find = new Finder<Long, City>(Long.class,
            City.class);


    public static City findByName(String name, String country)
    {
        return find.where()
                .eq("name",
                        name)
                .eq("country.name",
                        country)
                .findUnique();
    }

    public static List<City> findByCountryName(String name) {
        Country country = Country.findByName(name);
        return find.where()
                .eq("country",country)
                .findList();
    }

    public static Map<String,String> optionsAll() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(City c: City.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }

    public static Map<String, String> options(String country){
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(City c: City.findByCountryName(country) ) {
            options.put(c.id.toString(), c.name);
        }
        return options;
   }

}