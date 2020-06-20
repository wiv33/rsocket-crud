package org.psawesome.rsocketcrud.dto;

/**
 * author: ps [https://github.com/wiv33/rsocket-crud]
 * DATE: 20. 6. 21. Sunday
 */
@FunctionalInterface
public interface MyTransferData<T, R> {
  R transfer(T entity);
}
