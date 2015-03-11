import com.autohome.pam.core.domain.PamResAllocateAttr;

import java.util.Collection;

/**
 * Created by dongzhaocheng on 2014/12/3.
 * 资源分配属性
 *
 */
public interface ResourceAllocationDao {

    Collection<PamResAllocateAttr> getAll();

    Collection<PamResAllocateAttr> get(Integer[] ids);

    Collection<PamResAllocateAttr> get(Integer resId, Integer attributeId, String attributeValue);

    Integer update(PamResAllocateAttr resAllocateAttr);

    Integer delete(Integer[] ids);

    Integer addNew(Collection<PamResAllocateAttr> collection);

}
