package org.malignor.selpoc;
import com.opencsv.CSVReader;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseCaseCSVManager implements UseCaseDataManager{
    ch.qos.logback.classic.Logger parentLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("org.malignor.selpoc");
    List<ElementItem> elements;
    String dataSet;

    public UseCaseCSVManager() {
        this.elements = new ArrayList<ElementItem>();
        this.dataSet = "";
    }

    @Override
    public void loadData() {
        if (dataSet==null){
            String issue = "Can't run (loadData) with a null dataSet!!!";
            System.err.println(issue);
        } else if (dataSet.isEmpty() || dataSet.isBlank()){
            String issue = "Can't run (loadData) with a blank dataSet!!!";
            System.err.println(issue);
        }
        try {
            String dataAsString = new String(this.getClass().getResourceAsStream(dataSet).readAllBytes());
            System.out.println("File contents = "+dataAsString);
            CSVReader csvReader = new CSVReader(new StringReader(dataAsString));
            String[] nextRow;
            while ((nextRow = csvReader.readNext()) != null) {
                ElementItem newElement = new ElementItem();
                int firstVal = 0;
                newElement.page = nextRow[firstVal];
                newElement.humanName = nextRow[firstVal+1];
                newElement.byXml = nextRow[firstVal+2];
                newElement.byCss = nextRow[firstVal+3];
                newElement.byId = nextRow[firstVal+4];
                newElement.byTagName = nextRow[firstVal+5];
                int attrStart = firstVal+6;
                int attrValStart = firstVal+7;
                Map<String,String> attrSet = new HashMap<>();
                for (int attr=0; attrValStart+(attr*2)<nextRow.length; attr++){
                    int attrInd = attrStart+(attr*2);
                    int attrValInd = attrValStart+(attr*2);
                    if (nextRow[attrInd]==null||nextRow[attrValInd]==null) {break;}
                    if (nextRow[attrInd].isEmpty()||nextRow[attrInd].isBlank()) {break;}
                    attrSet.put(nextRow[attrInd],nextRow[attrValInd]);
                }
                newElement.attributes = attrSet;
                elements.add(newElement);
                System.out.println("element added successfully: "+newElement.humanName);
            }

            // Now to load the entries
            // @TODO if the CSV Data spec changes, change the indexes below as well!
        } catch (IOException eIO) {
            throw new RuntimeException(eIO);
        } catch (Exception e) {
            String errorOut = "FAILURE in UseCaseCSVManager - unable to open file ["+dataSet+"]";
            System.out.println(errorOut);
            System.err.println(errorOut);
            e.printStackTrace();
        }
    }

    @Override
    public void setSource(String sourcePath) {
        if (sourcePath==null){
            String issue = "Can't set a null source!!!";
            System.err.println(issue);
        } else if (sourcePath.isEmpty() || sourcePath.isBlank()){
            String issue = "Can't set a blank source!!!";
            System.err.println(issue);
        } else {
            this.dataSet = sourcePath;
        }
    }
}
