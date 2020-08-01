package ca.letkeman.gymmanjava.service.interfaces;

import ca.letkeman.gymmanjava.service.interfaces.subInterfaces.DeleteGetList;
import ca.letkeman.gymmanjava.service.interfaces.subInterfaces.UpdateCreate;

public interface CrudService<T> extends UpdateCreate<T>, DeleteGetList<T> {

}
