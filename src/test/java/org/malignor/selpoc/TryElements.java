package org.malignor.selpoc;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.URL;

public class TryElements {
    UseCaseDataManager qademoData = new UseCaseCSVManager();
    String resourceFile = "TestData-001.csv";

    @BeforeSuite(alwaysRun = true)
    void loadTestData(){
        String fullpath = "/"+resourceFile;
        System.out.println("Data file path = "+fullpath);
        qademoData.setSource(fullpath);
        qademoData.loadData();
    }

    @Test
    void naveToElements(){
        assert (true);
    }
}
