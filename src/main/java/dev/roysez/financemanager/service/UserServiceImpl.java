package dev.roysez.financemanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.roysez.financemanager.FinanceManagerApplication;
import dev.roysez.financemanager.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(FinanceManagerApplication.class);
    private final String fileDest = "C:\\Users\\roysez\\IdeaProjects\\finance-manager\\documents\\user_info.json";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public User getUser() throws IOException {
        User user = mapper.readValue(new File(fileDest), User.class);

        log.info("User was read - {}", user);

        return user;
    }

    @Override
    public User saveUser(User user) throws IOException {
        mapper.writeValue(new File(fileDest), user);

        log.info("User info was saved - {}", user);
        return user;

    }
}
