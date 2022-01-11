package com.spingboot.jpa.postgres.searchdata;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import java.io.InputStream;
import java.util.List;

public class SearchDataReader {

    public static List<SearchData> readFile(InputStream inputStream) throws Exception {
        MappingIterator<SearchData> pokemonIter = new CsvMapper()
                .readerWithTypedSchemaFor(SearchData.class)
                .readValues(inputStream);

        return pokemonIter.readAll();
    }
}
