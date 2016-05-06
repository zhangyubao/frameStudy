package com.zbao.news;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * 实体类生成器、同时也创建数据库DAO文件
 * <p/>
 * Note:这个依赖是一个java工程
 *
 *  GreenDao使用步骤：
 *  1、创建generato 类用来设置entity、dao文件的存放位置以及存放的项目(该类即是范例)（NewGenerator）
 *  2、封装service类用来实现数据库的CRUD（BaseService）
 *  3、封装一个核心类用来获取数据库管理器及会话对象(DBCore)
 *  4、封装工具类用来获取Service服务对象（DBUtils）
 *
 *
 */
public class NewGenerator {
    public static void main(String[] args) throws Exception {
        //我们创建了一个Schema,第一个参数是数据库的版本号，第二个参数是我们要生成的数据模型所在的包名。
        //生成的实体类所存放的位置
        Schema schema = new Schema(1, "com.zbao.news.greendao.entity");
        //生成的dao类所存放的位置
        schema.setDefaultJavaPackageDao("com.zbao.news.greendao.dao");
        schema.enableKeepSectionsByDefault();
        addEntity(schema);
        //第一个参数是“Schema”，第二个参数为将生成的数据模型输出到指定的项目下的src文件夹下。
        //设置生成的文件输出到那个项目的java目录下
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

    private static void addEntity(Schema schema) {

        Entity patient = schema.addEntity("Patient");
        patient.addIdProperty().primaryKey();
        patient.addStringProperty("PATIENT_ID");
        patient.addStringProperty("PARENT_DOCTOR_ID");
        patient.addStringProperty("SUPER_DOCTOR_ID");
        // 指定自增长主键
        Property patientPK = patient.addLongProperty("primary_id").getProperty();
        patient.addToOne(patient, patientPK);
    }

}
