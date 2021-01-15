package thanhtuu.springmvc.Service;

import thanhtuu.springmvc.Domain.Target;

import java.util.List;

/**
 * Created by Administrator on 9/3/2016.
 */
public interface TargetServiceLocal {

    void insertTarget(Target target);

    Target getIDByName(String name, Long subjectId);

    List<Target> getAllTarget(Long subjectId);

}
