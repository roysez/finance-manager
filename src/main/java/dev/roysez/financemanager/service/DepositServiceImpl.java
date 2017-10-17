package dev.roysez.financemanager.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.roysez.financemanager.FinanceManagerApplication;
import dev.roysez.financemanager.model.Deposit;
import dev.roysez.financemanager.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepositServiceImpl implements DepositService {


    private final String fileDest = "C:\\Users\\roysez\\IdeaProjects\\finance-manager\\documents\\deposits.json";

    private final ObjectMapper mapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(FinanceManagerApplication.class);



    @Override
    public boolean save(Deposit entity)  {
        Set<Deposit> list = findAll();
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
        log.info("Deposit entity " + (result?"saved":"already exist") + " - {}" ,entity);

        if(result)
            save(list);

        return result;
    }

    @Override
    public boolean update(Deposit entity)  {

        Set<Deposit> list = findAll();
        if(list.isEmpty())
            list = new TreeSet<>();

        list.removeIf(deposit -> deposit.getId().equals(entity.getId()));

        boolean result = list.add(entity);

        log.info("Deposit entity " + (result?"updated":"can't be saved") + " - {}" ,entity);

        if(result)
            save(list);

        return result;
    }

    @Override
    public Set<Deposit> save(Set<Deposit> entities) {
        try {
            mapper.writeValue(new File(fileDest),entities);
            return entities;
        } catch (Throwable e) {
            log.warn(e.getMessage());
            return Collections.emptySet();
        }
    }

    @Override
    public Deposit findOne(Integer id)  {

        Set<Deposit> list = findAll();
        Deposit deposit = list.stream()
                .filter(tr -> tr.getId().equals(id))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .orElseThrow(NoSuchElementException::new);

        System.out.println(deposit);
        return deposit;
    }

    @Override
    public boolean exists(Integer id)  {


        Set<Deposit> list = findAll();
        Optional<Deposit> deposit = list.stream()
                .filter(tr -> tr.getId().equals(id))
                .collect(Collectors.reducing((a, b) -> null));

        return deposit.isPresent();
    }

    @Override
    public Set<Deposit> findAll() {
        try {
            Set<Deposit> list = mapper.readValue(new File(fileDest),
                    new com.fasterxml.jackson.core.type.TypeReference<TreeSet<Deposit>>(){});

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
        Set<Deposit> list = findAll();
        Optional<Deposit> deposit = list.stream()
                .filter(tr -> tr.getId().equals(id))
                .collect(Collectors.reducing((a, b) -> null));

        if(deposit.isPresent())
            list.remove(deposit.get());

        save(list);
    }

    @Override
    public void delete(Deposit entity) {

    }
}
