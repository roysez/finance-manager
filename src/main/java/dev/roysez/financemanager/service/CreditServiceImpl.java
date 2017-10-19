package dev.roysez.financemanager.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.roysez.financemanager.FinanceManagerApplication;
import dev.roysez.financemanager.model.Credit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CreditServiceImpl implements CreditService {


    private final String fileDest = "C:\\Users\\roysez\\IdeaProjects\\finance-manager\\documents\\credits.json";

    private final ObjectMapper mapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(FinanceManagerApplication.class);



    @Override
    public boolean save(Credit entity)  {
        Set<Credit> list = findAll();
        if(list.isEmpty())
            list = new TreeSet<>();

        try {
            entity.setId(list.stream()
                    .reduce((first, second) -> second)
                    .orElseThrow(ArrayIndexOutOfBoundsException::new)
                    .getId() + 1);

        } catch (IndexOutOfBoundsException e) {
            entity.setId(0);
        }

        boolean result = list.add(entity);
        log.info("Credit entity " + (result?"saved":"already exist") + " - {}" ,entity);

        if(result)
            save(list);

        return result;
    }

    @Override
    public boolean update(Credit entity)  {

        Set<Credit> list = findAll();
        if(list.isEmpty())
            list = new TreeSet<>();

        list.removeIf(credit -> credit.getId().equals(entity.getId()));

        boolean result = list.add(entity);

        log.info("Credit entity " + (result?"updated":"can't be saved") + " - {}" ,entity);

        if(result)
            save(list);

        return result;
    }

    @Override
    public Set<Credit> save(Set<Credit> entities) {
        try {

            mapper.writeValue(new File(fileDest),entities);
            return entities;
        } catch (Throwable e) {

            return Collections.emptySet();
        }
    }

    @Override
    public Credit findOne(Integer id)  {

        Set<Credit> list = findAll();
        Credit credit = list.stream()
                .filter(tr -> tr.getId().equals(id))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .orElseThrow(NoSuchElementException::new);

        System.out.println(credit);
        return credit;
    }

    @Override
    public boolean exists(Integer id)  {


        Set<Credit> list = findAll();
        Optional<Credit> credit = list.stream()
                .filter(tr -> tr.getId().equals(id))
                .collect(Collectors.reducing((a, b) -> null));

        return credit.isPresent();
    }

    @Override
    public Set<Credit> findAll() {
        try {
            Set<Credit> list = mapper.readValue(new File(fileDest),
                    new com.fasterxml.jackson.core.type.TypeReference<TreeSet<Credit>>(){});

            return list==null? Collections.emptySet():list;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return Collections.emptySet();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }

    @Override
    public void delete(Integer id) {
        Set<Credit> list = findAll();
        Optional<Credit> credit = list.stream()
                .filter(tr -> tr.getId().equals(id))
                .collect(Collectors.reducing((a, b) -> null));

        if(credit.isPresent())
            list.remove(credit.get());

        save(list);
    }

    @Override
    public void delete(Credit entity) {

    }
}
