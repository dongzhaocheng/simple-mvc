package org.vice.core;

/**
 * Created by dongzhaocheng on 2014/12/17.
 * 常量
 */
public interface Constants {

    public static final String CFILE = "viceConfig.xml";
    public static final String CPATH = "org.vice.core.conf.ViceConfig";

    public static final String DB_L = "database";
    public static final String DB_TAB_PREFIX = "tablePrefix";
    public static final String DB_TAB_PK = "pk";
    public static final String DB_TAB_TYPE = "type";


    public static final String NODE_BASE = "base";
    /*base*/
    public static final String KEY_PACKAGE_DEFAULT = "package.defaultStructure";
    public static final String KEY_PACKAGE_VAL = "package.value";
    public static final String KEY_LOGGER_TYPE = "logger.type";
    public static final String KEY_LOGGER_SQL_VAL = "logger.printer.sql.value";
    public static final String KEY_LOGGER_SQL_LVL = "logger.printer.sql.level";
    public static final String KEY_LOGGER_USER_VAL = "logger.printer.userName.value";
    public static final String KEY_LOGGER_USER_LVL = "logger.printer.userName.level";
    public static final String KEY_LOGGER_ROLE_VAL = "logger.printer.role.value";
    public static final String KEY_LOGGER_ROLE_LVL = "logger.printer.role.level";
    public static final String KEY_SPRING_AUTOWIRED = "spring.autowired.value";
    public static final String KEY_GARB_ID_TYPE = "garbage.tableId.type";
    public static final String KEY_GARB_ID_AI = "garbage.tableId.autoInc";
    public static final String KEY_GARB_TIME_VAL = "garbage.recordCreateTime";
    /*customer*/
    public static final String KEY_DB_PREFIX = "database.tablePrefix";
    public static final String KEY_TABLE = "tables";

    /*dao keys*/
    public static final String DAO_KEY_PACK = "#package.value#";
    public static final String DAO_KEY_IMPORT = "#import#";
    public static final String DAO_KEY_INTERFACE = "#daoInterface#";
    public static final String DAO_KEY_DOMAIN_CLASS = "#domainClass#";
    public static final String DAO_KEY_CLASS = "#daoClass#";
    public static final String DAO_KEY_AUTO = "#autowired#";
    public static final String DAO_KEY_LOGGER = "#logger.code#";
    public static final String DAO_KEY_ID_TYPE = "#garbage.tableId.type#";
    public static final String DAO_KEY_ID_NAME = "#id#";
    public static final String DAO_KEY_PARA_COLS = "#para.columns#";
    public static final String DAO_KEY_APP_INSERT = "#append.insert#";
    public static final String DAO_KEY_APP_UPDATE = "#append.update#";
    public static final String DAO_KEY_TABLE_NAME = "#table.name#";
    public static final String DAO_KEY_ADD_WHERE = "#addWhere#";
    public static final String DAO_KEY_COLS = "#append.columns#";
    public static final String DAO_KEY_ID_MET = "#id.method#";
    public static final String DAO_KEY_COLS_USED = "#para.columns.used#";

    /*model KEYS*/
    public static final String MOD_KEY_INTF = "#modelInterface#";
    public static final String MOD_KEY_FIELDS = "#fields#";
    public static final String MOD_KEY_PACKAGE = "#package.value#";
    public static final String MOD_KEY_PARA = "#paraFeilds#";
    public static final String MOD_KEY_FIELD_INIT = "#feildsInitValue#";
    public static final String MOD_KEY_SETTER = "#setter#";
    public static final String MOD_KEY_GETTER = "#getter#";
    public static final String MOD_KEY_MAP_INIT = "#mapInitValue#";


}
