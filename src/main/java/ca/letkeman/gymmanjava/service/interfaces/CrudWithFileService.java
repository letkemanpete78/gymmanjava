package ca.letkeman.gymmanjava.service.interfaces;

import ca.letkeman.gymmanjava.service.interfaces.subInterfaces.DeleteGetList;
import ca.letkeman.gymmanjava.service.interfaces.subInterfaces.UpdateCreateFile;

public interface CrudWithFileService<T> extends DeleteGetList<T>, UpdateCreateFile<T> {

}
