package com.spingboot.jpa.postgres.fts;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.BooleanType;

public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {
    @Override
     public void contribute(MetadataBuilder metadataBuilder) {
        String sql = "";
        metadataBuilder.applySqlFunction("fts",
                new SQLFunctionTemplate(BooleanType.INSTANCE,
                        " to_tsvector(name || ' ' || type1 ||  ' ' || type2 ||  ' ' || description ) @@ to_tsquery(?1)"));
    }
}
