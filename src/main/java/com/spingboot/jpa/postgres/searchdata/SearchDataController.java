package com.spingboot.jpa.postgres.searchdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class SearchDataController {

    @Autowired
    private SearchDataRepository repository;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/{id}")
    public SearchData findById(@PathVariable("id") Long id) {
        return repository.findById(id).orElse(null);
    }

    @CrossOrigin
    @GetMapping(value = "/search")
    public List<SearchData> findByDescription(@RequestParam String search) {
        search = search + ":*";
        return repository.search(search);
    }

    @CrossOrigin
    @GetMapping(value = "/all")
    public List<SearchData> findAll(@PageableDefault() Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @RequestMapping("/migrate")
    String migrate() {
        if (repository.count() > 0) {
            return "already migrated";
        }

        try {
            InputStream inputStream = new ClassPathResource("data.csv").getInputStream();

            List<SearchData> searchData = SearchDataReader.readFile(inputStream);
            for (SearchData sData : searchData) {
                repository.save(sData);
            }
            return "success";
        } catch (Exception e){
            System.out.println("Unable to save pokemon: " + e.getMessage());
            return "error";
        }
    }
}
