package dev.roysez.financemanager.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.roysez.financemanager.FinanceManagerApplication;
import dev.roysez.financemanager.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(FinanceManagerApplication.class);
    private final String fileDest = "C:\\Users\\roysez\\IdeaProjects\\finance-manager\\documents\\categories.json";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean save(Category entity) throws IOException {
        Set<Category> set = findAll();
        if (set.isEmpty())
            set = new TreeSet<>();

        boolean result = set.add(entity);

        log.info("Category" + (result ? "added" : "already exist") + " - {}", entity);

        if (result)
            save(set);

        return result;
    }

    @Override
    public Set<Category> save(Set<Category> entities) throws IOException {
        try {
            mapper.writeValue(new File(fileDest), entities);
            return entities;
        } catch (JsonMappingException e) {
            //  e.printStackTrace();
            return Collections.emptySet();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    @Override
    public Category findOne(Integer id) throws IOException {
        return findAll().stream()
                .filter(category -> category.getId().equals(id))
                .reduce((category, category2) -> {
                    throw new IllegalStateException();
                })
                .orElseThrow(NoSuchElementException::new);

    }

    @Override
    public Category findOneByName(String sC) throws IOException {
        return findAll()
                .stream()
                .filter(element -> element.getCategoryName()
                        .equals(sC))
                .reduce((category1, category2) -> {
                    throw new IllegalStateException();
                })
                .orElseThrow(NoSuchElementException::new);

    }

    @Override
    public boolean exists(Integer id) throws IOException {
        return findAll().stream()
                .filter(category -> category.getId().equals(id))
                .count() == 1;
    }

    @Override
    public Set<Category> findAll() throws IOException {
        try {
            Set<Category> set = mapper.readValue(new File(fileDest),
                    new com.fasterxml.jackson.core.type.TypeReference<TreeSet<Category>>() {
                    });
            return set == null ? Collections.emptySet() : set;
        } catch (JsonMappingException e) {
            //  e.printStackTrace();
            return Collections.emptySet();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }
}
