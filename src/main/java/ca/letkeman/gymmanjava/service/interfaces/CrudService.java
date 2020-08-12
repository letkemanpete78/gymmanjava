package ca.letkeman.gymmanjava.service.interfaces;

import ca.letkeman.gymmanjava.service.interfaces.subinterfaces.DeleteGetList;
import ca.letkeman.gymmanjava.service.interfaces.subinterfaces.UpdateCreate;

public interface CrudService<T> extends UpdateCreate<T>, DeleteGetList<T> {

}
