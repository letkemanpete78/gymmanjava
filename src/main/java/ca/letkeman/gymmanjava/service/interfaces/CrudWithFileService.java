package ca.letkeman.gymmanjava.service.interfaces;

import ca.letkeman.gymmanjava.service.interfaces.subinterfaces.DeleteGetList;
import ca.letkeman.gymmanjava.service.interfaces.subinterfaces.UpdateCreateFile;

public interface CrudWithFileService<T> extends DeleteGetList<T>, UpdateCreateFile<T> {

}
