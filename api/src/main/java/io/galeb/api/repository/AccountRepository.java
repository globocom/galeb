package io.galeb.api.repository;

import io.galeb.api.repository.custom.AccountRepositoryCustom;
import io.galeb.core.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(path = "account", collectionResourceRel = "account", itemResourceRel = "account")
public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {

    @Override
    @PreAuthorize("@authz.checkSave(principal, #account, #this)")
    Account save(@Param("account") Account account);

    @Override
    @PreAuthorize("@authz.checkDelete(principal, #id, #this)")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("@authz.isMySelf(principal, #id) or @authz.isLocalAdmin(principal)")
    Account findOne(@Param("id") Long id);

    @Override
    @Query("SELECT a FROM Account a WHERE a.username LIKE ?#{principal.username == @localAdmin.username ? '%' : principal.username}")
    Page<Account> findAll(Pageable pageable);
}
