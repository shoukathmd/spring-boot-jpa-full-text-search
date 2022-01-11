package com.spingboot.jpa.postgres.searchdata;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchDataRepository extends PagingAndSortingRepository<SearchData, Long> {

    @Query("SELECT p FROM SearchData p WHERE fts(:description) = true")
    List<SearchData> search(@Param("description") String description);

}
