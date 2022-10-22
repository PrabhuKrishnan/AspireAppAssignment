package com.app.testdata;

import com.creditdatamw.zerocell.annotation.Column;

public class TestData {

    @Column(name = "TestCaseName", index = 0)
    public String testCaseName;

    @Column(name = "SearchCategory", index = 1)
    public String searchCategory;

    @Column(name = "SearchKeyword", index = 2)
    public String searchKeyword;

    @Column(name = "FeaturedBrands", index = 3)
    public String featureBrands;

    @Column(name = "MinimumPrice", index = 4)
    public String minimumPrice;

    @Column(name = "MaximumPrice", index = 5)
    public String maximumPrice;

    @Column(name = "SignInPageTitle", index = 6)
    public String signInPageTitle;

    @Column(name = "SortBy", index = 7)
    public String sortByFilter;











}
