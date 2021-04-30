package ee.taltech.iti0202.personstatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvPersonMapper {

    public static final int AGE_INDEX = 2;
    public static final int GENDER_INDEX = 3;
    public static final int HEIGHT_INDEX = 4;
    public static final int OCCUPATION_INDEX = 5;
    public static final int NATIONALITY_INDEX = 6;
    public static final int LAST_NAME_INDEX = 1;
    public static final int FIRST_NAME_INDEX = 0;

    /**
     * meetod, mis saab sisendiks csv faili asukoha ning
     * loeb failist inimeste infot stream'i abil (vihje - Files.lines),
     * tekitades jooksvalt builderit kasutades
     * Person objektid, millest moodustatakse list.
     * Person info tuleb salvestada listi,
     * seda läheb statistika arvutamisel vaja.
     * Vea korral tuleb visata CsvToPersonMappingException.
     *
     * @param path
     * @return
     */
    public List<Person> mapToPersons(String path) throws CsvToPersonMappingException {
        List<Person> persons = new ArrayList<>();
        Path tra = Path.of(path);
        String data = null;
        try {
            Stream<String> lines = Files.lines(tra);
            data = lines.collect(Collectors.joining("\n"));
            lines.close();
        } catch (IOException e) {
            throw new CsvToPersonMappingException();
        }
        List<String> linesList = Arrays.asList(data.split("\n").clone());
        for (String line : linesList
        ) {
            List<String> list = Arrays.asList(line.split(","));

            Person person = new PersonBuilder()
                    .setFirstName(list.get(FIRST_NAME_INDEX))
                    .setLastName(list.get(LAST_NAME_INDEX))
                    .setAge(Integer.parseInt(list.get(AGE_INDEX)))
                    .setGender(Gender.valueOf(list.get(GENDER_INDEX)))
                    .setHeightInMeters(Double.parseDouble(list.get(HEIGHT_INDEX)))
                    .setOccupation(list.get(OCCUPATION_INDEX))
                    .setNationality(list.get(NATIONALITY_INDEX))
                    .createPerson();
            persons.add(person);
        }
        return persons;
    }
}
