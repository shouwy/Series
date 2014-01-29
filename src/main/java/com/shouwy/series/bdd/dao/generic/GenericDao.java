/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shouwy.series.bdd.dao.generic;

import java.util.List;

/**
 *
 * @author Inspiron
 * @param <T>
 */
public interface GenericDao<T> {
    void save(T entity);
    T merge(T entity);
    void delete(T entity);
    T getById(Integer id);
    List<T> getAll();
    int count();
   void update(T entity);

}
