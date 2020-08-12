package ca.letkeman.gymmanjava.service.interfaces.subinterfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UpdateCreate<T> {

  T update(String payload) throws JsonProcessingException;

  T create(String payload) throws JsonProcessingException;

}
