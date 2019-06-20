package com.yhb.entity;

import org.apache.ibatis.type.Alias;

/**
 * @author York.yuan
 * @version <1.0>
 * @description
 * @createdate 2019/5/15 17:41
 **/
@Alias("Dept")
public class Dept {

    private Integer deptId;

    private String deptName;

    private String deptCode;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}
