package lego;

import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        LegoSet legoSet = new LegoSet(
                "CODE",
                "My Set",
                null,
                50,
                new LegoSet.Minifigure[1]
        );
        System.out.println(legoSet);

        // instantiate the JsonMapper (immutable, configured using a builder)
        JsonMapper mapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
                .enable(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS)
                .build();

        // read a JSON document
        LegoSet[] legoSets = mapper.readValue(
                new File("input.json"),
                LegoSet[].class
        );

        for (LegoSet set : legoSets) {
            System.out.println(set);
        }

        // write a JSON document
        mapper.writeValue(
                new File("output.json"),
                legoSets
        );
    }
}
