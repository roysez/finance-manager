package dev.roysez.financemanager.service;

import dev.roysez.financemanager.model.Transaction;

import java.io.IOException;
import java.util.Set;

public interface TransactionService {

    /**
     * Зберегти транзакцію
     * @param entity - транзакція для збереження
     * @return result - true, якщо транзакція була записана у файл
     */
    boolean save(Transaction entity);

    /**
     * Збереження колекцій транзакцій
     * @param entities - колекція для збереження
     * @return entities - emptySet, якщо виникла помилка
     */
    Set<Transaction> save(Set<Transaction> entities) throws IOException;

    /**
     * Обрати одну транзакцію
     * @param id - унікальне значення
     * @return {@link Transaction}
     */
    Transaction findOne(Integer id);

    /**
     * Перевірити чи існує у файлі транзакція
     * @param id - унікальне значення
     * @return true, якщо існує ; false, якщо ні
     */
    boolean exists(Integer id);

    /**
     * Обрати всі транзакцію з файлу
     * @return {@link Set} - сет транзакцій
     * @throws IOException - Помилка читання
     */
    Set<Transaction> findAll() throws IOException;

    /**
     * Видалити транзакцію
     * @param id - унікальне значення
     */
    void delete(Integer id);


}
