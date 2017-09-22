package dev.roysez.financemanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.roysez.financemanager.model.Transaction;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final String fileDest = "C:\\Users\\roysez\\IdeaProjects\\finance-manager\\documents\\transactions.txt";

    private final ObjectMapper mapper = new ObjectMapper();


    
    @Override
    public Transaction save(Transaction entity) {
        List<Transaction> list = findAll();
        list.add(entity);

        save(list);
        return entity;
    }

    @Override
    public List<Transaction> save(List<Transaction> entities) {

        try {
            mapper.writeValue(new File(fileDest),entities);


        // Convert object to JSON string
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entities);
        System.out.println(jsonInString);

        return entities;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Transaction findOne(Integer id) {
        List<Transaction> list = findAll();
        Transaction transaction = list.stream()
                .filter(tr -> tr.getId().equals(id))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .orElseThrow(NoSuchElementException::new);

        System.out.println(transaction);
        return transaction;
    }

    @Override
    public boolean exists(Integer id) {
        List<Transaction> list = findAll();
        Optional<Transaction> transaction = list.stream()
                .filter(tr -> tr.getId() == 1)
                .collect(Collectors.reducing((a, b) -> null));

        return transaction.isPresent();
    }

    @Override
    public List<Transaction> findAll()  {
        try {
            List<Transaction> list = mapper.readValue(new File(fileDest),List.class);
            return list==null? Collections.emptyList():list;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void delete(Transaction entity) {

    }
}
