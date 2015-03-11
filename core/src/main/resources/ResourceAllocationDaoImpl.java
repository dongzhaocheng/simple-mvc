import com.autohome.pam.core.dao.ResourceAllocationDao;
import com.autohome.pam.core.domain.PamResAllocateAttr;
import com.autohome.pam.core.domain.impl.PamResAllocateAttrImpl;
import com.autohome.pam.core.utils.Constants;
import com.autohome.pam.core.utils.InfoLoader;
import com.autohome.pam.core.utils.LoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by dongzhaocheng on 2014/12/3.
 * 资源分配属性
 */
@Repository("resourceAllocationDao")
public class ResourceAllocationDaoImpl implements ResourceAllocationDao {

    private static final Logger logger = LoggerFactory.getLogger(ResourceAllocationDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT = "select";
    private static final String UPDATE = "update";
    private static final String DEL = "delete";
    private static final String INSERT = "insert";

    @Override
    public Collection<PamResAllocateAttr> getAll() {
        StringBuilder sql = createSQLHeader(SELECT);
        logger.info(LoginInfo.getLoginUser() + Constants.EXE_SQL+sql.toString());
        List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql.toString());
        List<PamResAllocateAttr> list = new ArrayList<PamResAllocateAttr>();
        for (Map<String,Object> map : rows){
            list.add(new PamResAllocateAttrImpl(map));
        }
        return list;
    }

    @Override
    public Collection<PamResAllocateAttr> get(Integer[] ids) {
        StringBuilder sql = addWhereIn(createSQLHeader(SELECT),InfoLoader.getString(Constants.RES_ALLOC_ID),ids);
        logger.info(LoginInfo.getLoginUser() + Constants.EXE_SQL+sql.toString());
        List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql.toString());
        List<PamResAllocateAttr> list = new ArrayList<PamResAllocateAttr>();
        for (Map<String,Object> map : rows){
            list.add(new PamResAllocateAttrImpl(map));
        }
        return list;
    }

    @Override
    public Collection<PamResAllocateAttr> get(Integer resId, Integer attributeId, String attributeValue) {
        StringBuilder sql = addWhere(createSQLHeader(SELECT), resId, attributeId,attributeValue);
        logger.info(LoginInfo.getLoginUser() + Constants.EXE_SQL+sql.toString());
        List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql.toString());
        List<PamResAllocateAttr> list = new ArrayList<PamResAllocateAttr>();
        for (Map<String,Object> map : rows){
            list.add(new PamResAllocateAttrImpl(map));
        }
        return list;
    }

    //append("col1='").append(domain.getCol1()).append("',")
    @Override
    public Integer update(PamResAllocateAttr domain) {
        if(domain==null) {return 0;}
        StringBuilder sql = createSQLHeader(UPDATE);
        sql.append(InfoLoader.getString(Constants.RES_ALLOC_RESID)).append("=").append(domain.getResourceId())
                .append(",").append(InfoLoader.getString(Constants.RES_ALLOC_ATTRID)).append("=")
                .append(domain.getAttributeId()).append(",").append(InfoLoader.getString(Constants.RES_ALLOC_VAL))
                .append("='").append(domain.getAttributeValue()).append("'");
        Integer[] arg = {domain.getId()};
        sql = addWhereIn(sql,InfoLoader.getString(Constants.RES_RELATION_ID),arg);
        logger.info(LoginInfo.getLoginUser() + Constants.EXE_SQL+sql.toString());
        return jdbcTemplate.update(sql.toString());
    }

    @Override
    public Integer delete(Integer[] ids) {
        if(ids==null) {return 0;}
        StringBuilder sql = addWhereIn(createSQLHeader(DEL),InfoLoader.getString(Constants.RES_ALLOC_ID),ids);
        logger.info(LoginInfo.getLoginUser() + Constants.EXE_SQL+sql.toString());
        return jdbcTemplate.update(sql.toString());
    }

    @Override
    public Integer addNew(Collection<PamResAllocateAttr> collection) {
        if(collection==null){
            return 0;
        }
        StringBuilder sql = createSQLHeader(INSERT);
        for(PamResAllocateAttr r : collection){
            sql.append(" (").append(r.getResourceId()).append(",").append(r.getAttributeId()).append(",'")
                    .append(r.getAttributeValue()).append("'),");
        }
        sql.deleteCharAt(sql.length()-1);
        logger.info(LoginInfo.getLoginUser() + Constants.EXE_SQL+sql.toString());
        return jdbcTemplate.update(sql.toString());
    }

    //.append(" col1,col2,col3 ")
    private StringBuilder createSQLHeader(String type){
        StringBuilder sql = new StringBuilder();
        if(type.equals(SELECT)){
            sql.append("select * from ").append(InfoLoader.getString(Constants.RES_ALLOC_TABLE)).append(" ");
        }
        if(type.equals(UPDATE)){
            sql.append("update ").append(InfoLoader.getString(Constants.RES_ALLOC_TABLE)).append(" set ");
        }
        if(type.equals(DEL)){
            sql.append("delete from ").append(InfoLoader.getString(Constants.RES_ALLOC_TABLE)).append(" ");
        }
        if(type.equals(INSERT)){
            sql.append("insert into ").append(InfoLoader.getString(Constants.RES_ALLOC_TABLE))
                    .append(" (").append(InfoLoader.getString(Constants.RES_ALLOC_RESID)).append(",")
                    .append(InfoLoader.getString(Constants.RES_ALLOC_ATTRID)).append(",")
                    .append(InfoLoader.getString(Constants.RES_ALLOC_VAL)).append(") values ");
        }
        return sql;
    }

    private StringBuilder addWhereIn(StringBuilder sql,String col, Object[] values){
        boolean isStr = values[0] instanceof String;
        sql.append(" where ").append(col).append(" in (");
        for(Object v : values){
            if(isStr) sql.append("'");
            sql.append(v);
            if(isStr) sql.append("'");
            sql.append(",");
        }
        sql.deleteCharAt(sql.length()-1).append(")");
        return sql;
    }

    //if(!col1.equals(-1)){sql.append("colname1=").append(col1).append(HYPHEN);}
    //if(col1!=null){sql.append("colname1='").append(col1).append("'").append(HYPHEN);}
    private StringBuilder addWhere(StringBuilder sql, Integer resId, Integer attrId, String val){
        String HYPHEN = " and ";
        sql.append(" where ");
        if(!resId1.equals(-1)){
            sql.append(InfoLoader.getString(Constants.RES_RELATION_RES1)).append(" = ").append(resId1).append(HYPHEN);
        }
        if(!resId2.equals(-1)){
            sql.append(InfoLoader.getString(Constants.RES_RELATION_RES2)).append(" = ").append(resId2).append(HYPHEN);
        }
        sql.delete(sql.length() - HYPHEN.length(), sql.length());
        return sql;
    }

}
