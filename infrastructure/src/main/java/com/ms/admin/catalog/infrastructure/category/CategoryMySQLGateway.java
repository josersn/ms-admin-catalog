package com.ms.admin.catalog.infrastructure.category;

import com.ms.admin.catalog.infrastructure.category.persistence.Category.CategoryJpaEntity;
import com.ms.admin.catalog.infrastructure.category.persistence.Category.CategoryRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CategoryMySQLGateway implements CategoryRepository{

    private final CategoryRepository repository;

    public CategoryMySQLGateway(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryJpaEntity> findAll() {
        return null;
    }

    @Override
    public List<CategoryJpaEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CategoryJpaEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CategoryJpaEntity> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(CategoryJpaEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends CategoryJpaEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CategoryJpaEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends CategoryJpaEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CategoryJpaEntity> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends CategoryJpaEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CategoryJpaEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CategoryJpaEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CategoryJpaEntity getOne(String s) {
        return null;
    }

    @Override
    public CategoryJpaEntity getById(String s) {
        return null;
    }

    @Override
    public <S extends CategoryJpaEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CategoryJpaEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CategoryJpaEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CategoryJpaEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CategoryJpaEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CategoryJpaEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CategoryJpaEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
