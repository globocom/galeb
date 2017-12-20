package io.galeb.api.repository.custom;

import io.galeb.api.services.StatusService;
import io.galeb.core.entity.AbstractEntity;
import io.galeb.core.entity.WithStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@NoRepositoryBean
public class AbstractRepositoryImplementation<T extends AbstractEntity> {

    private SimpleJpaRepository<T, Long> simpleJpaRepository;
    private StatusService statusService;

    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }

    public void setSimpleJpaRepository(Class<T> klazz, EntityManager entityManager) {
        if (this.simpleJpaRepository != null) return;
        this.simpleJpaRepository = new SimpleJpaRepository<>(klazz, entityManager);
    }

    public T findOne(Long id) {
        T entity = simpleJpaRepository.findOne(id);
        if (entity instanceof WithStatus)
            ((WithStatus)entity).setStatus(statusService.status(entity));
        return entity;
    }

    public Iterable<T> findAll(Sort sort) {
        Iterable<T> iterable = simpleJpaRepository.findAll(sort);
        for (T entity: iterable) {
            if (entity instanceof WithStatus)
                ((WithStatus)entity).setStatus(statusService.status(entity));
        }
        return iterable;
    }

    public Page<T> findAll(Pageable pageable) {
        Page<T> page = simpleJpaRepository.findAll(pageable);
        for (T entity: page) {
            if (entity instanceof WithStatus)
                ((WithStatus)entity).setStatus(statusService.status(entity));
        }
        return page;
    }

    @Transactional
    public void delete(Long id) {
        T entity = simpleJpaRepository.findOne(id);
        entity.quarantine(true);
        simpleJpaRepository.saveAndFlush(entity);
    }
}