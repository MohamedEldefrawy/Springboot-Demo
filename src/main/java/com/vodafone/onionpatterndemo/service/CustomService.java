package com.vodafone.onionpatterndemo.service;

import java.util.List;

public interface CustomService<T> {
  T save(T t);

  List<T> findAll();

  void deleteById(Long id);

  T update(T t);

  T findById(Long id);
}
