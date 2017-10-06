package com.jonak.template.service;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class LuceneQueryFactory {

    public QueryBuilder getQueryBuilder(FullTextEntityManager fullTextEntityManager, Class<?> className) {
        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(className).get();
    }

    // a very basic query by keywords exact match
    public Query createBasicQuery(QueryBuilder queryBuilder, String term) {
        return queryBuilder.keyword().onFields("name", "mobile")
                .matching(term).createQuery();
    }

    //wild card query for subsring match based on fields
    public WildcardQuery createWildCardQuery(String field, String term) {
        return new WildcardQuery(new Term(field, "*" + term + "*"));
    }

    //exact match
    public TermQuery createTermQuery(String field, String term) {
        return new TermQuery(new Term(field, term));
    }

    //and or not type query
    public BooleanQuery createBooleanQuery(List<BooleanClause> list) {
        BooleanQuery booleanQuery = new BooleanQuery();
        booleanQuery.clauses().addAll(list);
        return booleanQuery;
    }
}
