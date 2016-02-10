package com.diplomski.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diplomski.blog.model.Authorities;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Integer>{

}
