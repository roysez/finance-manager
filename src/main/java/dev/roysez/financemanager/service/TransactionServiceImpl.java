package dev.roysez.financemanager.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.roysez.financemanager.FinanceManagerApplication;
import dev.roysez.financemanager.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.Set;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final String fileDest = "C:\\Users\\roysez\\IdeaProjects\\finance-manager\\documents\\transactions.txt";

    private final ObjectMapper mapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(FinanceManagerApplication.class);


    @Override
    public Transaction save(Transaction entity) {
        Set<Transaction> list = findAll();
        list.add(entity);

        save(list);
        return entity;
    }

    @Override
    public Set<Transaction> save(Set<Transaction> entities) {

        try {
            File temp = new File(fileDest);
            if (temp.exists()) {
                RandomAccessFile raf = new RandomAccessFile(temp, "rw");
                raf.setLength(0);
            }
            mapper.writeValue(new File(fileDest),entities);


        // Convert object to JSON string
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entities);
        System.out.println(jsonInString);

        return entities;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    @Override
    public Transaction findOne(Integer id) {
        Set<Transaction> list = findAll();
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
        Set<Transaction> list = findAll();
        Optional<Transaction> transaction = list.stream()
                .filter(tr -> tr.getId() == 1)
                .collect(Collectors.reducing((a, b) -> null));

        return transaction.isPresent();
    }

    @Override
    public Set<Transaction> findAll()  {
        try {
            Set<Transaction> list = mapper.readValue(new File(fileDest),Set.class);
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

    }

    @Override
    public void delete(Transaction entity) {

    }
}
