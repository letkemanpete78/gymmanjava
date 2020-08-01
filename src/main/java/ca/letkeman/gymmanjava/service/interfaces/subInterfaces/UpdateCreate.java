package ca.letkeman.gymmanjava.service.interfaces.subInterfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UpdateCreate<T> {

  T update(String payload);

  T create(String payload) throws JsonProcessingException;

}
