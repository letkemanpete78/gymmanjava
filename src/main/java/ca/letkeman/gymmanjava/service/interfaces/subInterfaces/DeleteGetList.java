package ca.letkeman.gymmanjava.service.interfaces.subInterfaces;

import java.util.List;

public interface DeleteGetList<T>  {

  boolean delete(String payload);

  T get(String id);

  List<T> list();
}
