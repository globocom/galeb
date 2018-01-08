package io.galeb.api.repository.custom;

import io.galeb.api.services.StatusService;
import io.galeb.core.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Set;

public class AccountRepositoryImpl extends AbstractRepositoryImplementation<Account> implements AccountRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private StatusService statusService;

    @PostConstruct
    private void init() {
        setSimpleJpaRepository(Account.class, em);
        setStatusService(statusService);
    }

    @Override
    public Set<String> roles(Object principal, Object criteria) {
        return Collections.emptySet();
    }

}
