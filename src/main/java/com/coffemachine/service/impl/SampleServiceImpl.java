package com.coffemachine.service.impl;

import com.coffemachine.model.Item;
import com.coffemachine.model.ItemFeedback;
import com.coffemachine.model.Station;
import com.coffemachine.services.ItemService;
import com.coffemachine.services.SampleService;
import com.coffemachine.services.StationService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class SampleServiceImpl implements SampleService {

    private static final String SAMPLE_DATASET_PATH = "././././samples/";
    private static final String SLASH = "/";
    private static final String EXTENSION_CSV = ".csv";

    @Autowired
    StationService stationService;

    @Autowired
    ItemService itemService;

    @Override
    public void addSample(ItemFeedback itemFeedback) {
        Station station = stationService.findOneByUsername(itemFeedback.getStationUsername());
        Item item = itemService.getItem(itemFeedback.getItemId());

        if (item != null && station != null) {
            Path folderPath = Paths.get(SAMPLE_DATASET_PATH + station.getId() + SLASH + item.getItemId());

            createFolderIfNotExists(folderPath);
            createCsvDatasetFile(itemFeedback, folderPath);
        }
    }

    private void createCsvDatasetFile(ItemFeedback itemFeedback, Path folderPath) {
        try {
            String fileName = String.valueOf(new Date().getTime());
            byte[] data = Base64.decodeBase64(itemFeedback.getSample());
            OutputStream stream = new FileOutputStream(folderPath + fileName + EXTENSION_CSV);
            stream.write(data);
        } catch (IOException exception) {
            System.out.println("Creating csv dataset failed, " + exception.getMessage());
        }
    }

    private void createFolderIfNotExists(Path folderPath) {
        try {
            Files.createDirectories(folderPath);
        } catch (Exception exception) {
            System.out.println("Creating directories failed, " + exception.getMessage());
        }
    }
}
