package com.app.testdata;

import com.creditdatamw.zerocell.annotation.Column;

public class TestData {

    @Column(name = "TestCaseName", index = 0)
    public String testCaseName;

    @Column(name = "SearchKeyword", index = 1)
    public String searchKeyword;

    @Column(name = "DisplayType", index = 2)
    public String displayType;

    @Column(name = "BrandsMaterial", index = 3)
    public String brandsMaterial;

    @Column(name = "BrandName", index = 4)
    public String brandName;

    @Column(name="Discount", index=5)
    public String discount;


}
